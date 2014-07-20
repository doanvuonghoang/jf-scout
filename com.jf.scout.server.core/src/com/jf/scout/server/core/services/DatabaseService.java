/**
 *
 */
package com.jf.scout.server.core.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.commons.exception.VetoException;
import org.eclipse.scout.service.AbstractService;
import org.eclipse.scout.service.SERVICES;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.jf.scout.shared.core.services.IConfigurationService;
import com.jf.scout.shared.core.services.IDatabaseService;

/**
 * @author Hoàng
 */
public class DatabaseService extends AbstractService implements IDatabaseService {
  private IConfigurationService cfg = SERVICES.getService(IConfigurationService.class);
  private ConnectionSource cs;

  @Override
  public <U, V> Dao<U, V> getDao(Class<U> cls) throws ProcessingException {
    //TODO [Hoàng] business logic here.
    try {
      Dao<U, V> dao = DaoManager.lookupDao(getConnectionSource(false), cls);

      if (dao == null) {
        dao = DaoManager.createDao(getConnectionSource(false), cls);
        DaoManager.registerDao(getConnectionSource(false), dao);
      }

      return dao;
    }
    catch (SQLException ex) {
      throw new VetoException(ex.getMessage(), ex);
    }
  }

  public ConnectionSource getConnectionSource(boolean refresh) throws ProcessingException {
    JdbcConnectionSource jcs = null;

    if (cs == null || refresh) {
      try {
        jcs = new JdbcConnectionSource(getDatabaseUri(), getDatabaseUser(), getDatabasePassword());
//        jcs.setDatabaseType(DatabaseTypeUtils.createDatabaseType(getDatabaseUri()));
      }
      catch (SQLException e) {
        // TODO Auto-generated catch block
        throw new VetoException(e.getMessage(), e);
      }

      cs = jcs;
    }

    return cs;
  }

  @Override
  public String getDatabaseUri() throws ProcessingException {
    return cfg.readString(IConfigurationService.DATABASE_URI, "");
  }

  public String getDatabaseDriver() throws ProcessingException {
    return cfg.readString(IConfigurationService.DATABASE_DRIVER, "");
  }

  public String getDatabaseUser() throws ProcessingException {
    return cfg.readString(IConfigurationService.DATABASE_USER, "");
  }

  public String getDatabasePassword() throws ProcessingException {
    return cfg.readString(IConfigurationService.DATABASE_PASSWORD, "");
  }

  @Override
  public void refreshSource() throws ProcessingException {
    //TODO [Hoàng] business logic here.
    getConnectionSource(true);
  }

  @Override
  public Connection getJdbcConnection() throws ClassNotFoundException, SQLException {
    //TODO [Hoàng] business logic here.
    String driver = "", uri = "", user = "", password = "";
    try {
      driver = getDatabaseDriver();
      uri = getDatabaseUri();
      user = getDatabaseUser();
      password = getDatabasePassword();
    }
    catch (ProcessingException e) {

    }

    Class.forName(driver);

    Connection conn = DriverManager.getConnection(uri, user, password);

    return conn;
  }
}
