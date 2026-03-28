package com.rays.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.rays.common.BaseDTO;

/**
 * Data Transfer Object (DTO) for Faculty.
 * This class represents faculty-related data and is mapped
 * to the database table st_faculty using JPA annotations.
 * 
 * It contains personal details such as name, date of birth,
 * gender, contact information, qualification, and associations
 * with college, course, and subject.
 * 
 * Extends BaseDTO to inherit common properties and methods.
 * 
 * It defines unique key, label, and table name for validation
 * and display purposes.
 * 
 * @author Rishabh Shrivastava
 */
@Entity
@Table(name = "st_faculty")
public class FacultyDTO extends BaseDTO {

	/**
	 * First name of the faculty.
	 */
	@Column(name = "first_name", length = 50)
	private String firstName;

	/**
	 * Last name of the faculty.
	 */
	@Column(name = "last_name", length = 50)
	private String lastName;

	/**
	 * Date of birth of the faculty.
	 */
	@Column(name = "dob")
	private Date dob;

	/**
	 * Gender of the faculty.
	 */
	@Column(name = "gender", length = 10)
	private String gender;

	/**
	 * Phone number of the faculty.
	 */
	@Column(name = "phone_no", length = 15)
	private String phoneNo;

	/**
	 * Email address of the faculty.
	 */
	@Column(name = "email", length = 50)
	private String email;

	/**
	 * Qualification of the faculty.
	 */
	@Column(name = "qualification", length = 15)
	private String qualification;

	/**
	 * Associated college ID.
	 */
	@Column(name = "college_id", length = 50)
	private long collegeId;

	/**
	 * Associated college name.
	 */
	@Column(name = "college_name", length = 50)
	private String collegeName;

	/**
	 * Associated course ID.
	 */
	@Column(name = "course_id", length = 50)
	private long courseId;

	/**
	 * Associated course name.
	 */
	@Column(name = "course_name", length = 50)
	private String courseName;

	/**
	 * Associated subject ID.
	 */
	@Column(name = "subject_id", length = 50)
	private long subjectId;

	/**
	 * Associated subject name.
	 */
	@Column(name = "subject_name", length = 50)
	private String subjectName;

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
	 * @param firstName faculty first name
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
	 * @param lastName faculty last name
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
	 * Gets gender.
	 * 
	 * @return gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * Sets gender.
	 * 
	 * @param gender faculty gender
	 */
	public void setGender(String gender) {
		this.gender = gender;
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
	 * Gets qualification.
	 * 
	 * @return qualification
	 */
	public String getQualification() {
		return qualification;
	}

	/**
	 * Sets qualification.
	 * 
	 * @param qualification faculty qualification
	 */
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	/**
	 * Gets college ID.
	 * 
	 * @return college ID
	 */
	public long getCollegeId() {
		return collegeId;
	}

	/**
	 * Sets college ID.
	 * 
	 * @param collegeId college ID
	 */
	public void setCollegeId(long collegeId) {
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
	 * Gets subject ID.
	 * 
	 * @return subject ID
	 */
	public long getSubjectId() {
		return subjectId;
	}

	/**
	 * Sets subject ID.
	 * 
	 * @param subjectId subject ID
	 */
	public void setSubjectId(long subjectId) {
		this.subjectId = subjectId;
	}

	/**
	 * Gets subject name.
	 * 
	 * @return subject name
	 */
	public String getSubjectName() {
		return subjectName;
	}

	/**
	 * Sets subject name.
	 * 
	 * @param subjectName subject name
	 */
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	/**
	 * Returns unique key for validation.
	 * 
	 * @return unique key (email)
	 */
	@Override
	public String getUniqueKey() {
		return "email";
	}

	/**
	 * Returns unique value for validation.
	 * 
	 * @return email value
	 */
	@Override
	public String getUniqueValue() {
		return email;
	}

	/**
	 * Returns label for display messages.
	 * 
	 * @return label string
	 */
	@Override
	public String getLabel() {
		return "Email";
	}
	
	/**
	 * Returns table name.
	 * 
	 * @return table name
	 */
	@Override
	public String getTableName() {
		return "Faculty";
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