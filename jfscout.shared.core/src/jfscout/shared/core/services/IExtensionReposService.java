/**
 *
 */
package jfscout.shared.core.services;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.shared.validate.IValidationStrategy;
import org.eclipse.scout.rt.shared.validate.InputValidation;
import org.eclipse.scout.service.IService;

import com.jf.commons.datamodels.Extension;

/**
 * @author Hoàng
 */
@InputValidation(IValidationStrategy.PROCESS.class)
public interface IExtensionReposService extends IService {
  /**
   * @param extName
   * @return
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  boolean isInstalled(String extName) throws ProcessingException;

  /**
   * @param ext
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  void install(Extension ext) throws ProcessingException;

  /**
   * @param extName
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  void uninstall(String extName) throws ProcessingException;
}
