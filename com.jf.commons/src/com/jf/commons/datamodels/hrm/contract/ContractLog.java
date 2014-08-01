/*
 * Copyright (C) 2014 Hoàng Doãn
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.jf.commons.datamodels.hrm.contract;

import java.util.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.jf.commons.datamodels.RecordHistEntity;
import com.jf.commons.datamodels.hrm.classifiers.ContractStatus;
import com.jf.commons.datamodels.hrm.classifiers.ContractType;
import com.jf.commons.datamodels.hrm.employee.Employee;

/**
 *
 * @author Hoàng Doãn
 */
@DatabaseTable(tableName = "hrm_ContractLogs")
public class ContractLog extends RecordHistEntity {
	private static final long serialVersionUID = 1L;
	
	public static final String FIELD_CONTRACT = "contractId";
	public static final String FIELD_EMPLOYEE = "employeeId";
	public static final String FIELD_CONTRACT_TYPE = "contractTypeId";
	public static final String FIELD_CONTRACT_STATUS = "contractStatusId";
	public static final String FIELD_ROOT_CONTRACT = "rootContractId";
	
	@DatabaseField(foreign = true, canBeNull = false, columnName = FIELD_CONTRACT)
	private Contract contract;

	@DatabaseField(foreign = true, canBeNull = false, columnName = FIELD_EMPLOYEE, uniqueCombo = true)
	private Employee employee;

	@DatabaseField(unique = true, canBeNull = false)
	private String code;

	@DatabaseField(width = 4000)
	private String description;
	
	@DatabaseField(canBeNull = false)
	private Date startDate;
	
	@DatabaseField
	private Date endDate;
	
	@DatabaseField(canBeNull = false, foreign = true, columnName = FIELD_CONTRACT_STATUS)
	private ContractStatus status;
	
	@DatabaseField(canBeNull = false, foreign = true, columnName = FIELD_CONTRACT_TYPE)
	private ContractType type;
	
	@DatabaseField
	private Date suspendedFromDate;
	
	@DatabaseField
	private Date suspendedToDate;
	
	@DatabaseField(width = 4000)
	private String suspendedReason;
	
	@DatabaseField(width = 4000)
	private String appendixChange;
	
	@DatabaseField
	private Date appendixStartDate;
	
	@DatabaseField
	private Date appendixEndDate;
	
	@DatabaseField(foreign = true, columnName = FIELD_ROOT_CONTRACT)
	private Contract rootContract;
	
	@DatabaseField
	private Date closedDate;
	
	@DatabaseField(width = 4000)
	private String closedReason;

	/**
	 * @return the labourAgreement
	 */
	public Contract getContract() {
		return contract;
	}

	/**
	 * @param contract the labourAgreement to set
	 */
	public void setContract(Contract contract) {
		this.contract = contract;
	}

	/**
	 * @return the employee
	 */
	public Employee getEmployee() {
		return employee;
	}

	/**
	 * @param employee the employee to set
	 */
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the status
	 */
	public ContractStatus getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(ContractStatus status) {
		this.status = status;
	}

	/**
	 * @return the type
	 */
	public ContractType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(ContractType type) {
		this.type = type;
	}

	/**
	 * @return the suspendedFromDate
	 */
	public Date getSuspendedFromDate() {
		return suspendedFromDate;
	}

	/**
	 * @param suspendedFromDate the suspendedFromDate to set
	 */
	public void setSuspendedFromDate(Date suspendedFromDate) {
		this.suspendedFromDate = suspendedFromDate;
	}

	/**
	 * @return the suspendedToDate
	 */
	public Date getSuspendedToDate() {
		return suspendedToDate;
	}

	/**
	 * @param suspendedToDate the suspendedToDate to set
	 */
	public void setSuspendedToDate(Date suspendedToDate) {
		this.suspendedToDate = suspendedToDate;
	}

	/**
	 * @return the suspendedReason
	 */
	public String getSuspendedReason() {
		return suspendedReason;
	}

	/**
	 * @param suspendedReason the suspendedReason to set
	 */
	public void setSuspendedReason(String suspendedReason) {
		this.suspendedReason = suspendedReason;
	}

	/**
	 * @return the appendixChange
	 */
	public String getAppendixChange() {
		return appendixChange;
	}

	/**
	 * @param appendixChange the appendixChange to set
	 */
	public void setAppendixChange(String appendixChange) {
		this.appendixChange = appendixChange;
	}

	/**
	 * @return the appendixStartDate
	 */
	public Date getAppendixStartDate() {
		return appendixStartDate;
	}

	/**
	 * @param appendixStartDate the appendixStartDate to set
	 */
	public void setAppendixStartDate(Date appendixStartDate) {
		this.appendixStartDate = appendixStartDate;
	}

	/**
	 * @return the appendixEndDate
	 */
	public Date getAppendixEndDate() {
		return appendixEndDate;
	}

	/**
	 * @param appendixEndDate the appendixEndDate to set
	 */
	public void setAppendixEndDate(Date appendixEndDate) {
		this.appendixEndDate = appendixEndDate;
	}

	/**
	 * @return the rootAgreement
	 */
	public Contract getRootContract() {
		return rootContract;
	}

	/**
	 * @param root the rootAgreement to set
	 */
	public void setRootContract(Contract root) {
		this.rootContract = root;
	}

	/**
	 * @return the closedDate
	 */
	public Date getClosedDate() {
		return closedDate;
	}

	/**
	 * @param closedDate the closedDate to set
	 */
	public void setClosedDate(Date closedDate) {
		this.closedDate = closedDate;
	}

	/**
	 * @return the closedReason
	 */
	public String getClosedReason() {
		return closedReason;
	}

	/**
	 * @param closedReason the closedReason to set
	 */
	public void setClosedReason(String closedReason) {
		this.closedReason = closedReason;
	}

}
