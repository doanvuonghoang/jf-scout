package com.jf.scout.server.core.services.common.security;

import java.security.AllPermission;
import java.security.Permission;
import java.security.Permissions;

import org.eclipse.scout.commons.logger.IScoutLogger;
import org.eclipse.scout.commons.logger.ScoutLogManager;
import org.eclipse.scout.rt.server.services.common.security.AbstractAccessControlService;
import org.eclipse.scout.rt.shared.security.RemoteServiceAccessPermission;
import org.eclipse.scout.service.SERVICES;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.jf.commons.datamodels.RolePermission;
import com.jf.commons.datamodels.User;
import com.jf.commons.datamodels.UserRole;
import com.jf.scout.server.core.ServerSession;
import com.jf.scout.shared.core.services.IDatabaseService;

public class AccessControlService extends AbstractAccessControlService {
  private IScoutLogger logger = ScoutLogManager.getLogger(getClass());

  @Override
  protected Permissions execLoadPermissions() {
    Permissions permissions = new Permissions();

    // calling services is free
    permissions.add(new RemoteServiceAccessPermission("*.shared.*", "*"));

    // backdoor: the first user may do everything?
    if (ServerSession.get().getUserId().equals("admin")) {
      logger.warn("backdoor used: user admin was granted all permissions");
      permissions.add(new AllPermission());
    }
    else {
      try {
        Dao<RolePermission, Long> rpdao = SERVICES.getService(IDatabaseService.class).getDao(RolePermission.class);
        Dao<User, Long> udao = SERVICES.getService(IDatabaseService.class).getDao(User.class);
        Dao<UserRole, Long> urdao = SERVICES.getService(IDatabaseService.class).getDao(UserRole.class);

        QueryBuilder<User, Long> uqb = udao.queryBuilder();
        QueryBuilder<RolePermission, Long> rpqb = rpdao.queryBuilder();

        uqb.where().eq(User.FIELD_NAME, ServerSession.get().getUserId());
        rpqb.join(urdao.queryBuilder().join(uqb));

        rpqb.query().forEach((RolePermission t) -> {
          try {
            permissions.add((Permission) Class.forName(t.getPermission()).newInstance());
          }
          catch (Exception e) {
            logger.warn(e.getMessage(), e);
          }
        });
      }
      catch (Exception e) {
        // TODO Auto-generated catch block
        logger.warn(e.getMessage(), e);
      }
    }

    return permissions;
  }

}
