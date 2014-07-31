package com.jf.commons.datamodels;

import java.util.ResourceBundle;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.TableUtils;

public class TypeBasedEntity extends RecordHistEntity {
	private static final long serialVersionUID = 1L;

	public static final String FIELD_NAME = "name";

	@DatabaseField(canBeNull = false, unique = true, width = 200, columnName = FIELD_NAME)
	private String name;

	@DatabaseField(width = 4000)
	private String description;

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
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		String old = this.description;
		this.description = description;

		this.propertyChange.firePropertyChange("description", old, description);
	}

	public static <T extends TypeBasedEntity> void generateData(
			Dao<T, Long> dao, Class<T> cls, String resource) throws Exception {
		// create table
		TableUtils.createTableIfNotExists(dao.getConnectionSource(), cls);

		// insert data
		ResourceBundle rb = ResourceBundle.getBundle(resource);
		for (String p : rb.getStringArray("types")) {
			String[] types = p.split(",");

			T m = cls.newInstance();
			m.setNew(true);
			m.setName(types[0].trim());
			m.setDescription(types[1]);
			m.setCreator("admin");

			dao.create(m);
		}
	}
}
