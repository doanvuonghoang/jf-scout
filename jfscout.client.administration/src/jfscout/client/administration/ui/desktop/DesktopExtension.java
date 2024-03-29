package jfscout.client.administration.ui.desktop;

import java.util.ArrayList;
import java.util.List;

import jfscout.client.administration.ui.desktop.outlines.AdministrationOutline;
import jfscout.shared.administration.services.IExtensionService;

import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.logger.IScoutLogger;
import org.eclipse.scout.commons.logger.ScoutLogManager;
import org.eclipse.scout.rt.client.ui.desktop.AbstractDesktopExtension;
import org.eclipse.scout.rt.client.ui.desktop.outline.AbstractOutlineViewButton;
import org.eclipse.scout.rt.client.ui.desktop.outline.IOutline;
import org.eclipse.scout.service.SERVICES;

import com.jf.commons.annotations.Author;
import com.jf.commons.annotations.Version;
import com.jf.scout.commons.IInstallable;

@Author(name = "Hoang Doan")
@Version(version = "1.0")
public class DesktopExtension extends AbstractDesktopExtension implements IInstallable {
  private IScoutLogger logger = ScoutLogManager.getLogger(getClass());

  public DesktopExtension() {
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
}
