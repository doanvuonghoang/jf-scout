/**
 *
 */
package com.jf.scout.server.administration.ui.desktop.forms;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;

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
import com.jf.commons.datamodels.RecordStatus;
import com.jf.commons.datamodels.User;
import com.jf.commons.datamodels.UserRole;
import com.jf.scout.server.core.ServerSession;
import com.jf.scout.shared.administration.ui.desktop.forms.CreateUserPermission;
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
    //TODO [Hoàng] business logic here.
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
    //TODO [Hoàng] business logic here.
    Dao<User, Long> dao = SERVICES.getService(IDatabaseService.class).getDao(User.class);
    try {
      User u = dao.queryForId(formData.getUserNr().longValue());

      formData.getUserName().setValue(u.getUserName());
      formData.getUserPassword().setValue(u.getPassword());
      formData.getValid().setValue(u.isValid());

      for (Long id : getRoleIdsOfUser(u.getId())) {
        formData.getRoles().getFieldById(id.toString()).setValueSet(true);
      }
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
    //TODO [Hoàng] business logic here.
    return formData;
  }

  @Override
  public UserFormData store(UserFormData formData) throws ProcessingException {
    if (!ACCESS.check(new UpdateUserPermission())) {
      throw new VetoException(TEXTS.get("AuthorizationFailed"));
    }
    //TODO [Hoàng] business logic here.
    Dao<User, Long> dao = SERVICES.getService(IDatabaseService.class).getDao(User.class);

    try {
      User u = dao.queryForId(formData.getUserNr().longValue());

      if (u.getPassword() != formData.getUserPassword().getValue()) {
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
//      addRoleIdsOfUser(u.getId(), formData.getRoles().)
    }
    catch (SQLException e) {
      throw new VetoException(e.getMessage(), e);
    }

    return formData;
  }

  @Override
  public Object[][] getAllUsers() throws ProcessingException {
    //TODO [Hoàng] business logic here.
    Dao<User, Long> dao = SERVICES.getService(IDatabaseService.class).getDao(User.class);

    try {
      List<User> us = dao.queryForAll();
      Object[][] result = new Object[us.size()][];
      int c = 0;

      for (User u : us) {
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
    catch (SQLException e) {
      // TODO Auto-generated catch block
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
    //TODO [Hoàng] business logic here.
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
  public Long[] getRoleIdsOfUser(Long uid) throws ProcessingException {
    Dao<UserRole, Long> urDao = SERVICES.getService(IDatabaseService.class).getDao(UserRole.class);
    try {
      return urDao.queryBuilder().where()
          .eq(UserRole.FIELD_USER_ID, uid).query().toArray(new Long[]{});
    }
    catch (SQLException e) {
      throw new VetoException(e.getMessage(), e);
    }
  }

  @Override
  public void addRoleIdsOfUser(Long uid, Long[] rids) throws ProcessingException {
    //TODO [Hoàng] business logic here.

  }

}
