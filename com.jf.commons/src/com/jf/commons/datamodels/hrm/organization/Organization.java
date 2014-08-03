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

package com.jf.commons.datamodels.hrm.organization;

import java.util.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.jf.commons.datamodels.RecordHistEntity;
import com.jf.commons.datamodels.hrm.classifiers.JobTitle;
import com.jf.commons.datamodels.hrm.classifiers.JobType;
import com.jf.commons.datamodels.hrm.classifiers.WorkStatus;
import com.jf.commons.datamodels.hrm.classifiers.WorkPosition;
import com.jf.commons.datamodels.hrm.employee.Employee;

/**
 * Tổ chức cán bộ
 * 
 * @author Hoàng Doãn
 */
@DatabaseTable(tableName = "hrm_Organizations")
public class Organization extends RecordHistEntity {
	private static final long serialVersionUID = 1L;

	@DatabaseField(foreign = true, canBeNull = false, uniqueCombo = true)
	private Department department;

	@DatabaseField(foreign = true, canBeNull = false, uniqueCombo = true)
	private Employee employee;

	/**
	 * chức vụ
	 */
	@DatabaseField(foreign = true)
	private WorkPosition position;

	/**
	 * Trạng thái: đề bạt, chính thức, thôi giữ?
	 */
	@DatabaseField(foreign = true, uniqueCombo = true)
	private WorkStatus status;

	@DatabaseField
	private Date statusChangedFrom;

	@DatabaseField(width = 4000)
	private String statusChangedReason;

	@DatabaseField
	private Date statusChangedTo;

	/**
	 * Chức danh
	 */
	@DatabaseField(foreign = true)
	private JobTitle title;
	
	/**
	 * Công việc: quản lý kinh tế, kỹ thuật, việc khác?
	 */
	@DatabaseField(foreign = true)
	private JobType type;

	/**
	 * @return the department
	 */
	public Department getDepartment() {
		return department;
	}

	/**
	 * @return the employee
	 */
	public Employee getEmployee() {
		return employee;
	}

	/**
	 * @return the workPosition
	 */
	public WorkPosition getPosition() {
		return position;
	}

	/**
	 * @return the status
	 */
	public WorkStatus getStatus() {
		return status;
	}

	/**
	 * @return the statusChangedFrom
	 */
	public Date getStatusChangedFrom() {
		return statusChangedFrom;
	}

	/**
	 * @return the statusChangedReason
	 */
	public String getStatusChangedReason() {
		return statusChangedReason;
	}

	/**
	 * @return the statusChangedTo
	 */
	public Date getStatusChangedTo() {
		return statusChangedTo;
	}

	/**
	 * @return the title
	 */
	public JobTitle getTitle() {
		return title;
	}

	/**
	 * @return the type
	 */
	public JobType getType() {
		return type;
	}

	/**
	 * @param department
	 *            the department to set
	 */
	public void setDepartment(Department department) {
		this.department = department;
	}

	/**
	 * @param employee
	 *            the employee to set
	 */
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	/**
	 * @param position
	 *            the workPosition to set
	 */
	public void setPosition(WorkPosition position) {
		this.position = position;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(WorkStatus status) {
		this.status = status;
	}

	/**
	 * @param statusChangedFrom
	 *            the statusChangedFrom to set
	 */
	public void setStatusChangedFrom(Date statusChangedFrom) {
		this.statusChangedFrom = statusChangedFrom;
	}

	/**
	 * @param statusChangedReason
	 *            the statusChangedReason to set
	 */
	public void setStatusChangedReason(String statusChangedReason) {
		this.statusChangedReason = statusChangedReason;
	}

	/**
	 * @param statusChangedTo
	 *            the statusChangedTo to set
	 */
	public void setStatusChangedTo(Date statusChangedTo) {
		this.statusChangedTo = statusChangedTo;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(JobTitle title) {
		this.title = title;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(JobType type) {
		this.type = type;
	}

}
