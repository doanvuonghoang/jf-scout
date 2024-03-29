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

import com.j256.ormlite.field.DatabaseField;

/**
 *
 * @author Hoàng Doãn
 */
public class GeneratedIdEntity {
	private boolean isNew = false;

    /**
     * Id of entity
     */
    @DatabaseField(generatedId = true)
    protected long id;

    /**
     * Get id of entity
     * @return id
     */
    public long getId() {
        return id;
    }

    /**
     * Set id of entity
     * @param id the id
     */
    public void setId(long id) {
        this.id = id;
    }

	/**
	 * @return the isNew
	 */
	public boolean isNew() {
		return isNew;
	}

	/**
	 * @param isNew the isNew to set
	 */
	public void setNew(boolean isNew) {
		this.isNew = isNew;
	}

    
}
