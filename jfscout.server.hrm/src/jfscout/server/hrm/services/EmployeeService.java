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
import com.jf.commons.datamodels.RecordStatus;
import com.jf.commons.datamodels.hrm.employee.Employee;

/**
 * @author Ho�ng
 */
public class EmployeeService extends AbstractService implements IEmployeeService {
  IScoutLogger logger = ScoutLogManager.getLogger(getClass());

  @Override
  public Object[][] loadEmployeeTableData() throws ProcessingException {
    //TODO [Hoàng] business logic here.
    Dao<Employee, Long> dao = SERVICES.getService(IDatabaseService.class).getDao(Employee.class);
    try {
      List<Employee> list = dao.queryBuilder()
          .where()
          //          .eq(Employee.FIELD_STATUS, EmployeeStatusCodeType.WorkingCode.ID)
          .ne(Employee.FIELD_RECORD_STATUS, RecordStatus.DELETE)
          .query();

      Object[][] result = new Object[list.size()][];
      int c = 0;
      for (Employee e : list) {
        // get departments

        result[c] = new Object[]{
            e.getId(),
            e.getCode(),
            e.getFullName(),
            e.getPhoto().getId(),
            e.getStatus().getId(),

        };
        c++;
      }
    }
    catch (SQLException e) {
      throw new VetoException(e.getMessage(), e);
    }
    return null;
  }
}
