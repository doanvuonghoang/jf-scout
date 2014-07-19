/**
 *
 */
package com.jf.scout.shared.core.services;

import java.util.Locale;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.shared.validate.IValidationStrategy;
import org.eclipse.scout.rt.shared.validate.InputValidation;
import org.eclipse.scout.service.IService;

/**
 * @author Hoàng
 */
@InputValidation(IValidationStrategy.PROCESS.class)
public interface ICoreService extends IService {

  /**
   * @return
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  boolean isFirstRun() throws ProcessingException;

  /**
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  void makeNotFirstRun() throws ProcessingException;

  /**
   * @return
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  Locale[] getAvailableLocales() throws ProcessingException;

  void setLocale(Locale defaultLocale) throws ProcessingException;
}
