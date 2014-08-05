/**
 * 
 */
package jfscout.shared.administration.services;

import java.security.BasicPermission;

/**
 * @author Hoàng
 */
public class UpdateRolePermission extends BasicPermission {

  private static final long serialVersionUID = 1L;

  /**
 * 
 */
  public UpdateRolePermission() {
    super("UpdateRole");
  }
}
