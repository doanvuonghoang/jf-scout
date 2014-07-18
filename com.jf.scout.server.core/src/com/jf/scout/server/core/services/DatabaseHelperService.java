/**
 *
 */
package com.jf.scout.server.core.services;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.commons.exception.VetoException;
import org.eclipse.scout.service.AbstractService;
import org.eclipse.scout.service.SERVICES;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.DataSourceConnectionSource;
import com.jf.scout.shared.core.services.IConfigurationService;
import com.jf.scout.shared.core.services.IDatabaseHelperService;

/**
 * @author Hoàng
 */
public class DatabaseHelperService extends AbstractService implements IDatabaseHelperService {
  private IConfigurationService cfg = SERVICES.getService(IConfigurationService.class);
  private DataSource ds;

  @Override
  public <U, V> Dao<U, V> createDao(Class<U> cls) throws ProcessingException {
    //TODO [Hoàng] business logic here.
    try {
      Dao<U, V> dao = DaoManager.createDao(
          new DataSourceConnectionSource(getDataSource(), getDatabaseUri()),
          cls);

      return dao;
    }
    catch (SQLException ex) {
      throw new VetoException(ex.getMessage(), ex);
    }
  }

  @Override
  public DataSource getDataSource() throws ProcessingException {
    //TODO [Hoàng] business logic here.
    if (ds == null) {
      BasicDataSource t = new BasicDataSource();
      t.setDriverClassName(getDatabaseDriver());
      t.setUrl(getDatabaseUri());
      if (!getDatabaseUser().isEmpty()) t.setUsername(getDatabaseUser());
      if (!getDatabasePassword().isEmpty()) t.setUsername(getDatabasePassword());

      ds = t;
    }

    return ds;
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
}
