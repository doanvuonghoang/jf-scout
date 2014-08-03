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

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;
import com.jf.commons.datamodels.RecordHistEntity;
import com.jf.commons.datamodels.hrm.File;
import com.jf.commons.datamodels.hrm.classifiers.EmployeeStatus;
import com.jf.commons.datamodels.hrm.contract.Contract;
import com.jf.commons.datamodels.hrm.insurance.Insurance;
import com.jf.commons.datamodels.hrm.training.Skill;

/**
 *
 * @author Hoàng Doãn
 */
@DatabaseTable(tableName = "hrm_Employees")
public class Employee extends RecordHistEntity {
	private static final long serialVersionUID = 1L;
	
    public static final String FIELD_CODE = "code";

    @DatabaseField(canBeNull = false, unique = true, columnName = FIELD_CODE)
    private String code;
    
    @DatabaseField(canBeNull = false)
    private String fullName;
    
    @DatabaseField(foreign = true)
    private File photo;
    
    @DatabaseField(foreign = true, canBeNull = false)
    private EmployeeStatus status;
    
    @ForeignCollectionField(eager = false)
    private ForeignCollection<Insurance> insurances;
    
    @ForeignCollectionField(eager = false)
    private ForeignCollection<Skill> skills;
    
    @ForeignCollectionField(eager = false)
    private ForeignCollection<Contract> contracts;

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
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * @param fullName the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * @return the photo
	 */
	public File getPhoto() {
		return photo;
	}

	/**
	 * @param photo the photo to set
	 */
	public void setPhoto(File photo) {
		this.photo = photo;
	}

	/**
	 * @return the status
	 */
	public EmployeeStatus getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(EmployeeStatus status) {
		this.status = status;
	}

	/**
	 * @return the insurances
	 */
	public ForeignCollection<Insurance> getInsurances() {
		return insurances;
	}

	/**
	 * @param insurances the insurances to set
	 */
	public void setInsurances(ForeignCollection<Insurance> insurances) {
		this.insurances = insurances;
	}

	/**
	 * @return the skills
	 */
	public ForeignCollection<Skill> getSkills() {
		return skills;
	}

	/**
	 * @param skills the skills to set
	 */
	public void setSkills(ForeignCollection<Skill> skills) {
		this.skills = skills;
	}

	/**
	 * @return the contracts
	 */
	public ForeignCollection<Contract> getContracts() {
		return contracts;
	}

	/**
	 * @param contracts the contracts to set
	 */
	public void setContracts(ForeignCollection<Contract> contracts) {
		this.contracts = contracts;
	}
    
}
