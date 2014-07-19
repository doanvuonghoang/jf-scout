package com.jf.scout.client.administration.ui.desktop;

import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.logger.IScoutLogger;
import org.eclipse.scout.commons.logger.ScoutLogManager;
import org.eclipse.scout.rt.client.ui.desktop.AbstractDesktopExtension;
import org.eclipse.scout.rt.extension.client.ui.action.menu.AbstractExtensibleMenu;
import org.eclipse.scout.rt.shared.TEXTS;

import com.jf.commons.annotations.Author;
import com.jf.commons.annotations.Version;
import com.jf.scout.commons.IInstallable;

@Author(name = "Hoang Doan")
@Version(version = "1.0")
public class DesktopExtension extends AbstractDesktopExtension implements IInstallable {
  private IScoutLogger logger = ScoutLogManager.getLogger(getClass());

  public DesktopExtension() {
  }

  @Order(10.0)
  public class AdministrationMenu extends AbstractExtensibleMenu {

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("Administration");
    }
  }

  /* (non-Javadoc)
   * @see com.jf.scout.commons.IInstallable#doInstall()
   */
  @Override
  public void doInstall() throws Exception {
    // TODO Auto-generated method stub
    logger.info("Installing extension: " + getClass().getName());
  }

  /* (non-Javadoc)
   * @see com.jf.scout.commons.IInstallable#doUnInstall()
   */
  @Override
  public void doUnInstall() throws Exception {
    // TODO Auto-generated method stub
    logger.info("Uninstalling extension: " + getClass().getName());
  }
}
