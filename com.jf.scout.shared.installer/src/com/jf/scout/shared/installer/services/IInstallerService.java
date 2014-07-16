/**
 *
 */
package com.jf.scout.shared.installer.services;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.shared.validate.IValidationStrategy;
import org.eclipse.scout.rt.shared.validate.InputValidation;
import org.eclipse.scout.service.IService;

/**
 * @author Ho�ng
 */
@InputValidation(IValidationStrategy.PROCESS.class)
public interface IInstallerService extends IService {

  /**
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  void install() throws ProcessingException;
}
