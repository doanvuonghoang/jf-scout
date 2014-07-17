/**
 *
 */
package com.jf.scout.server.core.services;

import java.io.File;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.commons.logger.ScoutLogManager;
import org.eclipse.scout.rt.server.services.common.file.RemoteFileService;
import org.eclipse.scout.service.AbstractService;
import org.eclipse.scout.service.SERVICES;
import org.osgi.framework.ServiceRegistration;

import com.jf.scout.shared.core.services.IConfigurationService;

/**
 * @author Ho�ng
 */
public class ConfigurationService extends AbstractService implements IConfigurationService {

  private Configuration cfg;

  /* (non-Javadoc)
   * @see org.eclipse.scout.service.AbstractService#initializeService(org.osgi.framework.ServiceRegistration)
   */
  @Override
  public void initializeService(ServiceRegistration registration) {
    // TODO Auto-generated method stub
    super.initializeService(registration);

    _initConfiguration();
  }

  /* (non-Javadoc)
   * @see org.eclipse.scout.service.AbstractService#disposeServices()
   */
  @Override
  public void disposeServices() {
    // TODO Auto-generated method stub
    try {
      ((XMLConfiguration) cfg).save();
    }
    catch (ConfigurationException e) {
      // TODO Auto-generated catch block
      ScoutLogManager.getLogger(getClass()).info(e.getMessage());
    }

    super.disposeServices();
  }

  /**
   *
   */
  private void _initConfiguration() {
    // TODO Auto-generated method stub
    File f = new File(_getConfigFile());
    if (f.exists()) {
      try {
        cfg = new XMLConfiguration(f);
      }
      catch (ConfigurationException ex) {
        ScoutLogManager.getLogger(getClass()).info(ex.getMessage(), ex);
      }
    }
    else {
      cfg = new XMLConfiguration();
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
    //TODO [Hoang] business logic here.
    return cfg.getProperty(key);
  }

  @Override
  public <T> T read(String key, Class<T> cls) throws ProcessingException {
    //TODO [Hoang] business logic here.
    return cls.cast(read(key));
  }

  @Override
  public void write(String key, Object val) throws ProcessingException {
    //TODO [Hoang] business logic here.
    cfg.setProperty(key, val);
  }

  @Override
  public Configuration getConfiguration() throws ProcessingException {
    //TODO [Hoàng] business logic here.
    return cfg;
  }

  @Override
  public boolean readBoolean(String key, boolean _default) throws ProcessingException {
    //TODO [Hoàng] business logic here.
    return cfg.getBoolean(key, _default);
  }

  @Override
  public void writeString(String key, String val) throws ProcessingException {
    //TODO [Hoàng] business logic here.
    cfg.setProperty(key, val);
  }

  @Override
  public String readString(String key, String _default) throws ProcessingException {
    //TODO [Hoàng] business logic here.
    return null;
  }
}
