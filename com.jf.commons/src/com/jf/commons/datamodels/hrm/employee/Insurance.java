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

package com.jf.commons.datamodels.hrm.employee;

import java.util.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.jf.commons.datamodels.RecordHistEntity;
import com.jf.commons.datamodels.hrm.classifiers.InsuranceStatus;
import com.jf.commons.datamodels.hrm.classifiers.InsuranceType;

/**
 *
 * @author Hoàng Doãn
 */
@DatabaseTable(tableName = "hrm_Insurances")
public class Insurance extends RecordHistEntity {
	private static final long serialVersionUID = 1L;

	public static final String FIELD_EMPLOYEE = "employeeId";
	public static final String FIELD_INSURANCE_TYPE = "insuranceTypeId";
	public static final String FIELD_INSURANCE_STATUS = "insuranceStatusId";

	@DatabaseField(foreign = true, canBeNull = false, columnName = FIELD_EMPLOYEE, uniqueCombo = true)
	private Employee employee;

	@DatabaseField(foreign = true, columnName = FIELD_INSURANCE_TYPE, uniqueCombo = true)
	private InsuranceType insuranceType;

	@DatabaseField(canBeNull = false)
	private String insuranceCode;

	/**
	 * Ngày bắt đầu nộp bảo hiểm
	 */
	@DatabaseField(canBeNull = false)
	private Date fromDate;

	/**
	 * Ngày kết thúc nộp bảo hiểm
	 */
	@DatabaseField
	private Date endDate;

	/**
	 * Trạng thái hiện nay, đang dừng hay không?
	 */
	@DatabaseField(canBeNull = false, foreign = true, columnName = FIELD_INSURANCE_STATUS, uniqueCombo = true)
	private InsuranceStatus status;

	/**
	 * Tạm dừng từ ngày
	 */
	@DatabaseField
	private Date suspendedFromDate;

	/**
	 * Tạm dừng đến ngày
	 */
	@DatabaseField
	private Date suspendedToDate;

	/**
	 * @return the employee
	 */
	public Employee getEmployee() {
		return employee;
	}

	/**
	 * @param employee
	 *            the employee to set
	 */
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	/**
	 * @return the insuranceType
	 */
	public InsuranceType getInsuranceType() {
		return insuranceType;
	}

	/**
	 * @param insuranceType
	 *            the insuranceType to set
	 */
	public void setInsuranceType(InsuranceType insuranceType) {
		this.insuranceType = insuranceType;
	}

	/**
	 * @return the insuranceCode
	 */
	public String getInsuranceCode() {
		return insuranceCode;
	}

	/**
	 * @param insuranceCode
	 *            the insuranceCode to set
	 */
	public void setInsuranceCode(String insuranceCode) {
		String old = this.insuranceCode;
		this.insuranceCode = insuranceCode;
		propertyChange.firePropertyChange("insuranceCode", old, insuranceCode);
	}

	/**
	 * @return the fromDate
	 */
	public Date getFromDate() {
		return fromDate;
	}

	/**
	 * @param fromDate
	 *            the fromDate to set
	 */
	public void setFromDate(Date fromDate) {
		Date old = this.fromDate;
		this.fromDate = fromDate;
		propertyChange.firePropertyChange("fromDate", old, fromDate);
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate
	 *            the endDate to set
	 */
	public void setEndDate(Date endDate) {
		Date old = this.endDate;
		this.endDate = endDate;
		propertyChange.firePropertyChange("endDate", old, endDate);
	}

	/**
	 * @return the status
	 */
	public InsuranceStatus getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(InsuranceStatus status) {
		InsuranceStatus old = this.status;
		this.status = status;
		propertyChange.firePropertyChange("status", old, status);
	}

	/**
	 * @return the suspendedFromDate
	 */
	public Date getSuspendedFromDate() {
		return suspendedFromDate;
	}

	/**
	 * @param suspendedFromDate
	 *            the suspendedFromDate to set
	 */
	public void setSuspendedFromDate(Date suspendedFromDate) {
		Date old = this.suspendedFromDate;
		this.suspendedFromDate = suspendedFromDate;
		propertyChange.firePropertyChange("suspendedFromDate", old,
				suspendedFromDate);
	}

	/**
	 * @return the suspendedToDate
	 */
	public Date getSuspendedToDate() {
		return suspendedToDate;
	}

	/**
	 * @param suspendedToDate
	 *            the suspendedToDate to set
	 */
	public void setSuspendedToDate(Date suspendedToDate) {
		Date old = this.suspendedToDate;
		this.suspendedToDate = suspendedToDate;
		propertyChange.firePropertyChange("suspendedToDate", old,
				suspendedToDate);
	}

}
