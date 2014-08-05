/**
 * 
 */
package jfscout.shared.core.services;

import java.security.BasicPermission;

/**
 * @author Hoàng
 */
public class CreateExtensionReposPermission extends BasicPermission {

  private static final long serialVersionUID = 1L;

  /**
 * 
 */
  public CreateExtensionReposPermission() {
    super("CreateExtensionRepos");
  }
}
