package com.jf.scout.client.administration.ui.desktop;

import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.rt.client.ui.desktop.AbstractDesktopExtension;
import org.eclipse.scout.rt.extension.client.ui.action.menu.AbstractExtensibleMenu;
import org.eclipse.scout.rt.shared.TEXTS;

public class DesktopExtension extends AbstractDesktopExtension {
  public DesktopExtension() {
  }

  @Order(10.0)
  public class AdministrationMenu extends AbstractExtensibleMenu {

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("Administration");
    }
  }
}
