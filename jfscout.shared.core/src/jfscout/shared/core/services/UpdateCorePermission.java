/**
 * 
 */
package jfscout.shared.core.services;

import java.security.BasicPermission;

/**
 * @author Hoàng
 */
public class UpdateCorePermission extends BasicPermission {

  private static final long serialVersionUID = 1L;

  /**
 * 
 */
  public UpdateCorePermission() {
    super("UpdateCore");
  }
}
