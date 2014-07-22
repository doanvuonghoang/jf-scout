/**
 * 
 */
package com.jf.scout.shared.administration.ui.desktop.forms;

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
