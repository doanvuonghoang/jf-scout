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

import java.io.InputStreamReader;
import java.io.Serializable;

import org.apache.commons.configuration.PropertiesConfiguration;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.stmt.SelectArg;
import com.j256.ormlite.table.DatabaseTable;
import com.j256.ormlite.table.TableUtils;
import com.jf.commons.datamodels.RecordHistEntity;

/**
 *
 * @author Hoàng Doãn
 */
@DatabaseTable(tableName = "hrm_Districts")
public class District extends RecordHistEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public final static String FIELD_NAME = "name";

	@DatabaseField(canBeNull = false, columnName = FIELD_NAME)
	private String name;

	@DatabaseField(canBeNull = false, foreign = true)
	private City city;

	@ForeignCollectionField(eager = false)
	private ForeignCollection<Ward> wards;

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

	public ForeignCollection<Ward> wards() {
		return wards;
	}

	public static void generateData(Dao<District, Long> dao,
			Dao<City, Long> cdao) throws Exception {
		// create table if not exists
		TableUtils.createTableIfNotExists(dao.getConnectionSource(),
				District.class);

		// insert predefine data
		PropertiesConfiguration cfg = new PropertiesConfiguration();
		cfg.load(new InputStreamReader(City.class.getResourceAsStream("districts.properties"), "UTF-8"));
		for (String d : cfg.getStringArray("districts")) {
			String[] district = d.split(":");

			District m = new District();
			m.setNew(true);

			m.setName(district[0].trim());
			m.setCity(cdao.queryForEq(City.FIELD_NAME, new SelectArg(district[1].trim())).get(
					0));
			m.setCreator("admin");

			dao.create(m);
		}
	}
}
