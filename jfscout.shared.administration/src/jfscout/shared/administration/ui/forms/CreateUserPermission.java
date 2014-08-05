/**
 * 
 */
package jfscout.shared.administration.ui.forms;

import java.security.BasicPermission;

/**
 * @author Hoàng
 */
public class CreateUserPermission extends BasicPermission {

  private static final long serialVersionUID = 1L;

  /**
 * 
 */
  public CreateUserPermission() {
    super("CreateUser");
  }
}
