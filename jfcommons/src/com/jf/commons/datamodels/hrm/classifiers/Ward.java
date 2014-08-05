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

import java.util.ResourceBundle;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.j256.ormlite.table.TableUtils;
import com.jf.commons.datamodels.RecordHistEntity;

/**
 *
 * @author Hoàng Doãn
 */
@DatabaseTable(tableName = "hrm_Wards")
public class Ward extends RecordHistEntity {
	private static final long serialVersionUID = 1L;

	@DatabaseField(canBeNull = false)
	private String name;

	@DatabaseField(canBeNull = false, foreign = true)
	private City city;

	@DatabaseField(canBeNull = false, foreign = true)
	private District district;

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

	/**
	 * @return the city
	 */
	public City getCity() {
		return city;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(City city) {
		this.city = city;
	}

	/**
	 * @return the district
	 */
	public District getDistrict() {
		return district;
	}

	/**
	 * @param district
	 *            the district to set
	 */
	public void setDistrict(District district) {
		this.district = district;
	}

	public static void generateData(Dao<Ward, Long> dao, Dao<City, Long> cdao,
			Dao<District, Long> ddao) throws Exception {
		// create table if not exists
		TableUtils
				.createTableIfNotExists(dao.getConnectionSource(), Ward.class);

		// insert wards
		ResourceBundle rb = ResourceBundle.getBundle("wards");
		for (String w : rb.getStringArray("wards")) {
			String[] parts = w.split(",");

			Ward m = new Ward();
			m.setNew(true);
			m.setName(parts[0].trim());
			m.setDistrict(ddao.queryForEq(District.FIELD_NAME, parts[1].trim())
					.get(0));
			m.setCity(cdao.queryForEq(City.FIELD_NAME, parts[2].trim()).get(0));
			m.setCreator("admin");

			dao.create(m);
		}
	}
}
