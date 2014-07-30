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
@DatabaseTable(tableName = "hrm_QualificationLevels")
public class QualificationLevel extends TrackableEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public final static String FIELD_RECORD_STATUS = "recordStatus";
	
	@DatabaseField(canBeNull = false)
	private String name;
	
    @DatabaseField(defaultValue = "CREATE", columnName = FIELD_RECORD_STATUS)
    private RecordStatus recordStatus;

    /**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		String old = this.name;
		this.name = name;
		
		this.propertyChange.firePropertyChange("name", old, name);
	}

	@Override
    public void propertyChange(PropertyChangeEvent evt) {
    	if(isNew()) return;
    	
    	super.propertyChange(evt);
    	    	
    	setRecordStatus(RecordStatus.UPDATE);
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
