/**
 * 
 */
package jfscout.shared.administration.ui.forms;

import java.security.BasicPermission;

/**
 * @author Ho�ng
 */
public class ReadUserPermission extends BasicPermission {

  private static final long serialVersionUID = 1L;

  /**
 * 
 */
  public ReadUserPermission() {
    super("ReadUser");
  }
}
