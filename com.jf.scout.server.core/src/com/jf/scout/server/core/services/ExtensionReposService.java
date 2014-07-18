/**
 *
 */
package com.jf.scout.server.core.services;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.commons.logger.ScoutLogManager;
import org.eclipse.scout.service.AbstractService;
import org.osgi.framework.ServiceRegistration;

import com.jf.scout.commons.IInstallable;
import com.jf.scout.shared.core.services.IExtensionReposService;

/**
 * @author Hoàng
 */
public class ExtensionReposService extends AbstractService implements IExtensionReposService, IInstallable {

  /* (non-Javadoc)
   * @see org.eclipse.scout.service.AbstractService#initializeService(org.osgi.framework.ServiceRegistration)
   */
  @Override
  public void initializeService(ServiceRegistration registration) {
    // TODO Auto-generated method stub
    super.initializeService(registration);

    // install service it-self
    try {
      if (!isInstalled(getClass().getName())) install(this);
    }
    catch (Exception e) {
      ScoutLogManager.getLogger(getClass()).info(e.getMessage(), e);
    }
  }

  @Override
  public boolean isInstalled(String extName) throws ProcessingException {
    //TODO [Hoàng] business logic here.

    return true;
  }

  @Override
  public void install(IInstallable ext) throws ProcessingException {
    //TODO [Hoàng] business logic here.
    ext.doInstall();
  }

  /* (non-Javadoc)
   * @see com.jf.scout.commons.IInstallable#doInstall()
   */
  @Override
  public void doInstall() {
    // TODO Auto-generated method stub

  }
}
