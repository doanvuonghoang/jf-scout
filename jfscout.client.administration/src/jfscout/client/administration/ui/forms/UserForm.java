/**
 *
 */
package jfscout.client.administration.ui.forms;

import jfscout.client.administration.ui.forms.UserForm.MainBox.CancelButton;
import jfscout.client.administration.ui.forms.UserForm.MainBox.OkButton;
import jfscout.client.administration.ui.forms.UserForm.MainBox.UserBasicInformationBox;
import jfscout.client.administration.ui.forms.UserForm.MainBox.UserBasicInformationBox.RolesField;
import jfscout.client.administration.ui.forms.UserForm.MainBox.UserBasicInformationBox.UserNameField;
import jfscout.client.administration.ui.forms.UserForm.MainBox.UserBasicInformationBox.UserPasswordField;
import jfscout.client.administration.ui.forms.UserForm.MainBox.UserBasicInformationBox.ValidField;
import jfscout.shared.administration.services.lookup.RoleLookupCall;
import jfscout.shared.administration.ui.forms.IUserService;
import jfscout.shared.administration.ui.forms.UpdateUserPermission;
import jfscout.shared.administration.ui.forms.UserFormData;
import jfscout.shared.core.Icons;

import org.eclipse.scout.commons.annotations.FormData;
import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.IValueField;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCancelButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractOkButton;
import org.eclipse.scout.rt.client.ui.form.fields.checkbox.AbstractCheckBox;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.listbox.AbstractListBox;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.lookup.ILookupCall;
import org.eclipse.scout.service.SERVICES;

/**
 * @author Hoàng
 */
@FormData(value = UserFormData.class, sdkCommand = FormData.SdkCommand.CREATE)
public class UserForm extends AbstractForm {

  private Long m_userNr;

  /**
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  public UserForm() throws ProcessingException {
    super();
  }

  /**
   * @return the UserNr
   */
  @FormData
  public Long getUserNr() {
    return m_userNr;
  }

  /**
   * @param userNr
   *          the UserNr to set
   */
  @FormData
  public void setUserNr(Long userNr) {
    m_userNr = userNr;
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("UserManagment");
  }

  /**
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  public void startModify() throws ProcessingException {
    startInternal(new ModifyHandler());
  }

  /**
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  public void startNew() throws ProcessingException {
    startInternal(new NewHandler());
  }

  /**
   * @return the CancelButton
   */
  public CancelButton getCancelButton() {
    return getFieldByClass(CancelButton.class);
  }

  /**
   * @return the MainBox
   */
  public MainBox getMainBox() {
    return getFieldByClass(MainBox.class);
  }

  /**
   * @return the OkButton
   */
  public OkButton getOkButton() {
    return getFieldByClass(OkButton.class);
  }

  /**
   * @return the RolesField
   */
  public RolesField getRolesField() {
    return getFieldByClass(RolesField.class);
  }

  /**
   * @return the UserBasicInformationBox
   */
  public UserBasicInformationBox getUserBasicInformationBox() {
    return getFieldByClass(UserBasicInformationBox.class);
  }

  /**
   * @return the UserNameField
   */
  public UserNameField getUserNameField() {
    return getFieldByClass(UserNameField.class);
  }

  /**
   * @return the UserPasswordField
   */
  public UserPasswordField getUserPasswordField() {
    return getFieldByClass(UserPasswordField.class);
  }

  /**
   * @return the ValidField
   */
  public ValidField getValidField() {
    return getFieldByClass(ValidField.class);
  }

  @Order(10.0)
  public class MainBox extends AbstractGroupBox {

    @Order(10.0)
    public class UserBasicInformationBox extends AbstractGroupBox {

      @Override
      protected int getConfiguredGridColumnCount() {
        return 1;
      }

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("UserBasicInformation");
      }

      @Order(10.0)
      public class UserNameField extends AbstractStringField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("UserName");
        }

        @Override
        protected boolean getConfiguredMandatory() {
          return true;
        }
      }

      @Order(20.0)
      public class UserPasswordField extends AbstractStringField {

        @Override
        protected boolean getConfiguredInputMasked() {
          return true;
        }

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("UserPassword");
        }

        @Override
        protected boolean getConfiguredMandatory() {
          return true;
        }
      }

      @Order(30.0)
      public class ValidField extends AbstractCheckBox {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Valid");
        }
      }

      @Order(40.0)
      public class RolesField extends AbstractListBox<Long> {

        @Override
        protected int getConfiguredGridH() {
          return 5;
        }

        @Override
        protected int getConfiguredGridY() {
          return 1;
        }

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Roles");
        }

        @Override
        protected Class<? extends ILookupCall<Long>> getConfiguredLookupCall() {
          return RoleLookupCall.class;
        }

        @Override
        protected Class<? extends IValueField> getConfiguredMasterField() {
          return UserForm.MainBox.UserBasicInformationBox.ValidField.class;
        }

        @Override
        protected boolean getConfiguredMasterRequired() {
          return true;
        }
      }
    }

    @Order(20.0)
    public class OkButton extends AbstractOkButton {

      @Override
      protected String getConfiguredIconId() {
        return Icons.UserAdd;
      }
    }

    @Order(30.0)
    public class CancelButton extends AbstractCancelButton {
    }
  }

  public class ModifyHandler extends AbstractFormHandler {

    @Override
    protected void execLoad() throws ProcessingException {
      IUserService service = SERVICES.getService(IUserService.class);
      UserFormData formData = new UserFormData();
      exportFormData(formData);
      formData = service.load(formData);
      importFormData(formData);
      setEnabledPermission(new UpdateUserPermission());
      getUserNameField().setEnabled(false);

    }

    @Override
    protected void execStore() throws ProcessingException {
      IUserService service = SERVICES.getService(IUserService.class);
      UserFormData formData = new UserFormData();
      exportFormData(formData);
      formData = service.store(formData);
      getUserNameField().setEnabled(true);

    }
  }

  public class NewHandler extends AbstractFormHandler {

    @Override
    protected void execLoad() throws ProcessingException {
      IUserService service = SERVICES.getService(IUserService.class);
      UserFormData formData = new UserFormData();
      exportFormData(formData);
      formData = service.prepareCreate(formData);
      importFormData(formData);

    }

    @Override
    protected void execStore() throws ProcessingException {
      IUserService service = SERVICES.getService(IUserService.class);
      UserFormData formData = new UserFormData();
      exportFormData(formData);
      formData = service.create(formData);

    }
  }
}
