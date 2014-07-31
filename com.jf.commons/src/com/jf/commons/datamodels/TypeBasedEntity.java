package com.jf.commons.datamodels;

import com.j256.ormlite.field.DatabaseField;

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
	 * @param name the name to set
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
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		String old = this.description;
		this.description = description;
		
		this.propertyChange.firePropertyChange("description", old, description);
	}
}
