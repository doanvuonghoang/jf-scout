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

package com.jf.commons.datamodels;

import java.beans.PropertyChangeEvent;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 *
 * @author Hoàng Doãn
 */
@DatabaseTable(tableName = "Roles")
public class Role extends TrackableEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
    public static final String FIELD_NAME = "roleName";
    public static final String FIELD_VALID = "valid";
    public static final String FIELD_RECORD_STATUS = "recordStatus";
    
    @DatabaseField(unique = true, canBeNull = false, columnName = FIELD_NAME)
    private String roleName;
    
    @DatabaseField(canBeNull = false, defaultValue = "true", columnName = FIELD_VALID)
    private Boolean valid;
    
    @DatabaseField
    private Date validChangedTime;
    
    @DatabaseField(defaultValue = "CREATE", columnName = FIELD_RECORD_STATUS)
    private RecordStatus recordStatus;
    
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
    	// TODO Auto-generated method stub
    	if(isNew()) return;
    	
    	super.propertyChange(evt);
    	
    	if(evt.getPropertyName().equals("valid")) {
    		setValidChangedTime(Calendar.getInstance().getTime());
    	}
    	
    	setRecordStatus(RecordStatus.UPDATE);
    }

	/**
	 * @return the roleName
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 * @param roleName the userName to set
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	/**
	 * @return the valid
	 */
	public boolean isValid() {
		return valid;
	}

	/**
	 * @param valid the valid to set
	 */
	public void setValid(boolean valid) {
		Boolean old = this.valid;
		this.valid = valid;
		propertyChange.firePropertyChange("valid", old, this.valid);
	}

	/**
	 * @return the validChangedTime
	 */
	public Date getValidChangedTime() {
		return validChangedTime;
	}

	/**
	 * This value is auto set, no need to call.
	 * @param validChangedTime the validChangedTime to set
	 */
	public void setValidChangedTime(Date validChangedTime) {
		this.validChangedTime = validChangedTime;
	}

	/**
	 * @return the recordStatus
	 */
	public RecordStatus getRecordStatus() {
		return recordStatus;
	}

	/**
	 * This value is auto set, no need to call.
	 * @param recordStatus the recordStatus to set
	 */
	public void setRecordStatus(RecordStatus recordStatus) {
		this.recordStatus = recordStatus;
	}
}
