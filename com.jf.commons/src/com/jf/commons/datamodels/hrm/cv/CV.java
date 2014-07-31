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

package com.jf.commons.datamodels.hrm.cv;

import java.util.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.jf.commons.datamodels.RecordHistEntity;
import com.jf.commons.datamodels.hrm.Address;
import com.jf.commons.datamodels.hrm.employee.Employee;

/**
 *
 * @author Hoàng Doãn
 */
@DatabaseTable(tableName = "hrm_CV")
public class CV extends RecordHistEntity {
	private static final long serialVersionUID = 1L;
	
    public static final String FIELD_CODE = "code";
    public static final String FIELD_FIRST_NAME = "firstName";
    public static final String FIELD_LAST_NAME = "lastName";
    
    @DatabaseField(foreign = true, canBeNull = false, unique = true)
	private Employee employee;
    
    @DatabaseField(canBeNull = false, unique = true, columnName = FIELD_CODE)
    private String code;
    
    @DatabaseField(canBeNull = false, columnName = FIELD_FIRST_NAME)
    private String firstName;
    
    @DatabaseField(canBeNull = false, columnName = FIELD_FIRST_NAME)
    private String lastName;
    
    @DatabaseField(canBeNull = false)
    private int gender;
    
    @DatabaseField
    private Date dob;
    
    @DatabaseField(foreign = true)
    private Address residentAddress;
    
    @DatabaseField
    private String personIdentityNumber;
    
    @DatabaseField
    private String issuedPlace;
    
    @DatabaseField
    private Date issuedDate;
    
    @DatabaseField(foreign = true)
    private Address contactAddress;

	/**
	 * @return the employee
	 */
	public Employee getEmployee() {
		return employee;
	}

	/**
	 * @param employee the employee to set
	 */
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

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
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the gender
	 */
	public int getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(int gender) {
		this.gender = gender;
	}

	/**
	 * @return the dob
	 */
	public Date getDob() {
		return dob;
	}

	/**
	 * @param dob the dob to set
	 */
	public void setDob(Date dob) {
		this.dob = dob;
	}

	/**
	 * @return the residentAddress
	 */
	public Address getResidentAddress() {
		return residentAddress;
	}

	/**
	 * @param residentAddress the residentAddress to set
	 */
	public void setResidentAddress(Address residentAddress) {
		this.residentAddress = residentAddress;
	}

	/**
	 * @return the personIdentityNumber
	 */
	public String getPersonIdentityNumber() {
		return personIdentityNumber;
	}

	/**
	 * @param personIdentityNumber the personIdentityNumber to set
	 */
	public void setPersonIdentityNumber(String personIdentityNumber) {
		this.personIdentityNumber = personIdentityNumber;
	}

	/**
	 * @return the issuedPlace
	 */
	public String getIssuedPlace() {
		return issuedPlace;
	}

	/**
	 * @param issuedPlace the issuedPlace to set
	 */
	public void setIssuedPlace(String issuedPlace) {
		this.issuedPlace = issuedPlace;
	}

	/**
	 * @return the issuedDate
	 */
	public Date getIssuedDate() {
		return issuedDate;
	}

	/**
	 * @param issuedDate the issuedDate to set
	 */
	public void setIssuedDate(Date issuedDate) {
		this.issuedDate = issuedDate;
	}

	/**
	 * @return the contactAddress
	 */
	public Address getContactAddress() {
		return contactAddress;
	}

	/**
	 * @param contactAddress the contactAddress to set
	 */
	public void setContactAddress(Address contactAddress) {
		this.contactAddress = contactAddress;
	}

}
