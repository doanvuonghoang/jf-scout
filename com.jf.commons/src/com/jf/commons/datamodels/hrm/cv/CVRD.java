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

import java.util.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.jf.commons.datamodels.RecordHistEntity;
import com.jf.commons.datamodels.hrm.classifiers.RDType;

/**
 * Sơ yếu lý lịch - Khối Khen thưởng và Kỷ luật
 * @author Hoàng Doãn
 */
@DatabaseTable(tableName = "hrm_CVRDs")
public class CVRD extends RecordHistEntity {
	private static final long serialVersionUID = 1L;
	
	public static final String FIELD_CV = "cvId";
	public static final String FIELD_RD_TYPE = "rdTypeId";

	@DatabaseField(foreign = true, canBeNull = false, uniqueCombo = true, columnName = FIELD_CV)
	private CVPersonalDetail cVPersonalDetail;

	@DatabaseField(foreign = true, canBeNull = false, uniqueCombo = true, columnName = FIELD_RD_TYPE)
	private RDType rdType;
	
	@DatabaseField(canBeNull = false)
	private String content;
	
	@DatabaseField
	private Date fromDate;
	
	@DatabaseField
	private Date toDate;
}
