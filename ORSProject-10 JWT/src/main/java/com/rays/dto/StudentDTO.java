package com.rays.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.rays.common.BaseDTO;

/**
 * Data Transfer Object (DTO) for Student.
 * This class represents student-related data and is mapped
 * to the database table st_student using JPA annotations.
 * 
 * It contains personal and academic details such as enrollment number,
 * name, date of birth, contact information, and associated college.
 * 
 * Extends BaseDTO to inherit common properties and methods.
 * 
 * It defines unique key, label, and table name
 * used for validation and display purposes.
 * 
 * @author Rishabh Shrivastava
 */
@Entity
@Table(name = "st_student")
public class StudentDTO extends BaseDTO {

	/**
	 * Enrollment number of the student.
	 */
	@Column(name = "enrol_no", length = 20)
	private String enrolNo;

	/**
	 * First name of the student.
	 */
	@Column(name = "first_name", length = 50)
	private String firstName;

	/**
	 * Last name of the student.
	 */
	@Column(name = "last_name", length = 50)
	private String lastName;

	/**
	 * Date of birth of the student.
	 */
	@Column(name = "dob")
	private Date dob;

	/**
	 * Phone number of the student.
	 */
	@Column(name = "phone_no", length = 15)
	private String phoneNo;

	/**
	 * Email address of the student.
	 */
	@Column(name = "email", length = 50)
	private String email;

	/**
	 * College ID associated with the student.
	 */
	@Column(name = "college_id")
	private Long collegeId;

	/**
	 * College name associated with the student.
	 */
	@Column(name = "college_name", length = 50)
	private String collegeName;

	/**
	 * Gets enrollment number.
	 * 
	 * @return enrollment number
	 */
	public String getEnrolNo() {
		return enrolNo;
	}

	/**
	 * Sets enrollment number.
	 * 
	 * @param enrolNo student enrollment number
	 */
	public void setEnrolNo(String enrolNo) {
		this.enrolNo = enrolNo;
	}

	/**
	 * Gets first name.
	 * 
	 * @return first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets first name.
	 * 
	 * @param firstName student first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Gets last name.
	 * 
	 * @return last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets last name.
	 * 
	 * @param lastName student last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Gets date of birth.
	 * 
	 * @return date of birth
	 */
	public Date getDob() {
		return dob;
	}

	/**
	 * Sets date of birth.
	 * 
	 * @param dob date of birth
	 */
	public void setDob(Date dob) {
		this.dob = dob;
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
	 * Gets email.
	 * 
	 * @return email address
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets email.
	 * 
	 * @param email email address
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets college ID.
	 * 
	 * @return college ID
	 */
	public Long getCollegeId() {
		return collegeId;
	}

	/**
	 * Sets college ID.
	 * 
	 * @param collegeId college ID
	 */
	public void setCollegeId(Long collegeId) {
		this.collegeId = collegeId;
	}

	/**
	 * Gets college name.
	 * 
	 * @return college name
	 */
	public String getCollegeName() {
		return collegeName;
	}

	/**
	 * Sets college name.
	 * 
	 * @param collegeName college name
	 */
	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

	/**
	 * Returns unique key for validation.
	 * 
	 * @return unique key (enrolNo)
	 */
	@Override
	public String getUniqueKey() {
		return "enrolNo";
	}

	/**
	 * Returns unique value for validation.
	 * 
	 * @return enrollment number
	 */
	@Override
	public String getUniqueValue() {
		return enrolNo;
	}

	/**
	 * Returns label for display messages.
	 * 
	 * @return label string
	 */
	@Override
	public String getLabel() {
		return "Enroll No";
	}

	/**
	 * Returns table name.
	 * 
	 * @return table name
	 */
	@Override
	public String getTableName() {
		return "Student";
	}

	/**
	 * Returns value for display.
	 * 
	 * @return full name of student
	 */
	@Override
	public String getValue() {
		return firstName + " " + lastName;
	}
}