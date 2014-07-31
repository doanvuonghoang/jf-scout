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

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.jf.commons.datamodels.TypeBasedEntity;

/**
 *
 * @author Hoàng Doãn
 */
@DatabaseTable(tableName = "hrm_FamilyMemberTypes")
public class FamilyMemberType extends TypeBasedEntity {
	private static final long serialVersionUID = 1L;

	/**
	 * Co phai thong tin co ban hay khong
	 */
	@DatabaseField(canBeNull = false, defaultValue = "true")
	private boolean isExtended;

	/**
	 * @return the isExtended
	 */
	public boolean isExtended() {
		return isExtended;
	}

	/**
	 * @param isExtended
	 *            the isExtended to set
	 */
	public void setExtended(boolean isExtended) {
		this.isExtended = isExtended;
	}

}
