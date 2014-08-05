/**
 *
 */
package jfscout.shared.core.services;

import org.apache.commons.configuration.Configuration;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.shared.validate.IValidationStrategy;
import org.eclipse.scout.rt.shared.validate.InputValidation;
import org.eclipse.scout.service.IService;

/**
 * @author Hoàng
 */
@InputValidation(IValidationStrategy.PROCESS.class)
public interface IConfigurationService extends IService {
  public static final String JF_SCOUT_HOME = "JF_SCOUT_HOME";
  public static final String JF_FIRST_RUN = "first_run";
  public static final String DATABASE_DRIVER = "application.database.driver";
  public static final String DATABASE_URI = "application.database.uri";
  public static final String DATABASE_USER = "application.database.user";
  public static final String DATABASE_PASSWORD = "application.database.password";

  /**
   * @param key
   * @return value of key, NULL if no key found
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  Object read(String key) throws ProcessingException;

  /**
   * @param key
   * @param cls
   * @return value of key, NULL if no key found
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  <T> T read(String key, Class<T> cls) throws ProcessingException;

  /**
   * @param key
   * @param val
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  void write(String key, Object val) throws ProcessingException;

  /**
   * @return
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  Configuration getConfiguration() throws ProcessingException;

  /**
   * @param key
   * @param _default
   * @return
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  boolean readBoolean(String key, boolean _default) throws ProcessingException;

  /**
   * @param key
   * @param val
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  void writeString(String key, String val) throws ProcessingException;

  /**
   * @param key
   * @param _default
   * @return
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  String readString(String key, String _default) throws ProcessingException;

  /**
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  void commit() throws ProcessingException;
}
