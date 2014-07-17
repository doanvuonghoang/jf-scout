/**
 *
 */
package com.jf.scout.server.installer.services;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.commons.exception.VetoException;
import org.eclipse.scout.service.AbstractService;
import org.eclipse.scout.service.SERVICES;

import com.jf.scout.server.core.services.ConfigurationService;
import com.jf.scout.shared.core.services.IConfigurationService;
import com.jf.scout.shared.installer.services.IInstallerService;

/**
 * @author Ho�ng
 */
public class InstallerService extends AbstractService implements IInstallerService {

  @Override
  public void install() throws ProcessingException {
    //TODO [Hoang] business logic here.
    IConfigurationService s = SERVICES.getService(IConfigurationService.class);
    if (s == null) {
      throw new VetoException("Cannot get configuration service");
    }

    boolean isFirstRun = s.getConfiguration().getBoolean(ConfigurationService.JF_FIRST_RUN, true);

    if (isFirstRun) {
      System.out.println("Install here");
      System.out.println(System.getProperty("user.dir"));
    }
  }
}
