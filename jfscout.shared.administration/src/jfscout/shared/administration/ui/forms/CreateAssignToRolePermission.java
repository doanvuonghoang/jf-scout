/**
 * 
 */
package jfscout.shared.administration.ui.forms;

import java.security.BasicPermission;

/**
 * @author Ho�ng
 */
public class CreateAssignToRolePermission extends BasicPermission {

  private static final long serialVersionUID = 1L;

  /**
 * 
 */
  public CreateAssignToRolePermission() {
    super("CreateAssignToRole");
  }
}
