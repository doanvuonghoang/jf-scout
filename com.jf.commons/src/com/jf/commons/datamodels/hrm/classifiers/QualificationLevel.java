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
import com.jf.commons.datamodels.RecordHistEntity;

/**
 * Trinh do chuyen mon
 * @author Hoàng Doãn
 */
@DatabaseTable(tableName = "hrm_QualificationLevels")
public class QualificationLevel extends RecordHistEntity {
	private static final long serialVersionUID = 1L;
	
	@DatabaseField(foreign = true, uniqueCombo = true)
	private QualificationType qualificationType;

	@DatabaseField(canBeNull = false, uniqueCombo = true)
	private String name;
	
	@DatabaseField(width = 4000)
	private String description;
	
	@DatabaseField(canBeNull = false, defaultValue = "1")
	private int showSequence;

	/**
	 * @return the qualificationType
	 */
	public QualificationType getQualificationType() {
		return qualificationType;
	}

	/**
	 * @param qualificationType the qualificationType to set
	 */
	public void setQualificationType(QualificationType qualificationType) {
		this.qualificationType = qualificationType;
	}

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
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the showSequence
	 */
	public int getShowSequence() {
		return showSequence;
	}

	/**
	 * @param showSequence the showSequence to set
	 */
	public void setShowSequence(int showSequence) {
		this.showSequence = showSequence;
	}

}
