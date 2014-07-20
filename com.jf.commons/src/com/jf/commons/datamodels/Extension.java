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
@DatabaseTable(tableName = "Extensions")
public class Extension extends TrackableEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
    public static final String FIELD_EXT_CLASS_NAME = "extClassName";
    public static final String FIELD_RECORD_STATUS = "recordStatus";
    public static final String FIELD_DEBUG = "debug";
    
    @DatabaseField(uniqueCombo = true, canBeNull = false, columnName = FIELD_EXT_CLASS_NAME)
    public String extClassName;
    
    @DatabaseField
    public String author;
    
    @DatabaseField
    public String version;
    
    @DatabaseField(defaultValue = "false", columnName = FIELD_DEBUG)
    public boolean debug;
    
    @DatabaseField(defaultValue = "CREATE", uniqueCombo = true, columnName = FIELD_RECORD_STATUS)
    public RecordStatus recordStatus;
}
