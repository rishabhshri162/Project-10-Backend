package com.rays.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.rays.common.BaseDTO;

/**
 * Data Transfer Object (DTO) for Marksheet.
 * This class represents student marksheet data and is mapped
 * to the database table st_marksheet using JPA annotations.
 * 
 * It contains details such as roll number, student ID,
 * student name, and marks of subjects like physics,
 * chemistry, and mathematics.
 * 
 * Extends BaseDTO to inherit common properties and methods.
 * 
 * It defines unique key, label, and table name
 * used for validation and display purposes.
 * 
 * @author Rishabh Shrivastava
 */
@Entity
@Table(name = "st_marksheet")
public class MarksheetDTO extends BaseDTO {

	/**
	 * Roll number of the student.
	 */
	@Column(name = "roll_no", length = 20)
	protected String rollNo = null;

	/**
	 * Student ID associated with the marksheet.
	 */
	@Column(name = "student_id")
	protected Long studentId;

	/**
	 * Name of the student.
	 */
	@Column(name = "name", length = 50)
	protected String name = null;

	/**
	 * Marks obtained in Physics.
	 */
	@Column(name = "physics")
	protected Integer physics;

	/**
	 * Marks obtained in Chemistry.
	 */
	@Column(name = "chemistry")
	protected Integer chemistry;

	/**
	 * Marks obtained in Mathematics.
	 */
	@Column(name = "maths")
	protected Integer maths;

	/**
	 * Gets roll number.
	 * 
	 * @return roll number
	 */
	public String getRollNo() {
		return rollNo;
	}

	/**
	 * Sets roll number.
	 * 
	 * @param rollNo student roll number
	 */
	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
	}

	/**
	 * Gets student ID.
	 * 
	 * @return student ID
	 */
	public Long getStudentId() {
		return studentId;
	}

	/**
	 * Sets student ID.
	 * 
	 * @param studentId student ID
	 */
	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	/**
	 * Gets student name.
	 * 
	 * @return student name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets student name.
	 * 
	 * @param name student name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets Physics marks.
	 * 
	 * @return physics marks
	 */
	public Integer getPhysics() {
		return physics;
	}

	/**
	 * Sets Physics marks.
	 * 
	 * @param physics marks in physics
	 */
	public void setPhysics(Integer physics) {
		this.physics = physics;
	}

	/**
	 * Gets Chemistry marks.
	 * 
	 * @return chemistry marks
	 */
	public Integer getChemistry() {
		return chemistry;
	}

	/**
	 * Sets Chemistry marks.
	 * 
	 * @param chemistry marks in chemistry
	 */
	public void setChemistry(Integer chemistry) {
		this.chemistry = chemistry;
	}

	/**
	 * Gets Mathematics marks.
	 * 
	 * @return maths marks
	 */
	public Integer getMaths() {
		return maths;
	}

	/**
	 * Sets Mathematics marks.
	 * 
	 * @param maths marks in mathematics
	 */
	public void setMaths(Integer maths) {
		this.maths = maths;
	}
	
	/**
	 * Returns unique key for validation.
	 * 
	 * @return unique key (rollNo)
	 */
	@Override
	public String getUniqueKey() {
		return "rollNo";
	}

	/**
	 * Returns unique value for validation.
	 * 
	 * @return roll number
	 */
	@Override
	public String getUniqueValue() {
		return rollNo;
	}

	/**
	 * Returns label for display messages.
	 * 
	 * @return label string
	 */
	@Override
	public String getLabel() {
		return "Roll No";
	}
	
	/**
	 * Returns table name.
	 * 
	 * @return table name
	 */
	@Override
	public String getTableName() {
		return "Marksheet";
	}

	/**
	 * Returns value (not implemented).
	 * 
	 * @return null
	 */
	@Override
	public String getValue() {
		return null;
	}
}