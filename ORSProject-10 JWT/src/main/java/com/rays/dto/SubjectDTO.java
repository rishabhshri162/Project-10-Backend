package com.rays.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.rays.common.BaseDTO;

/**
 * Data Transfer Object (DTO) for Subject.
 * This class represents subject-related data and is mapped
 * to the database table st_subject using JPA annotations.
 * 
 * It contains details such as subject name, description,
 * and its associated course information.
 * 
 * Extends BaseDTO to inherit common properties and methods.
 * 
 * It defines unique key, label, and table name
 * used for validation and display purposes.
 * 
 * @author Rishabh Shrivastava
 */
@Entity
@Table(name = "st_subject")
public class SubjectDTO extends BaseDTO {

	/**
	 * Course ID associated with the subject.
	 */
	@Column(name = "course_id", length = 50)
	private long courseId;

	/**
	 * Course name associated with the subject.
	 */
	@Column(name = "course_name", length = 50)
	private String courseName;

	/**
	 * Name of the subject.
	 */
	@Column(name = "name", length = 50)
	private String name;

	/**
	 * Description of the subject.
	 */
	@Column(name = "description", length = 50)
	private String description;

	/**
	 * Gets course ID.
	 * 
	 * @return course ID
	 */
	public long getCourseId() {
		return courseId;
	}

	/**
	 * Sets course ID.
	 * 
	 * @param courseId course ID
	 */
	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}

	/**
	 * Gets course name.
	 * 
	 * @return course name
	 */
	public String getCourseName() {
		return courseName;
	}

	/**
	 * Sets course name.
	 * 
	 * @param courseName course name
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	/**
	 * Gets subject name.
	 * 
	 * @return subject name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets subject name.
	 * 
	 * @param name subject name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets subject description.
	 * 
	 * @return description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets subject description.
	 * 
	 * @param description subject description
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
	 * @return subject name
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
		return "Subject Name";
	}
	
	/**
	 * Returns table name.
	 * 
	 * @return table name
	 */
	@Override
	public String getTableName() {
		return "Subject";
	}

	/**
	 * Returns value for display.
	 * 
	 * @return subject name
	 */
	@Override
	public String getValue() {
		return name;
	}
}