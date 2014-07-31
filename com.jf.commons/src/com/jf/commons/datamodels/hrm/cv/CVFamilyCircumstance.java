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

/**
 * Sơ yếu lý lịch - Khối Hoàn cảnh gia đình
 * @author Hoàng Doãn
 */
@DatabaseTable(tableName = "hrm_CVFamilyCircumstances")
public class CVFamilyCircumstance extends RecordHistEntity {
	private static final long serialVersionUID = 1L;
	
	public static final String FIELD_CV = "cvId";

	@DatabaseField(foreign = true, canBeNull = false, unique = true, columnName = FIELD_CV)
	private CV cv;

	@DatabaseField(canBeNull = false)
	private String fatherFullName;
	
	@DatabaseField
	private int fatherAge;
	
	@DatabaseField
	private String fatherJob;
	
	@DatabaseField(width = 4000)
	private String fatherWwBefore8thRev;
	
	@DatabaseField(width = 4000)
	private String fatherWwDuringResistanceWar;
	
	@DatabaseField(width = 4000)
	private String fatherWwAfter1955;
	
	@DatabaseField(canBeNull = false)
	private String motherFullName;
	
	@DatabaseField
	private int motherAge;
	
	@DatabaseField
	private String motherJob;
	
	@DatabaseField(width = 4000)
	private String motherWwBefore8thRev;
	
	@DatabaseField(width = 4000)
	private String motherWwDuringResistanceWar;
	
	@DatabaseField(width = 4000)
	private String motherWwAfter1955;

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
	 * @return the fatherFullName
	 */
	public String getFatherFullName() {
		return fatherFullName;
	}

	/**
	 * @param fatherFullName the fatherFullName to set
	 */
	public void setFatherFullName(String fatherFullName) {
		this.fatherFullName = fatherFullName;
	}

	/**
	 * @return the fatherAge
	 */
	public int getFatherAge() {
		return fatherAge;
	}

	/**
	 * @param fatherAge the fatherAge to set
	 */
	public void setFatherAge(int fatherAge) {
		this.fatherAge = fatherAge;
	}

	/**
	 * @return the fatherJob
	 */
	public String getFatherJob() {
		return fatherJob;
	}

	/**
	 * @param fatherJob the fatherJob to set
	 */
	public void setFatherJob(String fatherJob) {
		this.fatherJob = fatherJob;
	}

	/**
	 * Trước CM Tháng 8 làm gì, ở đâu?
	 * @return the fatherWwBefore8thRev
	 */
	public String getFatherWwBefore8thRev() {
		return fatherWwBefore8thRev;
	}

	/**
	 * @param fatherWwBefore8thRev the fatherWwBefore8thRev to set
	 */
	public void setFatherWwBefore8thRev(String fatherWwBefore8thRev) {
		this.fatherWwBefore8thRev = fatherWwBefore8thRev;
	}

	/**
	 * Trong Kháng chiến chống Pháp làm gì ở đâu?
	 * @return the fatherWwDuringResistanceWar
	 */
	public String getFatherWwDuringResistanceWar() {
		return fatherWwDuringResistanceWar;
	}

	/**
	 * @param fatherWwDuringResistanceWar the fatherWwDuringResistanceWar to set
	 */
	public void setFatherWwDuringResistanceWar(String fatherWwDuringResistanceWar) {
		this.fatherWwDuringResistanceWar = fatherWwDuringResistanceWar;
	}

	/**
	 * Sau 1955 làm gì, ở đâu?
	 * @return the fatherWwAfter1955
	 */
	public String getFatherWwAfter1955() {
		return fatherWwAfter1955;
	}

	/**
	 * @param fatherWwAfter1955 the fatherWwAfter1955 to set
	 */
	public void setFatherWwAfter1955(String fatherWwAfter1955) {
		this.fatherWwAfter1955 = fatherWwAfter1955;
	}

	/**
	 * @return the motherFullName
	 */
	public String getMotherFullName() {
		return motherFullName;
	}

	/**
	 * @param motherFullName the motherFullName to set
	 */
	public void setMotherFullName(String motherFullName) {
		this.motherFullName = motherFullName;
	}

	/**
	 * @return the motherAge
	 */
	public int getMotherAge() {
		return motherAge;
	}

	/**
	 * @param motherAge the motherAge to set
	 */
	public void setMotherAge(int motherAge) {
		this.motherAge = motherAge;
	}

	/**
	 * @return the motherJob
	 */
	public String getMotherJob() {
		return motherJob;
	}

	/**
	 * @param motherJob the motherJob to set
	 */
	public void setMotherJob(String motherJob) {
		this.motherJob = motherJob;
	}

	/**
	 * Trước CM Tháng 8 làm gì, ở đâu?
	 * @return the motherWwBefore8thRev
	 */
	public String getMotherWwBefore8thRev() {
		return motherWwBefore8thRev;
	}

	/**
	 * @param motherWwBefore8thRev the motherWwBefore8thRev to set
	 */
	public void setMotherWwBefore8thRev(String motherWwBefore8thRev) {
		this.motherWwBefore8thRev = motherWwBefore8thRev;
	}

	/**
	 * Trong Kháng chiến chống Pháp làm gì ở đâu?
	 * @return the motherWwDuringResistanceWar
	 */
	public String getMotherWwDuringResistanceWar() {
		return motherWwDuringResistanceWar;
	}

	/**
	 * @param motherWwDuringResistanceWar the motherWwDuringResistanceWar to set
	 */
	public void setMotherWwDuringResistanceWar(String motherWwDuringResistanceWar) {
		this.motherWwDuringResistanceWar = motherWwDuringResistanceWar;
	}

	/**
	 * Sau 1955 làm gì, ở đâu?
	 * @return the motherWwAfter1955
	 */
	public String getMotherWwAfter1955() {
		return motherWwAfter1955;
	}

	/**
	 * @param motherWwAfter1955 the motherWwAfter1955 to set
	 */
	public void setMotherWwAfter1955(String motherWwAfter1955) {
		this.motherWwAfter1955 = motherWwAfter1955;
	}

}
