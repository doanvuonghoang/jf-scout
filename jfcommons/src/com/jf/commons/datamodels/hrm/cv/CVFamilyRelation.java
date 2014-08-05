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

package com.jf.commons.datamodels.hrm.cv;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.jf.commons.datamodels.RecordHistEntity;
import com.jf.commons.datamodels.hrm.classifiers.FamilyRelationType;

/**
 * Sơ yếu lý lịch - Khối Anh chị em ruột
 * @author Hoàng Doãn
 */
@DatabaseTable(tableName = "hrm_CVFamilyRelations")
public class CVFamilyRelation extends RecordHistEntity {
	private static final long serialVersionUID = 1L;
	
	public static final String FIELD_CV = "cvId";
	public static final String FIELD_RELATION_TYPE = "relationTypeId";

	@DatabaseField(foreign = true, canBeNull = false, uniqueCombo = true, columnName = FIELD_CV)
	private CV cv;

	@DatabaseField(foreign = true, canBeNull = false, uniqueCombo = true, columnName = FIELD_RELATION_TYPE)
	private FamilyRelationType relationType;
	
	@DatabaseField(canBeNull = false, width = 4000)
	private String fullname;
	
	@DatabaseField
	private int birthYear;
	
	@DatabaseField
	private String birthPlace;
	
	@DatabaseField(width = 4000)
	private String address;
	
	@DatabaseField
	private String job;
	
	@DatabaseField
	private String jobPlace;
	
	@DatabaseField
	private String politicalLevel;
	
	@DatabaseField(width = 4000)
	private String otherInformation;

	/**
	 * @return the cv
	 */
	public CV getCv() {
		return cv;
	}

	/**
	 * @param cv the cv to set
	 */
	public void setCv(CV cv) {
		this.cv = cv;
	}

	/**
	 * @return the relationType
	 */
	public FamilyRelationType getRelationType() {
		return relationType;
	}

	/**
	 * @param relationType the relationType to set
	 */
	public void setRelationType(FamilyRelationType relationType) {
		this.relationType = relationType;
	}

	/**
	 * @return the fullname
	 */
	public String getFullname() {
		return fullname;
	}

	/**
	 * @param fullname the fullname to set
	 */
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	/**
	 * @return the birthYear
	 */
	public int getBirthYear() {
		return birthYear;
	}

	/**
	 * @param birthYear the birthYear to set
	 */
	public void setBirthYear(int birthYear) {
		this.birthYear = birthYear;
	}

	/**
	 * @return the birthPlace
	 */
	public String getBirthPlace() {
		return birthPlace;
	}

	/**
	 * @param birthPlace the birthPlace to set
	 */
	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the job
	 */
	public String getJob() {
		return job;
	}

	/**
	 * @param job the job to set
	 */
	public void setJob(String job) {
		this.job = job;
	}

	/**
	 * @return the jobPlace
	 */
	public String getJobPlace() {
		return jobPlace;
	}

	/**
	 * @param jobPlace the jobPlace to set
	 */
	public void setJobPlace(String jobPlace) {
		this.jobPlace = jobPlace;
	}

	/**
	 * @return the politicalLevel
	 */
	public String getPoliticalLevel() {
		return politicalLevel;
	}

	/**
	 * @param politicalLevel the politicalLevel to set
	 */
	public void setPoliticalLevel(String politicalLevel) {
		this.politicalLevel = politicalLevel;
	}

	/**
	 * @return the otherInformation
	 */
	public String getOtherInformation() {
		return otherInformation;
	}

	/**
	 * @param otherInformation the otherInformation to set
	 */
	public void setOtherInformation(String otherInformation) {
		this.otherInformation = otherInformation;
	}
}
