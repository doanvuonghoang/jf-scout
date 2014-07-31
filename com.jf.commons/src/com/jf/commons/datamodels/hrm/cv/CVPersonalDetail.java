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

import java.io.Serializable;
import java.util.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.jf.commons.datamodels.RecordHistEntity;
import com.jf.commons.datamodels.hrm.Address;
import com.jf.commons.datamodels.hrm.classifiers.EducationLevel;
import com.jf.commons.datamodels.hrm.classifiers.ForeignLanguageLevel;
import com.jf.commons.datamodels.hrm.classifiers.MaritalStatus;
import com.jf.commons.datamodels.hrm.classifiers.QualificationLevel;

/**
 *
 * @author Hoàng Doãn
 */
@DatabaseTable(tableName = "hrm_CVPersonalDetails")
public class CVPersonalDetail extends RecordHistEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@DatabaseField(foreign = true, canBeNull = false, unique = true)
	private CV cv;
	
	/**
	 * Ho va ten
	 */
	@DatabaseField(canBeNull = false)
	private String fullName;
	
	/**
	 * Bi danh
	 */
	@DatabaseField
	private String aliasName;
	
	/**
	 * Ten thuong goi
	 */
	@DatabaseField
	private String nickName;
	
	@DatabaseField
	private Date birthdate;
	
	@DatabaseField(foreign = true)
	private Address birthplace;
	
	@DatabaseField(foreign = true)
	private Address motherland;
	
	@DatabaseField(foreign = true)
	private Address residentAddress;

	@DatabaseField
	private String ethnic;
	
	@DatabaseField
	private String religion;
	
	@DatabaseField
	private String familyComposition;
	
	@DatabaseField
	private String personComposition;
	
	@DatabaseField(foreign = true)
	private EducationLevel educationLevel;
	
	@DatabaseField(foreign = true)
	private ForeignLanguageLevel foreignLanguageLevel;
	
	@DatabaseField
	private Date unionJoinedDate;
	
	@DatabaseField(foreign = true)
	private Address unionJoinedPlace;
	
	@DatabaseField
	private Date partyJoinedDate;
	
	@DatabaseField(foreign = true)
	private Address partyJoinedPlace;
	
	@DatabaseField
	private String healthStatus;
	
	@DatabaseField
	private String job;
	
	@DatabaseField(foreign = true)
	private QualificationLevel qualificationLevel;
	
	@DatabaseField(foreign = true)
	private MaritalStatus maritalStatus;
    
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
	 * @return the maritalStatus
	 */
	public MaritalStatus getMaritalStatus() {
		return maritalStatus;
	}

	/**
	 * @param maritalStatus the maritalStatus to set
	 */
	public void setMaritalStatus(MaritalStatus maritalStatus) {
		this.maritalStatus = maritalStatus;
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
	 * @return the aliasName
	 */
	public String getAliasName() {
		return aliasName;
	}

	/**
	 * @param aliasName the aliasName to set
	 */
	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}

	/**
	 * @return the nickName
	 */
	public String getNickName() {
		return nickName;
	}

	/**
	 * @param nickName the nickName to set
	 */
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	/**
	 * @return the birthdate
	 */
	public Date getBirthdate() {
		return birthdate;
	}

	/**
	 * @param birthdate the birthdate to set
	 */
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	/**
	 * @return the birthplace
	 */
	public Address getBirthplace() {
		return birthplace;
	}

	/**
	 * @param birthplace the birthplace to set
	 */
	public void setBirthplace(Address birthplace) {
		this.birthplace = birthplace;
	}

	/**
	 * @return the motherland
	 */
	public Address getMotherland() {
		return motherland;
	}

	/**
	 * @param motherland the motherland to set
	 */
	public void setMotherland(Address motherland) {
		this.motherland = motherland;
	}

	/**
	 * @return the residentAddress
	 */
	public Address getResidentAddress() {
		return residentAddress;
	}

	/**
	 * @param residentAddress the residentAddress to set
	 */
	public void setResidentAddress(Address residentAddress) {
		this.residentAddress = residentAddress;
	}

	/**
	 * @return the ethnic
	 */
	public String getEthnic() {
		return ethnic;
	}

	/**
	 * @param ethnic the ethnic to set
	 */
	public void setEthnic(String ethnic) {
		this.ethnic = ethnic;
	}

	/**
	 * @return the religion
	 */
	public String getReligion() {
		return religion;
	}

	/**
	 * @param religion the religion to set
	 */
	public void setReligion(String religion) {
		this.religion = religion;
	}

	/**
	 * @return the familyComposition
	 */
	public String getFamilyComposition() {
		return familyComposition;
	}

	/**
	 * @param familyComposition the familyComposition to set
	 */
	public void setFamilyComposition(String familyComposition) {
		this.familyComposition = familyComposition;
	}

	/**
	 * @return the personComposition
	 */
	public String getPersonComposition() {
		return personComposition;
	}

	/**
	 * @param personComposition the personComposition to set
	 */
	public void setPersonComposition(String personComposition) {
		this.personComposition = personComposition;
	}

	/**
	 * @return the educationLevel
	 */
	public EducationLevel getEducationLevel() {
		return educationLevel;
	}

	/**
	 * @param educationLevel the educationLevel to set
	 */
	public void setEducationLevel(EducationLevel educationLevel) {
		this.educationLevel = educationLevel;
	}

	/**
	 * @return the foreignLanguageSkill
	 */
	public ForeignLanguageLevel getForeignLanguageLevel() {
		return foreignLanguageLevel;
	}

	/**
	 * @param foreignLanguageLevel the foreignLanguageSkill to set
	 */
	public void setForeignLanguageLevel(ForeignLanguageLevel foreignLanguageLevel) {
		this.foreignLanguageLevel = foreignLanguageLevel;
	}

	/**
	 * @return the unionJoinedDate
	 */
	public Date getUnionJoinedDate() {
		return unionJoinedDate;
	}

	/**
	 * @param unionJoinedDate the unionJoinedDate to set
	 */
	public void setUnionJoinedDate(Date unionJoinedDate) {
		this.unionJoinedDate = unionJoinedDate;
	}

	/**
	 * @return the unionJoinedPlace
	 */
	public Address getUnionJoinedPlace() {
		return unionJoinedPlace;
	}

	/**
	 * @param unionJoinedPlace the unionJoinedPlace to set
	 */
	public void setUnionJoinedPlace(Address unionJoinedPlace) {
		this.unionJoinedPlace = unionJoinedPlace;
	}

	/**
	 * @return the partyJoinedDate
	 */
	public Date getPartyJoinedDate() {
		return partyJoinedDate;
	}

	/**
	 * @param partyJoinedDate the partyJoinedDate to set
	 */
	public void setPartyJoinedDate(Date partyJoinedDate) {
		this.partyJoinedDate = partyJoinedDate;
	}

	/**
	 * @return the partyJoinedPlace
	 */
	public Address getPartyJoinedPlace() {
		return partyJoinedPlace;
	}

	/**
	 * @param partyJoinedPlace the partyJoinedPlace to set
	 */
	public void setPartyJoinedPlace(Address partyJoinedPlace) {
		this.partyJoinedPlace = partyJoinedPlace;
	}

	/**
	 * @return the healthStatus
	 */
	public String getHealthStatus() {
		return healthStatus;
	}

	/**
	 * @param healthStatus the healthStatus to set
	 */
	public void setHealthStatus(String healthStatus) {
		this.healthStatus = healthStatus;
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
	 * @return the qualificationLevel
	 */
	public QualificationLevel getQualificationLevel() {
		return qualificationLevel;
	}

	/**
	 * @param qualificationLevel the qualificationLevel to set
	 */
	public void setQualificationLevel(QualificationLevel qualificationLevel) {
		this.qualificationLevel = qualificationLevel;
	}
}
