/**
 *
 */
package jfscout.server.core.services;

import java.util.Locale;

import jfscout.shared.core.services.IConfigurationService;
import jfscout.shared.core.services.ICoreService;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.commons.logger.IScoutLogger;
import org.eclipse.scout.commons.logger.ScoutLogManager;
import org.eclipse.scout.service.AbstractService;
import org.eclipse.scout.service.SERVICES;

/**
 * @author Hoàng
 */
public class CoreService extends AbstractService implements ICoreService {
  private IScoutLogger logger = ScoutLogManager.getLogger(getClass());

  @Override
  public boolean isFirstRun() throws ProcessingException {
    return SERVICES.getService(IConfigurationService.class).readBoolean(IConfigurationService.JF_FIRST_RUN, true);
  }

  @Override
  public void makeNotFirstRun() throws ProcessingException {
    SERVICES.getService(IConfigurationService.class).write(IConfigurationService.JF_FIRST_RUN, false);
    SERVICES.getService(IConfigurationService.class).commit();
  }

  @Override
  public Locale[] getAvailableLocales() throws ProcessingException {
    return Locale.getAvailableLocales();
  }

  /* (non-Javadoc)
   * @see com.jf.scout.shared.core.services.ICoreService#setLocale(java.util.Locale)
   */
  @Override
  public void setLocale(Locale defaultLocale) throws ProcessingException {
    Locale.setDefault(defaultLocale);
  }
}
