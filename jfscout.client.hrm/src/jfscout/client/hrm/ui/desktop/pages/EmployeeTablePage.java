/**
 *
 */
package jfscout.client.hrm.ui.desktop.pages;

import jfscout.client.hrm.ui.desktop.pages.EmployeeTablePage.Table;
import jfscout.shared.hrm.ui.desktop.pages.EmployeeTablePageData;

import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.annotations.PageData;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractDateColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractIntegerColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractLongColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractStringColumn;
import org.eclipse.scout.rt.extension.client.ui.basic.table.AbstractExtensibleTable;
import org.eclipse.scout.rt.extension.client.ui.desktop.outline.pages.AbstractExtensiblePageWithTable;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

/**
 * @author Ho�ng
 */
@PageData(EmployeeTablePageData.class)
public class EmployeeTablePage extends AbstractExtensiblePageWithTable<Table> {

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("Employee");
  }

  @Override
  protected Object[][] execLoadTableData(SearchFilter filter) throws ProcessingException {
    // read from service

    return super.execLoadTableData(filter);
  }

  @Order(10.0)
  public class Table extends AbstractExtensibleTable {

    /**
     * @return the FullNameColumn
     */
    public FullNameColumn getFullNameColumn() {
      return getColumnSet().getColumnByClass(FullNameColumn.class);
    }

    /**
     * @return the StatusColumn
     */
    public StatusColumn getStatusColumn() {
      return getColumnSet().getColumnByClass(StatusColumn.class);
    }

    /**
     * @return the PhotoColumn
     */
    public PhotoColumn getPhotoColumn() {
      return getColumnSet().getColumnByClass(EmployeeTablePage.Table.PhotoColumn.class);
    }

    /**
     * @return the DepartmentColumn
     */
    public DepartmentColumn getDepartmentColumn() {
      return getColumnSet().getColumnByClass(DepartmentColumn.class);
    }

    /**
     * @return the GenderColumn
     */
    public GenderColumn getGenderColumn() {
      return getColumnSet().getColumnByClass(GenderColumn.class);
    }

    /**
     * @return the BirthplaceColumn
     */
    public BirthplaceColumn getBirthplaceColumn() {
      return getColumnSet().getColumnByClass(BirthplaceColumn.class);
    }

    /**
     * @return the PINColumn
     */
    public PINColumn getPINColumn() {
      return getColumnSet().getColumnByClass(PINColumn.class);
    }

    /**
     * @return the BirthdateColumn
     */
    public BirthdateColumn getBirthdateColumn() {
      return getColumnSet().getColumnByClass(BirthdateColumn.class);
    }

    /**
     * @return the CodeColumn
     */
    public CodeColumn getCodeColumn() {
      return getColumnSet().getColumnByClass(CodeColumn.class);
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
      protected String getConfiguredHeaderText() {
        return TEXTS.get("Id");
      }
    }

    @Order(20.0)
    public class CodeColumn extends AbstractStringColumn {

      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("Code");
      }
    }

    @Order(30.0)
    public class FullNameColumn extends AbstractStringColumn {

      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("FullName");
      }
    }

    @Order(40.0)
    public class PhotoColumn extends AbstractIntegerColumn {

      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("Photo");
      }
    }

    @Order(50.0)
    public class StatusColumn extends AbstractIntegerColumn {

      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("Status");
      }
    }

    @Order(60.0)
    public class DepartmentColumn extends AbstractStringColumn {

      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("Department");
      }
    }

    @Order(70.0)
    public class GenderColumn extends AbstractStringColumn {

      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("Gender");
      }
    }

    @Order(80.0)
    public class BirthdateColumn extends AbstractDateColumn {

      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("Birthdate");
      }
    }

    @Order(90.0)
    public class BirthplaceColumn extends AbstractStringColumn {

      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("Birthplace");
      }
    }

    @Order(100.0)
    public class PINColumn extends AbstractStringColumn {

      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("PIN");
      }
    }
  }
}
