package com.jf.scout.client.installer.ui.desktop;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.desktop.AbstractDesktopExtension;
import org.eclipse.scout.rt.client.ui.desktop.ContributionCommand;
import org.eclipse.scout.service.SERVICES;

import com.jf.scout.shared.installer.services.IInstallerService;

public class DesktopExtension extends AbstractDesktopExtension {
  public DesktopExtension() {
  }

  /* (non-Javadoc)
   * @see org.eclipse.scout.rt.client.ui.desktop.AbstractDesktopExtension#execOpened()
   */
  @Override
  protected ContributionCommand execOpened() throws ProcessingException {
    // TODO Auto-generated method stub
    System.out.println("Extension opened");
    SERVICES.getService(IInstallerService.class).install();

    return super.execOpened();
  }
}
