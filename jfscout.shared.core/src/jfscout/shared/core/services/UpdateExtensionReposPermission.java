/**
 * 
 */
package jfscout.shared.core.services;

import java.security.BasicPermission;

/**
 * @author Hoàng
 */
public class UpdateExtensionReposPermission extends BasicPermission {

  private static final long serialVersionUID = 1L;

  /**
 * 
 */
  public UpdateExtensionReposPermission() {
    super("UpdateExtensionRepos");
  }
}
