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

package com.jf.commons.datamodels.hrm.employee;

import java.util.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.jf.commons.datamodels.RecordHistEntity;
import com.jf.commons.datamodels.hrm.classifiers.EmployeeStatus;

/**
 *
 * @author Hoàng Doãn
 */
@DatabaseTable(tableName = "hrm_EmployeeStatusLogs")
public class EmployeeStatusLog extends RecordHistEntity {
	private static final long serialVersionUID = 1L;

	@DatabaseField(foreign = true, canBeNull = false, uniqueCombo = true)
	private Employee employee;

	@DatabaseField(foreign = true, canBeNull = false)
    private EmployeeStatus status;
    
    @DatabaseField
    private Date statusChangedFrom;
    
    @DatabaseField
    private Date statusChangedTo;
    
    @DatabaseField(width = 4000)
    private String statusChangedReason;
}
