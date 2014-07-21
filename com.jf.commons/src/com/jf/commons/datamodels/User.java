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
@DatabaseTable(tableName = "Users")
public class User extends TrackableEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
    public static final String FIELD_NAME = "userName";
    public static final String FIELD_PASSWORD = "password";
    public static final String FIELD_VALID = "valid";
    public static final String FIELD_RECORD_STATUS = "recordStatus";
    
    @DatabaseField(unique = true, canBeNull = false, columnName = FIELD_NAME)
    private String userName;
    
    @DatabaseField(columnName = FIELD_PASSWORD)
    private String password;
    
    @DatabaseField
    private Date lastChangePass;
    
    @DatabaseField(canBeNull = false, defaultValue = "false", columnName = FIELD_VALID)
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
    	} else if(evt.getPropertyName().equals("password")) {
    		setLastChangePass(Calendar.getInstance().getTime());
    	}
    	
    	setRecordStatus(RecordStatus.UPDATE);
    }

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		String old = this.password;
		this.password = password;
		propertyChange.firePropertyChange("password", old, this.password);
	}

	/**
	 * @return the lastChangePass
	 */
	public Date getLastChangePass() {
		return lastChangePass;
	}

	/**
	 * This value is auto set, no need to call.
	 * @param lastChangePass the lastChangePass to set
	 */
	public void setLastChangePass(Date lastChangePass) {
		this.lastChangePass = lastChangePass;
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
		setLastModifiedTime(Calendar.getInstance().getTime());
	}
}
