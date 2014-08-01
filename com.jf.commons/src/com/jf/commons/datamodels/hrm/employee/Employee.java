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

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;
import com.jf.commons.datamodels.RecordHistEntity;
import com.jf.commons.datamodels.hrm.File;
import com.jf.commons.datamodels.hrm.classifiers.EmployeeStatus;

/**
 *
 * @author Hoàng Doãn
 */
@DatabaseTable(tableName = "hrm_Employees")
public class Employee extends RecordHistEntity {
	private static final long serialVersionUID = 1L;
	
    public static final String FIELD_CODE = "code";

    @DatabaseField(canBeNull = false, unique = true, columnName = FIELD_CODE)
    private String code;
    
    @DatabaseField(canBeNull = false)
    private String fullName;
    
    @DatabaseField(foreign = true)
    private File photo;
    
    @DatabaseField(foreign = true, canBeNull = false)
    private EmployeeStatus status;
    
    @ForeignCollectionField(eager = false)
    private ForeignCollection<Insurance> insurances;
    
    @ForeignCollectionField(eager = false)
    private ForeignCollection<Qualification> qualifications;
    
    @ForeignCollectionField(eager = false)
    private ForeignCollection<LabourAgreement> labourAgreements;
}
