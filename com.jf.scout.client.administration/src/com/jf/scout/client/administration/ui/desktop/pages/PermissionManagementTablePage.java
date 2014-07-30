/**
 *
 */
package com.jf.scout.client.administration.ui.desktop.pages;

import java.util.Set;

import org.eclipse.scout.commons.CollectionUtility;
import org.eclipse.scout.commons.annotations.FormData;
import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.annotations.PageData;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.action.menu.IMenuType;
import org.eclipse.scout.rt.client.ui.action.menu.TableMenuType;
import org.eclipse.scout.rt.client.ui.action.menu.TreeMenuType;
import org.eclipse.scout.rt.client.ui.action.menu.ValueFieldMenuType;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractStringColumn;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithTable;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.ISearchForm;
import org.eclipse.scout.rt.client.ui.messagebox.MessageBox;
import org.eclipse.scout.rt.extension.client.ui.action.menu.AbstractExtensibleMenu;
import org.eclipse.scout.rt.extension.client.ui.basic.table.AbstractExtensibleTable;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;
import org.eclipse.scout.service.SERVICES;

import com.jf.scout.client.administration.ui.desktop.forms.AssignToRoleForm;
import com.jf.scout.client.administration.ui.desktop.forms.PermissionManagementSearchForm;
import com.jf.scout.client.administration.ui.desktop.pages.PermissionManagementTablePage.Table;
import com.jf.scout.shared.administration.services.IExtensionService;
import com.jf.scout.shared.administration.ui.desktop.forms.IRoleService;
import com.jf.scout.shared.administration.ui.desktop.forms.PermissionManagementSearchFormData;
import com.jf.scout.shared.administration.ui.desktop.pages.PermissionManagementTablePageData;
import com.jf.scout.shared.core.Icons;

/**
 * @author Hoàng
 */
@PageData(PermissionManagementTablePageData.class)
public class PermissionManagementTablePage extends AbstractPageWithTable<Table> {

  private Long m_roleId;

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("PermissionManagement");
  }

  @Order(10.0)
  public class Table extends AbstractExtensibleTable {

    @Override
    protected String getConfiguredDefaultIconId() {
      return Icons.Permission;
    }

    /**
     * @return the DescriptionColumn
     */
    public DescriptionColumn getDescriptionColumn() {
      return getColumnSet().getColumnByClass(DescriptionColumn.class);
    }

    /**
     * @return the PermissionNameColumn
     */
    public PermissionNameColumn getPermissionNameColumn() {
      return getColumnSet().getColumnByClass(PermissionNameColumn.class);
    }

    @Order(10.0)
    public class PermissionNameColumn extends AbstractStringColumn {

      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("PermissionName");
      }
    }

    @Order(20.0)
    public class DescriptionColumn extends AbstractStringColumn {

      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("Description");
      }
    }

    @Order(10.0)
    public class AssignToRoleMenu extends AbstractExtensibleMenu {

      //      @Override
      //      protected Set<? extends IMenuType> getConfiguredMenuTypes() {
      //        return CollectionUtility.<IMenuType> hashSet(TableMenuType.EmptySpace);
      //      }

      @Override
      protected Set<? extends IMenuType> getConfiguredMenuTypes() {
        return CollectionUtility.<IMenuType> hashSet(TableMenuType.MultiSelection, TableMenuType.SingleSelection, ValueFieldMenuType.NotNull, TreeMenuType.MultiSelection, TreeMenuType.SingleSelection);
      }

      @Override
      protected String getConfiguredText() {
        return TEXTS.get("AssignToRole");
      }

      @Override
      protected void execAction() throws ProcessingException {
        AssignToRoleForm form = new AssignToRoleForm();
        form.setPermission(getPermissionNameColumn().getSelectedValues());
        form.startNew();
        form.waitFor();
        if (form.isFormStored()) {
          reloadPage();
        }
      }
    }

    @Order(20.0)
    public class RevokeMenu extends AbstractExtensibleMenu {

      @Override
      protected Set<? extends IMenuType> getConfiguredMenuTypes() {
        return CollectionUtility.<IMenuType> hashSet(TableMenuType.MultiSelection, TableMenuType.SingleSelection, ValueFieldMenuType.NotNull, TreeMenuType.MultiSelection, TreeMenuType.SingleSelection);
      }

      @Override
      protected String getConfiguredText() {
        return TEXTS.get("Revoke");
      }

      @Override
      protected void execAction() throws ProcessingException {
        // show message box
        int ans = MessageBox.showYesNoMessage(
            TEXTS.get("ApplicationTitle"),
            TEXTS.get("MsgBox.DeleteHeader"),
            TEXTS.get("MsgBox.DeleteMessage"));

        if (ans != MessageBox.YES_OPTION) return;

        SERVICES.getService(IRoleService.class).revokePermissionsOfRole(getRoleId(), getPermissionNameColumn().getSelectedValues());

        reloadPage();
      }

      @Override
      protected void execOwnerValueChanged(Object newOwnerValue) throws ProcessingException {
        if (getRoleId() == null) setVisible(false);
      }
    }
  }

  @Override
  protected Object[][] execLoadTableData(SearchFilter filter) throws ProcessingException {
    if (getRoleId() == null) {
      PermissionManagementSearchFormData formData = (PermissionManagementSearchFormData) filter.getFormData();
      String pfilter = "";
      if (formData != null) pfilter = formData.getPermissionName().getValue();

      return SERVICES.getService(IExtensionService.class).getPermissionTableData(pfilter);
    }
    else {
      return SERVICES.getService(IExtensionService.class).getPermissionTableData(getRoleId());
//      return null;
    }
  }

  /**
   * @return the RoleId
   */
  @FormData
  public Long getRoleId() {
    return m_roleId;
  }

  /**
   * @param roleId
   *          the RoleId to set
   */
  @FormData
  public void setRoleId(Long roleId) {
    m_roleId = roleId;
    setSearchActive(false);
  }

  @Override
  protected String getConfiguredIconId() {
    return Icons.PermissionManagement;
  }

  @Override
  protected Class<? extends ISearchForm> getConfiguredSearchForm() {
    return PermissionManagementSearchForm.class;
  }
}
