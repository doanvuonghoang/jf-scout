/**
 * 
 */
package com.jf.scout.client.administration.ui.desktop.forms;

import org.eclipse.scout.commons.annotations.FormData;
import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.booleanfield.AbstractBooleanField;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCancelButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractOkButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.service.SERVICES;

import com.jf.scout.client.administration.ui.desktop.forms.RoleForm.MainBox.CancelButton;
import com.jf.scout.client.administration.ui.desktop.forms.RoleForm.MainBox.OkButton;
import com.jf.scout.client.administration.ui.desktop.forms.RoleForm.MainBox.RoleInformationBox;
import com.jf.scout.client.administration.ui.desktop.forms.RoleForm.MainBox.RoleInformationBox.RoleNameField;
import com.jf.scout.client.administration.ui.desktop.forms.RoleForm.MainBox.RoleInformationBox.ValidField;
import com.jf.scout.shared.administration.ui.desktop.forms.IRoleService;
import com.jf.scout.shared.administration.ui.desktop.forms.RoleFormData;
import com.jf.scout.shared.administration.ui.desktop.forms.UpdateRolePermission;

/**
 * @author Hoàng
 */
@FormData(value = RoleFormData.class, sdkCommand = FormData.SdkCommand.CREATE)
public class RoleForm extends AbstractForm {

  private Long m_roleNr;

  /**
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  public RoleForm() throws ProcessingException {
    super();
  }

  /**
   * @return the RoleNr
   */
  @FormData
  public Long getRoleNr() {
    return m_roleNr;
  }

  /**
   * @param roleNr
   *          the RoleNr to set
   */
  @FormData
  public void setRoleNr(Long roleNr) {
    m_roleNr = roleNr;
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("Role");
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
   * @return the RoleInformationBox
   */
  public RoleInformationBox getRoleInformationBox(){
    return getFieldByClass(RoleInformationBox.class);
  }

  /**
   * @return the RoleNameField
   */
  public RoleNameField getRoleNameField(){
    return getFieldByClass(RoleNameField.class);
  }

  /**
   * @return the ValidField
   */
  public ValidField getValidField(){
    return getFieldByClass(ValidField.class);
  }

  @Order(10.0)
  public class MainBox extends AbstractGroupBox {

    @Order(10.0)
    public class RoleInformationBox extends AbstractGroupBox {

      @Override
      protected int getConfiguredGridColumnCount() {
        return 1;
      }

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("RoleInformation");
      }

      @Override
      protected boolean getConfiguredMandatory() {
        return true;
      }

      @Order(10.0)
      public class RoleNameField extends AbstractStringField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("RoleName");
        }
      }

      @Order(20.0)
      public class ValidField extends AbstractBooleanField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Valid");
        }
      }
    }

    @Order(20.0)
    public class OkButton extends AbstractOkButton {
    }

    @Order(30.0)
    public class CancelButton extends AbstractCancelButton {
    }
  }

  public class ModifyHandler extends AbstractFormHandler {

    @Override
    protected void execLoad() throws ProcessingException {
      IRoleService service = SERVICES.getService(IRoleService.class);
      RoleFormData formData = new RoleFormData();
      exportFormData(formData);
      formData = service.load(formData);
      importFormData(formData);
      setEnabledPermission(new UpdateRolePermission());

    }

    @Override
    protected void execStore() throws ProcessingException {
      IRoleService service = SERVICES.getService(IRoleService.class);
      RoleFormData formData = new RoleFormData();
      exportFormData(formData);
      formData = service.store(formData);

    }
  }

  public class NewHandler extends AbstractFormHandler {

    @Override
    protected void execLoad() throws ProcessingException {
      IRoleService service = SERVICES.getService(IRoleService.class);
      RoleFormData formData = new RoleFormData();
      exportFormData(formData);
      formData = service.prepareCreate(formData);
      importFormData(formData);

    }

    @Override
    protected void execStore() throws ProcessingException {
      IRoleService service = SERVICES.getService(IRoleService.class);
      RoleFormData formData = new RoleFormData();
      exportFormData(formData);
      formData = service.create(formData);

    }
  }
}
