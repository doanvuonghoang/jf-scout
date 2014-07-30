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
import java.util.Date;

import org.eclipse.scout.commons.DateUtility;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;
import com.jf.commons.datamodels.RecordStatus;
import com.jf.commons.datamodels.TrackableEntity;

/**
 *
 * @author Hoàng Doãn
 */
@DatabaseTable(tableName = "hrm_Departments")
public class Department extends TrackableEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String FIELD_PARENT_ID = "parentId";
	public static final String FIELD_CODE = "code";
	public static final String FIELD_NAME = "name";
	public static final String FIELD_RECORD_STATUS = "recordStatus";

	@DatabaseField(foreign = true, foreignColumnName = FIELD_PARENT_ID)
	private Department parent;

	@DatabaseField(uniqueCombo = true)
	private String code;

	@DatabaseField(uniqueCombo = true, canBeNull = false, columnName = FIELD_NAME)
	private String name;

	@DatabaseField(width = 4000)
	private String description;

	@DatabaseField(width = 2000)
	private String address;

	@DatabaseField(width = 200)
	private String phone;

	@DatabaseField(width = 200)
	private String fax;

	@DatabaseField(width = 200)
	private String email;

	@DatabaseField(defaultValue = "CREATE", columnName = FIELD_RECORD_STATUS)
	private RecordStatus recordStatus;

	@ForeignCollectionField(foreignFieldName = "parent")
	private ForeignCollection<Department> subUnits;

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (isNew())
			return;

		super.propertyChange(evt);

		setRecordStatus(RecordStatus.UPDATE);
	}

	public ForeignCollection<Department> subUnits() {
		return subUnits;
	}

	/**
	 * @return the parent
	 */
	public Department getParent() {
		return parent;
	}

	/**
	 * @param parent
	 *            the parent to set
	 */
	public void setParent(Department parent) {
		this.parent = parent;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone
	 *            the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the fax
	 */
	public String getFax() {
		return fax;
	}

	/**
	 * @param fax
	 *            the fax to set
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
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

	/**
	 * Create an unique department code
	 * 
	 * @return
	 */
	public static String createCode() {
		Date cur = Calendar.getInstance().getTime();
		String result = "DV-" + DateUtility.format(cur, "ddMMyyyy") + "-"
				+ String.format("%06d", cur.getTime());

		return result;
	}
}
