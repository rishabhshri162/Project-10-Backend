package com.rays.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.rays.common.BaseDTO;

/**
 * Data Transfer Object (DTO) for TimeTable.
 * This class represents timetable-related data and is mapped
 * to the database table st_timetable using JPA annotations.
 * 
 * It contains details such as course, subject, exam date,
 * exam time, semester, and description.
 * 
 * Extends BaseDTO to inherit common properties and methods.
 * 
 * It defines unique key, table name, and other methods
 * used for validation and display purposes.
 * 
 * @author Rishabh Shrivastava
 */
@Entity
@Table(name = "st_timetable")
public class TimeTableDTO extends BaseDTO {

	/**
	 * Course ID associated with the timetable.
	 */
	@Column(name = "course_id", length = 50)
	private long courseId;

	/**
	 * Course name associated with the timetable.
	 */
	@Column(name = "course_name", length = 50)
	private String courseName;

	/**
	 * Subject ID associated with the timetable.
	 */
	@Column(name = "subject_id", length = 50)
	private long subjectId;

	/**
	 * Subject name associated with the timetable.
	 */
	@Column(name = "subject_name", length = 50)
	private String subjectName;

	/**
	 * Exam date.
	 */
	@Column(name = "exam_date")
	private Date examDate;

	/**
	 * Exam time.
	 */
	@Column(name = "exam_time", length = 50)
	private String examTime;

	/**
	 * Semester information.
	 */
	@Column(name = "semester", length = 30)
	private String semester;

	/**
	 * Description of the timetable.
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
	 * Gets exam date.
	 * 
	 * @return exam date
	 */
	public Date getExamDate() {
		return examDate;
	}

	/**
	 * Sets exam date.
	 * 
	 * @param examDate exam date
	 */
	public void setExamDate(Date examDate) {
		this.examDate = examDate;
	}

	/**
	 * Gets exam time.
	 * 
	 * @return exam time
	 */
	public String getExamTime() {
		return examTime;
	}

	/**
	 * Sets exam time.
	 * 
	 * @param examTime exam time
	 */
	public void setExamTime(String examTime) {
		this.examTime = examTime;
	}

	/**
	 * Gets semester.
	 * 
	 * @return semester
	 */
	public String getSemester() {
		return semester;
	}

	/**
	 * Sets semester.
	 * 
	 * @param semester semester value
	 */
	public void setSemester(String semester) {
		this.semester = semester;
	}

	/**
	 * Gets description.
	 * 
	 * @return description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets description.
	 * 
	 * @param description timetable description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Returns unique key for validation.
	 * 
	 * @return unique key (courseName)
	 */
	@Override
	public String getUniqueKey() {
		return "courseName";
	}

	/**
	 * Returns unique value for validation.
	 * 
	 * @return course name
	 */
	@Override
	public String getUniqueValue() {
		return courseName;
	}

	/**
	 * Returns label (not implemented).
	 * 
	 * @return null
	 */
	@Override
	public String getLabel() {
		return null;
	}

	/**
	 * Returns table name.
	 * 
	 * @return table name
	 */
	@Override
	public String getTableName() {
		return "TimeTable";
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