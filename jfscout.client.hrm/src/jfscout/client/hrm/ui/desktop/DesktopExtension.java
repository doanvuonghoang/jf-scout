package jfscout.client.hrm.ui.desktop;

import java.util.ArrayList;
import java.util.List;

import jfscout.client.hrm.ui.desktop.outlines.HRMOutline;

import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.rt.client.ui.desktop.AbstractDesktopExtension;
import org.eclipse.scout.rt.client.ui.desktop.outline.AbstractOutlineViewButton;
import org.eclipse.scout.rt.client.ui.desktop.outline.IOutline;

public class DesktopExtension extends AbstractDesktopExtension {
  public DesktopExtension() {
  }

  @Override
  protected List<Class<? extends IOutline>> getConfiguredOutlines() {
    List<Class<? extends IOutline>> outlines = new ArrayList<Class<? extends IOutline>>();
    outlines.add(HRMOutline.class);
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
}
