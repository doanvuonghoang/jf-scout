/**
 * 
 */
package jfscout.shared.hrm.services;

import org.eclipse.scout.rt.shared.validate.IValidationStrategy;
import org.eclipse.scout.rt.shared.validate.InputValidation;
import org.eclipse.scout.service.IService;
import org.eclipse.scout.commons.exception.ProcessingException;

/**
 * @author Ho�ng
 */
@InputValidation(IValidationStrategy.PROCESS.class)
public interface IEmployeeService extends IService {

  /**
   * @return
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  Object[][] loadEmployeeTableData() throws ProcessingException;
}
