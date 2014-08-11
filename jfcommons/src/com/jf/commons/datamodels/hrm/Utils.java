package com.jf.commons.datamodels.hrm;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.table.DatabaseTable;
import com.jf.commons.datamodels.RecordStatus;
import com.jf.commons.datamodels.hrm.classifiers.ContractType;
import com.jf.commons.datamodels.hrm.contract.Contract;
import com.jf.commons.datamodels.hrm.employee.Employee;
import com.jf.commons.datamodels.hrm.organization.Organization;

public class Utils {
	public static final String VIEW_EMPLOYEE_LIST_VW = "HRM_EMPLOYEE_LIST_VW";
	
	public static void generateEmployeeListView(Dao<?, Long> dao) throws Exception {
		String employeeTbl = Employee.class.getAnnotation(DatabaseTable.class).tableName();
		String contractTbl = Contract.class.getAnnotation(DatabaseTable.class).tableName();
		String contractTypeTbl = ContractType.class.getAnnotation(DatabaseTable.class).tableName();
		String orgTbl = Organization.class.getAnnotation(DatabaseTable.class).tableName();
		
		dao.executeRaw("CREATE VIEW " + VIEW_EMPLOYEE_LIST_VW + 
				" AS "
				+ "SELECT "
				+ "e.id, "
				+ "e." + Employee.FIELD_FULLNAME + ", "
				+ "e." + Employee.FIELD_CODE + ", "
				+ "c." + Contract.FIELD_CONTRACT_TYPE + ", "
				+ "ct." + ContractType.FIELD_NAME + " ContractTypeName, "
				
				+ "FROM " + employeeTbl + " e "
				+ "INNER JOIN " + contractTbl + " c "
				+ "ON e.id = c." + Contract.FIELD_EMPLOYEE + " "
				+ "INNER JOIN " + contractTypeTbl + " ct "
				+ "ON c." + Contract.FIELD_CONTRACT_TYPE + " = ct.id "
				+ "INNER JOIN " + orgTbl + " o "
				+ "ON e.id = o." + Organization.FIELD_EMPLOYEE + " "
				+ "WHERE "
				+ "e." + Employee.FIELD_RECORD_STATUS + " <> '" + RecordStatus.DELETE.toString() + "' "
				+ "AND e." + Employee.FIELD_STATUS + " not in (4, 5 ,6) " // Nghi huu, nghi viec, tam dung
				
		);
		
		
	}
}
