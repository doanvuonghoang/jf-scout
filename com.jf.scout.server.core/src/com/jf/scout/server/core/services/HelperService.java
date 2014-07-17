/**
 *
 */
package com.jf.scout.server.core.services;

import java.sql.Connection;
import java.sql.DriverManager;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.service.AbstractService;

import com.jf.scout.shared.core.services.IHelperService;

/**
 * @author Hoàng
 */
public class HelperService extends AbstractService implements IHelperService {

  @Override
  public String testConnection(String driver, String uri, String user, String password) throws ProcessingException {
    //TODO [Hoàng] business logic here.
    try {
      Class.forName(driver);

      Connection conn = DriverManager.getConnection(uri, user, password);
      conn.close();

      return "Connected!";
    }
    catch (Exception e) {
      // TODO Auto-generated catch block
      return e.getMessage();
    }
  }
}
