package com.jf.scout.commons.jdbc.style;

import java.sql.Connection;
import java.sql.SQLException;

import org.eclipse.scout.rt.server.services.common.jdbc.style.DB2OracleModeSqlStyle;


public class SQLStyle extends DB2OracleModeSqlStyle {
    private static final long serialVersionUID = 1L;

    /* (non-Javadoc)
     * @see org.eclipse.scout.rt.server.services.common.jdbc.style.ISqlStyle#testConnection(java.sql.Connection)
     */
    @Override
    public void testConnection(Connection conn) throws SQLException {
          }

  }
