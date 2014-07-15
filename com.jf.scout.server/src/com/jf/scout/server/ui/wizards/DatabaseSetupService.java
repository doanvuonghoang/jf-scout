/**
 * 
 */
package com.jf.scout.server.ui.wizards;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.service.AbstractService;

import com.jf.scout.shared.ui.wizards.DatabaseSetupFormData;
import com.jf.scout.shared.ui.wizards.IDatabaseSetupService;

/**
 * @author Hoàng
 */
public class DatabaseSetupService extends AbstractService implements IDatabaseSetupService {

  @Override
  public DatabaseSetupFormData create(DatabaseSetupFormData formData) throws ProcessingException {
    //TODO [Hoàng] business logic here.
    return formData;
  }

  @Override
  public DatabaseSetupFormData load(DatabaseSetupFormData formData) throws ProcessingException {
    //TODO [Hoàng] business logic here.
    return formData;
  }

  @Override
  public DatabaseSetupFormData prepareCreate(DatabaseSetupFormData formData) throws ProcessingException {
    //TODO [Hoàng] business logic here.
    return formData;
  }

  @Override
  public DatabaseSetupFormData store(DatabaseSetupFormData formData) throws ProcessingException {
    //TODO [Hoàng] business logic here.
    return formData;
  }
}
