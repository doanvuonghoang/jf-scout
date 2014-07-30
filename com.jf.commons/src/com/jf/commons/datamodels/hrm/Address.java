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

package com.jf.commons.datamodels.hrm;

import java.beans.PropertyChangeEvent;
import java.io.Serializable;
import java.util.Calendar;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.jf.commons.datamodels.RecordStatus;
import com.jf.commons.datamodels.TrackableEntity;

/**
 *
 * @author Hoàng Doãn
 */
@DatabaseTable(tableName = "hrm_Addresses")
public class Address extends TrackableEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public final static String FIELD_RECORD_STATUS = "recordStatus";

	@DatabaseField(defaultValue = "CREATE", columnName = FIELD_RECORD_STATUS)
	private RecordStatus recordStatus;

	@DatabaseField(uniqueCombo = true, foreign = true)
	private City city;

	@DatabaseField(uniqueCombo = true, foreign = true)
	private District district;

	@DatabaseField(uniqueCombo = true, foreign = true)
	private Ward ward;

	@DatabaseField(uniqueCombo = true)
	private String freeText;

	@DatabaseField(canBeNull = false)
	private String toString;

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (isNew())
			return;

		super.propertyChange(evt);

		setRecordStatus(RecordStatus.UPDATE);
	}

	/**
	 * @return the city
	 */
	public City getCity() {
		return city;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(City city) {
		this.city = city;
	}

	/**
	 * @return the district
	 */
	public District getDistrict() {
		return district;
	}

	/**
	 * @param district
	 *            the district to set
	 */
	public void setDistrict(District district) {
		this.district = district;
	}

	/**
	 * @return the ward
	 */
	public Ward getWard() {
		return ward;
	}

	/**
	 * @param ward
	 *            the ward to set
	 */
	public void setWard(Ward ward) {
		this.ward = ward;
	}

	/**
	 * @return the freeText
	 */
	public String getFreeText() {
		return freeText;
	}

	/**
	 * @param freeText
	 *            the freeText to set
	 */
	public void setFreeText(String freeText) {
		this.freeText = freeText;
	}

	/**
	 * @return the toString
	 */
	public String getToString() {
		return toString;
	}

	/**
	 * @param toString
	 *            the toString to set
	 */
	public void setToString(String toString) {
		this.toString = toString;
	}

	/**
	 * @return the recordStatus
	 */
	public RecordStatus getRecordStatus() {
		return recordStatus;
	}

	/**
	 * This value is auto set, no need to call.
	 * 
	 * @param recordStatus
	 *            the recordStatus to set
	 */
	public void setRecordStatus(RecordStatus recordStatus) {
		this.recordStatus = recordStatus;
		setLastModifiedTime(Calendar.getInstance().getTime());
	}
	
	@Override
	public String toString() {
		return this.toString;
	}
	
	@Override
	public boolean equals(Object obj) {
		return this.toString.equals(obj.toString());
	}

	public static String toString(Address a) {
		if (a.getCity() != null)
			return String.format("%s, %s, %s, %s", a.getCity().getName(), a
					.getDistrict().getName(), a.getWard().getName(), a
					.getFreeText());
		else
			return a.getFreeText();
	}
}
