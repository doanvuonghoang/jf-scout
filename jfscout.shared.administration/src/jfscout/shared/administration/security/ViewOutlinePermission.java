/**
 * 
 */
package jfscout.shared.administration.security;

import java.security.BasicPermission;

/**
 * @author Ho�ng
 */
public class ViewOutlinePermission extends BasicPermission {

  private static final long serialVersionUID = 1L;

  /**
 * 
 */
  public ViewOutlinePermission() {
    super("ViewOutline");
  }
}
