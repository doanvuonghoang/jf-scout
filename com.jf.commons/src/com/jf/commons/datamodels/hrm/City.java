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

import java.beans.PropertyChangeEvent;
import java.io.Serializable;
import java.util.Calendar;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;
import com.j256.ormlite.table.TableUtils;
import com.jf.commons.datamodels.RecordStatus;
import com.jf.commons.datamodels.TrackableEntity;

/**
 *
 * @author Hoàng Doãn
 */
@DatabaseTable(tableName = "hrm_Cities")
public class City extends TrackableEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public final static String FIELD_RECORD_STATUS = "recordStatus";
	public final static String FIELD_NAME = "name";

	@DatabaseField(canBeNull = false, unique = true, width = 5)
	private String code;

	@DatabaseField(canBeNull = false, columnName = FIELD_NAME)
	private String name;

	@DatabaseField(canBeNull = false, defaultValue = "Tỉnh")
	private String namePrefix;

	@DatabaseField(defaultValue = "CREATE", columnName = FIELD_RECORD_STATUS)
	private RecordStatus recordStatus;

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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (isNew())
			return;

		super.propertyChange(evt);

		setRecordStatus(RecordStatus.UPDATE);
	}

	/**
	 * @return the recordStatus
	 */
	public RecordStatus getRecordStatus() {
		return recordStatus;
	}

	/**
	 * This value is auto set, no need to call.
	 * 
	 * @param recordStatus
	 *            the recordStatus to set
	 */
	public void setRecordStatus(RecordStatus recordStatus) {
		this.recordStatus = recordStatus;
		setLastModifiedTime(Calendar.getInstance().getTime());
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
		String[][] cityNames = new String[][] { { "805", "An Giang", "Tỉnh" },
				{ "717", "Bà Rịa - Vũng Tàu", "Tỉnh" },
				{ "821", "Bạc Liêu", "Tỉnh" }, { "221", "Bắc Giang", "Tỉnh" },
				{ "207", "Bắc Kạn", "Tỉnh" }, { "223", "Bắc Ninh", "Tỉnh" },
				{ "811", "Bến Tre", "Tỉnh" }, { "711", "Bình Dương", "Tỉnh" },
				{ "507", "Bình Định", "Tỉnh" },
				{ "707", "Bình Phước", "Tỉnh" },
				{ "715", "Bình Thuận", "Tỉnh" }, { "823", "Cà Mau", "Tỉnh" },
				{ "203", "Cao Bằng", "Tỉnh" },
				{ "815", "Cần Thơ", "Thành phố" },
				{ "501", "Đà Nẵng", "Thành phố" },
				{ "605", "Đắk Lắk", "Tỉnh" }, { "606", "Đắk Nông", "Tỉnh" },
				{ "301", "Điện Biên", "Tỉnh" }, { "713", "Đồng Nai", "Tỉnh" },
				{ "803", "Đồng Tháp", "Tỉnh" }, { "603", "Gia Lai", "Tỉnh" },
				{ "201", "Hà Giang", "Tỉnh" }, { "111", "Hà Nam", "Tỉnh" },
				{ "101", "Hà Nội", "Thành phố" }, { "405", "Hà Tĩnh", "Tỉnh" },
				{ "107", "Hải Dương", "Tỉnh" },
				{ "103", "Hải Phòng", "Thành phố" },
				{ "816", "Hậu Giang", "Tỉnh" }, { "305", "Hòa Bình", "Tỉnh" },
				{ "701", "Hồ Chí Minh", "Thành phố" },
				{ "109", "Hưng Yên", "Tỉnh" }, { "511", "Khánh Hòa", "Tỉnh" },
				{ "813", "Kiên Giang", "Tỉnh" }, { "601", "Kon Tum", "Tỉnh" },
				{ "302", "Lai Châu", "Tỉnh" }, { "209", "Lạng Sơn", "Tỉnh" },
				{ "205", "Lào Cai", "Tỉnh" }, { "703", "Lâm Đồng", "Tỉnh" },
				{ "801", "Long An", "Tỉnh" }, { "113", "Nam Định", "Tỉnh" },
				{ "403", "Nghệ An", "Tỉnh" }, { "117", "Ninh Bình", "Tỉnh" },
				{ "705", "Ninh Thuận", "Tỉnh" }, { "217", "Phú Thọ", "Tỉnh" },
				{ "509", "Phú Yên", "Tỉnh" }, { "407", "Quảng Bình", "Tỉnh" },
				{ "503", "Quảng Nam", "Tỉnh" },
				{ "505", "Quảng Ngãi", "Tỉnh" },
				{ "225", "Quảng Ninh", "Tỉnh" },
				{ "409", "Quảng Trị", "Tỉnh" }, { "819", "Sóc Trăng", "Tỉnh" },
				{ "303", "Sơn La", "Tỉnh" }, { "709", "Tây Ninh", "Tỉnh" },
				{ "115", "Thái Bình", "Tỉnh" },
				{ "215", "Thái Nguyên", "Tỉnh" },
				{ "401", "Thanh Hoá", "Tỉnh" },
				{ "411", "Thừa Thiên Huế", "Tỉnh" },
				{ "807", "Tiền Giang", "Tỉnh" }, { "817", "Trà Vinh", "Tỉnh" },
				{ "211", "Tuyên Quang", "Tỉnh" },
				{ "809", "Vĩnh Long", "Tỉnh" }, { "219", "Vĩnh Phúc", "Tỉnh" },
				{ "213", "Yên Bái", "Tỉnh" } };

		for (String[] city : cityNames) {
			City c = new City();
			c.setNew(true);

			c.setCode(city[0]);
			c.setName(city[1]);
			c.setNamePrefix(city[2]);
			c.setCreator("admin");

			dao.create(c);
		}
	}
}
