/**
 *
 */
package jfscout.server.core.services.common.sql;

import java.sql.Connection;

import jfscout.shared.core.services.IConfigurationService;
import jfscout.shared.core.services.IDatabaseService;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.commons.logger.ScoutLogManager;
import org.eclipse.scout.rt.server.services.common.jdbc.AbstractSqlService;
import org.eclipse.scout.rt.server.services.common.jdbc.style.ISqlStyle;
import org.eclipse.scout.service.SERVICES;

import com.jf.scout.commons.jdbc.style.SQLStyle;

/**
 * @author Hoàng
 */
public class DBSqlService extends AbstractSqlService {
  private IConfigurationService cfg;

  @Override
  protected Class<? extends ISqlStyle> getConfiguredSqlStyle() {
    return SQLStyle.class;
  }

  @Override
  protected Connection execCreateConnection() throws Throwable {
    //TODO [Hoàng] Use my database service to get connection
    return SERVICES.getService(IDatabaseService.class).getJdbcConnection();
  }

  private IConfigurationService getCfg() throws ProcessingException {
    if (cfg == null) {
      cfg = SERVICES.getService(IConfigurationService.class);
    }

    return cfg;
  }

  /* (non-Javadoc)
   * @see org.eclipse.scout.rt.server.services.common.jdbc.AbstractSqlService#getConfiguredJdbcDriverName()
   */
  @Override
  protected String getConfiguredJdbcDriverName() {
    try {
      return getCfg().readString(IConfigurationService.DATABASE_DRIVER, "");
    }
    catch (ProcessingException e) {
      ScoutLogManager.getLogger(getClass()).info(e.getMessage(), e);

      return super.getConfiguredJdbcDriverName();
    }
  }

  /* (non-Javadoc)
   * @see org.eclipse.scout.rt.server.services.common.jdbc.AbstractSqlService#getConfiguredJndiProviderUrl()
   */
  @Override
  protected String getConfiguredJdbcMappingName() {
    try {
      return getCfg().readString(IConfigurationService.DATABASE_URI, "");
    }
    catch (ProcessingException e) {
      ScoutLogManager.getLogger(getClass()).info(e.getMessage(), e);

      return super.getConfiguredJdbcMappingName();
    }
  }

  /* (non-Javadoc)
   * @see org.eclipse.scout.rt.server.services.common.jdbc.AbstractSqlService#getConfiguredUsername()
   */
  @Override
  protected String getConfiguredUsername() {
    try {
      return getCfg().readString(IConfigurationService.DATABASE_USER, "");
    }
    catch (ProcessingException e) {
      ScoutLogManager.getLogger(getClass()).info(e.getMessage(), e);

      return super.getConfiguredUsername();
    }
  }

  /* (non-Javadoc)
   * @see org.eclipse.scout.rt.server.services.common.jdbc.AbstractSqlService#getConfiguredPassword()
   */
  @Override
  protected String getConfiguredPassword() {
    try {
      return getCfg().readString(IConfigurationService.DATABASE_PASSWORD, "");
    }
    catch (ProcessingException e) {
      ScoutLogManager.getLogger(getClass()).info(e.getMessage(), e);

      return super.getConfiguredPassword();
    }
  }
}
