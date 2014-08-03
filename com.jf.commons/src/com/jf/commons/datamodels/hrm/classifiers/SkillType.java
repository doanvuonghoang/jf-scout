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

package com.jf.commons.datamodels.hrm.classifiers;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.jf.commons.datamodels.TypeBasedEntity;

/**
 * Loại chuyên môn, ngành đào tạo, ...
 * @author Hoàng Doãn
 */
@DatabaseTable(tableName = "hrm_SkillTypes")
public class SkillType extends TypeBasedEntity {
	private static final long serialVersionUID = 1L;

	/**
	 * Phân loại
	 */
	@DatabaseField
	private String category;

	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}

}
