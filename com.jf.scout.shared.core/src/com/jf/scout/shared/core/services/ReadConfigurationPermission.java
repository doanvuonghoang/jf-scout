/**
 * 
 */
package com.jf.scout.shared.core.services;

import java.security.BasicPermission;

/**
 * @author Ho�ng
 */
public class ReadConfigurationPermission extends BasicPermission {

  private static final long serialVersionUID = 1L;

  /**
 * 
 */
  public ReadConfigurationPermission() {
    super("ReadConfiguration");
  }
}
