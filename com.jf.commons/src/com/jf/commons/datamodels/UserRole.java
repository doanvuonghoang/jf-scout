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

import java.io.Serializable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 *
 * @author Hoàng Doãn
 */
@DatabaseTable(tableName = "UserRole")
public class UserRole extends TrackableEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
    public static final String FIELD_RECORD_STATUS = "recordStatus";
    public static final String FIELD_ROLE_ID = "roleId";
    public static final String FIELD_USER_ID = "userId";
    
    @DatabaseField(canBeNull = false, foreign = true, uniqueCombo = true, columnName = FIELD_USER_ID)
    private User user;
    
    @DatabaseField(canBeNull = false, foreign = true, uniqueCombo = true, columnName = FIELD_ROLE_ID)
    private Role role;
    
    @DatabaseField(defaultValue = "CREATE", columnName = FIELD_RECORD_STATUS)
    private RecordStatus recordStatus;
    
	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the role
	 */
	public Role getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(Role role) {
		this.role = role;
	}

	/**
	 * @return the recordStatus
	 */
	public RecordStatus getRecordStatus() {
		return recordStatus;
	}

	/**
	 * @param recordStatus the recordStatus to set
	 */
	public void setRecordStatus(RecordStatus recordStatus) {
		this.recordStatus = recordStatus;
	}
    
}
