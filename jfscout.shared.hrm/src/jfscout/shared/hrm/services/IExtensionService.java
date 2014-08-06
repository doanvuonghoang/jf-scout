/**
 * 
 */
package jfscout.shared.hrm.services;

import org.eclipse.scout.rt.shared.validate.IValidationStrategy;
import org.eclipse.scout.rt.shared.validate.InputValidation;
import org.eclipse.scout.service.IService;
import org.eclipse.scout.commons.exception.ProcessingException;

/**
 * @author Hoàng
 */
@InputValidation(IValidationStrategy.PROCESS.class)
public interface IExtensionService extends IService {

  /**
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  void installDB() throws ProcessingException;
}
