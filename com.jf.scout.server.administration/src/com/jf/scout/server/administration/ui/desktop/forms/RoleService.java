/**
 *
 */
package com.jf.scout.server.administration.ui.desktop.forms;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.commons.exception.VetoException;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.security.ACCESS;
import org.eclipse.scout.service.AbstractService;
import org.eclipse.scout.service.SERVICES;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.misc.TransactionManager;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;
import com.jf.commons.datamodels.RecordStatus;
import com.jf.commons.datamodels.Role;
import com.jf.commons.datamodels.RolePermission;
import com.jf.commons.datamodels.User;
import com.jf.commons.datamodels.UserRole;
import com.jf.scout.server.core.ServerSession;
import com.jf.scout.shared.administration.ui.desktop.forms.CreateRolePermission;
import com.jf.scout.shared.administration.ui.desktop.forms.IRoleService;
import com.jf.scout.shared.administration.ui.desktop.forms.ReadRolePermission;
import com.jf.scout.shared.administration.ui.desktop.forms.RoleFormData;
import com.jf.scout.shared.administration.ui.desktop.forms.UpdateRolePermission;
import com.jf.scout.shared.core.services.IDatabaseService;

/**
 * @author Hoàng
 */
public class RoleService extends AbstractService implements IRoleService {

  @Override
  public RoleFormData create(RoleFormData formData) throws ProcessingException {
    if (!ACCESS.check(new CreateRolePermission())) {
      throw new VetoException(TEXTS.get("AuthorizationFailed"));
    }
        Role m = new Role();
    m.setNew(true);
    m.setRoleName(formData.getRoleName().getValue());
    m.setValid(formData.getValid().getValue());
    m.setCreator(ServerSession.get().getUserId());
    try {
      SERVICES.getService(IDatabaseService.class).getDao(Role.class).create(m);
    }
    catch (SQLException e) {
      throw new VetoException(e.getMessage(), e);
    }

    return formData;
  }

  @Override
  public RoleFormData load(RoleFormData formData) throws ProcessingException {
    if (!ACCESS.check(new ReadRolePermission())) {
      throw new VetoException(TEXTS.get("AuthorizationFailed"));
    }
        Dao<Role, Long> dao = SERVICES.getService(IDatabaseService.class).getDao(Role.class);
    try {
      Role m = dao.queryForId(formData.getRoleNr().longValue());

      formData.getRoleName().setValue(m.getRoleName());
      formData.getValid().setValue(m.isValid());
    }
    catch (SQLException e) {
      throw new VetoException(e.getMessage(), e);
    }

    return formData;
  }

  @Override
  public RoleFormData prepareCreate(RoleFormData formData) throws ProcessingException {
    if (!ACCESS.check(new CreateRolePermission())) {
      throw new VetoException(TEXTS.get("AuthorizationFailed"));
    }
        return formData;
  }

  @Override
  public RoleFormData store(RoleFormData formData) throws ProcessingException {
    if (!ACCESS.check(new UpdateRolePermission())) {
      throw new VetoException(TEXTS.get("AuthorizationFailed"));
    }
        Dao<Role, Long> dao = SERVICES.getService(IDatabaseService.class).getDao(Role.class);

    try {
      Role m = new Role();
      m.setId(formData.getRoleNr().longValue());
      dao.refresh(m);

      m.setValid(formData.getValid().getValue());
      m.setLastModifier(ServerSession.get().getUserId());

      dao.update(m);
    }
    catch (SQLException e) {
      throw new VetoException(e.getMessage(), e);
    }

    return formData;
  }

  @Override
  public Object[][] getAllRoles() throws ProcessingException {
        Dao<Role, Long> dao = SERVICES.getService(IDatabaseService.class).getDao(Role.class);

    try {
      List<Role> rs = dao.queryForAll();
      Object[][] result = new Object[rs.size()][];
      int c = 0;

      for (Role r : rs) {
        result[c] = new Object[]{
            r.getId(),
            r.getRoleName(),
            r.isValid(),
            r.getValidChangedTime(),
            r.getRecordStatus(),
            r.getCreatedTime(),
            r.getCreator(),
            r.getLastModifiedTime(),
            r.getLastModifier()
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
  public void deleteRoles(Long[] ids) throws ProcessingException {
    Dao<Role, Long> dao = SERVICES.getService(IDatabaseService.class).getDao(Role.class);

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
                Role u = dao.queryForId(id);
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
  public void deleteRolesPermantly(Long[] ids) throws ProcessingException {
    Dao<Role, Long> dao = SERVICES.getService(IDatabaseService.class).getDao(Role.class);

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
  public void restoreRoles(Long[] ids) throws ProcessingException {
        Dao<Role, Long> dao = SERVICES.getService(IDatabaseService.class).getDao(Role.class);

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
                Role u = dao.queryForId(id);
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
  public void addUsersToRole(long[] uids, long rid) throws ProcessingException {
        Dao<UserRole, Long> dao = SERVICES.getService(IDatabaseService.class).getDao(UserRole.class);

    try {
      TransactionManager.callInTransaction(
          dao.getConnectionSource(),
          new Callable<Void>() {
            /* (non-Javadoc)
             * @see java.util.concurrent.Callable#call()
             */
            @Override
            public Void call() throws Exception {
              Role r = new Role();
              r.setId(rid);

              for (long id : uids) {
                User u = new User();
                u.setId(id);

                UserRole ur = new UserRole();
                ur.setNew(true);
                ur.setUser(u);
                ur.setRole(r);

                dao.create(ur);
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
  public Set<User> getUsersInRole(Long rid) throws ProcessingException {
    Dao<UserRole, Long> urdao = SERVICES.getService(IDatabaseService.class).getDao(UserRole.class);
    Dao<User, Long> udao = SERVICES.getService(IDatabaseService.class).getDao(User.class);
    QueryBuilder<User, Long> uqb = udao.queryBuilder();
    QueryBuilder<UserRole, Long> urqb = urdao.queryBuilder();

    try {
      urqb.where().eq(UserRole.FIELD_ROLE_ID, rid);

      return new HashSet<User>(uqb.join(urqb).query());
    }
    catch (SQLException e) {
            throw new VetoException(e.getMessage(), e);
    }
  }

  @Override
  public Set<RolePermission> getPermissionsOfRole(Long roleId) throws ProcessingException {
    Dao<RolePermission, Long> rpdao = SERVICES.getService(IDatabaseService.class).getDao(RolePermission.class);

    try {
      List<RolePermission> rps = rpdao.queryBuilder().where().eq(RolePermission.FIELD_ROLE_ID, roleId).query();
      rps.sort(new Comparator<RolePermission>() {
        /* (non-Javadoc)
         * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
         */
        @Override
        public int compare(RolePermission o1, RolePermission o2) {
          return o1.getPermission().compareTo(o2.getPermission());
        }
      });

      return new HashSet<RolePermission>(rps);
    }
    catch (SQLException e) {
      throw new VetoException(e.getMessage(), e);
    }
  }

  @Override
  public void revokePermissionsOfRole(Long roleId, List<String> permissions) throws ProcessingException {
    Dao<RolePermission, Long> rpdao = SERVICES.getService(IDatabaseService.class).getDao(RolePermission.class);

    try {
      TransactionManager.callInTransaction(rpdao.getConnectionSource(), new Callable<Void>() {
        /* (non-Javadoc)
         * @see java.util.concurrent.Callable#call()
         */
        @Override
        public Void call() throws Exception {
          DeleteBuilder<RolePermission, Long> db = rpdao.deleteBuilder();
          db.where().eq(RolePermission.FIELD_ROLE_ID, roleId).and().in(RolePermission.FIELD_PERMISSION, permissions);
          db.delete();

          return null;
        }
      });

    }
    catch (Exception e) {
      throw new VetoException(e.getMessage(), e);
    }
  }
}
