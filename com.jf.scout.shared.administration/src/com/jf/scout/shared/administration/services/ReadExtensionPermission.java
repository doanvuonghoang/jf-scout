/**
 * 
 */
package com.jf.scout.shared.administration.services;

import java.security.BasicPermission;

/**
 * @author Hoàng
 */
public class ReadExtensionPermission extends BasicPermission {

  private static final long serialVersionUID = 1L;

  /**
 * 
 */
  public ReadExtensionPermission() {
    super("ReadExtension");
  }
}
