/**
 *
 */
package jfscout.client.hrm.ui.desktop.outlines;

import org.eclipse.scout.rt.extension.client.ui.desktop.outline.AbstractExtensibleOutline;
import org.eclipse.scout.rt.shared.TEXTS;
import java.util.Collection;
import jfscout.client.hrm.ui.desktop.pages.EmployeeListTablePage;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.IPage;
import jfscout.client.hrm.ui.desktop.pages.EmployeeReportTablePage;

/**
 * @author Ho�ng
 */
public class EmployeeManagementOutline extends AbstractExtensibleOutline {

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("EmployeeManagement");
  }

  @Override
  protected void execCreateChildPages(Collection<IPage> pageList) throws ProcessingException {
    EmployeeListTablePage employeeListTablePage = new EmployeeListTablePage();
    pageList.add(employeeListTablePage);
    EmployeeReportTablePage employeeReportTablePage = new EmployeeReportTablePage();
    pageList.add(employeeReportTablePage);
  }

}
