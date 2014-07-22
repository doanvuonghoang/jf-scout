/**
 *
 */
package com.jf.scout.client.administration.ui.desktop.pages;

import java.util.ArrayList;
import java.util.Set;
import java.util.function.Consumer;

import org.eclipse.scout.commons.CollectionUtility;
import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.annotations.PageData;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.action.menu.IMenuType;
import org.eclipse.scout.rt.client.ui.action.menu.TableMenuType;
import org.eclipse.scout.rt.client.ui.basic.table.ITableRow;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractBooleanColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractDateTimeColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractLongColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractStringColumn;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.IPage;
import org.eclipse.scout.rt.client.ui.messagebox.MessageBox;
import org.eclipse.scout.rt.extension.client.ui.action.menu.AbstractExtensibleMenu;
import org.eclipse.scout.rt.extension.client.ui.basic.table.AbstractExtensibleTable;
import org.eclipse.scout.rt.extension.client.ui.desktop.outline.pages.AbstractExtensiblePageWithTable;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;
import org.eclipse.scout.service.SERVICES;

import com.jf.scout.client.administration.ui.desktop.forms.RoleForm;
import com.jf.scout.client.administration.ui.desktop.pages.RoleTablePage.Table;
import com.jf.scout.shared.administration.ui.desktop.forms.IRoleService;
import com.jf.scout.shared.administration.ui.desktop.pages.RoleTablePageData;

/**
 * @author Hoàng
 */
@PageData(RoleTablePageData.class)
public class RoleTablePage extends AbstractExtensiblePageWithTable<Table> {

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("Role");
  }

  @Override
  protected IPage execCreateChildPage(ITableRow row) throws ProcessingException {
    RoleDetailsNodePage childPage = new RoleDetailsNodePage();
    childPage.setRoleNr(getTable().getRoleIdColumn().getValue(row));
    return childPage;
  }

  @Override
  protected Object[][] execLoadTableData(SearchFilter filter) throws ProcessingException {
    return SERVICES.getService(IRoleService.class).getAllRoles();
  }

  @Order(10.0)
  public class Table extends AbstractExtensibleTable {

    /**
     * @return the RoleNameColumn
     */
    public RoleNameColumn getRoleNameColumn() {
      return getColumnSet().getColumnByClass(RoleNameColumn.class);
    }

    /**
     * @return the ValidColumn
     */
    public ValidColumn getValidColumn() {
      return getColumnSet().getColumnByClass(ValidColumn.class);
    }

    /**
     * @return the StatusColumn
     */
    public StatusColumn getStatusColumn() {
      return getColumnSet().getColumnByClass(StatusColumn.class);
    }

    /**
     * @return the LastModifiedTimeColumn
     */
    public LastModifiedTimeColumn getLastModifiedTimeColumn() {
      return getColumnSet().getColumnByClass(LastModifiedTimeColumn.class);
    }

    /**
     * @return the ValidChangeDateColumn
     */
    public ValidChangeDateColumn getValidChangeDateColumn() {
      return getColumnSet().getColumnByClass(ValidChangeDateColumn.class);
    }

    /**
     * @return the LastModifierColumn
     */
    public LastModifierColumn getLastModifierColumn() {
      return getColumnSet().getColumnByClass(RoleTablePage.Table.LastModifierColumn.class);
    }

    /**
     * @return the CreatedTimeColumn
     */
    public CreatedTimeColumn getCreatedTimeColumn() {
      return getColumnSet().getColumnByClass(RoleTablePage.Table.CreatedTimeColumn.class);
    }

    /**
     * @return the CreatorColumn
     */
    public CreatorColumn getCreatorColumn() {
      return getColumnSet().getColumnByClass(RoleTablePage.Table.CreatorColumn.class);
    }

    /**
     * @return the RoleIdColumn
     */
    public RoleIdColumn getRoleIdColumn() {
      return getColumnSet().getColumnByClass(RoleIdColumn.class);
    }

    @Order(10.0)
    public class RoleIdColumn extends AbstractLongColumn {

      @Override
      protected boolean getConfiguredDisplayable() {
        return false;
      }

      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("RoleId");
      }

      @Override
      protected boolean getConfiguredPrimaryKey() {
        return true;
      }

      @Override
      protected boolean getConfiguredVisible() {
        return false;
      }
    }

    @Order(20.0)
    public class RoleNameColumn extends AbstractStringColumn {

      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("RoleName");
      }

      @Override
      protected int getConfiguredWidth() {
        return 200;
      }
    }

    @Order(30.0)
    public class ValidColumn extends AbstractBooleanColumn {

      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("Valid");
      }
    }

    @Order(40.0)
    public class ValidChangeDateColumn extends AbstractDateTimeColumn {

      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("ValidChangeDate");
      }
    }

    @Order(50.0)
    public class StatusColumn extends AbstractStringColumn {

      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("Status");
      }
    }

    @Order(60.0)
    public class CreatedTimeColumn extends AbstractDateTimeColumn {

      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("CreatedTime");
      }
    }

    @Order(70.0)
    public class CreatorColumn extends AbstractStringColumn {

      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("Creator");
      }
    }

    @Order(80.0)
    public class LastModifiedTimeColumn extends AbstractDateTimeColumn {

      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("LastModifiedTime");
      }
    }

    @Order(90.0)
    public class LastModifierColumn extends AbstractStringColumn {

      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("LastModifier");
      }
    }

    @Order(10.0)
    public class CreateRoleMenu extends AbstractExtensibleMenu {

      @Override
      protected Set<? extends IMenuType> getConfiguredMenuTypes() {
        return CollectionUtility.<IMenuType> hashSet(TableMenuType.EmptySpace);
      }

      @Override
      protected String getConfiguredText() {
        return TEXTS.get("CreateRole");
      }

      @Override
      protected void execAction() throws ProcessingException {
        RoleForm form = new RoleForm();
        form.startNew();
        form.waitFor();
        if (form.isFormStored()) {
          reloadPage();
        }
      }
    }

    @Order(20.0)
    public class EditRoleMenu extends AbstractExtensibleMenu {

      @Override
      protected String getConfiguredText() {
        return TEXTS.get("EditRole");
      }

      @Override
      protected void execAction() throws ProcessingException {
        RoleForm form = new RoleForm();
        form.setRoleNr(getRoleIdColumn().getSelectedValue());
        form.startModify();
        form.waitFor();
        if (form.isFormStored()) {
          reloadPage();
        }
      }
    }

    @Order(30.0)
    public class DeleteRoleMenu extends AbstractExtensibleMenu {

      @Override
      protected String getConfiguredText() {
        return TEXTS.get("DeleteRole");
      }

      @Override
      protected void execAction() throws ProcessingException {
        // show message box
        int ans = MessageBox.showYesNoMessage(
            TEXTS.get("ApplicationTitle"),
            TEXTS.get("MsgBox.DeleteHeader"),
            TEXTS.get("MsgBox.DeleteMessage"));

        if (ans != MessageBox.YES_OPTION) return;

        // if yes go to delete
        ArrayList<Long> ids = new ArrayList<Long>();

        getSelectedRows().forEach(new Consumer<ITableRow>() {
          @Override
          public void accept(ITableRow t) {
            ids.add(getRoleIdColumn().getValue(t));
          }
        });

        SERVICES.getService(IRoleService.class).deleteRoles(ids.toArray(new Long[]{}));

        reloadPage();
      }
    }

    @Order(40.0)
    public class RestoreRoleMenu extends AbstractExtensibleMenu {

      @Override
      protected String getConfiguredText() {
        return TEXTS.get("RestoreRole");
      }

      /* (non-Javadoc)
       * @see org.eclipse.scout.rt.client.ui.action.AbstractAction#execAction()
       */
      @Override
      protected void execAction() throws ProcessingException {
        // show message box
        int ans = MessageBox.showYesNoMessage(
            TEXTS.get("ApplicationTitle"),
            TEXTS.get("MsgBox.RestoreRoleHeader"),
            TEXTS.get("MsgBox.RestoreRoleMessage"));

        if (ans != MessageBox.YES_OPTION) return;

        // if yes go to delete
        ArrayList<Long> ids = new ArrayList<Long>();

        getSelectedRows().forEach(new Consumer<ITableRow>() {
          @Override
          public void accept(ITableRow t) {
            ids.add(getRoleIdColumn().getValue(t));
          }
        });

        SERVICES.getService(IRoleService.class).restoreRoles(ids.toArray(new Long[]{}));

        reloadPage();
      }
    }

    @Order(50.0)
    public class SeparatorMenu extends AbstractExtensibleMenu {

      @Override
      protected boolean getConfiguredSeparator() {
        return true;
      }
    }

    @Order(60.0)
    public class DeleteRolePermantlyMenu extends AbstractExtensibleMenu {

      @Override
      protected String getConfiguredText() {
        return TEXTS.get("DeleteRolePermantly");
      }

      /* (non-Javadoc)
       * @see org.eclipse.scout.rt.client.ui.action.AbstractAction#execAction()
       */
      @Override
      protected void execAction() throws ProcessingException {
        // show message box
        int ans = MessageBox.showYesNoMessage(
            TEXTS.get("ApplicationTitle"),
            TEXTS.get("MsgBox.DeletePermantlyHeader"),
            TEXTS.get("MsgBox.DeletePermantlyMessage"));

        if (ans != MessageBox.YES_OPTION) return;

        // if yes go to delete
        ArrayList<Long> ids = new ArrayList<Long>();

        getSelectedRows().forEach(new Consumer<ITableRow>() {
          @Override
          public void accept(ITableRow t) {
            ids.add(getRoleIdColumn().getValue(t));
          }
        });

        SERVICES.getService(IRoleService.class).deleteRolesPermantly(ids.toArray(new Long[]{}));

        reloadPage();
      }
    }
  }
}
