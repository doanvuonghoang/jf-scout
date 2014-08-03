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
import com.jf.commons.datamodels.hrm.classifiers.JobTitle;
import com.jf.commons.datamodels.hrm.classifiers.MaritalStatus;
import com.jf.commons.datamodels.hrm.classifiers.SkillType;

/**
 *
 * @author Hoàng Doãn
 */
@DatabaseTable(tableName = "hrm_CVPersonalDetails")
public class CVPersonalDetail extends RecordHistEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * Bi danh
	 */
	@DatabaseField
	private String aliasName;

	@DatabaseField
	private Date birthdate;

	@DatabaseField(foreign = true)
	private Address birthplace;

	@DatabaseField(foreign = true, canBeNull = false, unique = true)
	private CV cv;

	@DatabaseField
	private String ethnic;

	@DatabaseField
	private String familyComposition;

	/**
	 * Ho va ten
	 */
	@DatabaseField(canBeNull = false)
	private String fullName;

	@DatabaseField
	private String healthStatus;

	@DatabaseField
	private String job;

	@DatabaseField(foreign = true)
	private JobTitle jobTitle;

	@DatabaseField(foreign = true)
	private MaritalStatus maritalStatus;

	@DatabaseField(foreign = true)
	private Address motherland;

	/**
	 * Ten thuong goi
	 */
	@DatabaseField
	private String nickName;

	@DatabaseField
	private Date partyJoinedDate;

	@DatabaseField(foreign = true)
	private Address partyJoinedPlace;

	@DatabaseField
	private String personComposition;

	@DatabaseField
	private String religion;

	@DatabaseField(foreign = true)
	private Address residentAddress;

	@DatabaseField(foreign = true)
	private SkillType skillType;

	@DatabaseField
	private Date unionJoinedDate;

	@DatabaseField(foreign = true)
	private Address unionJoinedPlace;

	/**
	 * @return the aliasName
	 */
	public String getAliasName() {
		return aliasName;
	}

	/**
	 * @return the birthdate
	 */
	public Date getBirthdate() {
		return birthdate;
	}

	/**
	 * @return the birthplace
	 */
	public Address getBirthplace() {
		return birthplace;
	}

	/**
	 * @return the cv
	 */
	public CV getCv() {
		return cv;
	}

	/**
	 * @return the ethnic
	 */
	public String getEthnic() {
		return ethnic;
	}

	/**
	 * @return the familyComposition
	 */
	public String getFamilyComposition() {
		return familyComposition;
	}

	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * @return the healthStatus
	 */
	public String getHealthStatus() {
		return healthStatus;
	}

	/**
	 * @return the job
	 */
	public String getJob() {
		return job;
	}

	/**
	 * @return the jobTitle
	 */
	public JobTitle getJobTitle() {
		return jobTitle;
	}

	/**
	 * @return the maritalStatus
	 */
	public MaritalStatus getMaritalStatus() {
		return maritalStatus;
	}

	/**
	 * @return the motherland
	 */
	public Address getMotherland() {
		return motherland;
	}

	/**
	 * @return the nickName
	 */
	public String getNickName() {
		return nickName;
	}

	/**
	 * @return the partyJoinedDate
	 */
	public Date getPartyJoinedDate() {
		return partyJoinedDate;
	}

	/**
	 * @return the partyJoinedPlace
	 */
	public Address getPartyJoinedPlace() {
		return partyJoinedPlace;
	}

	/**
	 * @return the personComposition
	 */
	public String getPersonComposition() {
		return personComposition;
	}

	/**
	 * @return the religion
	 */
	public String getReligion() {
		return religion;
	}

	/**
	 * @return the residentAddress
	 */
	public Address getResidentAddress() {
		return residentAddress;
	}

	/**
	 * @return the skillType
	 */
	public SkillType getSkillType() {
		return skillType;
	}

	/**
	 * @return the unionJoinedDate
	 */
	public Date getUnionJoinedDate() {
		return unionJoinedDate;
	}

	/**
	 * @return the unionJoinedPlace
	 */
	public Address getUnionJoinedPlace() {
		return unionJoinedPlace;
	}

	/**
	 * @param aliasName
	 *            the aliasName to set
	 */
	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}

	/**
	 * @param birthdate
	 *            the birthdate to set
	 */
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	/**
	 * @param birthplace
	 *            the birthplace to set
	 */
	public void setBirthplace(Address birthplace) {
		this.birthplace = birthplace;
	}

	/**
	 * @param cv
	 *            the cv to set
	 */
	public void setCv(CV cv) {
		this.cv = cv;
	}

	/**
	 * @param ethnic
	 *            the ethnic to set
	 */
	public void setEthnic(String ethnic) {
		this.ethnic = ethnic;
	}

	/**
	 * @param familyComposition
	 *            the familyComposition to set
	 */
	public void setFamilyComposition(String familyComposition) {
		this.familyComposition = familyComposition;
	}

	/**
	 * @param fullName
	 *            the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * @param healthStatus
	 *            the healthStatus to set
	 */
	public void setHealthStatus(String healthStatus) {
		this.healthStatus = healthStatus;
	}

	/**
	 * @param job
	 *            the job to set
	 */
	public void setJob(String job) {
		this.job = job;
	}

	/**
	 * @param jobTitle the jobTitle to set
	 */
	public void setJobTitle(JobTitle jobTitle) {
		this.jobTitle = jobTitle;
	}

	/**
	 * @param maritalStatus
	 *            the maritalStatus to set
	 */
	public void setMaritalStatus(MaritalStatus maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	/**
	 * @param motherland
	 *            the motherland to set
	 */
	public void setMotherland(Address motherland) {
		this.motherland = motherland;
	}

	/**
	 * @param nickName
	 *            the nickName to set
	 */
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	/**
	 * @param partyJoinedDate
	 *            the partyJoinedDate to set
	 */
	public void setPartyJoinedDate(Date partyJoinedDate) {
		this.partyJoinedDate = partyJoinedDate;
	}

	/**
	 * @param partyJoinedPlace
	 *            the partyJoinedPlace to set
	 */
	public void setPartyJoinedPlace(Address partyJoinedPlace) {
		this.partyJoinedPlace = partyJoinedPlace;
	}

	/**
	 * @param personComposition
	 *            the personComposition to set
	 */
	public void setPersonComposition(String personComposition) {
		this.personComposition = personComposition;
	}

	/**
	 * @param religion
	 *            the religion to set
	 */
	public void setReligion(String religion) {
		this.religion = religion;
	}

	/**
	 * @param residentAddress
	 *            the residentAddress to set
	 */
	public void setResidentAddress(Address residentAddress) {
		this.residentAddress = residentAddress;
	}

	/**
	 * @param skillType the skillType to set
	 */
	public void setSkillType(SkillType skillType) {
		this.skillType = skillType;
	}

	/**
	 * @param unionJoinedDate
	 *            the unionJoinedDate to set
	 */
	public void setUnionJoinedDate(Date unionJoinedDate) {
		this.unionJoinedDate = unionJoinedDate;
	}

	/**
	 * @param unionJoinedPlace
	 *            the unionJoinedPlace to set
	 */
	public void setUnionJoinedPlace(Address unionJoinedPlace) {
		this.unionJoinedPlace = unionJoinedPlace;
	}

}
