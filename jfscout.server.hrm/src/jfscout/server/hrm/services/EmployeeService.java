/**
 *
 */
package jfscout.server.hrm.services;

import java.sql.SQLException;
import java.util.List;

import jfscout.shared.core.services.IDatabaseService;
import jfscout.shared.hrm.services.IEmployeeService;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.commons.exception.VetoException;
import org.eclipse.scout.commons.logger.IScoutLogger;
import org.eclipse.scout.commons.logger.ScoutLogManager;
import org.eclipse.scout.service.AbstractService;
import org.eclipse.scout.service.SERVICES;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.field.DataType;
import com.jf.commons.datamodels.hrm.Utils;
import com.jf.commons.datamodels.hrm.employee.Employee;

/**
 * @author Ho�ng
 */
public class EmployeeService extends AbstractService implements IEmployeeService {
  IScoutLogger logger = ScoutLogManager.getLogger(getClass());

  @Override
  public Object[][] loadEmployeeTableData() throws ProcessingException {
    //
    Dao<Employee, Long> dao = SERVICES.getService(IDatabaseService.class).getDao(Employee.class);
    try {
      List<Object[]> results = dao.queryRaw("select * from " + Utils.VIEW_EMPLOYEE_SLIST_VW,
          new DataType[]{DataType.LONG, DataType.STRING, DataType.STRING, DataType.STRING, DataType.STRING, DataType.STRING}).getResults();
      Object[][] _r = new Object[results.size()][];
      int c = 0;
      for (Object[] e : results) {
        _r[c] = e;
        c++;
      }

      return _r;
    }
    catch (SQLException e) {
      throw new VetoException(e.getMessage(), e);
    }
  }
}
