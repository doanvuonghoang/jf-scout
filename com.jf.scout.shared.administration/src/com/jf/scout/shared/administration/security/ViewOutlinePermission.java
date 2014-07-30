/**
 *
 */
package com.jf.scout.shared.administration.security;

import java.security.BasicPermission;

import com.jf.commons.annotations.Description;

/**
 * @author Hoàng
 */
@Description(content = "Grant this permission to view these extension outlines")
public class ViewOutlinePermission extends BasicPermission {

  private static final long serialVersionUID = 1L;

  /**
   *
   */
  public ViewOutlinePermission() {
    super("ViewOutline");
  }
}
