/**
 * 
 */
package jfscout.shared.core.services;

import java.security.BasicPermission;

/**
 * @author Ho�ng
 */
public class CreateCorePermission extends BasicPermission {

  private static final long serialVersionUID = 1L;

  /**
 * 
 */
  public CreateCorePermission() {
    super("CreateCore");
  }
}
