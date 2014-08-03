/**
 *
 */
package com.jf.scout.server.administration.ui.desktop.forms;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.function.Consumer;

import org.eclipse.scout.commons.Base64Utility;
import org.eclipse.scout.commons.EncryptionUtility;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.commons.exception.VetoException;
import org.eclipse.scout.commons.logger.IScoutLogger;
import org.eclipse.scout.commons.logger.ScoutLogManager;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.security.ACCESS;
import org.eclipse.scout.service.AbstractService;
import org.eclipse.scout.service.SERVICES;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.misc.TransactionManager;
import com.j256.ormlite.stmt.QueryBuilder;
import com.jf.commons.datamodels.RecordStatus;
import com.jf.commons.datamodels.Role;
import com.jf.commons.datamodels.RolePermission;
import com.jf.commons.datamodels.User;
import com.jf.commons.datamodels.UserRole;
import com.jf.scout.server.core.ServerSession;
import com.jf.scout.shared.administration.ui.desktop.forms.CreateUserPermission;
import com.jf.scout.shared.administration.ui.desktop.forms.IRoleService;
import com.jf.scout.shared.administration.ui.desktop.forms.IUserService;
import com.jf.scout.shared.administration.ui.desktop.forms.ReadUserPermission;
import com.jf.scout.shared.administration.ui.desktop.forms.UpdateUserPermission;
import com.jf.scout.shared.administration.ui.desktop.forms.UserFormData;
import com.jf.scout.shared.core.services.IDatabaseService;

/**
 * @author Hoàng
 */
public class UserService extends AbstractService implements IUserService {
  private IScoutLogger logger = ScoutLogManager.getLogger(getClass());

  @Override
  public UserFormData create(UserFormData formData) throws ProcessingException {
    if (!ACCESS.check(new CreateUserPermission())) {
      throw new VetoException(TEXTS.get("AuthorizationFailed"));
    }
        User u = new User();
    u.setNew(true);
    u.setUserName(formData.getUserName().getValue());
    try {
      u.setPassword(Base64Utility.encode(EncryptionUtility.signMD5(formData.getUserPassword().getValue().getBytes())));
    }
    catch (NoSuchAlgorithmException e1) {
      logger.info(e1.getMessage(), e1);
    }
    u.setValid(formData.getValid().getValue());
    u.setCreator(ServerSession.get().getUserId());
    try {
      SERVICES.getService(IDatabaseService.class).getDao(User.class).create(u);
    }
    catch (SQLException e) {
      throw new VetoException(e.getMessage(), e);
    }

    return formData;
  }

  @Override
  public UserFormData load(UserFormData formData) throws ProcessingException {
    if (!ACCESS.check(new ReadUserPermission())) {
      throw new VetoException(TEXTS.get("AuthorizationFailed"));
    }
        Dao<User, Long> dao = SERVICES.getService(IDatabaseService.class).getDao(User.class);
    try {
      User u = dao.queryForId(formData.getUserNr().longValue());

      formData.getUserName().setValue(u.getUserName());
      formData.getUserPassword().setValue(u.getPassword());
      formData.getValid().setValue(u.isValid());

      formData.getRoles().setValue(getRoleIdsOfUser(u.getId()));
    }
    catch (SQLException e) {
      throw new VetoException(e.getMessage(), e);
    }

    return formData;
  }

  @Override
  public UserFormData prepareCreate(UserFormData formData) throws ProcessingException {
    if (!ACCESS.check(new CreateUserPermission())) {
      throw new VetoException(TEXTS.get("AuthorizationFailed"));
    }
        return formData;
  }

  @Override
  public UserFormData store(UserFormData formData) throws ProcessingException {
    if (!ACCESS.check(new UpdateUserPermission())) {
      throw new VetoException(TEXTS.get("AuthorizationFailed"));
    }
        Dao<User, Long> dao = SERVICES.getService(IDatabaseService.class).getDao(User.class);

    try {
      User u = dao.queryForId(formData.getUserNr().longValue());

      if (!u.getPassword().equals(formData.getUserPassword().getValue())) {
        try {
          u.setPassword(Base64Utility.encode(EncryptionUtility.signMD5(formData.getUserPassword().getValue().getBytes())));
        }
        catch (NoSuchAlgorithmException e) {
          logger.info(e.getMessage(), e);
        }
      }
      u.setValid(formData.getValid().getValue());
      u.setLastModifier(ServerSession.get().getUserId());

      dao.update(u);

      // create or update role ids of user
      addRoleIdsOfUser(u.getId(), formData.getRoles().getValue());
    }
    catch (SQLException e) {
      throw new VetoException(e.getMessage(), e);
    }

    return formData;
  }

  @Override
  public Object[][] getAllUsers() throws ProcessingException {
        Dao<User, Long> dao = SERVICES.getService(IDatabaseService.class).getDao(User.class);

    try {
      List<User> us = dao.queryForAll();
      Object[][] result = new Object[us.size()][];
      int c = 0;

      for (User u : us) {
        result[c] = new Object[]{
            // TODO fill data in sequence
            u.getId(),
            u.getUserName(),
            u.isValid(),
            u.getValidChangedTime(),
            u.getRecordStatus(),
            u.getCreatedTime(),
            u.getCreator(),
            u.getLastModifiedTime(),
            u.getLastModifier()
        };
        c++;
      }

      return result;
    }
    catch (SQLException e) {
            e.printStackTrace();
    }

    return null;
  }

  @Override
  public void deleteUsers(Long[] ids) throws ProcessingException {
    Dao<User, Long> dao = SERVICES.getService(IDatabaseService.class).getDao(User.class);

    try {
      TransactionManager.callInTransaction(
          dao.getConnectionSource(),
          new Callable<Void>() {
            /* (non-Javadoc)
             * @see java.util.concurrent.Callable#call()
             */
            @Override
            public Void call() throws Exception {
              for (long id : ids) {
                User u = dao.queryForId(id);
                u.setRecordStatus(RecordStatus.DELETE);
                u.setLastModifier(ServerSession.get().getUserId());

                dao.update(u);
              }
              return null;
            }
          });
    }
    catch (SQLException e) {
      throw new VetoException(e.getMessage(), e);
    }
  }

  @Override
  public void deleteUsersPermantly(Long[] ids) throws ProcessingException {
    Dao<User, Long> dao = SERVICES.getService(IDatabaseService.class).getDao(User.class);

    try {
      TransactionManager.callInTransaction(
          dao.getConnectionSource(),
          new Callable<Void>() {
            /* (non-Javadoc)
             * @see java.util.concurrent.Callable#call()
             */
            @Override
            public Void call() throws Exception {
              for (long id : ids) {
                dao.deleteById(id);
              }
              return null;
            }
          });
    }
    catch (SQLException e) {
      throw new VetoException(e.getMessage(), e);
    }
  }

  @Override
  public void restoreUsers(Long[] ids) throws ProcessingException {
        Dao<User, Long> dao = SERVICES.getService(IDatabaseService.class).getDao(User.class);

    try {
      TransactionManager.callInTransaction(
          dao.getConnectionSource(),
          new Callable<Void>() {
            /* (non-Javadoc)
             * @see java.util.concurrent.Callable#call()
             */
            @Override
            public Void call() throws Exception {
              for (long id : ids) {
                User u = dao.queryForId(id);
                u.setRecordStatus(RecordStatus.UPDATE);
                u.setLastModifier(ServerSession.get().getUserId());

                dao.update(u);
              }
              return null;
            }
          });
    }
    catch (SQLException e) {
      throw new VetoException(e.getMessage(), e);
    }
  }

  @Override
  public Set<Long> getRoleIdsOfUser(Long uid) throws ProcessingException {
    Dao<UserRole, Long> urDao = SERVICES.getService(IDatabaseService.class).getDao(UserRole.class);
    try {
      Set<Long> result = new HashSet<Long>();

      urDao.queryBuilder().selectColumns(UserRole.FIELD_ROLE_ID)
      .where()
      .eq(UserRole.FIELD_USER_ID, uid)
      .and().ne(UserRole.FIELD_RECORD_STATUS, RecordStatus.DELETE)
      .query().forEach(new Consumer<UserRole>() {
        /* (non-Javadoc)
         * @see java.util.function.Consumer#accept(java.lang.Object)
         */
        @Override
        public void accept(UserRole t) {
          result.add(t.getRole().getId());
        }
      });

      return result;
    }
    catch (SQLException e) {
      throw new VetoException(e.getMessage(), e);
    }
  }

  @Override
  public void addRoleIdsOfUser(Long uid, Set<Long> rids) throws ProcessingException {
    Dao<UserRole, Long> urDao = SERVICES.getService(IDatabaseService.class).getDao(UserRole.class);

    try {
      TransactionManager.callInTransaction(urDao.getConnectionSource(), new Callable<Void>() {
        /* (non-Javadoc)
         * @see java.util.concurrent.Callable#call()
         */
        @Override
        public Void call() throws Exception {
          urDao.queryBuilder().where()
              .eq(UserRole.FIELD_USER_ID, uid)
              .and().ne(UserRole.FIELD_RECORD_STATUS, RecordStatus.DELETE).query().forEach(new Consumer<UserRole>() {
                /* (non-Javadoc)
                 * @see java.util.function.Consumer#accept(java.lang.Object)
                 */
                @Override
                public void accept(UserRole t) {
                  if (!rids.contains(t.getRole().getId())) {
                    try {
                      urDao.delete(t);
                    }
                    catch (SQLException e) {
                      logger.info(e.getMessage(), e);
                    }
                  }
                  else rids.remove(t.getRole().getId());
                }
              });

          rids.forEach(new Consumer<Long>() {
            @Override
            public void accept(Long t) {
              UserRole ur = new UserRole();
              ur.setNew(true);

              Role r = new Role();
              r.setId(t);
              ur.setRole(r);

              User u = new User();
              u.setId(uid);
              ur.setUser(u);

              try {
                urDao.create(ur);
              }
              catch (SQLException e) {
                logger.info(e.getMessage(), e);
              }
            }
          });

          return null;
        }
      });

    }
    catch (Exception e) {
      throw new VetoException(e.getMessage(), e);
    }
  }

  @Override
  public Object[][] getAllUsers(Long rid) throws ProcessingException {
    Set<User> users = SERVICES.getService(IRoleService.class).getUsersInRole(rid);
    Object[][] result = new Object[users.size()][];
    int c = 0;

    for (User u : users) {
      result[c] = new Object[]{
          u.getId(),
          u.getUserName(),
          u.isValid(),
          u.getValidChangedTime(),
          u.getRecordStatus(),
          u.getCreatedTime(),
          u.getCreator(),
          u.getLastModifiedTime(),
          u.getLastModifier()
      };
      c++;
    }

    return result;
  }

  @Override
  public List<RolePermission> getPermissionsOfUser(String userName) throws ProcessingException {
    Dao<RolePermission, Long> rpdao = SERVICES.getService(IDatabaseService.class).getDao(RolePermission.class);
    Dao<User, Long> udao = SERVICES.getService(IDatabaseService.class).getDao(User.class);
    Dao<UserRole, Long> urdao = SERVICES.getService(IDatabaseService.class).getDao(UserRole.class);

    QueryBuilder<User, Long> uqb = udao.queryBuilder();
    QueryBuilder<RolePermission, Long> rpqb = rpdao.queryBuilder();

    try {
      uqb.where().eq(User.FIELD_NAME, userName);
      rpqb.join(urdao.queryBuilder().join(uqb));

      return rpqb.query();
    }
    catch (SQLException e) {
            throw new VetoException(e.getMessage(), e);
    }
  }

}
