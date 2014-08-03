/**
 *
 */
package com.jf.scout.server.core.security;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;

import org.eclipse.scout.service.SERVICES;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.jf.commons.datamodels.RecordStatus;
import com.jf.commons.datamodels.User;
import com.jf.scout.server.core.services.ConfigurationService;
import com.jf.scout.shared.core.services.IConfigurationService;
import com.jf.scout.shared.core.services.IDatabaseService;

/**
 * @author Hoàng
 */
public class DataSourceSecurityFilter extends org.eclipse.scout.rt.server.commons.servletfilter.security.DataSourceSecurityFilter {
  /* (non-Javadoc)
   * @see org.eclipse.scout.rt.server.commons.servletfilter.security.DataSourceSecurityFilter#createJdbcDirectConnection()
   */
  @Override
  protected Connection createJdbcDirectConnection() throws ClassNotFoundException, SQLException {
    return SERVICES.getService(IDatabaseService.class).getJdbcConnection();
  }

  /* (non-Javadoc)
   * @see org.eclipse.scout.rt.server.commons.servletfilter.security.DataSourceSecurityFilter#isValidUser(java.lang.String, java.lang.String)
   */
  @Override
  protected boolean isValidUser(String username, String password) throws ServletException {
    try {
      // trick init config service
      ConfigurationService s = new ConfigurationService();

      Dao<User, Long> dao = DaoManager.createDao(
          new JdbcConnectionSource(s.getConfiguration().getString(IConfigurationService.DATABASE_URI),
              s.getConfiguration().getString(IConfigurationService.DATABASE_USER),
              s.getConfiguration().getString(IConfigurationService.DATABASE_PASSWORD)),
              User.class);
      User u = new User();
      u.setNew(true);
      u.setUserName(username);
      u.setPassword(password);
      u.setValid(true);

      return !dao.queryBuilder().where()
          .eq(User.FIELD_NAME, username)
          .and().eq(User.FIELD_PASSWORD, password)
          .and().eq(User.FIELD_VALID, true)
          .and().ne(User.FIELD_RECORD_STATUS, RecordStatus.DELETE)
          .query().isEmpty();
    }
    catch (Exception e) {
            throw new ServletException(e.getMessage(), e);
    }
  }
}
