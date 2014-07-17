/**
 * 
 */
package com.jf.scout.shared.installer.ui.desktop.forms;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.shared.validate.IValidationStrategy;
import org.eclipse.scout.rt.shared.validate.InputValidation;
import org.eclipse.scout.service.IService;

/**
 * @author Hoàng
 */
@InputValidation(IValidationStrategy.PROCESS.class)
public interface Inull extends IService {

  /**
   * @param formData
   * @return
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  WelcomeFormData create(WelcomeFormData formData) throws ProcessingException;

  /**
   * @param formData
   * @return
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  WelcomeFormData load(WelcomeFormData formData) throws ProcessingException;

  /**
   * @param formData
   * @return
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  WelcomeFormData prepareCreate(WelcomeFormData formData) throws ProcessingException;

  /**
   * @param formData
   * @return
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  WelcomeFormData store(WelcomeFormData formData) throws ProcessingException;
}
