/**
 *
 */
package jfscout.server.hrm.services;

import jfscout.shared.core.services.IDatabaseService;
import jfscout.shared.hrm.services.IExtensionService;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.commons.logger.IScoutLogger;
import org.eclipse.scout.commons.logger.ScoutLogManager;
import org.eclipse.scout.service.AbstractService;
import org.eclipse.scout.service.SERVICES;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.table.TableUtils;
import com.jf.commons.datamodels.TypeBasedEntity;
import com.jf.commons.datamodels.hrm.Address;
import com.jf.commons.datamodels.hrm.File;
import com.jf.commons.datamodels.hrm.classifiers.City;
import com.jf.commons.datamodels.hrm.classifiers.ContractStatus;
import com.jf.commons.datamodels.hrm.classifiers.ContractType;
import com.jf.commons.datamodels.hrm.classifiers.DepartmentType;
import com.jf.commons.datamodels.hrm.classifiers.District;
import com.jf.commons.datamodels.hrm.classifiers.EmployeeStatus;
import com.jf.commons.datamodels.hrm.classifiers.FamilyRelationType;
import com.jf.commons.datamodels.hrm.classifiers.InsuranceStatus;
import com.jf.commons.datamodels.hrm.classifiers.InsuranceType;
import com.jf.commons.datamodels.hrm.classifiers.JobTitle;
import com.jf.commons.datamodels.hrm.classifiers.JobType;
import com.jf.commons.datamodels.hrm.classifiers.MaritalStatus;
import com.jf.commons.datamodels.hrm.classifiers.RDType;
import com.jf.commons.datamodels.hrm.classifiers.SkillLevel;
import com.jf.commons.datamodels.hrm.classifiers.SkillTitle;
import com.jf.commons.datamodels.hrm.classifiers.SkillType;
import com.jf.commons.datamodels.hrm.classifiers.Ward;
import com.jf.commons.datamodels.hrm.classifiers.WorkPosition;
import com.jf.commons.datamodels.hrm.classifiers.WorkStatus;
import com.jf.commons.datamodels.hrm.contract.Contract;
import com.jf.commons.datamodels.hrm.contract.ContractLog;
import com.jf.commons.datamodels.hrm.cv.CV;
import com.jf.commons.datamodels.hrm.cv.CVFamilyCircumstance;
import com.jf.commons.datamodels.hrm.cv.CVFamilyRelation;
import com.jf.commons.datamodels.hrm.cv.CVPersonalActivity;
import com.jf.commons.datamodels.hrm.cv.CVPersonalDetail;
import com.jf.commons.datamodels.hrm.cv.CVRD;
import com.jf.commons.datamodels.hrm.employee.Employee;
import com.jf.commons.datamodels.hrm.employee.EmployeeStatusLog;
import com.jf.commons.datamodels.hrm.organization.Department;
import com.jf.commons.datamodels.hrm.organization.Organization;
import com.jf.commons.datamodels.hrm.organization.OrganizationLog;
import com.jf.commons.datamodels.hrm.training.Skill;
import com.jf.commons.datamodels.hrm.training.SkillLog;

/**
 * @author Hoàng
 */
public class ExtensionService extends AbstractService implements IExtensionService {
  private IScoutLogger logger = ScoutLogManager.getLogger(getClass());

  @SuppressWarnings("unchecked")
  @Override
  public void installDB() throws ProcessingException {
    // install db
    logger.info("Preparing install hrm tables");
    // first install classifiers
    Class<?>[] step1 = new Class<?>[]{
        Address.class,
        File.class,
        // classifiers
        ContractStatus.class,
        ContractType.class,
        DepartmentType.class,
        EmployeeStatus.class,
        FamilyRelationType.class,
        InsuranceStatus.class,
        InsuranceType.class,
        JobTitle.class,
        JobType.class,
        MaritalStatus.class,
        RDType.class,
        SkillLevel.class,
        SkillTitle.class,
        SkillType.class,
        WorkPosition.class,
        WorkStatus.class,
        // addresses
        City.class,
        District.class,
        Ward.class,
        // department
        Department.class,
        // employee
        Employee.class,
        EmployeeStatusLog.class,
        // cv
        CV.class,
        CVFamilyCircumstance.class,
        CVFamilyRelation.class,
        CVPersonalActivity.class,
        CVPersonalDetail.class,
        CVRD.class,
        // contract
        Contract.class,
        ContractLog.class,
        // org
        Organization.class,
        OrganizationLog.class,
        // training
        Skill.class,
        SkillLog.class
    };

    for (Class<?> t : step1) {
      Dao<?, Long> dao = SERVICES.getService(IDatabaseService.class).getDao(t);
      try {
        if (TypeBasedEntity.class.isAssignableFrom(t)) {
          TypeBasedEntity m = (TypeBasedEntity) t.newInstance();
          m.generateData((Dao<TypeBasedEntity, Long>) dao, (Class<TypeBasedEntity>) t);
        }
        else if (City.class.isAssignableFrom(t)) {
          City.generateData((Dao<City, Long>) dao);
        }
        else if (District.class.isAssignableFrom(t)) {
          Dao<City, Long> cdao = SERVICES.getService(IDatabaseService.class).getDao(City.class);
          District.generateData((Dao<District, Long>) dao, cdao);
        }
        else if (Ward.class.isAssignableFrom(t)) {
          Dao<City, Long> cdao = SERVICES.getService(IDatabaseService.class).getDao(City.class);
          Dao<District, Long> ddao = SERVICES.getService(IDatabaseService.class).getDao(District.class);
          Ward.generateData((Dao<Ward, Long>) dao, cdao, ddao);
        }
        else {
          TableUtils.createTableIfNotExists(dao.getConnectionSource(), t);
        }

        logger.info("installed table: " + t.getSimpleName());
      }
      catch (Exception e) {
        logger.warn(e.getMessage(), e);
      }
    }

  }
}
