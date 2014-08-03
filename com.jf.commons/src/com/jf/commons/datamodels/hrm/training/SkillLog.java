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

package com.jf.commons.datamodels.hrm.training;

import java.util.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.jf.commons.datamodels.RecordHistEntity;
import com.jf.commons.datamodels.hrm.classifiers.SkillLevel;
import com.jf.commons.datamodels.hrm.classifiers.SkillType;
import com.jf.commons.datamodels.hrm.employee.Employee;

/**
 *
 * @author Hoàng Doãn
 */
@DatabaseTable(tableName = "hrm_SkillLogs")
public class SkillLog extends RecordHistEntity {
	private static final long serialVersionUID = 1L;
	
	@DatabaseField(foreign = true, canBeNull = false, uniqueCombo = true)
	private Employee employee;
	
	/**
	 * Trường đào tạo
	 */
	@DatabaseField(canBeNull = false)
	private String trainingUnit;
	
	@DatabaseField(canBeNull = false)
	private Date fromDate;
	
	@DatabaseField
	private Date toDate;
	
	/**
	 * Loại chuyên môn
	 */
	@DatabaseField(foreign = true, uniqueCombo = true)
	private SkillType type;
	
	/**
	 * Bậc thợ
	 */
	@DatabaseField(foreign = true, uniqueCombo = true)
	private SkillLevel level;
	
	/**
	 * Học vị
	 */
	@DatabaseField(canBeNull = false)
	private String title;
	
	/**
	 * Tốt nghiệp loại
	 */
	@DatabaseField
	private String grade;
	
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
	 * @return the fromDate
	 */
	public Date getFromDate() {
		return fromDate;
	}

	/**
	 * @param fromDate the fromDate to set
	 */
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	/**
	 * @return the toDate
	 */
	public Date getToDate() {
		return toDate;
	}

	/**
	 * @param toDate the toDate to set
	 */
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	/**
	 * @return the trainingUnit
	 */
	public String getTrainingUnit() {
		return trainingUnit;
	}

	/**
	 * @param trainingUnit the trainingUnit to set
	 */
	public void setTrainingUnit(String trainingUnit) {
		this.trainingUnit = trainingUnit;
	}

	/**
	 * @return the type
	 */
	public SkillType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(SkillType type) {
		this.type = type;
	}

	/**
	 * @return the level
	 */
	public SkillLevel getLevel() {
		return level;
	}

	/**
	 * @param level the level to set
	 */
	public void setLevel(SkillLevel level) {
		this.level = level;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the grade
	 */
	public String getGrade() {
		return grade;
	}

	/**
	 * @param grade the grade to set
	 */
	public void setGrade(String grade) {
		this.grade = grade;
	}

}
