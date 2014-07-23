/**
 * 
 */
package com.jf.scout.shared.administration.security;

import java.security.BasicPermission;

/**
 * @author Hoàng
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
