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

package com.jf.commons.datamodels.hrm.insurance;

import java.util.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.jf.commons.datamodels.RecordHistEntity;
import com.jf.commons.datamodels.hrm.classifiers.InsuranceStatus;

/**
 *
 * @author Hoàng Doãn
 */
@DatabaseTable(tableName = "hrm_InsuranceLogs")
public class InsuranceLog extends RecordHistEntity {
	private static final long serialVersionUID = 1L;
	
	public static final String FIELD_INSURANCE = "insuranceId";
	
	@DatabaseField(canBeNull = false, foreign = true, columnName = FIELD_INSURANCE)
	private Insurance insurance;

	/**
	 * Ngày bắt đầu nộp bảo hiểm
	 */
	@DatabaseField(canBeNull = false)
	private Date statusChangedFromDate;
	
	@DatabaseField
	private Date statusChangedToDate;
	
	/**
	 * Trạng thái hiện nay, đang dừng hay không?
	 */
	@DatabaseField(canBeNull = false, foreign = true, columnName = Insurance.FIELD_INSURANCE_STATUS)
	private InsuranceStatus status;
	
	/**
	 * Ti le phai nop bao hiem
	 */
	@DatabaseField
	private double rate;

	/**
	 * Nam tuong ung voi ty le theo quy dinh ve bao hiem
	 */
	@DatabaseField
	private int yearAssc;

	/**
	 * Co bat buoc phai nop hay khong
	 */
	@DatabaseField(canBeNull = false, defaultValue = "false")
	private boolean isRequired;

	/**
	 * Ti le nguoi lao dong can dong
	 */
	@DatabaseField
	private double employeeRate;

	/**
	 * Ti le nha tuyen dung dong
	 */
	@DatabaseField
	private double employerRate;

	/**
	 * @return the insurance
	 */
	public Insurance getInsurance() {
		return insurance;
	}

	/**
	 * @param insurance the insurance to set
	 */
	public void setInsurance(Insurance insurance) {
		this.insurance = insurance;
	}

	/**
	 * @return the statusChangedFromDate
	 */
	public Date getStatusChangedFromDate() {
		return statusChangedFromDate;
	}

	/**
	 * @param statusChangedFromDate the statusChangedFromDate to set
	 */
	public void setStatusChangedFromDate(Date statusChangedFromDate) {
		this.statusChangedFromDate = statusChangedFromDate;
	}

	/**
	 * @return the statusChangedToDate
	 */
	public Date getStatusChangedToDate() {
		return statusChangedToDate;
	}

	/**
	 * @param statusChangedToDate the statusChangedToDate to set
	 */
	public void setStatusChangedToDate(Date statusChangedToDate) {
		this.statusChangedToDate = statusChangedToDate;
	}

	/**
	 * @return the status
	 */
	public InsuranceStatus getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(InsuranceStatus status) {
		this.status = status;
	}

	/**
	 * @return the rate
	 */
	public double getRate() {
		return rate;
	}

	/**
	 * @param rate the rate to set
	 */
	public void setRate(double rate) {
		this.rate = rate;
	}

	/**
	 * @return the yearAssc
	 */
	public int getYearAssc() {
		return yearAssc;
	}

	/**
	 * @param yearAssc the yearAssc to set
	 */
	public void setYearAssc(int yearAssc) {
		this.yearAssc = yearAssc;
	}

	/**
	 * @return the isRequired
	 */
	public boolean isRequired() {
		return isRequired;
	}

	/**
	 * @param isRequired the isRequired to set
	 */
	public void setRequired(boolean isRequired) {
		this.isRequired = isRequired;
	}

	/**
	 * @return the employeeRate
	 */
	public double getEmployeeRate() {
		return employeeRate;
	}

	/**
	 * @param employeeRate the employeeRate to set
	 */
	public void setEmployeeRate(double employeeRate) {
		this.employeeRate = employeeRate;
	}

	/**
	 * @return the employerRate
	 */
	public double getEmployerRate() {
		return employerRate;
	}

	/**
	 * @param employerRate the employerRate to set
	 */
	public void setEmployerRate(double employerRate) {
		this.employerRate = employerRate;
	}
	
}
