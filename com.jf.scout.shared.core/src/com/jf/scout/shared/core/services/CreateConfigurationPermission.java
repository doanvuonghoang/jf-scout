/**
 * 
 */
package com.jf.scout.shared.core.services;

import java.security.BasicPermission;

/**
 * @author Ho�ng
 */
public class CreateConfigurationPermission extends BasicPermission {

  private static final long serialVersionUID = 1L;

  /**
 * 
 */
  public CreateConfigurationPermission() {
    super("CreateConfiguration");
  }
}
