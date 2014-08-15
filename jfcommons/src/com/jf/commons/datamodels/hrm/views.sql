DROP VIEW HRM_EMPLOYEE_SLIST_VW;
CREATE VIEW HRM_EMPLOYEE_SLIST_VW 
AS
SELECT E."id", E."code", E."fullName", ES."name" AS "employeeStatusName",
  WP."name" AS "workPositionName", D."name" AS "departmentName",
  H."fullName" AS "headOfUnitName"
  FROM
       PUBLIC.HRM_EMPLOYEES AS E 
       INNER JOIN PUBLIC.HRM_EMPLOYEESTATUSES AS ES ON E."statusId" = ES."id" 
   LEFT JOIN PUBLIC.HRM_ORGANIZATIONS AS O ON E."id" = O."employeeId" AND O."recordStatus" <> 'DELETE'
   LEFT JOIN PUBLIC.HRM_WORKPOSITIONS AS WP ON O."workPosistionId" = WP."id" 
   LEFT JOIN PUBLIC.HRM_DEPARTMENTS AS D ON O."departmentId" = D."id" AND D."recordStatus" <> 'DELETE' 
   LEFT JOIN PUBLIC.HRM_EMPLOYEES AS H ON D."headOfUnit" = H."id"
  WHERE E."recordStatus" <> 'DELETE' AND E."statusId" NOT IN (4, 5, 6)
  ORDER BY E."fullName";