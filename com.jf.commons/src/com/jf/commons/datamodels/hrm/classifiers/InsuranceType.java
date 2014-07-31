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
import com.jf.commons.datamodels.TypeBasedEntity;

/**
 *
 * @author Hoàng Doãn
 */
@DatabaseTable(tableName = "hrm_InsuranceTypes")
public class InsuranceType extends TypeBasedEntity {
	private static final long serialVersionUID = 1L;

	/**
	 * Ti le phai nop bao hiem
	 */
	@DatabaseField
	private double rate;

	/**
	 * Nam tuong ung voi ty le theo quy dinh ve bao hiem
	 */
	@DatabaseField
	private int yearAssc;

	/**
	 * Co bat buoc phai nop hay khong
	 */
	@DatabaseField(canBeNull = false, defaultValue = "false")
	private boolean isRequired;

	/**
	 * Ti le nguoi lao dong can dong
	 */
	@DatabaseField
	private double employeeRate;

	/**
	 * Ti le nha tuyen dung dong
	 */
	@DatabaseField
	private double employerRate;

	/**
	 * @return the rate
	 */
	public double getRate() {
		return rate;
	}

	/**
	 * @param rate
	 *            the rate to set
	 */
	public void setRate(double rate) {
		double old = this.rate;
		this.rate = rate;

		this.propertyChange.firePropertyChange("rate", old, rate);
	}

	/**
	 * @return the yearAssc
	 */
	public int getYearAssc() {
		return yearAssc;
	}

	/**
	 * @param yearAssc
	 *            the yearAssc to set
	 */
	public void setYearAssc(int yearAssc) {
		int old = this.yearAssc;
		this.yearAssc = yearAssc;

		this.propertyChange.firePropertyChange("yearAssc", old, yearAssc);
	}

	/**
	 * @return the isRequired
	 */
	public boolean isRequired() {
		return isRequired;
	}

	/**
	 * @param isRequired
	 *            the isRequired to set
	 */
	public void setRequired(boolean isRequired) {
		boolean old = this.isRequired;
		this.isRequired = isRequired;

		this.propertyChange.firePropertyChange("required", old, isRequired);
	}

	/**
	 * @return the employeeRate
	 */
	public double getEmployeeRate() {
		return employeeRate;
	}

	/**
	 * @param employeeRate
	 *            the employeeRate to set
	 */
	public void setEmployeeRate(double employeeRate) {
		double old = this.employeeRate;
		this.employeeRate = employeeRate;

		this.propertyChange.firePropertyChange("employeeRate", old,
				employeeRate);
	}

	/**
	 * @return the employerRate
	 */
	public double getEmployerRate() {
		return employerRate;
	}

	/**
	 * @param employerRate
	 *            the employerRate to set
	 */
	public void setEmployerRate(double employerRate) {
		double old = this.employerRate;
		this.employerRate = employerRate;

		this.propertyChange.firePropertyChange("employerRate", old,
				employerRate);
	}

	/**
	 * Generate predefine data
	 * 
	 * @param dao
	 * @throws Exception
	 */
	public static void generateData(Dao<InsuranceType, Long> dao)
			throws Exception {
		// create table
		TableUtils.createTableIfNotExists(dao.getConnectionSource(),
				InsuranceType.class);

		// insert data
		ResourceBundle rb = ResourceBundle.getBundle("insuranceTypes");
		for (String p : rb.getStringArray("types")) {
			String[] types = p.split(",");

			InsuranceType m = new InsuranceType();
			m.setNew(true);
			m.setName(types[0].trim());
			m.setRate(Double.parseDouble(types[1]));
			m.setYearAssc(Integer.parseInt(types[2]));
			m.setRequired(Boolean.parseBoolean(types[3]));
			m.setCreator("admin");

			dao.create(m);
		}
	}
}
