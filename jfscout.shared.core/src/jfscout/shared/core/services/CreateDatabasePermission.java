/**
 * 
 */
package jfscout.shared.core.services;

import java.security.BasicPermission;

/**
 * @author Hoàng
 */
public class CreateDatabasePermission extends BasicPermission {

  private static final long serialVersionUID = 1L;

  /**
 * 
 */
  public CreateDatabasePermission() {
    super("CreateDatabase");
  }
}
