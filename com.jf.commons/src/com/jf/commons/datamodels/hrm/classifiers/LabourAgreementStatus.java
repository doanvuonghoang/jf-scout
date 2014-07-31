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
import com.j256.ormlite.table.DatabaseTable;
import com.j256.ormlite.table.TableUtils;
import com.jf.commons.datamodels.TypeBasedEntity;

/**
 *
 * @author Hoàng Doãn
 */
@DatabaseTable(tableName = "hrm_LabourAgreementStatuses")
public class LabourAgreementStatus extends TypeBasedEntity {
	private static final long serialVersionUID = 1L;

	/**
	 * Generate predefine data
	 * 
	 * @param dao
	 * @throws Exception
	 */
	public static void generateData(Dao<LabourAgreementStatus, Long> dao)
			throws Exception {
		// create table
		TableUtils.createTableIfNotExists(dao.getConnectionSource(),
				InsuranceStatus.class);

		// insert data
		ResourceBundle rb = ResourceBundle.getBundle("labourAgreementStatuses");
		for (String p : rb.getStringArray("statuses")) {
			String[] statuses = p.split(",");

			LabourAgreementStatus m = new LabourAgreementStatus();
			m.setNew(true);
			m.setName(statuses[0].trim());
			m.setDescription(statuses[1]);
			m.setCreator("admin");

			dao.create(m);
		}
	}
}
