/**
 * 
 */
package com.jf.scout.shared.administration.ui.desktop.forms;

import java.security.BasicPermission;

/**
 * @author Hoàng
 */
public class ReadRolePermission extends BasicPermission {

  private static final long serialVersionUID = 1L;

  /**
 * 
 */
  public ReadRolePermission() {
    super("ReadRole");
  }
}
