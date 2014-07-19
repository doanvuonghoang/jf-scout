/**
 *
 */
package com.jf.scout.shared.core.services;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.shared.validate.IValidationStrategy;
import org.eclipse.scout.rt.shared.validate.InputValidation;
import org.eclipse.scout.service.IService;

import com.j256.ormlite.dao.Dao;

/**
 * @author Hoàng
 */
@InputValidation(IValidationStrategy.PROCESS.class)
public interface IDatabaseService extends IService {

  /**
   * @param cls
   * @return
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  <U, V> Dao<U, V> createDao(Class<U> cls) throws ProcessingException;

  /**
   * @return
   * @throws ProcessingException
   */
  public String getDatabaseUri() throws ProcessingException;

  /**
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  void refreshSource() throws ProcessingException;
}