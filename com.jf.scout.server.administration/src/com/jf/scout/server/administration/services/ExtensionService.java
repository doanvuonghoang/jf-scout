/**
 *
 */
package com.jf.scout.server.administration.services;

import java.sql.SQLException;
import java.util.concurrent.Callable;

import org.eclipse.scout.commons.Base64Utility;
import org.eclipse.scout.commons.EncryptionUtility;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.commons.exception.VetoException;
import org.eclipse.scout.commons.logger.IScoutLogger;
import org.eclipse.scout.commons.logger.ScoutLogManager;
import org.eclipse.scout.service.AbstractService;
import org.eclipse.scout.service.SERVICES;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.misc.TransactionManager;
import com.j256.ormlite.table.TableUtils;
import com.jf.commons.datamodels.Role;
import com.jf.commons.datamodels.RolePermission;
import com.jf.commons.datamodels.User;
import com.jf.commons.datamodels.UserRole;
import com.jf.scout.server.core.ServerSession;
import com.jf.scout.shared.administration.services.IExtensionService;
import com.jf.scout.shared.core.services.IDatabaseService;

/**
 * @author Hoàng
 */
public class ExtensionService extends AbstractService implements IExtensionService {
  private IScoutLogger logger = ScoutLogManager.getLogger(getClass());

  /**
   * Get user Dao
   *
   * @return
   * @throws ProcessingException
   */
  private <T> Dao<T, Long> getDao(Class<T> cls) throws ProcessingException {
    return SERVICES.getService(IDatabaseService.class).getDao(cls);
  }

  @Override
  public void installDB() throws ProcessingException {
    //TODO [Hoàng] business logic here.
    logger.info("Preparing scripts to install database for administration extension");

    try {
      TransactionManager.callInTransaction(getDao(User.class).getConnectionSource(), new Callable<Void>() {
        /* (non-Javadoc)
         * @see java.util.concurrent.Callable#call()
         */
        @Override
        public Void call() throws Exception {
          // TODO Auto-generated method stub
          // create user table
          TableUtils.createTable(getDao(User.class).getConnectionSource(), User.class);
          logger.info("Installed user table");
          // create role table
          TableUtils.createTable(getDao(Role.class).getConnectionSource(), Role.class);
          logger.info("Installed role table");
          // create user role table
          TableUtils.createTable(getDao(UserRole.class).getConnectionSource(), UserRole.class);
          logger.info("Installed user role table");
          // create role permission table
          TableUtils.createTable(getDao(RolePermission.class).getConnectionSource(), RolePermission.class);
          logger.info("Installed role permission table");

          // create admin user
          User admin = new User();
          admin.setNew(true);
          admin.setUserName("admin");
          admin.setPassword(Base64Utility.encode(EncryptionUtility.signMD5("admin".getBytes())));
          admin.setValid(true);
          admin.setCreator(ServerSession.get().getUserId());
          getDao(User.class).create(admin);

          return null;
        }
      });
    }
    catch (SQLException e) {
      // TODO Auto-generated catch block
      throw new VetoException(e.getMessage(), e);
    }

    logger.info("Installed database for administration extension successfully");
  }
}
