/**
 * 
 */
package jfscout.client.hrm.ui.desktop.pages;

import jfscout.client.hrm.ui.desktop.pages.EmployeeListTablePage.Table;
import jfscout.shared.hrm.Icons;
import jfscout.shared.hrm.ui.desktop.pages.EmployeeListTablePageData;

import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.annotations.PageData;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractLongColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractSmartColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractStringColumn;
import org.eclipse.scout.rt.extension.client.ui.basic.table.AbstractExtensibleTable;
import org.eclipse.scout.rt.extension.client.ui.desktop.outline.pages.AbstractExtensiblePageWithTable;
import org.eclipse.scout.rt.shared.TEXTS;
import jfscout.client.hrm.ui.forms.EmployeeListSearchForm;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.ISearchForm;

/**
 * @author Ho�ng
 */
@PageData(EmployeeListTablePageData.class)
public class EmployeeListTablePage extends AbstractExtensiblePageWithTable<Table> {

  @Override
  protected String getConfiguredIconId() {
    return Icons.EmployeeManagement;
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("EmployeeList");
  }

  @Order(10.0)
  public class Table extends AbstractExtensibleTable {

    /**
     * @return the StatusColumn
     */
    public StatusColumn getStatusColumn() {
      return getColumnSet().getColumnByClass(StatusColumn.class);
    }

    /**
     * @return the DepartmentColumn
     */
    public DepartmentColumn getDepartmentColumn() {
      return getColumnSet().getColumnByClass(DepartmentColumn.class);
    }

    /**
     * @return the HeadOfUnitColumn
     */
    public HeadOfUnitColumn getHeadOfUnitColumn() {
      return getColumnSet().getColumnByClass(HeadOfUnitColumn.class);
    }

    /**
     * @return the JobTitleColumn
     */
    public JobTitleColumn getJobTitleColumn() {
      return getColumnSet().getColumnByClass(EmployeeListTablePage.Table.JobTitleColumn.class);
    }

    /**
     * @return the CodeColumn
     */
    public CodeColumn getCodeColumn() {
      return getColumnSet().getColumnByClass(CodeColumn.class);
    }

    /**
     * @return the FullNameColumn
     */
    public FullNameColumn getFullNameColumn() {
      return getColumnSet().getColumnByClass(FullNameColumn.class);
    }

    /**
     * @return the IdColumn
     */
    public IdColumn getIdColumn() {
      return getColumnSet().getColumnByClass(IdColumn.class);
    }

    @Order(10.0)
    public class IdColumn extends AbstractLongColumn {

      @Override
      protected boolean getConfiguredDisplayable() {
        return false;
      }

      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("Id");
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
    public class FullNameColumn extends AbstractStringColumn {

      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("FullName");
      }

      @Override
      protected int getConfiguredWidth() {
        return 400;
      }
    }

    @Order(30.0)
    public class CodeColumn extends AbstractStringColumn {

      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("Code");
      }

      @Override
      protected int getConfiguredWidth() {
        return 20;
      }
    }

    @Order(40.0)
    public class StatusColumn extends AbstractSmartColumn<Long> {

      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("Status");
      }
    }

    @Order(50.0)
    public class JobTitleColumn extends AbstractSmartColumn<Long> {

      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("JobTitle");
      }
    }

    @Order(60.0)
    public class DepartmentColumn extends AbstractSmartColumn<Long> {

      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("Department");
      }
    }

    @Order(70.0)
    public class HeadOfUnitColumn extends AbstractStringColumn {

      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("HeadOfUnit");
      }
    }
  }

  @Override
  protected Class<? extends ISearchForm> getConfiguredSearchForm() {
    return EmployeeListSearchForm.class;
  }
}
