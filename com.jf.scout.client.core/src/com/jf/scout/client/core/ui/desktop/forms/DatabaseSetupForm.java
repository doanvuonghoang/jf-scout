/**
 *
 */
package com.jf.scout.client.core.ui.desktop.forms;

import org.eclipse.scout.commons.annotations.FormData;
import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.commons.exception.VetoException;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.IValidateContentDescriptor;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.service.SERVICES;

import com.jf.scout.client.core.ui.desktop.forms.DatabaseSetupForm.MainBox.DatabaseConfigurationBox;
import com.jf.scout.client.core.ui.desktop.forms.DatabaseSetupForm.MainBox.DatabaseConfigurationBox.DatabaseDriverField;
import com.jf.scout.client.core.ui.desktop.forms.DatabaseSetupForm.MainBox.DatabaseConfigurationBox.DatabasePasswordField;
import com.jf.scout.client.core.ui.desktop.forms.DatabaseSetupForm.MainBox.DatabaseConfigurationBox.DatabaseUriField;
import com.jf.scout.client.core.ui.desktop.forms.DatabaseSetupForm.MainBox.DatabaseConfigurationBox.DatabaseUserField;
import com.jf.scout.client.core.ui.desktop.forms.DatabaseSetupForm.MainBox.TestConnectionBox;
import com.jf.scout.client.core.ui.desktop.forms.DatabaseSetupForm.MainBox.TestConnectionBox.ConnectionStatusField;
import com.jf.scout.client.core.ui.desktop.forms.DatabaseSetupForm.MainBox.TestConnectionBox.TestConnectionButton;
import com.jf.scout.shared.core.services.IConfigurationService;
import com.jf.scout.shared.core.ui.desktop.forms.DatabaseSetupFormData;
import com.jf.scout.shared.core.ui.desktop.forms.IDatabaseSetupService;

/**
 * @author Hoàng
 */
@FormData(value = DatabaseSetupFormData.class, sdkCommand = FormData.SdkCommand.CREATE)
public class DatabaseSetupForm extends AbstractForm {

  private Long m_databaseSetupNr;

  /**
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  public DatabaseSetupForm() throws ProcessingException {
    super();
  }

  @Override
  protected boolean execCheckFields() throws ProcessingException {
    //TODO [Hoàng] Auto-generated method stub.
    IValidateContentDescriptor[] vcds = new IValidateContentDescriptor[]{
        getDatabaseDriverField().validateContent(),
        getDatabaseUriField().validateContent()
    };

    for (IValidateContentDescriptor vcd : vcds) {
      if (vcd != null) {
        vcd.activateProblemLocation();

        if (vcd.getErrorStatus() != null) throw new VetoException(vcd.getErrorStatus());
      }
    }

    return super.execCheckFields();
  }

  /**
   * @return the DatabaseSetupNr
   */
  @FormData
  public Long getDatabaseSetupNr() {
    return m_databaseSetupNr;
  }

  /**
   * @param databaseSetupNr
   *          the DatabaseSetupNr to set
   */
  @FormData
  public void setDatabaseSetupNr(Long databaseSetupNr) {
    m_databaseSetupNr = databaseSetupNr;
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("DatabaseSetup");
  }

  /**
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  public void startForm() throws ProcessingException {
    startInternal(new DatabaseSetupForm.FormHandler());
  }

  /**
   * @return the ConnectionStatusField
   */
  public ConnectionStatusField getConnectionStatusField() {
    return getFieldByClass(ConnectionStatusField.class);
  }

  /**
   * @return the DatabaseConfigurationBox
   */
  public DatabaseConfigurationBox getDatabaseConfigurationBox() {
    return getFieldByClass(DatabaseConfigurationBox.class);
  }

  /**
   * @return the DatabaseDriverField
   */
  public DatabaseDriverField getDatabaseDriverField() {
    return getFieldByClass(DatabaseDriverField.class);
  }

  /**
   * @return the DatabasePasswordField
   */
  public DatabasePasswordField getDatabasePasswordField() {
    return getFieldByClass(DatabasePasswordField.class);
  }

  /**
   * @return the DatabaseUriField
   */
  public DatabaseUriField getDatabaseUriField() {
    return getFieldByClass(DatabaseUriField.class);
  }

  /**
   * @return the DatabaseUserField
   */
  public DatabaseUserField getDatabaseUserField() {
    return getFieldByClass(DatabaseUserField.class);
  }

  /**
   * @return the MainBox
   */
  public MainBox getMainBox() {
    return getFieldByClass(MainBox.class);
  }

  /**
   * @return the TestConnectionBox
   */
  public TestConnectionBox getTestConnectionBox() {
    return getFieldByClass(TestConnectionBox.class);
  }

  /**
   * @return the TestConnectionButton
   */
  public TestConnectionButton getTestConnectionButton() {
    return getFieldByClass(TestConnectionButton.class);
  }

  @Order(10.0)
  public class MainBox extends AbstractGroupBox {

    @Order(10.0)
    public class DatabaseConfigurationBox extends AbstractGroupBox {

      @Override
      protected int getConfiguredGridColumnCount() {
        return 1;
      }

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("DatabaseConfiguration");
      }

      @Order(10.0)
      public class DatabaseDriverField extends AbstractStringField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("DatabaseDriver");
        }

        @Override
        protected boolean getConfiguredMandatory() {
          return true;
        }

        @Override
        protected String execValidateValue(String rawValue) throws ProcessingException {
          //TODO [Hoàng] Auto-generated method stub.
          if (rawValue == null || rawValue.isEmpty()) throw new VetoException(TEXTS.get("emptyString.error"));

          return super.execValidateValue(rawValue);
        }
      }

      @Order(20.0)
      public class DatabaseUriField extends AbstractStringField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("DatabaseUri");
        }

        @Override
        protected boolean getConfiguredMandatory() {
          return true;
        }

        @Override
        protected String execValidateValue(String rawValue) throws ProcessingException {
          //TODO [Hoàng] Auto-generated method stub.
          if (rawValue == null || rawValue.isEmpty()) throw new VetoException(TEXTS.get("emptyString.error"));

          return super.execValidateValue(rawValue);
        }
      }

      @Order(30.0)
      public class DatabaseUserField extends AbstractStringField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("DatabaseUser");
        }
      }

      @Order(40.0)
      public class DatabasePasswordField extends AbstractStringField {

        @Override
        protected boolean getConfiguredInputMasked() {
          return true;
        }

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("DatabasePassword");
        }
      }
    }

    @Order(20.0)
    public class TestConnectionBox extends AbstractGroupBox {

      @Override
      protected int getConfiguredGridColumnCount() {
        return 1;
      }

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("TestConnection");
      }

      @Order(10.0)
      public class ConnectionStatusField extends AbstractStringField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("ConnectionStatus");
        }
      }

      @Order(20.0)
      public class TestConnectionButton extends AbstractButton {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("TestConnection");
        }

        @Override
        protected void execClickAction() throws ProcessingException {
          //TODO [Hoàng] Auto-generated method stub.
          try {
            SERVICES.getService(IDatabaseSetupService.class).testConnection(
                getDatabaseDriverField().getValue(),
                getDatabaseUriField().getValue(),
                getDatabaseUserField().getValue(),
                getDatabasePasswordField().getValue());

            getConnectionStatusField().setValue("OK");
          }
          catch (Exception e) {
            getConnectionStatusField().setValue(e.getMessage());
          }
        }
      }
    }
  }

  /**
   * @author Hoàng
   */
  public class FormHandler extends AbstractFormHandler {

    @Override
    protected void execLoad() throws ProcessingException {
      //TODO [Hoàng] Auto-generated method stub.
      IConfigurationService cfg = SERVICES.getService(IConfigurationService.class);

      // read database information
      getDatabaseDriverField().setValue(cfg.readString(IConfigurationService.DATABASE_DRIVER, ""));
      getDatabaseUriField().setValue(cfg.readString(IConfigurationService.DATABASE_URI, ""));
      getDatabaseUserField().setValue(cfg.readString(IConfigurationService.DATABASE_USER, ""));

      super.execLoad();
    }

    @Override
    protected void execStore() throws ProcessingException {
      //TODO [Hoàng] Auto-generated method stub.
      DatabaseSetupFormData fd = new DatabaseSetupFormData();
      exportFormData(fd);
      SERVICES.getService(IDatabaseSetupService.class).store(fd);

      super.execStore();
    }
  }
}
