/**
 *
 */
package com.jf.scout.shared.core.services;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.shared.validate.IValidationStrategy;
import org.eclipse.scout.rt.shared.validate.InputValidation;
import org.eclipse.scout.service.IService;

/**
 * @author Hoàng
 */
@InputValidation(IValidationStrategy.PROCESS.class)
public interface IHelperService extends IService {

  /**
   * @param driver
   * @param uri
   * @param user
   * @param password
   * @return
   * @throws ProcessingException
   */
  String testConnection(String driver, String uri, String user, String password) throws ProcessingException;
}
