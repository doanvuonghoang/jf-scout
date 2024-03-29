/**
 *
 */
package jfscout.server.core.services;

import java.io.File;

import jfscout.shared.core.services.IConfigurationService;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.commons.exception.VetoException;
import org.eclipse.scout.commons.logger.IScoutLogger;
import org.eclipse.scout.commons.logger.ScoutLogManager;
import org.eclipse.scout.rt.server.services.common.file.RemoteFileService;
import org.eclipse.scout.service.AbstractService;
import org.eclipse.scout.service.SERVICES;
import org.osgi.framework.ServiceRegistration;

/**
 * @author Ho�ng
 */
public class ConfigurationService extends AbstractService implements IConfigurationService {
  private IScoutLogger logger = ScoutLogManager.getLogger(getClass());
  private Configuration cfg;

  /* (non-Javadoc)
   * @see org.eclipse.scout.service.AbstractService#initializeService(org.osgi.framework.ServiceRegistration)
   */
  @Override
  public void initializeService(ServiceRegistration registration) {
    super.initializeService(registration);

    _initConfiguration();
  }

  /* (non-Javadoc)
   * @see org.eclipse.scout.service.AbstractService#disposeServices()
   */
  @Override
  public void disposeServices() {
    try {
      commit();
    }
    catch (ProcessingException e) {
      logger.info(e.getMessage(), e);
    }

    super.disposeServices();
  }

  /**
   *
   */
  private void _initConfiguration() {
    File f = new File(_getConfigFile());

    if (f.exists()) {
      try {
        cfg = new XMLConfiguration(f);
      }
      catch (ConfigurationException ex) {
        logger.info(ex.getMessage(), ex);
      }
    }
    else {
      cfg = new XMLConfiguration();
//      try {
//        f.createNewFile();
//      }
//      catch (IOException e) {
//        //        logger.info(e.getMessage(), e);
//      }
      ((XMLConfiguration) cfg).setFileName(_getConfigFile());
    }
  }

  /**
   * Get properties file in string
   *
   * @return properties file in string
   */
  protected String _getConfigFile() {
    return _getRootPath() + File.separator + "config.xml";
  }

  /**
   * Get root path.
   *
   * @return String
   */
  protected String _getRootPath() {
    return SERVICES.getService(RemoteFileService.class).getRootPath();
  }

  @Override
  public Object read(String key) throws ProcessingException {
    return cfg.getProperty(key);
  }

  @Override
  public <T> T read(String key, Class<T> cls) throws ProcessingException {
    return cls.cast(read(key));
  }

  @Override
  public void write(String key, Object val) throws ProcessingException {
    cfg.setProperty(key, val);
  }

  @Override
  public Configuration getConfiguration() throws ProcessingException {
    if (cfg == null) _initConfiguration();

    return cfg;
  }

  @Override
  public boolean readBoolean(String key, boolean _default) throws ProcessingException {
    return cfg.getBoolean(key, _default);
  }

  @Override
  public void writeString(String key, String val) throws ProcessingException {
    cfg.setProperty(key, val);
  }

  @Override
  public String readString(String key, String _default) throws ProcessingException {
    return cfg.getString(key, _default);
  }

  @Override
  public void commit() throws ProcessingException {
    logger.info("Storing config");
    try {
      ((XMLConfiguration) cfg).save();
    }
    catch (ConfigurationException e) {
      throw new VetoException(e.getMessage(), e);
    }
  }
}
