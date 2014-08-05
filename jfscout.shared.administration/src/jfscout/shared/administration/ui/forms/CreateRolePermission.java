/**
 * 
 */
package jfscout.shared.administration.ui.forms;

import java.security.BasicPermission;

/**
 * @author Hoàng
 */
public class CreateRolePermission extends BasicPermission {

  private static final long serialVersionUID = 1L;

  /**
 * 
 */
  public CreateRolePermission() {
    super("CreateRole");
  }
}
