/**
 * 
 */
package jfscout.client.hrm.ui.desktop.pages;

import jfscout.client.hrm.ui.desktop.pages.EmployeeTablePage.Table;
import jfscout.shared.hrm.ui.desktop.pages.EmployeeTablePageData;

import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.annotations.PageData;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractLongColumn;
import org.eclipse.scout.rt.extension.client.ui.basic.table.AbstractExtensibleTable;
import org.eclipse.scout.rt.extension.client.ui.desktop.outline.pages.AbstractExtensiblePageWithTable;
import org.eclipse.scout.rt.shared.TEXTS;
import jfscout.client.hrm.ui.desktop.pages.EmployeeTablePage.Table.IdColumn;

/**
 * @author Hoàng
 */
@PageData(EmployeeTablePageData.class)
public class EmployeeTablePage extends AbstractExtensiblePageWithTable<Table> {

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("Employee");
  }

  @Order(10.0)
  public class Table extends AbstractExtensibleTable {

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
  }
}
