/**
 *
 */
package jfscout.client.administration.ui.forms;

import java.util.List;

import jfscout.client.administration.ui.forms.AssignToRoleForm.MainBox.CancelButton;
import jfscout.client.administration.ui.forms.AssignToRoleForm.MainBox.GroupBox;
import jfscout.client.administration.ui.forms.AssignToRoleForm.MainBox.GroupBox.RoleField;
import jfscout.client.administration.ui.forms.AssignToRoleForm.MainBox.OkButton;
import jfscout.shared.administration.services.lookup.RoleLookupCall;
import jfscout.shared.administration.ui.forms.AssignToRoleFormData;
import jfscout.shared.administration.ui.forms.IAssignToRoleService;

import org.eclipse.scout.commons.annotations.FormData;
import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCancelButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractOkButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.listbox.AbstractListBox;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.lookup.ILookupCall;
import org.eclipse.scout.service.SERVICES;

/**
 * @author Hoàng
 */
@FormData(value = AssignToRoleFormData.class, sdkCommand = FormData.SdkCommand.CREATE)
public class AssignToRoleForm extends AbstractForm {

  private Long m_assignToRoleNr;
  private List<String> m_permission;

  /**
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  public AssignToRoleForm() throws ProcessingException {
    super();
  }

  /**
   * @return the AssignToRoleNr
   */
  @FormData
  public Long getAssignToRoleNr() {
    return m_assignToRoleNr;
  }

  /**
   * @param assignToRoleNr
   *          the AssignToRoleNr to set
   */
  @FormData
  public void setAssignToRoleNr(Long assignToRoleNr) {
    m_assignToRoleNr = assignToRoleNr;
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("AssignToRole");
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
   * @return the OkButton
   */
  public OkButton getOkButton() {
    return getFieldByClass(OkButton.class);
  }

  /**
   * @return the RoleField
   */
  public RoleField getRoleField() {
    return getFieldByClass(RoleField.class);
  }

  @Order(10.0)
  public class MainBox extends AbstractGroupBox {

    @Order(10.0)
    public class GroupBox extends AbstractGroupBox {

      @Order(10.0)
      public class RoleField extends AbstractListBox<Long> {

        @Override
        protected int getConfiguredGridH() {
          return 5;
        }

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Role");
        }

        @Override
        protected Class<? extends ILookupCall<Long>> getConfiguredLookupCall() {
          return RoleLookupCall.class;
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

  public class NewHandler extends AbstractFormHandler {

    @Override
    protected void execLoad() throws ProcessingException {
      IAssignToRoleService service = SERVICES.getService(IAssignToRoleService.class);
      AssignToRoleFormData formData = new AssignToRoleFormData();
      exportFormData(formData);
      formData = service.prepareCreate(formData);
      importFormData(formData);

    }

    @Override
    protected void execStore() throws ProcessingException {
      IAssignToRoleService service = SERVICES.getService(IAssignToRoleService.class);
      AssignToRoleFormData formData = new AssignToRoleFormData();
      exportFormData(formData);
      formData = service.create(formData);

    }
  }

  /**
   * @return the Permission
   */
  @FormData
  public List<String> getPermission() {
    return m_permission;
  }

  /**
   * @param permission
   *          the Permission to set
   */
  @FormData
  public void setPermission(List<String> permission) {
    m_permission = permission;
  }
}
