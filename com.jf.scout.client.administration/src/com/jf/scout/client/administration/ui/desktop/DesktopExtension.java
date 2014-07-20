package com.jf.scout.client.administration.ui.desktop;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.logger.IScoutLogger;
import org.eclipse.scout.commons.logger.ScoutLogManager;
import org.eclipse.scout.rt.client.ui.action.tool.AbstractToolButton;
import org.eclipse.scout.rt.client.ui.desktop.AbstractDesktopExtension;
import org.eclipse.scout.rt.client.ui.desktop.outline.AbstractOutlineViewButton;
import org.eclipse.scout.rt.client.ui.desktop.outline.IOutline;
import org.eclipse.scout.rt.extension.client.ui.action.menu.AbstractExtensibleMenu;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.service.SERVICES;

import com.jf.commons.annotations.Author;
import com.jf.commons.annotations.Version;
import com.jf.scout.client.administration.ui.desktop.outlines.AdministrationOutline;
import com.jf.scout.commons.IInstallable;
import com.jf.scout.shared.administration.services.IExtensionService;
import com.jf.scout.shared.core.Icons;

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

  @Override
  protected List<Class<? extends IOutline>> getConfiguredOutlines() {
    List<Class<? extends IOutline>> outlines = new ArrayList<Class<? extends IOutline>>();
    outlines.add(AdministrationOutline.class);
    return outlines;
  }

  /* (non-Javadoc)
   * @see com.jf.scout.commons.IInstallable#doInstall()
   */
  @Override
  public void doInstall() throws Exception {
    SERVICES.getService(IExtensionService.class).installDB();
  }

  /* (non-Javadoc)
   * @see com.jf.scout.commons.IInstallable#doUnInstall()
   */
  @Override
  public void doUnInstall() throws Exception {
    // TODO Auto-generated method stub
    logger.info("Uninstalling extension: " + getClass().getName());
  }

  @Order(10.0)
  public class AdministrationOutlineViewButton extends AbstractOutlineViewButton {

    /**
   * 
   */
    public AdministrationOutlineViewButton() {
      super(getCoreDesktop(), AdministrationOutline.class);
    }
  }

  @Order(10.0)
  public class CreateUserTool extends AbstractToolButton {

    @Override
    protected String getConfiguredIconId() {
      return Icons.CreateUser;
    }

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("CreateUser");
    }

    @Override
    protected String getConfiguredTooltipText() {
      return TEXTS.get("CreateUser");
    }
  }
}
