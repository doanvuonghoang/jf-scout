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
@DatabaseTable(tableName = "hrm_Districts")
public class District extends TrackableEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public final static String FIELD_RECORD_STATUS = "recordStatus";

	@DatabaseField(canBeNull = false)
	private String name;

	@DatabaseField(defaultValue = "CREATE", columnName = FIELD_RECORD_STATUS)
	private RecordStatus recordStatus;

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

	public static void generateData(Dao<District, Long> dao, Dao<City, Long> cdao) throws Exception {
		// create table if not exists
		TableUtils.createTableIfNotExists(dao.getConnectionSource(),
				District.class);

		// insert predefine data
		String[][] districts = new String[][] {
				{ "Huyện  Hồng Ngự", "Đồng Tháp" },
				{ "Huyện  Tây  Hoà", "Phú Yên" },
				{ "Huyện  Tu Mơ Rông", "Kon Tum" },
				{ "Huyện A Lưới", "Thừa Thiên Huế" },
				{ "Huyện An Biên", "Kiên Giang" },
				{ "Huyện An Dương", "Hải Phòng" },
				{ "Huyện An Lão", "Bình Định" },
				{ "Huyện An Lão", "Hải Phòng" },
				{ "Huyện An Minh", "Kiên Giang" },
				{ "Huyện An Phú", "An Giang" }, { "Huyện Anh Sơn", "Nghệ An" },
				{ "Huyện Ân Thi", "Hưng Yên" }, { "Huyện Ba Bể", "Bắc Kạn" },
				{ "Huyện Ba Chẽ", "Quảng Ninh" },
				{ "Huyện Bá Thước", "Thanh Hoá" },
				{ "Huyện Ba Tơ", "Quảng Ngãi" }, { "Huyện Ba Tri", "Bến Tre" },
				{ "Huyện Ba Vì", "Hà Nội" }, { "Huyện Bác ái", "Ninh Thuận" },
				{ "Huyện Bạch Long Vĩ", "Hải Phòng" },
				{ "Huyện Bạch Thông", "Bắc Kạn" },
				{ "Huyện Bảo Lạc", "Cao Bằng" },
				{ "Huyện Bảo Lâm", "Lâm Đồng" },
				{ "Huyện Bảo Lâm", "Cao Bằng" },
				{ "Huyện Bảo Thắng", "Lào Cai" },
				{ "Huyện Bảo Yên", "Lào Cai" }, { "Huyện Bát Xát", "Lào Cai" },
				{ "Huyện Bàu Bàng", "Bình Dương" },
				{ "Huyện Bắc Bình", "Bình Thuận" },
				{ "Huyện Bắc Hà", "Lào Cai" }, { "Huyện Bắc Mê", "Hà Giang" },
				{ "Huyện Bắc Quang", "Hà Giang" },
				{ "Huyện Bắc Sơn", "Lạng Sơn" },
				{ "Huyện Bắc Tân Uyên", "Bình Dương" },
				{ "Huyện Bắc Trà My", "Quảng Nam" },
				{ "Huyện Bắc Yên", "Sơn La" }, { "Huyện Bến Cầu", "Tây Ninh" },
				{ "Huyện Bến Lức", "Long An" },
				{ "Huyện Bình Chánh", "Hồ Chí Minh" },
				{ "Huyện Bình Đại", "Bến Tre" },
				{ "Huyện Bình Gia", "Lạng Sơn" },
				{ "Huyện Bình Giang", "Hải Dương" },
				{ "Huyện Bình Liêu", "Quảng Ninh" },
				{ "Huyện Bình Lục", "Hà Nam" },
				{ "Huyện Bình Sơn", "Quảng Ngãi" },
				{ "Huyện Bình Tân", "Vĩnh Long" },
				{ "Huyện Bình Xuyên", "Vĩnh Phúc" },
				{ "Huyện Bố Trạch", "Quảng Bình" },
				{ "Huyện Bù Đăng", "Bình Phước" },
				{ "Huyện Bù Đốp", "Bình Phước" },
				{ "Huyện Bù Gia Mập", "Bình Phước" },
				{ "Huyện Buôn Đôn", "Đắk Lắk" },
				{ "Huyện Cái Bè", "Tiền Giang" },
				{ "Huyện Cai Lậy", "Tiền Giang" },
				{ "Huyện Cái Nước", "Cà Mau" },
				{ "Huyện Cam Lâm", "Khánh Hòa" },
				{ "Huyện Cam Lộ", "Quảng Trị" },
				{ "Huyện Can Lộc", "Hà Tĩnh" },
				{ "Huyện Càng Long", "Trà Vinh" },
				{ "Huyện Cao Lãnh", "Đồng Tháp" },
				{ "Huyện Cao Lộc", "Lạng Sơn" },
				{ "Huyện Cao Phong", "Hòa Bình" },
				{ "Huyện Cát Hải", "Hải Phòng" },
				{ "Huyện Cát Tiên", "Lâm Đồng" },
				{ "Huyện Cẩm Giàng", "Hải Dương" },
				{ "Huyện Cẩm Khê", "Phú Thọ" }, { "Huyện Cẩm Mỹ", "Đồng Nai" },
				{ "Huyện Cẩm Thuỷ", "Thanh Hoá" },
				{ "Huyện Cẩm Xuyên", "Hà Tĩnh" },
				{ "Huyện Cần Đước", "Long An" },
				{ "Huyện Cần Giờ", "Hồ Chí Minh" },
				{ "Huyện Cần Giuộc", "Long An" },
				{ "Huyện Cầu Kè", "Trà Vinh" },
				{ "Huyện Cầu Ngang", "Trà Vinh" },
				{ "Huyện Châu Đức", "Bà Rịa - Vũng Tàu" },
				{ "Huyện Châu Phú", "An Giang" },
				{ "Huyện Châu Thành", "Đồng Tháp" },
				{ "Huyện Châu Thành", "Long An" },
				{ "Huyện Châu Thành", "Tây Ninh" },
				{ "Huyện Châu Thành", "Sóc Trăng" },
				{ "Huyện Châu Thành", "Hậu Giang" },
				{ "Huyện Châu Thành", "Trà Vinh" },
				{ "Huyện Châu Thành", "Kiên Giang" },
				{ "Huyện Châu Thành", "Bến Tre" },
				{ "Huyện Châu Thành", "Tiền Giang" },
				{ "Huyện Châu Thành", "An Giang" },
				{ "Huyện Châu Thành - Không còn hiệu lực", "Cần Thơ" },
				{ "Huyện Châu Thành A", "Hậu Giang" },
				{ "Huyện Châu Thành A - Không còn hiệu lực", "Cần Thơ" },
				{ "Huyện Chi Lăng", "Lạng Sơn" },
				{ "Huyện Chiêm Hoá", "Tuyên Quang" },
				{ "Huyện Chợ Đồn", "Bắc Kạn" },
				{ "Huyện Chợ Gạo", "Tiền Giang" },
				{ "Huyện Chợ Lách", "Bến Tre" },
				{ "Huyện Chợ Mới", "An Giang" },
				{ "Huyện Chợ mới", "Bắc Kạn" },
				{ "Huyện Chơn Thành", "Bình Phước" },
				{ "Huyện Chư Păh", "Gia Lai" },
				{ "Huyện Chư Prông", "Gia Lai" },
				{ "Huyện Chư Pưh", "Gia Lai" }, { "Huyện Chư Sê", "Gia Lai" },
				{ "Huyện Chương Mỹ", "Hà Nội" },
				{ "Huyện Con Cuông", "Nghệ An" },
				{ "Huyện Cô Tô", "Quảng Ninh" },
				{ "Huyện Côn Đảo", "Bà Rịa - Vũng Tàu" },
				{ "Huyện Cờ Đỏ", "Cần Thơ" },
				{ "Huyện Củ Chi", "Hồ Chí Minh" },
				{ "Huyện Cù Lao Dung", "Sóc Trăng" },
				{ "Huyện Cư Jút", "Đắk Nông" },
				{ "Huyện Cư Jút - Không còn hiệu lực", "Đắk Lắk" },
				{ "Huyện Cư Kuin", "Đắk Lắk" },
				{ "Huyện Cư M'gar", "Đắk Lắk" },
				{ "Huyện Dầu Tiếng", "Bình Dương" },
				{ "Huyện Di Linh", "Lâm Đồng" },
				{ "Huyện Diễn Châu", "Nghệ An" },
				{ "Huyện Diên Khánh", "Khánh Hòa" },
				{ "Huyện Duy Tiên", "Hà Nam" },
				{ "Huyện Duy Xuyên", "Quảng Nam" },
				{ "Huyện Duyên Hải", "Trà Vinh" },
				{ "Huyện Dương Minh Châu", "Tây Ninh" },
				{ "Huyện Đà Bắc", "Hòa Bình" },
				{ "Huyện Đạ Huoai", "Lâm Đồng" },
				{ "Huyện Đa Krông", "Quảng Trị" },
				{ "Huyện Đạ Tẻh", "Lâm Đồng" },
				{ "Huyện Đại Lộc", "Quảng Nam" },
				{ "Huyện Đại Từ", "Thái Nguyên" },
				{ "Huyện ĐakPơ", "Gia Lai" }, { "Huyện Đam Rông", "Lâm Đồng" },
				{ "Huyện Đan Phượng", "Hà Nội" },
				{ "Huyện Đảo Cồn Cỏ", "Quảng Trị" },
				{ "Huyện Đảo Hoàng Sa", "Đà Nẵng" },
				{ "Huyện Đắk Đoa", "Gia Lai" },
				{ "Huyện Đắk Glei", "Kon Tum" },
				{ "Huyện Đắk Glong", "Đắk Nông" },
				{ "Huyện Đắk Hà", "Kon Tum" }, { "Huyện Đắk Mil", "Đắk Nông" },
				{ "Huyện Đắk Mil - Không còn hiệu lực", "Đắk Lắk" },
				{ "Huyện Đắk Nông - Không còn hiệu lực", "Đắk Lắk" },
				{ "Huyện Đắk Nông - Không còn hiệu lực", "Đắk Nông" },
				{ "Huyện Đắk R'Lấp", "Đắk Nông" },
				{ "Huyện Đắk R'Lấp - Không còn hiệu lực", "Đắk Lắk" },
				{ "Huyện Đắk Song", "Đắk Nông" },
				{ "Huyện Đắk Song - Không còn hiệu lực", "Đắk Lắk" },
				{ "Huyện Đắk Tô", "Kon Tum" }, { "Huyện Đầm Dơi", "Cà Mau" },
				{ "Huyện Đầm Hà", "Quảng Ninh" },
				{ "Huyện Đất đỏ", "Bà Rịa - Vũng Tàu" },
				{ "Huyện Điện Bàn", "Quảng Nam" },
				{ "Huyện Điện Biên", "Điện Biên" },
				{ "Huyện Điện Biên Đông", "Điện Biên" },
				{ "Huyện Định Hoá", "Thái Nguyên" },
				{ "Huyện Đình Lập", "Lạng Sơn" },
				{ "Huyện Định Quán", "Đồng Nai" },
				{ "Huyện Đoan Hùng", "Phú Thọ" },
				{ "Huyện Đô Lương", "Nghệ An" },
				{ "Huyện Đông Anh", "Hà Nội" },
				{ "Huyện Đông Giang", "Quảng Nam" },
				{ "Huyện Đông Hải", "Bạc Liêu" },
				{ "Huyện Đông Hoà", "Phú Yên" },
				{ "Huyện Đông Hưng", "Thái Bình" },
				{ "Huyện Đồng Hỷ", "Thái Nguyên" },
				{ "Huyện Đồng Phú", "Bình Phước" },
				{ "Huyện Đông Sơn", "Thanh Hoá" },
				{ "Huyện Đông Triều", "Quảng Ninh" },
				{ "Huyện Đồng Văn", "Hà Giang" },
				{ "Huyện Đồng Xuân", "Phú Yên" },
				{ "Huyện Đơn Dương", "Lâm Đồng" },
				{ "Huyện Đức Cơ", "Gia Lai" }, { "Huyện Đức Hoà", "Long An" },
				{ "Huyện Đức Huệ", "Long An" },
				{ "Huyện Đức Linh", "Bình Thuận" },
				{ "Huyện Đức Phổ", "Quảng Ngãi" },
				{ "Huyện Đức Thọ", "Hà Tĩnh" },
				{ "Huyện Đức Trọng", "Lâm Đồng" },
				{ "Huyện Ea H'leo", "Đắk Lắk" }, { "Huyện Ea Kar", "Đắk Lắk" },
				{ "Huyện Ea Súp", "Đắk Lắk" },
				{ "Huyện Gia Bình", "Bắc Ninh" },
				{ "Huyện Gia Lâm", "Hà Nội" },
				{ "Huyện Gia Lộc", "Hải Dương" },
				{ "Huyện Giá Rai", "Bạc Liêu" },
				{ "Huyện Gia Viễn", "Ninh Bình" },
				{ "Huyện Giang Thành", "Kiên Giang" },
				{ "Huyện Giao Thuỷ", "Nam Định" },
				{ "Huyện Gio Linh", "Quảng Trị" },
				{ "Huyện Giồng Riềng", "Kiên Giang" },
				{ "Huyện Giồng Trôm", "Bến Tre" },
				{ "Huyện Gò Công Đông", "Tiền Giang" },
				{ "Huyện Gò Công Tây", "Tiền Giang" },
				{ "Huyện Gò Dầu", "Tây Ninh" },
				{ "Huyện Gò Quao", "Kiên Giang" },
				{ "Huyện Hạ Hoà", "Phú Thọ" }, { "Huyện Hạ Lang", "Cao Bằng" },
				{ "Huyện Hà Quảng", "Cao Bằng" },
				{ "Huyện Hà Trung", "Thanh Hoá" },
				{ "Huyện Hải Hà", "Quảng Ninh" },
				{ "Huyện Hải Hậu", "Nam Định" },
				{ "Huyện Hải Lăng", "Quảng Trị" },
				{ "Huyện Hàm Tân", "Bình Thuận" },
				{ "Huyện Hàm Thuận Bắc", "Bình Thuận" },
				{ "Huyện Hàm Thuận Nam", "Bình Thuận" },
				{ "Huyện Hàm Yên", "Tuyên Quang" },
				{ "Huyện Hậu Lộc", "Thanh Hoá" },
				{ "Huyện Hiệp Đức", "Quảng Nam" },
				{ "Huyện Hiệp Hoà", "Bắc Giang" },
				{ "Huyện Hoà An", "Cao Bằng" },
				{ "Huyện Hoà Bình", "Bạc Liêu" },
				{ "Huyện Hoa Lư", "Ninh Bình" },
				{ "Huyện Hoà Thành", "Tây Ninh" },
				{ "Huyện Hoà Vang", "Đà Nẵng" },
				{ "Huyện Hoài Ân", "Bình Định" },
				{ "Huyện Hoài Đức", "Hà Nội" },
				{ "Huyện Hoài Nhơn", "Bình Định" },
				{ "Huyện Hoàng Su Phì", "Hà Giang" },
				{ "Huyện Hoành Bồ", "Quảng Ninh" },
				{ "Huyện Hoằng Hoá", "Thanh Hoá" },
				{ "Huyện Hóc Môn", "Hồ Chí Minh" },
				{ "Huyện Hòn Đất", "Kiên Giang" },
				{ "Huyện Hồng Dân", "Bạc Liêu" },
				{ "Huyện Hớn Quản", "Bình Phước" },
				{ "Huyện Hưng Hà", "Thái Bình" },
				{ "Huyện Hưng Nguyên", "Nghệ An" },
				{ "Huyện Hướng Hoá", "Quảng Trị" },
				{ "Huyện Hương Khê", "Hà Tĩnh" },
				{ "Huyện Hương Sơn", "Hà Tĩnh" },
				{ "Huyện Hữu Lũng", "Lạng Sơn" },
				{ "Huyện Ia Grai", "Gia Lai" }, { "Huyện IaPa", "Gia Lai" },
				{ "Huyện Kbang", "Gia Lai" }, { "Huyện Kế Sách", "Sóc Trăng" },
				{ "Huyện Khánh Sơn", "Khánh Hòa" },
				{ "Huyện Khánh Vĩnh", "Khánh Hòa" },
				{ "Huyện Khoái Châu ", "Hưng Yên" },
				{ "Huyện Kiên Hải", "Kiên Giang" },
				{ "Huyện Kiên Lương", "Kiên Giang" },
				{ "Huyện Kiến Thuỵ", "Hải Phòng" },
				{ "Huyện Kiến Xương", "Thái Bình" },
				{ "Huyện Kim Bảng", "Hà Nam" },
				{ "Huyện Kim Bôi", "Hòa Bình" },
				{ "Huyện Kim Động", "Hưng Yên" },
				{ "Huyện Kim Sơn", "Ninh Bình" },
				{ "Huyện Kim Thành", "Hải Dương" },
				{ "Huyện Kinh Môn", "Hải Dương" },
				{ "Huyện Kon Plông", "Kon Tum" },
				{ "Huyện Kon Rẫy", "Kon Tum" },
				{ "Huyện Kông Chro", "Gia Lai" },
				{ "Huyện Krông A Na", "Đắk Lắk" },
				{ "Huyện Krông Bông", "Đắk Lắk" },
				{ "Huyện Krông Buk", "Đắk Lắk" },
				{ "Huyện Krông Năng", "Đắk Lắk" },
				{ "Huyện Krông Nô", "Đắk Nông" },
				{ "Huyện Krông Nô - Không còn hiệu lực", "Đắk Lắk" },
				{ "Huyện Krông Pa", "Gia Lai" },
				{ "Huyện Krông Pắk", "Đắk Lắk" },
				{ "Huyện Kỳ Anh", "Hà Tĩnh" }, { "Huyện Kỳ Sơn", "Nghệ An" },
				{ "Huyện Kỳ Sơn", "Hòa Bình" },
				{ "Huyện Lạc Dương", "Lâm Đồng" },
				{ "Huyện Lạc Sơn", "Hòa Bình" },
				{ "Huyện Lạc Thuỷ", "Hòa Bình" },
				{ "Huyện Lai Vung", "Đồng Tháp" },
				{ "Huyện Lang Chánh", "Thanh Hoá" },
				{ "Huyện Lạng Giang", "Bắc Giang" },
				{ "Huyện Lắk", "Đắk Lắk" },
				{ "Huyện Lâm Bình", "Tuyên Quang" },
				{ "Huyện Lâm Hà", "Lâm Đồng" },
				{ "Huyện Lâm Thao", "Phú Thọ" },
				{ "Huyện Lập Thạch", "Vĩnh Phúc" },
				{ "Huyện Lấp Vò", "Đồng Tháp" },
				{ "Huyện Lệ Thuỷ", "Quảng Bình" },
				{ "Huyện Long Điền", "Bà Rịa - Vũng Tàu" },
				{ "Huyện Long Hồ", "Vĩnh Long" },
				{ "Huyện Long Mỹ", "Hậu Giang" },
				{ "Huyện Long Mỹ  - Không còn hiệu lực", "Cần Thơ" },
				{ "Huyện Long Phú", "Sóc Trăng" },
				{ "Huyện Long Thành", "Đồng Nai" },
				{ "Huyện Lộc Bình", "Lạng Sơn" },
				{ "Huyện Lộc Hà", "Hà Tĩnh" },
				{ "Huyện Lộc Ninh", "Bình Phước" },
				{ "Huyện Lục Nam", "Bắc Giang" },
				{ "Huyện Lục Ngạn", "Bắc Giang" },
				{ "Huyện Lục Yên", "Yên Bái" },
				{ "Huyện Lương Sơn", "Hòa Bình" },
				{ "Huyện Lương Tài", "Bắc Ninh" },
				{ "Huyện Lý Nhân", "Hà Nam" },
				{ "Huyện Lý Sơn", "Quảng Ngãi" },
				{ "Huyện Mai Châu", "Hòa Bình" },
				{ "Huyện Mai Sơn", "Sơn La" },
				{ "Huyện Mang Thít", "Vĩnh Long" },
				{ "Huyện Mang Yang", "Gia Lai" },
				{ "Huyện M'ĐrắK", "Đắk Lắk" }, { "Huyện Mèo Vạc", "Hà Giang" },
				{ "Huyện Mê Linh", "Hà Nội" },
				{ "Huyện Mê Linh - Chuyển Hà Nội", "Vĩnh Phúc" },
				{ "Huyện Minh Hoá", "Quảng Bình" },
				{ "Huyện Minh Long", "Quảng Ngãi" },
				{ "Huyện Mỏ Cày Bắc", "Bến Tre" },
				{ "Huyện Mỏ Cày Nam", "Bến Tre" },
				{ "Huyện Mộ Đức", "Quảng Ngãi" },
				{ "Huyện Mộc Châu", "Sơn La" }, { "Huyện Mộc Hoá", "Long An" },
				{ "Huyện Mù Cang Chải", "Yên Bái" },
				{ "Huyện Mường ảng", "Điện Biên" },
				{ "Huyện Mường Chà", "Điện Biên" },
				{ "Huyện Mường Khương", "Lào Cai" },
				{ "Huyện Mường La", "Sơn La" },
				{ "Huyện Mường Lát", "Thanh Hoá" },
				{ "Huyện Mường Nhé", "Điện Biên" },
				{ "Huyện Mường Tè", "Lai Châu" },
				{ "Huyện Mường Tè - Không còn hiệu lực", "Điện Biên" },
				{ "Huyện Mỹ Đức", "Hà Nội" }, { "Huyện Mỹ Hào", "Hưng Yên" },
				{ "Huyện Mỹ Lộc", "Nam Định" }, { "Huyện Mỹ Tú", "Sóc Trăng" },
				{ "Huyện Mỹ Xuyên", "Sóc Trăng" },
				{ "Huyện Na Hang", "Tuyên Quang" },
				{ "Huyện Na Rì", "Bắc Kạn" }, { "Huyện Nam Đàn", "Nghệ An" },
				{ "Huyện Nam Đông", "Thừa Thiên Huế" },
				{ "Huyện Nam Giang", "Quảng Nam" },
				{ "Huyện Nam Sách", "Hải Dương" },
				{ "Huyện Nam Trà My", "Quảng Nam" },
				{ "Huyện Nam Trực", "Nam Định" },
				{ "Huyện Năm Căn", "Cà Mau" },
				{ "Huyện Nậm Nhùn", "Lai Châu" },
				{ "Huyện Nậm Pồ", "Điện Biên" },
				{ "Huyện Nga Sơn", "Thanh Hoá" },
				{ "Huyện Ngân Sơn", "Bắc Kạn" },
				{ "Huyện Nghi Lộc", "Nghệ An" },
				{ "Huyện Nghi Xuân", "Hà Tĩnh" },
				{ "Huyện Nghĩa Đàn", "Nghệ An" },
				{ "Huyện Nghĩa Hành", "Quảng Ngãi" },
				{ "Huyện Nghĩa Hưng", "Nam Định" },
				{ "Huyện Ngọc Hiển", "Cà Mau" },
				{ "Huyện Ngọc Hồi", "Kon Tum" },
				{ "Huyện Ngọc Lặc", "Thanh Hoá" },
				{ "Huyện Nguyên Bình", "Cao Bằng" },
				{ "Huyện Nhà Bè", "Hồ Chí Minh" },
				{ "Huyện Nho quan", "Ninh Bình" },
				{ "Huyện Nhơn Trạch", "Đồng Nai" },
				{ "Huyện Như Thanh", "Thanh Hoá" },
				{ "Huyện Như Xuân", "Thanh Hoá" },
				{ "Huyện Ninh Giang", "Hải Dương" },
				{ "Huyện Ninh Hải", "Ninh Thuận" },
				{ "Huyện Ninh Phước", "Ninh Thuận" },
				{ "Huyện Ninh Sơn", "Ninh Thuận" },
				{ "Huyện Nông Cống", "Thanh Hoá" },
				{ "Huyện Nông Sơn", "Quảng Nam" },
				{ "Huyện Núi Thành", "Quảng Nam" },
				{ "Huyện Pác Nặm", "Bắc Kạn" },
				{ "Huyện Phong Điền", "Thừa Thiên Huế" },
				{ "Huyện Phong Điền", "Cần Thơ" },
				{ "Huyện Phong Thổ", "Lai Châu" },
				{ "Huyện Phong Thổ - Không còn hiệu lực", "Điện Biên" },
				{ "Huyện Phổ Yên", "Thái Nguyên" },
				{ "Huyện Phú Bình", "Thái Nguyên" },
				{ "Huyện Phù Cát", "Bình Định" },
				{ "Huyện Phù Cừ", "Hưng Yên" },
				{ "Huyện Phú Giáo", "Bình Dương" },
				{ "Huyện Phú Hoà", "Phú Yên" },
				{ "Huyện Phú Lộc", "Thừa Thiên Huế" },
				{ "Huyện Phú Lương", "Thái Nguyên" },
				{ "Huyện Phù Mỹ", "Bình Định" },
				{ "Huyện Phù Ninh", "Phú Thọ" },
				{ "Huyện Phú Ninh", "Quảng Nam" },
				{ "Huyện Phú Quốc", "Kiên Giang" },
				{ "Huyện Phú Quý", "Bình Thuận" },
				{ "Huyện Phú Tân", "Cà Mau" }, { "Huyện Phú Tân", "An Giang" },
				{ "Huyện Phú Thiện", "Gia Lai" },
				{ "Huyện Phú Vang", "Thừa Thiên Huế" },
				{ "Huyện Phú Xuyên", "Hà Nội" }, { "Huyện Phù Yên", "Sơn La" },
				{ "Huyện Phục Hoà", "Cao Bằng" },
				{ "Huyện Phúc Thọ", "Hà Nội" },
				{ "Huyện Phụng Hiệp", "Hậu Giang" },
				{ "Huyện Phụng Hiệp - Không còn hiệu lực", "Cần Thơ" },
				{ "Huyện Phước Long", "Bạc Liêu" },
				{ "Huyện Phước Sơn", "Quảng Nam" },
				{ "Huyện Quản Bạ", "Hà Giang" },
				{ "Huyện Quan Hoá", "Thanh Hoá" },
				{ "Huyện Quan Sơn", "Thanh Hoá" },
				{ "Huyện Quang Bình", "Hà Giang" },
				{ "Huyện Quảng Điền", "Thừa Thiên Huế" },
				{ "Huyện Quảng Ninh", "Quảng Bình" },
				{ "Huyện Quảng Trạch", "Quảng Bình" },
				{ "Huyện Quảng Uyên", "Cao Bằng" },
				{ "Huyện Quảng Xương", "Thanh Hoá" },
				{ "Huyện Quế Phong", "Nghệ An" },
				{ "Huyện Quế Sơn", "Quảng Nam" },
				{ "Huyện Quế Võ", "Bắc Ninh" }, { "Huyện Quốc Oai", "Hà Nội" },
				{ "Huyện Quỳ Châu", "Nghệ An" },
				{ "Huyện Quỳ Hợp", "Nghệ An" },
				{ "Huyện Quỳnh Lưu", "Nghệ An" },
				{ "Huyện Quỳnh Nhai", "Sơn La" },
				{ "Huyện Quỳnh Phụ", "Thái Bình" },
				{ "Huyện Sa Pa", "Lào Cai" }, { "Huyện Sa Thầy", "Kon Tum" },
				{ "Huyện Si Ma Cai", "Lào Cai" },
				{ "Huyện Sìn Hồ", "Lai Châu" },
				{ "Huyện Sìn Hồ - Không còn hiệu lực", "Điện Biên" },
				{ "Huyện Sóc Sơn", "Hà Nội" },
				{ "Huyện Sông Hinh", "Phú Yên" },
				{ "Huyện Sông Lô", "Vĩnh Phúc" },
				{ "Huyện Sông Mã", "Sơn La" }, { "Huyện Sốp Cộp", "Sơn La" },
				{ "Huyện Sơn Dương", "Tuyên Quang" },
				{ "Huyện Sơn Động", "Bắc Giang" },
				{ "Huyện Sơn Hà", "Quảng Ngãi" },
				{ "Huyện Sơn Hoà", "Phú Yên" },
				{ "Huyện Sơn Tây", "Quảng Ngãi" },
				{ "Huyện Sơn Tịnh", "Quảng Ngãi" },
				{ "Huyện Tam Bình", "Vĩnh Long" },
				{ "Huyện Tam Dương", "Vĩnh Phúc" },
				{ "Huyện Tam đảo", "Vĩnh Phúc" },
				{ "Huyện Tam Đường", "Lai Châu" },
				{ "Huyện Tam Đường - Không còn hiệu lực", "Điện Biên" },
				{ "Huyện Tam Nông", "Đồng Tháp" },
				{ "Huyện Tam Nông", "Phú Thọ" },
				{ "Huyện Tánh Linh", "Bình Thuận" },
				{ "Huyện Tân Biên", "Tây Ninh" },
				{ "Huyện Tân Châu", "Tây Ninh" },
				{ "Huyện Tân Hiệp", "Kiên Giang" },
				{ "Huyện Tân Hồng", "Đồng Tháp" },
				{ "Huyện Tân Hưng", "Long An" }, { "Huyện Tân Kỳ", "Nghệ An" },
				{ "Huyện Tân Lạc", "Hòa Bình" },
				{ "Huyện Tân Phú", "Đồng Nai" },
				{ "Huyện Tân Phú Đông", "Tiền Giang" },
				{ "Huyện Tân Phước", "Tiền Giang" },
				{ "Huyện Tân Sơn", "Phú Thọ" },
				{ "Huyện Tân Thành", "Bà Rịa - Vũng Tàu" },
				{ "Huyện Tân Thạnh", "Long An" },
				{ "Huyện Tân Trụ", "Long An" },
				{ "Huyện Tân Uyên", "Lai Châu" },
				{ "Huyện Tân Yên", "Bắc Giang" },
				{ "Huyện Tây Giang", "Quảng Nam" },
				{ "Huyện Tây Sơn", "Bình Định" },
				{ "Huyện Tây Trà", "Quảng Ngãi" },
				{ "Huyện Thạch An", "Cao Bằng" },
				{ "Huyện Thạch Hà", "Hà Tĩnh" },
				{ "Huyện Thạch Thành", "Thanh Hoá" },
				{ "Huyện Thạch Thất", "Hà Nội" },
				{ "Huyện Thái Thụy", "Thái Bình" },
				{ "Huyện Than Uyên", "Lai Châu" },
				{ "Huyện Than Uyên - Không còn hiệu lực", "Lào Cai" },
				{ "Huyện Thanh Ba", "Phú Thọ" },
				{ "Huyện Thanh Bình", "Đồng Tháp" },
				{ "Huyện Thanh Chương", "Nghệ An" },
				{ "Huyện Thanh Hà", "Hải Dương" },
				{ "Huyện Thạnh Hoá", "Long An" },
				{ "Huyện Thanh Liêm", "Hà Nam" },
				{ "Huyện Thanh Miện", "Hải Dương" },
				{ "Huyện Thanh Oai", "Hà Nội" },
				{ "Huyện Thạnh Phú", "Bến Tre" },
				{ "Huyện Thanh Sơn", "Phú Thọ" },
				{ "Huyện Thanh Thuỷ", "Phú Thọ" },
				{ "Huyện Thanh Trì", "Hà Nội" },
				{ "Huyện Thạnh Trị", "Sóc Trăng" },
				{ "Huyện Tháp Mười", "Đồng Tháp" },
				{ "Huyện Thăng Bình", "Quảng Nam" },
				{ "Huyện Thiệu Hoá", "Thanh Hoá" },
				{ "Huyện Thọ Xuân", "Thanh Hoá" },
				{ "Huyện Thoại Sơn", "An Giang" },
				{ "Huyện Thống Nhất", "Đồng Nai" },
				{ "Huyện Thông Nông", "Cao Bằng" },
				{ "Huyện Thới Bình", "Cà Mau" },
				{ "Huyện Thới Lai", "Cần Thơ" },
				{ "Huyện Thủ Thừa", "Long An" },
				{ "Huyện Thuận Bắc", "Ninh Thuận" },
				{ "Huyện Thuận Châu", "Sơn La" },
				{ "Huyện Thuận Nam", "Ninh Thuận" },
				{ "Huyện Thuận Thành", "Bắc Ninh" },
				{ "Huyện Thuỷ Nguyên", "Hải Phòng" },
				{ "Huyện Thường Tín", "Hà Nội" },
				{ "Huyện Thường Xuân", "Thanh Hoá" },
				{ "Huyện Tiên Du", "Bắc Ninh" },
				{ "Huyện Tiền Hải", "Thái Bình" },
				{ "Huyện Tiên Lãng", "Hải Phòng" },
				{ "Huyện Tiên Lữ", "Hưng Yên" },
				{ "Huyện Tiên Phước", "Quảng Nam" },
				{ "Huyện Tiên Yên", "Quảng Ninh" },
				{ "Huyện Tiểu Cần", "Trà Vinh" },
				{ "Huyện Tịnh Biên", "An Giang" },
				{ "Huyện Tĩnh Gia", "Thanh Hoá" },
				{ "Huyện Trà Bồng", "Quảng Ngãi" },
				{ "Huyện Trà Cú", "Trà Vinh" },
				{ "Huyện Trà Lĩnh", "Cao Bằng" },
				{ "Huyện Trà Ôn", "Vĩnh Long" },
				{ "Huyện Trạm Tấu", "Yên Bái" },
				{ "Huyện Trảng Bàng", "Tây Ninh" },
				{ "Huyện Trảng Bom", "Đồng Nai" },
				{ "Huyện Tràng Định", "Lạng Sơn" },
				{ "Huyện Trần Đề", "Sóc Trăng" },
				{ "Huyện Trần Văn Thời", "Cà Mau" },
				{ "Huyện Trấn Yên", "Yên Bái" },
				{ "Huyện Tri Tôn", "An Giang" },
				{ "Huyện Triệu Phong", "Quảng Trị" },
				{ "Huyện Triệu Sơn", "Thanh Hoá" },
				{ "Huyện Trùng Khánh", "Cao Bằng" },
				{ "Huyện Trực Ninh", "Nam Định" },
				{ "Huyện Trường Sa", "Khánh Hòa" },
				{ "Huyện Tủa Chùa", "Điện Biên" },
				{ "Huyện Tuần Giáo", "Điện Biên" },
				{ "Huyện Tuy An", "Phú Yên" }, { "Huyện Tuy Đức", "Đắk Nông" },
				{ "Huyện Tuy Phong", "Bình Thuận" },
				{ "Huyện Tuy Phước", "Bình Định" },
				{ "Huyện Tuyên Hoá", "Quảng Bình" },
				{ "Huyện Tứ Kỳ", "Hải Dương" }, { "Huyện Từ Liêm", "Hà Nội" },
				{ "Huyện Tư Nghĩa", "Quảng Ngãi" },
				{ "Huyện Tương Dương", "Nghệ An" },
				{ "Huyện U Minh", "Cà Mau" },
				{ "Huyện U Minh Thượng", "Kiên Giang" },
				{ "Huyện ứng Hoà", "Hà Nội" },
				{ "Huyện Vạn Ninh", "Khánh Hòa" },
				{ "Huyện Văn Bàn", "Lào Cai" },
				{ "Huyện Văn Chấn", "Yên Bái" },
				{ "Huyện Văn Giang", "Hưng Yên" },
				{ "Huyện Văn Lãng", "Lạng Sơn" },
				{ "Huyện Văn Lâm", "Hưng Yên" },
				{ "Huyện Văn Quan", "Lạng Sơn" },
				{ "Huyện Văn Yên", "Yên Bái" },
				{ "Huyện Vân Canh", "Bình Định" },
				{ "Huyện Vân Đồn", "Quảng Ninh" },
				{ "Huyện Vân Hồ", "Sơn La" }, { "Huyện Vị Thủy", "Hậu Giang" },
				{ "Huyện Vị Thuỷ - Không còn hiệu lực", "Cần Thơ" },
				{ "Huyện Vị Xuyên", "Hà Giang" },
				{ "Huyện Việt Yên", "Bắc Giang" },
				{ "Huyện Vĩnh Bảo", "Hải Phòng" },
				{ "Huyện Vĩnh Cửu", "Đồng Nai" },
				{ "Huyện Vĩnh Hưng", "Long An" },
				{ "Huyện Vĩnh Linh", "Quảng Trị" },
				{ "Huyện Vĩnh Lộc", "Thanh Hoá" },
				{ "Huyện Vĩnh Lợi", "Bạc Liêu" },
				{ "Huyện Vĩnh Thạnh", "Cần Thơ" },
				{ "Huyện Vĩnh Thạnh", "Bình Định" },
				{ "Huyện Vĩnh Thuận", "Kiên Giang" },
				{ "Huyện Vĩnh Tường", "Vĩnh Phúc" },
				{ "Huyện Võ Nhai", "Thái Nguyên" },
				{ "Huyện Vụ Bản", "Nam Định" },
				{ "Huyện Vũ Quang", "Hà Tĩnh" },
				{ "Huyện Vũ Thư", "Thái Bình" },
				{ "Huyện Vũng Liêm", "Vĩnh Long" },
				{ "Huyện Xín Mần", "Hà Giang" },
				{ "Huyện Xuân Lộc", "Đồng Nai" },
				{ "Huyện Xuân Trường", "Nam Định" },
				{ "Huyện Xuyên Mộc", "Bà Rịa - Vũng Tàu" },
				{ "Huyện ý Yên", "Nam Định" }, { "Huyện Yên Bình", "Yên Bái" },
				{ "Huyện Yên Châu", "Sơn La" },
				{ "Huyện Yên Dũng", "Bắc Giang" },
				{ "Huyện Yên Định", "Thanh Hoá" },
				{ "Huyện Yên Khánh", "Ninh Bình" },
				{ "Huyện Yên Lạc", "Vĩnh Phúc" },
				{ "Huyện Yên Lập", "Phú Thọ" },
				{ "Huyện Yên Minh", "Hà Giang" },
				{ "Huyện Yên Mô", "Ninh Bình" },
				{ "Huyện Yên Mỹ", "Hưng Yên" },
				{ "Huyện Yên Phong", "Bắc Ninh" },
				{ "Huyện Yên Sơn", "Tuyên Quang" },
				{ "Huyện Yên Thành", "Nghệ An" },
				{ "Huyện Yên Thế", "Bắc Giang" },
				{ "Huyện Yên Thuỷ", "Hòa Bình" }, { "Quận Ba Đình", "Hà Nội" },
				{ "Quận Bắc Từ Liêm", "Hà Nội" },
				{ "Quận Bình Tân", "Hồ Chí Minh" },
				{ "Quận Bình Thạnh", "Hồ Chí Minh" },
				{ "Quận Bình Thuỷ", "Cần Thơ" },
				{ "Quận Cái Răng", "Cần Thơ" }, { "Quận Cẩm Lệ", "Đà Nẵng" },
				{ "Quận Cầu Giấy", "Hà Nội" },
				{ "Quận Dương Kinh", "Hải Phòng" },
				{ "Quận Đồ Sơn", "Hải Phòng" }, { "Quận Đống đa", "Hà Nội" },
				{ "Quận Gò Vấp", "Hồ Chí Minh" }, { "Quận Hà Đông", "Hà Nội" },
				{ "Quận Hải An", "Hải Phòng" },
				{ "Quận Hai Bà Trưng", "Hà Nội" },
				{ "Quận Hải Châu", "Đà Nẵng" }, { "Quận Hoàn Kiếm", "Hà Nội" },
				{ "Quận Hoàng Mai", "Hà Nội" },
				{ "Quận Hồng Bàng", "Hải Phòng" },
				{ "Quận Kiến An", "Hải Phòng" },
				{ "Quận Lê Chân", "Hải Phòng" },
				{ "Quận Liên Chiểu", "Đà Nẵng" },
				{ "Quận Long Biên", "Hà Nội" },
				{ "Quận Nam Từ Liêm", "Hà Nội" },
				{ "Quận Ngô Quyền", "Hải Phòng" },
				{ "Quận Ngũ Hành Sơn", "Đà Nẵng" },
				{ "Quận Ninh Kiều", "Cần Thơ" }, { "Quận Ô Môn", "Cần Thơ" },
				{ "Quận Phú Nhuận", "Hồ Chí Minh" },
				{ "Quận Sơn Trà", "Đà Nẵng" },
				{ "Quận Tân Bình", "Hồ Chí Minh" },
				{ "Quận Tân phú", "Hồ Chí Minh" }, { "Quận Tây Hồ", "Hà Nội" },
				{ "Quận Thanh Khê", "Đà Nẵng" },
				{ "Quận Thanh Xuân", "Hà Nội" },
				{ "Quận Thốt Nốt", "Cần Thơ" },
				{ "Quận Thủ Đức", "Hồ Chí Minh" }, { "Quận 1", "Hồ Chí Minh" },
				{ "Quận 10", "Hồ Chí Minh" }, { "Quận 11", "Hồ Chí Minh" },
				{ "Quận 12", "Hồ Chí Minh" }, { "Quận 2", "Hồ Chí Minh" },
				{ "Quận 3", "Hồ Chí Minh" }, { "Quận 4", "Hồ Chí Minh" },
				{ "Quận 5", "Hồ Chí Minh" }, { "Quận 6", "Hồ Chí Minh" },
				{ "Quận 7", "Hồ Chí Minh" }, { "Quận 8", "Hồ Chí Minh" },
				{ "Quận 9", "Hồ Chí Minh" },
				{ "Thành phố  Bắc Giang", "Bắc Giang" },
				{ "Thành phố  Phan Thiết", "Bình Thuận" },
				{ "Thành phố  Pleiku", "Gia Lai" },
				{ "Thành phố  Vĩnh Long", "Vĩnh Long" },
				{ "Thành phố  Yên Bái", "Yên Bái" },
				{ "Thành phố Bà Rịa", "Bà Rịa - Vũng Tàu" },
				{ "Thành phố Bạc Liêu", "Bạc Liêu" },
				{ "Thành phố Bảo Lộc", "Lâm Đồng" },
				{ "Thành phố Bắc Ninh", "Bắc Ninh" },
				{ "Thành phố Bến Tre", "Bến Tre" },
				{ "Thành phố Biên Hoà", "Đồng Nai" },
				{ "Thành phố Cà Mau", "Cà Mau" },
				{ "Thành Phố Cam Ranh", "Khánh Hòa" },
				{ "Thành phố Cao Bằng", "Cao Bằng" },
				{ "Thành phố Cao Lãnh", "Đồng Tháp" },
				{ "Thành phố Cẩm Phả", "Quảng Ninh" },
				{ "Thành phố Cần Thơ - Không còn hiệu lực", "Cần Thơ" },
				{ "Thành phố Châu Đốc", "An Giang" },
				{ "Thành phố Đà Lạt", "Lâm Đồng" },
				{ "Thành phố Đông Hà", "Quảng Trị" },
				{ "Thành phố Đồng Hới", "Quảng Bình" },
				{ "Thành Phố Hà Giang", "Hà Giang" },
				{ "Thành phố Hạ Long", "Quảng Ninh" },
				{ "Thành phố Hà Tĩnh", "Hà Tĩnh" },
				{ "Thành phố Hải Dương", "Hải Dương" },
				{ "Thành phố Hoà Bình", "Hòa Bình" },
				{ "Thành phố Hội An", "Quảng Nam" },
				{ "Thành phố Huế", "Thừa Thiên Huế" },
				{ "Thành phố Hưng yên", "Hưng Yên" },
				{ "Thành phố Kon Tum", "Kon Tum" },
				{ "Thành phố Lai Châu", "Lai Châu" },
				{ "Thành phố Lạng Sơn", "Lạng Sơn" },
				{ "Thành phố Lào Cai", "Lào Cai" },
				{ "Thành phố Long Xuyên ", "An Giang" },
				{ "Thành phố Móng cái", "Quảng Ninh" },
				{ "Thành phố Mỹ Tho", "Tiền Giang" },
				{ "Thành phố Nam Định", "Nam Định" },
				{ "Thành phố Nha Trang", "Khánh Hòa" },
				{ "Thành phố Ninh Bình", "Ninh Bình" },
				{ "Thành phố Phủ Lý", "Hà Nam" },
				{ "Thành phố Quảng Ngãi", "Quảng Ngãi" },
				{ "Thành phố Quy Nhơn", "Bình Định" },
				{ "Thành phố Rạch Giá", "Kiên Giang" },
				{ "Thành phố Sa Đéc", "Đồng Tháp" },
				{ "Thành phố Sóc Trăng", "Sóc Trăng" },
				{ "Thành phố Sơn La", "Sơn La" },
				{ "Thành phố Tam Kỳ", "Quảng Nam" },
				{ "Thành phố Tân An", "Long An" },
				{ "Thành phố Tây Ninh", "Tây Ninh" },
				{ "Thành phố Thái Bình", "Thái Bình" },
				{ "Thành phố Thái Nguyên", "Thái Nguyên" },
				{ "Thành phố Thanh Hoá", "Thanh Hoá" },
				{ "Thành phố Thủ Dầu Một", "Bình Dương" },
				{ "Thành phố Trà Vinh", "Trà Vinh" },
				{ "Thành phố Tuyên Quang", "Tuyên Quang" },
				{ "Thành phố Uông Bí", "Quảng Ninh" },
				{ "Thành phố Vị Thanh", "Hậu Giang" },
				{ "Thành phố Việt Trì", "Phú Thọ" },
				{ "Thành phố Vinh", "Nghệ An" },
				{ "Thành phố Vĩnh Yên", "Vĩnh Phúc" },
				{ "Thành phố Vũng Tàu", "Bà Rịa - Vũng Tàu" },
				{ "Thị  Xã Ngã Bảy", "Hậu Giang" },
				{ "Thị xã  Bình Long", "Bình Phước" },
				{ "Thị xã  Phước Long", "Bình Phước" },
				{ "Thị xã An Khê", "Gia Lai" },
				{ "Thị xã An Nhơn", "Bình Định" },
				{ "Thị xã Ayun Pa", "Gia Lai" },
				{ "Thị Xã Ba Đồn", "Quảng Bình" },
				{ "Thị xã Bắc Kạn", "Bắc Kạn" },
				{ "Thị xã Bến Cát", "Bình Dương" },
				{ "Thị xã Bỉm Sơn", "Thanh Hoá" },
				{ "Thị xã Bình Minh", "Vĩnh Long" },
				{ "Thị xã Buôn Hồ", "Đắk Lắk" },
				{ "Thị Xã Cai Lậy", "Tiền Giang" },
				{ "Thị xã Cam Đường - Không còn hiệu lực", "Lào Cai" },
				{ "Thị xã Chí Linh", "Hải Dương" },
				{ "Thị xã Cửa Lò", "Nghệ An" },
				{ "Thị Xã Dĩ An", "Bình Dương" },
				{ "Thị xã Đồng Xoài", "Bình Phước" },
				{ "Thị xã Gia Nghĩa", "Đắk Nông" },
				{ "Thị xã Gò Công", "Tiền Giang" },
				{ "Thị xã Hà Tiên", "Kiên Giang" },
				{ "Thị xã Hoàng Mai", "Nghệ An" },
				{ "Thị xã Hồng Lĩnh", "Hà Tĩnh" },
				{ "Thị xã Hồng Ngự", "Đồng Tháp" },
				{ "Thị xã Hương Thuỷ", "Thừa Thiên Huế" },
				{ "Thị xã Hương Trà", "Thừa Thiên Huế" },
				{ "Thị xã Kiến Tường", "Long An" },
				{ "Thị xã La Gi", "Bình Thuận" },
				{ "Thị xã Long khánh", "Đồng Nai" },
				{ "Thị xã Mường Lay", "Điện Biên" },
				{ "Thị xã Ngã Năm", "Sóc Trăng" },
				{ "Thị xã Nghĩa Lộ", "Yên Bái" },
				{ "Thị xã Ninh Hoà", "Khánh Hòa" },
				{ "Thị xã Phú Thọ", "Phú Thọ" },
				{ "Thị xã Phúc Yên", "Vĩnh Phúc" },
				{ "Thị xã Quảng Trị", "Quảng Trị" },
				{ "Thị xã Quảng Yên", "Quảng Ninh" },
				{ "Thị xã Sầm Sơn", "Thanh Hoá" },
				{ "Thị xã Sông Cầu", "Phú Yên" },
				{ "Thị xã Sông Công", "Thái Nguyên" },
				{ "Thị Xã Sơn Tây", "Hà Nội" },
				{ "Thị xã Tam Điệp", "Ninh Bình" },
				{ "Thị xã Tân Châu", "An Giang" },
				{ "Thị xã Tân Uyên", "Bình Dương" },
				{ "Thị xã Thái Hoà", "Nghệ An" },
				{ "Thị xã Thuận An", "Bình Dương" },
				{ "Thị xã Từ Sơn", "Bắc Ninh" },
				{ "Thị xã Vị Thanh - Không còn hiệu lực", "Cần Thơ" },
				{ "Thị xã Vĩnh Châu", "Sóc Trăng" },
				{ "TP Điện Biên Phủ", "Điện Biên" },
				{ "TP. Phan Rang-Tháp Chàm", "Ninh Thuận" },
				{ "TP Tuy Hoà", "Phú Yên" }, { "TP.Buôn Ma Thuột", "Đắk Lắk" } };
		for (String[] district : districts) {
			District d = new District();
			d.setNew(true);
			
			d.setName(district[0]);
			d.setCity(cdao.queryForEq(City.FIELD_NAME, district[1]).get(0));
			
			dao.create(d);
		}
	}
}
