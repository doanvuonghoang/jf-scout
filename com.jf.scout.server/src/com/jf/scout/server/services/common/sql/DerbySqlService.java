/**
 * 
 */
package com.jf.scout.server.services.common.sql;

import org.eclipse.scout.rt.server.services.common.jdbc.AbstractSqlService;

/**
 * @author Hoàng
 */
public class DerbySqlService extends AbstractSqlService {

  @Override
  protected String getConfiguredJdbcDriverName() {
    return "org.apache.derby.jdbc.EmbeddedDriver";
  }

  @Override
  protected String getConfiguredJdbcMappingName() {
    return "jdbc:derby:data/derby/jfscout;create=true";
  }

  @Override
  protected String getConfiguredPassword() {
    return "sa";
  }

  @Override
  protected String getConfiguredUsername() {
    return "sa";
  }
}
