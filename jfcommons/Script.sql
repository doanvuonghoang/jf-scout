--<ScriptOptions statementTerminator=";"/>

DROP VIEW "HRM_EMPLOYEE_SLIST_VW";

CREATE VIEW "HRM_EMPLOYEE_SLIST_VW" (id, code, fullName, employeeStatusName, workPositionName, departmentName, headOfUnitName) 
WITH CASCADED CHECK OPTION;

