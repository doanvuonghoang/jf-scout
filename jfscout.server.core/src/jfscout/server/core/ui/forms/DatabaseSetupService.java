/**
 *
 */
package jfscout.server.core.ui.forms;

import java.sql.Connection;
import java.sql.DriverManager;

import jfscout.shared.core.services.IConfigurationService;
import jfscout.shared.core.services.IDatabaseService;
import jfscout.shared.core.ui.forms.DatabaseSetupFormData;
import jfscout.shared.core.ui.forms.IDatabaseSetupService;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.commons.exception.VetoException;
import org.eclipse.scout.service.AbstractService;
import org.eclipse.scout.service.SERVICES;

/**
 * @author Hoàng
 */
public class DatabaseSetupService extends AbstractService implements IDatabaseSetupService {

  @Override
  public DatabaseSetupFormData create(DatabaseSetupFormData formData) throws ProcessingException {
    return formData;
  }

  @Override
  public DatabaseSetupFormData load(DatabaseSetupFormData formData) throws ProcessingException {
    return formData;
  }

  @Override
  public DatabaseSetupFormData prepareCreate(DatabaseSetupFormData formData) throws ProcessingException {
    return formData;
  }

  @Override
  public DatabaseSetupFormData store(DatabaseSetupFormData formData) throws ProcessingException {
    // test connection
    testConnection(
        formData.getDatabaseDriver().getValue(),
        formData.getDatabaseUri().getValue(),
        formData.getDatabaseUser().getValue(),
        formData.getDatabasePassword().getValue());

    // save config
    IConfigurationService svc = SERVICES.getService(IConfigurationService.class);
    svc.writeString(IConfigurationService.DATABASE_DRIVER, formData.getDatabaseDriver().getValue());
    svc.writeString(IConfigurationService.DATABASE_URI, formData.getDatabaseUri().getValue());
    svc.writeString(IConfigurationService.DATABASE_USER, formData.getDatabaseUser().getValue());
    svc.writeString(IConfigurationService.DATABASE_PASSWORD, formData.getDatabasePassword().getValue());

    SERVICES.getService(IDatabaseService.class).refreshSource();

    return formData;
  }

  @Override
  public boolean testConnection(String driver, String uri, String user, String password) throws ProcessingException {
    try {
      Class.forName(driver);

      Connection conn = DriverManager.getConnection(uri, user, password);
      conn.close();

      return true;
    }
    catch (Exception e) {
      throw new VetoException(e.getMessage(), e);
    }
  }
}
