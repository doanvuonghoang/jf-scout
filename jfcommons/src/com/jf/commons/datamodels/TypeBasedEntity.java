package com.jf.commons.datamodels;

import java.io.InputStreamReader;

import org.apache.commons.configuration.PropertiesConfiguration;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.TableUtils;

public class TypeBasedEntity extends RecordHistEntity {
	private static final long serialVersionUID = 1L;

	public static final String FIELD_NAME = "name";
	public static final String FIELD_CODE = "code";
	
	@DatabaseField(canBeNull = false, unique = true, width = 5, columnName = FIELD_CODE)
	private String code;

	@DatabaseField(canBeNull = false, unique = true, width = 200, columnName = FIELD_NAME)
	private String name;

	@DatabaseField(width = 4000)
	private String description;
	
	@DatabaseField(canBeNull = false, defaultValue = "0")
	private int showSequence;

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
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

	/**
	 * Generate data. If table not exist, create one then insert predefine data
	 * @param dao
	 * @param cls
	 * @throws Exception
	 */
	public <T extends TypeBasedEntity> void generateData(
			Dao<T, Long> dao, Class<T> cls) throws Exception {
		// create table
		TableUtils.createTableIfNotExists(dao.getConnectionSource(), cls);

		// insert data
		PropertiesConfiguration cfg = new PropertiesConfiguration();
		cfg.load(new InputStreamReader(cls.getResource(cls.getSimpleName() + ".properties").openStream(), "UTF-8"));
		
		for (String entry : cfg.getStringArray("entries")) {
			dao.create(createModel(entry, cls));
		}
	}

	/**
	 * Create model
	 * @param entry
	 * @param cls
	 * @return
	 * @throws Exception
	 */
	protected <T extends TypeBasedEntity> T createModel(String entry,
			Class<T> cls) throws Exception {
		String[] parts = entry.split(":");

		T m = cls.newInstance();
		m.setNew(true);
		onModelCreated(m, parts);
		m.setCreator("admin");

		return m;
	}

	/**
	 * Can use this method to set additional properties
	 * 
	 * @param t
	 */
	protected <T extends TypeBasedEntity> void onModelCreated(T t,
			String[] parts) {
		t.setCode(parts[0].trim());
		t.setName(parts[1].trim());
		t.setDescription(parts[2].trim());
		t.setShowSequence(Integer.valueOf(parts[3].trim()));
	}
}
