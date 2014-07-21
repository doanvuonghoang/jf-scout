/**
 *
 */
package com.jf.scout.server.core.services.common.sql;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.commons.logger.ScoutLogManager;
import org.eclipse.scout.rt.server.services.common.jdbc.AbstractSqlService;
import org.eclipse.scout.service.SERVICES;

import com.jf.scout.shared.core.services.IConfigurationService;

/**
 * @author Hoàng
 */
public class DBSqlService extends AbstractSqlService {
  private IConfigurationService cfg;

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
    // TODO Auto-generated method stub
    try {
      return getCfg().readString(IConfigurationService.DATABASE_DRIVER, "");
    }
    catch (ProcessingException e) {
      // TODO Auto-generated catch block
      ScoutLogManager.getLogger(getClass()).info(e.getMessage(), e);

      return super.getConfiguredJdbcDriverName();
    }
  }

  /* (non-Javadoc)
   * @see org.eclipse.scout.rt.server.services.common.jdbc.AbstractSqlService#getConfiguredJndiProviderUrl()
   */
  @Override
  protected String getConfiguredJdbcMappingName() {
    // TODO Auto-generated method stub
    try {
      return getCfg().readString(IConfigurationService.DATABASE_URI, "");
    }
    catch (ProcessingException e) {
      // TODO Auto-generated catch block
      ScoutLogManager.getLogger(getClass()).info(e.getMessage(), e);

      return super.getConfiguredJdbcMappingName();
    }
  }

  /* (non-Javadoc)
   * @see org.eclipse.scout.rt.server.services.common.jdbc.AbstractSqlService#getConfiguredUsername()
   */
  @Override
  protected String getConfiguredUsername() {
    // TODO Auto-generated method stub
    try {
      return getCfg().readString(IConfigurationService.DATABASE_USER, "");
    }
    catch (ProcessingException e) {
      // TODO Auto-generated catch block
      ScoutLogManager.getLogger(getClass()).info(e.getMessage(), e);

      return super.getConfiguredUsername();
    }
  }

  /* (non-Javadoc)
   * @see org.eclipse.scout.rt.server.services.common.jdbc.AbstractSqlService#getConfiguredPassword()
   */
  @Override
  protected String getConfiguredPassword() {
    // TODO Auto-generated method stub
    try {
      return getCfg().readString(IConfigurationService.DATABASE_PASSWORD, "");
    }
    catch (ProcessingException e) {
      // TODO Auto-generated catch block
      ScoutLogManager.getLogger(getClass()).info(e.getMessage(), e);

      return super.getConfiguredPassword();
    }
  }
}
