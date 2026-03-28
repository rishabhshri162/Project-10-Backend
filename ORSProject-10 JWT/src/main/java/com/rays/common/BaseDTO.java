package com.rays.common;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

/**
 * Base DTO class containing common fields for all entities.
 * 
 * This class is extended by all DTO classes to provide
 * common attributes such as id, createdBy, modifiedBy,
 * createdDatetime, and modifiedDatetime.
 * 
 * It also defines abstract methods for unique key handling,
 * label generation, and table name reference.
 * 
 * @MappedSuperclass is used so that its properties are
 * inherited by child entity classes.
 * 
 * @author Rishabh Shrivastava
 */
@MappedSuperclass
public abstract class BaseDTO implements DropdownList {

	@Id
	@GeneratedValue(generator = "ncsPk")
	@GenericGenerator(name = "ncsPk", strategy = "native")
	@Column(name = "id", unique = true, nullable = false)
	protected Long id;

	@Column(name = "created_by", length = 50)
	protected String createdBy = "root";

	@Column(name = "modified_by", length = 50)
	protected String modifiedBy = "root";

	@Column(name = "created_datetime")
	protected Timestamp createdDatetime;

	@Column(name = "modified_datetime")
	protected Timestamp modifiedDatetime;

	/**
	 * Returns unique key field name.
	 */
	public abstract String getUniqueKey();

	/**
	 * Returns value of unique key.
	 */
	public abstract String getUniqueValue();

	/**
	 * Returns label for display.
	 */
	public abstract String getLabel();
	
	/**
	 * Returns table name.
	 */
	public abstract String getTableName();

	/**
	 * Gets ID.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets ID.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets createdBy.
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * Sets createdBy.
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * Gets modifiedBy.
	 */
	public String getModifiedBy() {
		return modifiedBy;
	}

	/**
	 * Sets modifiedBy.
	 */
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	/**
	 * Gets created datetime.
	 */
	public Timestamp getCreatedDatetime() {
		return createdDatetime;
	}

	/**
	 * Sets created datetime.
	 */
	public void setCreatedDatetime(Timestamp createdDatetime) {
		this.createdDatetime = createdDatetime;
	}

	/**
	 * Gets modified datetime.
	 */
	public Timestamp getModifiedDatetime() {
		return modifiedDatetime;
	}

	/**
	 * Sets modified datetime.
	 */
	public void setModifiedDatetime(Timestamp modifiedDatetime) {
		this.modifiedDatetime = modifiedDatetime;
	}

	/**
	 * Returns key for dropdown list.
	 */
	public String getKey() {
		return String.valueOf(id);
	}
}