/**
 * 
 */
package com.jf.scout.shared.administration.ui.desktop.forms;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Generated;

import org.eclipse.scout.rt.shared.data.form.AbstractFormData;
import org.eclipse.scout.rt.shared.data.form.ValidationRule;
import org.eclipse.scout.rt.shared.data.form.fields.AbstractValueFieldData;
import org.eclipse.scout.rt.shared.data.form.properties.AbstractPropertyData;

import com.jf.scout.shared.administration.lookup.RoleLookupCall;

/**
 * <b>NOTE:</b><br>
 * This class is auto generated by the Scout SDK. No manual modifications recommended.
 * 
 * @generated
 */
@Generated(value = "org.eclipse.scout.sdk.workspace.dto.formdata.FormDataDtoUpdateOperation", comments = "This class is auto generated by the Scout SDK. No manual modifications recommended.")
public class AssignToRoleFormData extends AbstractFormData {

  private static final long serialVersionUID = 1L;

  public AssignToRoleFormData() {
  }

  /**
   * access method for property AssignToRoleNr.
   */
  public Long getAssignToRoleNr() {
    return getAssignToRoleNrProperty().getValue();
  }

  /**
   * access method for property AssignToRoleNr.
   */
  public void setAssignToRoleNr(Long assignToRoleNr) {
    getAssignToRoleNrProperty().setValue(assignToRoleNr);
  }

  public AssignToRoleNrProperty getAssignToRoleNrProperty() {
    return getPropertyByClass(AssignToRoleNrProperty.class);
  }

  /**
   * access method for property Permission.
   */
  public List<String> getPermission() {
    return getPermissionProperty().getValue();
  }

  /**
   * access method for property Permission.
   */
  public void setPermission(List<String> permission) {
    getPermissionProperty().setValue(permission);
  }

  public PermissionProperty getPermissionProperty() {
    return getPropertyByClass(PermissionProperty.class);
  }

  public Role getRole() {
    return getFieldByClass(Role.class);
  }

  public static class AssignToRoleNrProperty extends AbstractPropertyData<Long> {

    private static final long serialVersionUID = 1L;

    public AssignToRoleNrProperty() {
    }
  }

  public static class PermissionProperty extends AbstractPropertyData<List<String>> {

    private static final long serialVersionUID = 1L;

    public PermissionProperty() {
    }
  }

  public static class Role extends AbstractValueFieldData<Set<Long>> {

    private static final long serialVersionUID = 1L;

    public Role() {
    }

    /**
     * list of derived validation rules.
     */
    @Override
    protected void initValidationRules(Map<String, Object> ruleMap) {
      super.initValidationRules(ruleMap);
      ruleMap.put(ValidationRule.LOOKUP_CALL, RoleLookupCall.class);
    }
  }
}
