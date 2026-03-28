package com.rays.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.rays.common.BaseDTO;

/**
 * Data Transfer Object (DTO) for College.
 * This class represents college-related data and is mapped
 * to the database table st_college using JPA annotations.
 * 
 * It contains information such as name, address, state,
 * city, and phone number of the college.
 * 
 * Extends BaseDTO to inherit common properties and methods.
 * 
 * It also defines unique key, label, and table name
 * used for validation and display purposes.
 * 
 * @author Rishabh Shrivastava
 */
@Entity
@Table(name = "st_college")
public class CollegeDTO extends BaseDTO {

	/**
	 * Name of the college.
	 */
	@Column(name = "name", length = 50)
	private String name;

	/**
	 * Address of the college.
	 */
	@Column(name = "address", length = 50)
	private String address;

	/**
	 * State where the college is located.
	 */
	@Column(name = "state", length = 50)
	private String state;

	/**
	 * City where the college is located.
	 */
	@Column(name = "city", length = 50)
	private String city;

	/**
	 * Phone number of the college.
	 */
	@Column(name = "phone_no", length = 15)
	private String phoneNo;

	/**
	 * Gets college name.
	 * 
	 * @return college name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets college name.
	 * 
	 * @param name college name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets address.
	 * 
	 * @return address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Sets address.
	 * 
	 * @param address college address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Gets state.
	 * 
	 * @return state
	 */
	public String getState() {
		return state;
	}

	/**
	 * Sets state.
	 * 
	 * @param state college state
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * Gets city.
	 * 
	 * @return city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Sets city.
	 * 
	 * @param city college city
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * Gets phone number.
	 * 
	 * @return phone number
	 */
	public String getPhoneNo() {
		return phoneNo;
	}

	/**
	 * Sets phone number.
	 * 
	 * @param phoneNo contact number
	 */
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	/**
	 * Returns unique key for validation.
	 * 
	 * @return unique key (name)
	 */
	@Override
	public String getUniqueKey() {
		return "name";
	}

	/**
	 * Returns unique value for validation.
	 * 
	 * @return college name
	 */
	@Override
	public String getUniqueValue() {
		return name;
	}

	/**
	 * Returns label for display messages.
	 * 
	 * @return label string
	 */
	@Override
	public String getLabel() {
		return "College Name";
	}
	
	/**
	 * Returns table name.
	 * 
	 * @return table name
	 */
	@Override
	public String getTableName() {
		return "College";
	}

	/**
	 * Returns value for display.
	 * 
	 * @return college name
	 */
	@Override
	public String getValue() {
		return name;
	}
}