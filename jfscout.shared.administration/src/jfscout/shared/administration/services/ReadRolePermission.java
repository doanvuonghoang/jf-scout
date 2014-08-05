/**
 * 
 */
package jfscout.shared.administration.services;

import java.security.BasicPermission;

/**
 * @author Ho�ng
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
