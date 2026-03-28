package com.rays.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.rays.common.BaseDTO;

/**
 * Data Transfer Object (DTO) for Role.
 * This class represents role-related data and is mapped
 * to the database table st_role using JPA annotations.
 * 
 * It contains information such as role name and description.
 * 
 * Extends BaseDTO to inherit common properties and methods.
 * 
 * It defines unique key, label, and table name
 * used for validation and display purposes.
 * 
 * @author Rishabh Shrivastava
 */
@Entity
@Table(name = "st_role")
public class RoleDTO extends BaseDTO {

	/**
	 * Name of the role.
	 */
	@Column(name = "name", length = 50)
	private String name = null;

	/**
	 * Description of the role.
	 */
	@Column(name = "description", length = 100)
	private String description = null;

	/**
	 * Gets role name.
	 * 
	 * @return role name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets role name.
	 * 
	 * @param name role name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets role description.
	 * 
	 * @return description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets role description.
	 * 
	 * @param description role description
	 */
	public void setDescription(String description) {
		this.description = description;
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
	 * @return role name
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
		return "Role Name";
	}
	
	/**
	 * Returns table name.
	 * 
	 * @return table name
	 */
	@Override
	public String getTableName() {
		return "Role";
	}

	/**
	 * Returns value for display.
	 * 
	 * @return role name
	 */
	@Override
	public String getValue() {
		return name;
	}
}