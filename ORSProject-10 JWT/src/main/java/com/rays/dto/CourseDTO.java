package com.rays.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.rays.common.BaseDTO;

/**
 * Data Transfer Object (DTO) for Course.
 * This class represents course-related data and is mapped
 * to the database table st_course using JPA annotations.
 * 
 * It contains details such as course name, duration,
 * and description.
 * 
 * Extends BaseDTO to inherit common properties and methods.
 * 
 * It also defines unique key, label, and table name
 * used for validation and display purposes.
 * 
 * @author Rishabh Shrivastava
 */
@Entity
@Table(name = "st_course")
public class CourseDTO extends BaseDTO {

	/**
	 * Name of the course.
	 */
	@Column(name = "name", length = 50)
	private String name;

	/**
	 * Duration of the course.
	 */
	@Column(name = "duration", length = 50)
	private String duration;

	/**
	 * Description of the course.
	 */
	@Column(name = "description", length = 50)
	private String description;

	/**
	 * Gets course name.
	 * 
	 * @return course name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets course name.
	 * 
	 * @param name course name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets course duration.
	 * 
	 * @return duration
	 */
	public String getDuration() {
		return duration;
	}

	/**
	 * Sets course duration.
	 * 
	 * @param duration course duration
	 */
	public void setDuration(String duration) {
		this.duration = duration;
	}

	/**
	 * Gets course description.
	 * 
	 * @return description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets course description.
	 * 
	 * @param description course description
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
	 * @return course name
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
		return "Course Name";
	}
	
	/**
	 * Returns table name.
	 * 
	 * @return table name
	 */
	@Override
	public String getTableName() {
		return "Course";
	}

	/**
	 * Returns value for display.
	 * 
	 * @return course name
	 */
	@Override
	public String getValue() {
		return name;
	}
}