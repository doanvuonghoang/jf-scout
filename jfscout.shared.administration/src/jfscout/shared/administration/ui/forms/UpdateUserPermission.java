/**
 * 
 */
package jfscout.shared.administration.ui.forms;

import java.security.BasicPermission;

/**
 * @author Hoàng
 */
public class UpdateUserPermission extends BasicPermission {

  private static final long serialVersionUID = 1L;

  /**
 * 
 */
  public UpdateUserPermission() {
    super("UpdateUser");
  }
}
