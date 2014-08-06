package jfscout.client.hrm.ui.desktop;

import java.util.ArrayList;
import java.util.List;

import jfscout.client.hrm.ui.desktop.outlines.DepartmentOutline;
import jfscout.client.hrm.ui.desktop.outlines.HRMOutline;
import jfscout.client.hrm.ui.desktop.outlines.ReportOutline;
import jfscout.client.hrm.ui.desktop.outlines.SettingOutline;
import jfscout.shared.hrm.services.IExtensionService;

import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.rt.client.ui.desktop.AbstractDesktopExtension;
import org.eclipse.scout.rt.client.ui.desktop.outline.AbstractOutlineViewButton;
import org.eclipse.scout.rt.client.ui.desktop.outline.IOutline;
import org.eclipse.scout.service.SERVICES;

import com.jf.commons.annotations.Author;
import com.jf.commons.annotations.Version;
import com.jf.scout.commons.IInstallable;

@Author(name = "Hoang Doanz")
@Version(version = "1.0.0")
public class DesktopExtension extends AbstractDesktopExtension implements IInstallable {
  public DesktopExtension() {
  }

  /* (non-Javadoc)
   * @see com.jf.scout.commons.IInstallable#doInstall()
   */
  @Override
  public void doInstall() throws Exception {
    // call extension service to install
    SERVICES.getService(IExtensionService.class).installDB();
  }

  /* (non-Javadoc)
   * @see com.jf.scout.commons.IInstallable#doUnInstall()
   */
  @Override
  public void doUnInstall() throws Exception {
    // do nothing now
  }

  @Override
  protected List<Class<? extends IOutline>> getConfiguredOutlines() {
    List<Class<? extends IOutline>> outlines = new ArrayList<Class<? extends IOutline>>();
    outlines.add(HRMOutline.class);
    outlines.add(DepartmentOutline.class);
    outlines.add(ReportOutline.class);
    outlines.add(SettingOutline.class);
    return outlines;
  }

  @Order(10.0)
  public class HRMOutlineViewButton extends AbstractOutlineViewButton {

    /**
     *
     */
    public HRMOutlineViewButton() {
      super(getCoreDesktop(), HRMOutline.class);
    }
  }

  @Order(20.0)
  public class DepartmentOutlineViewButton extends AbstractOutlineViewButton {

    /**
     *
     */
    public DepartmentOutlineViewButton() {
      super(getCoreDesktop(), DepartmentOutline.class);
    }
  }

  @Order(30.0)
  public class ReportOutlineViewButton extends AbstractOutlineViewButton {

    /**
     *
     */
    public ReportOutlineViewButton() {
      super(getCoreDesktop(), ReportOutline.class);
    }
  }

  @Order(40.0)
  public class SettingOutlineViewButton extends AbstractOutlineViewButton {

    /**
     *
     */
    public SettingOutlineViewButton() {
      super(getCoreDesktop(), SettingOutline.class);
    }
  }
}
