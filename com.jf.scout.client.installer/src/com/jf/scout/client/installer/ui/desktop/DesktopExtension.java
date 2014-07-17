package com.jf.scout.client.installer.ui.desktop;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.commons.exception.VetoException;
import org.eclipse.scout.commons.logger.IScoutLogger;
import org.eclipse.scout.commons.logger.ScoutLogManager;
import org.eclipse.scout.rt.client.ui.desktop.AbstractDesktopExtension;
import org.eclipse.scout.rt.client.ui.desktop.ContributionCommand;
import org.eclipse.scout.service.SERVICES;

import com.jf.scout.client.installer.ui.wizards.SetupWizard;
import com.jf.scout.shared.core.services.IConfigurationService;

public class DesktopExtension extends AbstractDesktopExtension {
  private IScoutLogger logger = ScoutLogManager.getLogger(getClass());

  public DesktopExtension() {
  }

  /* (non-Javadoc)
   * @see org.eclipse.scout.rt.client.ui.desktop.AbstractDesktopExtension#execOpened()
   */
  @Override
  protected ContributionCommand execOpened() throws ProcessingException {
    // TODO Auto-generated method stub
    IConfigurationService cfg = SERVICES.getService(IConfigurationService.class);
    if (cfg == null) throw new VetoException("cannot read configuration service");

    if (cfg.readBoolean(IConfigurationService.JF_FIRST_RUN, true)) {
      // install for first run
      logger.info("Prepare install scripts for first run");
      new SetupWizard().start();
    }

    return super.execOpened();
  }
}
