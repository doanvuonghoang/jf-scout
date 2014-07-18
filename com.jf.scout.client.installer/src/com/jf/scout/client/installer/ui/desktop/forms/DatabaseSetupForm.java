/**
 *
 */
package com.jf.scout.client.installer.ui.desktop.forms;

import org.eclipse.scout.commons.annotations.FormData;
import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.commons.exception.VetoException;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.IValidateContentDescriptor;
import org.eclipse.scout.rt.client.ui.form.fields.IValueField;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.labelfield.AbstractLabelField;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.service.SERVICES;

import com.jf.scout.client.installer.ui.desktop.forms.DatabaseSetupForm.MainBox.GroupBox;
import com.jf.scout.client.installer.ui.desktop.forms.DatabaseSetupForm.MainBox.GroupBox.DatabaseDriverField;
import com.jf.scout.client.installer.ui.desktop.forms.DatabaseSetupForm.MainBox.GroupBox.DatabasePasswordField;
import com.jf.scout.client.installer.ui.desktop.forms.DatabaseSetupForm.MainBox.GroupBox.DatabaseUriField;
import com.jf.scout.client.installer.ui.desktop.forms.DatabaseSetupForm.MainBox.GroupBox.DatabaseUserField;
import com.jf.scout.client.installer.ui.desktop.forms.DatabaseSetupForm.MainBox.TestDatabaseConnectionBox;
import com.jf.scout.client.installer.ui.desktop.forms.DatabaseSetupForm.MainBox.TestDatabaseConnectionBox.ConnectionStatusField;
import com.jf.scout.client.installer.ui.desktop.forms.DatabaseSetupForm.MainBox.TestDatabaseConnectionBox.TryToConnectionButton;
import com.jf.scout.shared.installer.services.IInstallerService;
import com.jf.scout.shared.installer.ui.desktop.forms.DatabaseSetupFormData;

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
  public void startEmpty() throws ProcessingException {
    startInternal(new DatabaseSetupForm.EmptyHandler());
  }

  /**
   * @return the ConnectionStatusField
   */
  public ConnectionStatusField getConnectionStatusField() {
    return getFieldByClass(ConnectionStatusField.class);
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
   * @return the GroupBox
   */
  public GroupBox getGroupBox() {
    return getFieldByClass(GroupBox.class);
  }

  /**
   * @return the MainBox
   */
  public MainBox getMainBox() {
    return getFieldByClass(MainBox.class);
  }

  /**
   * @return the TestDatabaseConnectionBox
   */
  public TestDatabaseConnectionBox getTestDatabaseConnectionBox() {
    return getFieldByClass(TestDatabaseConnectionBox.class);
  }

  /**
   * @return the TryToConnectionButton
   */
  public TryToConnectionButton getTryToConnectionButton() {
    return getFieldByClass(TryToConnectionButton.class);
  }

  @Order(10.0)
  public class MainBox extends AbstractGroupBox {

    @Override
    protected boolean getConfiguredFillHorizontal() {
      return false;
    }

    @Override
    protected boolean getConfiguredFillVertical() {
      return false;
    }

    @Override
    protected int getConfiguredGridColumnCount() {
      return 1;
    }

    @Override
    protected boolean getConfiguredGridUseUiHeight() {
      return false;
    }

    @Override
    protected boolean getConfiguredMandatory() {
      return true;
    }

    @Order(10.0)
    public class GroupBox extends AbstractGroupBox {

      @Override
      protected boolean getConfiguredFillVertical() {
        return false;
      }

      @Override
      protected int getConfiguredGridColumnCount() {
        return 1;
      }

      @Override
      protected int getConfiguredHeightInPixel() {
        return 400;
      }

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("DatabaseInformation");
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
        protected String getConfiguredValueFormat() {
          return null;
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
    public class TestDatabaseConnectionBox extends AbstractGroupBox {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("TestDatabaseConnection");
      }

      @Override
      protected Class<? extends IValueField> getConfiguredMasterField() {
        return DatabaseSetupForm.MainBox.GroupBox.DatabaseUriField.class;
      }

      @Override
      protected boolean getConfiguredMasterRequired() {
        return true;
      }

      @Order(10.0)
      public class ConnectionStatusField extends AbstractLabelField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("ConnectionStatus");
        }
      }

      @Order(20.0)
      public class TryToConnectionButton extends AbstractButton {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("TryToConnection");
        }

      }
    }
  }

  /**
   * @author Hoàng
   */
  public class EmptyHandler extends AbstractFormHandler {

    @Override
    protected void execStore() throws ProcessingException {
      //TODO [Hoàng] Auto-generated method stub.
      SERVICES.getService(IInstallerService.class).testConnection(
          getDatabaseDriverField().getValue(),
          getDatabaseUriField().getValue(),
          getDatabaseUserField().getValue(),
          getDatabasePasswordField().getValue());

      super.execStore();
    }
  }

}
