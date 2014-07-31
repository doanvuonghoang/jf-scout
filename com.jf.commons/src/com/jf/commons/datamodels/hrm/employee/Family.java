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

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.jf.commons.datamodels.RecordHistEntity;
import com.jf.commons.datamodels.hrm.classifiers.FamilyRelationType;

/**
 *
 * @author Hoàng Doãn
 */
@DatabaseTable(tableName = "hrm_Family")
public class Family extends RecordHistEntity {
	private static final long serialVersionUID = 1L;

	@DatabaseField(foreign = true, canBeNull = false)
	private Employee employee;

	@DatabaseField(foreign = true)
	private FamilyRelationType familyMemberType;

	@DatabaseField(canBeNull = false)
	private String fullname;

	@DatabaseField
	private int age;

	@DatabaseField
	private String job;

	@DatabaseField
	private String workplace;

	@DatabaseField(width = 4000)
	private String wwBefore8thRevolution;

	@DatabaseField(width = 4000)
	private String wwDuringResistanceWar;

	@DatabaseField(width = 4000)
	private String wwAfter1955;

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
	 * @return the familyMemberType
	 */
	public FamilyRelationType getFamilyMemberType() {
		return familyMemberType;
	}

	/**
	 * @param familyMemberType
	 *            the familyMemberType to set
	 */
	public void setFamilyMemberType(FamilyRelationType familyMemberType) {
		this.familyMemberType = familyMemberType;
	}

	/**
	 * @return the fullname
	 */
	public String getFullname() {
		return fullname;
	}

	/**
	 * @param fullname
	 *            the fullname to set
	 */
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @param age
	 *            the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * @return the job
	 */
	public String getJob() {
		return job;
	}

	/**
	 * @param job
	 *            the job to set
	 */
	public void setJob(String job) {
		this.job = job;
	}

	/**
	 * @return the workplace
	 */
	public String getWorkplace() {
		return workplace;
	}

	/**
	 * @param workplace
	 *            the workplace to set
	 */
	public void setWorkplace(String workplace) {
		this.workplace = workplace;
	}

	/**
	 * @return the wwBefore8thRevolution
	 */
	public String getWwBefore8thRevolution() {
		return wwBefore8thRevolution;
	}

	/**
	 * @param wwBefore8thRevolution
	 *            the wwBefore8thRevolution to set
	 */
	public void setWwBefore8thRevolution(String wwBefore8thRevolution) {
		this.wwBefore8thRevolution = wwBefore8thRevolution;
	}

	/**
	 * @return the wwDuringResistanceWar
	 */
	public String getWwDuringResistanceWar() {
		return wwDuringResistanceWar;
	}

	/**
	 * @param wwDuringResistanceWar
	 *            the wwDuringResistanceWar to set
	 */
	public void setWwDuringResistanceWar(String wwDuringResistanceWar) {
		this.wwDuringResistanceWar = wwDuringResistanceWar;
	}

	/**
	 * @return the wwAfter1955
	 */
	public String getWwAfter1955() {
		return wwAfter1955;
	}

	/**
	 * @param wwAfter1955
	 *            the wwAfter1955 to set
	 */
	public void setWwAfter1955(String wwAfter1955) {
		this.wwAfter1955 = wwAfter1955;
	}

}
