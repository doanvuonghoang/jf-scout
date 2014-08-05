/**
 *
 */
package jfscout.shared.administration.services;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.shared.validate.IValidationStrategy;
import org.eclipse.scout.rt.shared.validate.InputValidation;
import org.eclipse.scout.service.IService;

/**
 * @author Hoàng
 */
@InputValidation(IValidationStrategy.PROCESS.class)
public interface IExtensionService extends IService {
  /**
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  void installDB() throws ProcessingException;

  /**
   * @param roleId
   * @return
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  Object[][] getPermissionTableData(Long roleId) throws ProcessingException;

  /**
   * @return
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  Object[][] getPermissionTableData(String pfilter) throws ProcessingException;
}
