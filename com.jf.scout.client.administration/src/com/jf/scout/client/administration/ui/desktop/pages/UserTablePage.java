/**
 *
 */
package com.jf.scout.client.administration.ui.desktop.pages;

import java.util.ArrayList;
import java.util.Set;
import java.util.function.Consumer;

import org.eclipse.scout.commons.CollectionUtility;
import org.eclipse.scout.commons.annotations.FormData;
import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.annotations.PageData;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.action.menu.IMenuType;
import org.eclipse.scout.rt.client.ui.action.menu.TableMenuType;
import org.eclipse.scout.rt.client.ui.action.menu.TreeMenuType;
import org.eclipse.scout.rt.client.ui.action.menu.ValueFieldMenuType;
import org.eclipse.scout.rt.client.ui.basic.table.ITableRow;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractBooleanColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractDateTimeColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractLongColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractStringColumn;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithTable;
import org.eclipse.scout.rt.client.ui.messagebox.MessageBox;
import org.eclipse.scout.rt.extension.client.ui.action.menu.AbstractExtensibleMenu;
import org.eclipse.scout.rt.extension.client.ui.basic.table.AbstractExtensibleTable;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;
import org.eclipse.scout.service.SERVICES;

import com.jf.commons.datamodels.RecordStatus;
import com.jf.scout.client.administration.ui.desktop.forms.UserForm;
import com.jf.scout.client.administration.ui.desktop.pages.UserTablePage.Table;
import com.jf.scout.shared.administration.ui.desktop.forms.IUserService;
import com.jf.scout.shared.administration.ui.desktop.pages.UserTablePageData;
import com.jf.scout.shared.core.Icons;

/**
 * @author Hoàng
 */
@PageData(UserTablePageData.class)
public class UserTablePage extends AbstractPageWithTable<Table> {

  private Long m_roleNr;

  @Override
  protected String getConfiguredIconId() {
    return Icons.UserManagement;
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("User");
  }

  @Override
  protected void execInitPage() throws ProcessingException {
    
    super.execInitPage();
  }

  @Override
  protected Object[][] execLoadTableData(SearchFilter filter) throws ProcessingException {
    // get data from service
    if (getRoleNr() != null) {
      return SERVICES.getService(IUserService.class).getAllUsers(getRoleNr().longValue());
    }
    else return SERVICES.getService(IUserService.class).getAllUsers();
  }

  @Order(10.0)
  public class Table extends AbstractExtensibleTable {

    /**
     * @return the ValidColumn
     */
    public ValidColumn getValidColumn() {
      return getColumnSet().getColumnByClass(ValidColumn.class);
    }

    /**
     * @return the UserIdColumn
     */
    public UserIdColumn getUserIdColumn() {
      return getColumnSet().getColumnByClass(UserIdColumn.class);
    }

    /**
     * @return the CreatorColumn
     */
    public CreatorColumn getCreatorColumn() {
      return getColumnSet().getColumnByClass(CreatorColumn.class);
    }

    /**
     * @return the LastModifiedTimeColumn
     */
    public LastModifiedTimeColumn getLastModifiedTimeColumn() {
      return getColumnSet().getColumnByClass(LastModifiedTimeColumn.class);
    }

    /**
     * @return the LastModifierColumn
     */
    public LastModifierColumn getLastModifierColumn() {
      return getColumnSet().getColumnByClass(LastModifierColumn.class);
    }

    /**
     * @return the ValidChangeDateColumn
     */
    public ValidChangeDateColumn getValidChangeDateColumn() {
      return getColumnSet().getColumnByClass(ValidChangeDateColumn.class);
    }

    @Override
    protected String getConfiguredDefaultIconId() {
      return Icons.User;
    }

    /**
     * @return the CreatedTimeColumn
     */
    public CreatedTimeColumn getCreatedTimeColumn() {
      return getColumnSet().getColumnByClass(CreatedTimeColumn.class);
    }

    /**
     * @return the StatusColumn
     */
    public StatusColumn getStatusColumn() {
      return getColumnSet().getColumnByClass(StatusColumn.class);
    }

    /**
     * @return the UserColumn
     */
    public UserColumn getUserColumn() {
      return getColumnSet().getColumnByClass(UserColumn.class);
    }

    @Order(10.0)
    public class UserIdColumn extends AbstractLongColumn {

      @Override
      protected boolean getConfiguredDisplayable() {
        return false;
      }

      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("UserId");
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
    public class UserColumn extends AbstractStringColumn {

      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("User");
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

      @Override
      protected int getConfiguredWidth() {
        return 40;
      }
    }

    @Order(40.0)
    public class ValidChangeDateColumn extends AbstractDateTimeColumn {

      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("ValidChangeDate");
      }

      @Override
      protected int getConfiguredWidth() {
        return 100;
      }
    }

    @Order(50.0)
    public class StatusColumn extends AbstractStringColumn {

      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("Status");
      }

      @Override
      protected int getConfiguredWidth() {
        return 80;
      }
    }

    @Order(60.0)
    public class CreatedTimeColumn extends AbstractDateTimeColumn {

      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("CreatedTime");
      }

      @Override
      protected int getConfiguredWidth() {
        return 100;
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
    public class CreateUserMenu extends AbstractExtensibleMenu {

      @Override
      protected String getConfiguredIconId() {
        return Icons.UserAdd;
      }

      @Override
      protected Set<? extends IMenuType> getConfiguredMenuTypes() {
        return CollectionUtility.<IMenuType> hashSet(TableMenuType.EmptySpace, TableMenuType.SingleSelection);
      }

      @Override
      protected String getConfiguredText() {
        return TEXTS.get("CreateUser");
      }

      @Override
      protected void execAction() throws ProcessingException {
        UserForm form = new UserForm();
        form.startNew();
        form.waitFor();
        if (form.isFormStored()) {
          reloadPage();
        }
      }
    }

    @Order(20.0)
    public class EditUserMenu extends AbstractExtensibleMenu {

      @Override
      protected String getConfiguredIconId() {
        return Icons.UserEdit;
      }

      @Override
      protected String getConfiguredText() {
        return TEXTS.get("EditUser");
      }

      @Override
      protected void execAboutToShow() throws ProcessingException {
        // disable menu when role was deleted
        final AbstractExtensibleMenu menu = this;

        getSelectedRows().forEach(new Consumer<ITableRow>() {
          @Override
          public void accept(ITableRow t) {
            menu.setEnabled(!getStatusColumn().getValue(t).equals(RecordStatus.DELETE.toString()));
          }
        });
      }

      @Override
      protected void execAction() throws ProcessingException {
        UserForm form = new UserForm();
        form.setUserNr(getUserIdColumn().getSelectedValue());
        form.startModify();
        form.waitFor();
        if (form.isFormStored()) {
          reloadPage();
        }
      }
    }

    @Order(30.0)
    public class DeleteUserMenu extends AbstractExtensibleMenu {

      @Override
      protected String getConfiguredIconId() {
        return Icons.UserDelete;
      }

      @Override
      protected Set<? extends IMenuType> getConfiguredMenuTypes() {
        return CollectionUtility.<IMenuType> hashSet(TableMenuType.MultiSelection, TableMenuType.SingleSelection, ValueFieldMenuType.NotNull, TreeMenuType.MultiSelection, TreeMenuType.SingleSelection);
      }

      @Override
      protected String getConfiguredText() {
        return TEXTS.get("DeleteUser");
      }

      @Override
      protected void execAboutToShow() throws ProcessingException {
        // disable menu when role was deleted
        final AbstractExtensibleMenu menu = this;

        getSelectedRows().forEach(new Consumer<ITableRow>() {
          @Override
          public void accept(ITableRow t) {
            menu.setEnabled(!getStatusColumn().getValue(t).equals(RecordStatus.DELETE.toString()));
          }
        });
      }

      /* (non-Javadoc)
       * @see org.eclipse.scout.rt.client.ui.action.AbstractAction#execAction()
       */
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
            ids.add(Long.valueOf(t.getCell(0).getValue().toString()));
          }
        });

        SERVICES.getService(IUserService.class).deleteUsers(ids.toArray(new Long[]{}));

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
    public class DeletePermantlyUserMenu extends AbstractExtensibleMenu {

      @Override
      protected Set<? extends IMenuType> getConfiguredMenuTypes() {
        return CollectionUtility.<IMenuType> hashSet(TableMenuType.MultiSelection, TableMenuType.SingleSelection, ValueFieldMenuType.NotNull, TreeMenuType.MultiSelection, TreeMenuType.SingleSelection);
      }

      @Override
      protected String getConfiguredText() {
        return TEXTS.get("DeletePermantlyUser");
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
            ids.add(Long.valueOf(t.getCell(0).getValue().toString()));
          }
        });

        SERVICES.getService(IUserService.class).deleteUsersPermantly(ids.toArray(new Long[]{}));

        reloadPage();
      }
    }

    @Order(40.0)
    public class RestoreUserMenu extends AbstractExtensibleMenu {

      @Override
      protected Set<? extends IMenuType> getConfiguredMenuTypes() {
        return CollectionUtility.<IMenuType> hashSet(TableMenuType.MultiSelection, TableMenuType.SingleSelection, ValueFieldMenuType.NotNull, TreeMenuType.MultiSelection, TreeMenuType.SingleSelection);
      }

      @Override
      protected String getConfiguredText() {
        return TEXTS.get("RestoreUser");
      }

      @Override
      protected void execAboutToShow() throws ProcessingException {
        //  disable menu when role was not deleted
        final AbstractExtensibleMenu menu = this;

        getSelectedRows().forEach(new Consumer<ITableRow>() {
          @Override
          public void accept(ITableRow t) {
            menu.setEnabled(getStatusColumn().getValue(t).equals(RecordStatus.DELETE.toString()));
          }
        });
      }

      /* (non-Javadoc)
       * @see org.eclipse.scout.rt.client.ui.action.AbstractAction#execAction()
       */
      @Override
      protected void execAction() throws ProcessingException {
        // show message box
        int ans = MessageBox.showYesNoMessage(
            TEXTS.get("ApplicationTitle"),
            TEXTS.get("MsgBox.RestoreUserHeader"),
            TEXTS.get("MsgBox.RestoreUserMessage"));

        if (ans != MessageBox.YES_OPTION) return;

        // if yes go to delete
        ArrayList<Long> ids = new ArrayList<Long>();

        getSelectedRows().forEach(new Consumer<ITableRow>() {
          @Override
          public void accept(ITableRow t) {
            ids.add(Long.valueOf(t.getCell(0).getValue().toString()));
          }
        });

        SERVICES.getService(IUserService.class).restoreUsers(ids.toArray(new Long[]{}));

        reloadPage();
      }
    }
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
}
