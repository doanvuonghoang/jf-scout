/**
 * 
 */
package jfscout.client.hrm.ui.desktop.pages;

import jfscout.client.hrm.ui.desktop.pages.EmployeeReportTablePage.Table;
import jfscout.shared.hrm.ui.desktop.pages.EmployeeReportTablePageData;

import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.annotations.PageData;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithTable;
import org.eclipse.scout.rt.extension.client.ui.basic.table.AbstractExtensibleTable;
import org.eclipse.scout.rt.shared.TEXTS;
import jfscout.shared.hrm.Icons;

/**
 * @author Ho�ng
 */
@PageData(EmployeeReportTablePageData.class)
public class EmployeeReportTablePage extends AbstractPageWithTable<Table> {

  @Override
  protected String getConfiguredIconId() {
    return Icons.Report;
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("Report");
  }

  @Order(10.0)
  public class Table extends AbstractExtensibleTable {
  }
}
