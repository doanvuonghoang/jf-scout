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

import java.io.Serializable;
import java.util.ResourceBundle;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;
import com.j256.ormlite.table.TableUtils;
import com.jf.commons.datamodels.RecordHistEntity;

/**
 *
 * @author Hoàng Doãn
 */
@DatabaseTable(tableName = "hrm_Cities")
public class City extends RecordHistEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public final static String FIELD_NAME = "name";

	@DatabaseField(canBeNull = false, columnName = FIELD_NAME)
	private String name;

	@DatabaseField(canBeNull = false, defaultValue = "Tỉnh")
	private String namePrefix;

	@ForeignCollectionField(eager = false)
	private ForeignCollection<District> districts;

	@ForeignCollectionField(eager = false)
	private ForeignCollection<Ward> wards;

	/**
	 * @return the districts
	 */
	public ForeignCollection<District> districts() {
		return districts;
	}

	/**
	 * @return the wards
	 */
	public ForeignCollection<Ward> wards() {
		return wards;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		String old = this.name;
		this.name = name;

		this.propertyChange.firePropertyChange("name", old, name);
	}

	public void setNamePrefix(String namePrefix) {
		this.namePrefix = namePrefix;
	}

	public String getNamePrefix() {
		return namePrefix;
	}

	/**
	 * Create table and insert predefine data
	 * 
	 * @param dao
	 * @throws Exception
	 */
	public static void generateData(Dao<City, Long> dao) throws Exception {
		// create table if not exists
		TableUtils
				.createTableIfNotExists(dao.getConnectionSource(), City.class);

		// insert predefine data
		ResourceBundle rb = ResourceBundle.getBundle("cities");
		for(String c : rb.getStringArray("cities")) {
			String[] city = c.split(",");
			City m = new City();
			m.setNew(true);

			m.setName(city[1].trim());
			m.setNamePrefix(city[2].trim());
			m.setCreator("admin");

			dao.create(m);
		}
	}
}
