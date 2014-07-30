/**
 *
 */
package com.jf.scout.shared.administration.ui.desktop.forms;

import java.security.BasicPermission;

import com.jf.commons.annotations.Description;

/**
 * @author Hoàng
 */
@Description(content = "Grant this users will have ability to grant permissions to role")
public class CreateAssignToRolePermission extends BasicPermission {

  private static final long serialVersionUID = 1L;

  /**
   *
   */
  public CreateAssignToRolePermission() {
    super("CreateAssignToRole");
  }
}
