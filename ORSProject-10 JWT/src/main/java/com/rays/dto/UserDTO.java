package com.rays.dto;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.rays.common.BaseDTO;

/**
 * Data Transfer Object (DTO) for User.
 * This class represents user-related data and is mapped
 * to the database table st_user using JPA annotations.
 * 
 * It contains personal details, login credentials,
 * role information, status, and login tracking details.
 * 
 * Extends BaseDTO to inherit common properties and methods.
 * 
 * It defines unique key, label, and table name
 * used for validation and display purposes.
 * 
 * @author Rishabh Shrivastava
 */
@Entity
@Table(name = "st_user")
public class UserDTO extends BaseDTO {

	/**
	 * User status constants.
	 */
	public static final String ACTIVE = "Active";
	public static final String DEACTIVE = "Deactive";
	public static final String LOCKED = "Locked";

	/**
	 * First name of the user.
	 */
	@Column(name = "first_name", length = 50)
	private String firstName;

	/**
	 * Last name of the user.
	 */
	@Column(name = "last_name", length = 50)
	private String lastName;

	/**
	 * Login ID (username) of the user.
	 */
	@Column(name = "login_id", length = 50)
	private String loginId;

	/**
	 * Password of the user.
	 */
	@Column(name = "password", length = 50)
	private String password;

	/**
	 * Role ID assigned to the user.
	 */
	@Column(name = "role_id")
	private Long roleId;

	/**
	 * Role name assigned to the user.
	 */
	@Column(name = "role_name", length = 50)
	private String roleName = null;

	/**
	 * Date of birth of the user.
	 */
	@Column(name = "dob")
	private Date dob;

	/**
	 * Gender of the user.
	 */
	@Column(name = "gender", length = 10)
	private String gender;

	/**
	 * Primary phone number of the user.
	 */
	@Column(name = "phone", length = 50)
	private String phone;

	/**
	 * Alternate mobile number.
	 */
	@Column(name = "alternate_mobile", length = 50)
	private String alternateMobile;

	/**
	 * Current status of the user (Active, Deactive, Locked).
	 */
	@Column(name = "status", length = 20)
	private String status;

	/**
	 * Last login timestamp.
	 */
	@Column(name = "last_login")
	private Timestamp lastLogin;

	/**
	 * Count of unsuccessful login attempts.
	 */
	@Column(name = "unsucess_login")
	private Integer unsucessfullLoginAttempt = 0;

	/**
	 * Image ID associated with the user.
	 */
	@Column(name = "image_id")
	private Long imageId;

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
	 * @param firstName user first name
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
	 * @param lastName user last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Gets login ID.
	 * 
	 * @return login ID
	 */
	public String getLoginId() {
		return loginId;
	}

	/**
	 * Sets login ID.
	 * 
	 * @param loginId username
	 */
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	/**
	 * Gets password.
	 * 
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets password.
	 * 
	 * @param password user password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets role ID.
	 * 
	 * @return role ID
	 */
	public Long getRoleId() {
		return roleId;
	}

	/**
	 * Sets role ID.
	 * 
	 * @param roleId role ID
	 */
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	/**
	 * Gets role name.
	 * 
	 * @return role name
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 * Sets role name.
	 * 
	 * @param roleName role name
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
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
	 * @param gender user gender
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * Gets phone number.
	 * 
	 * @return phone number
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * Sets phone number.
	 * 
	 * @param phone contact number
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * Gets alternate mobile number.
	 * 
	 * @return alternate mobile
	 */
	public String getAlternateMobile() {
		return alternateMobile;
	}

	/**
	 * Sets alternate mobile number.
	 * 
	 * @param alternateMobile alternate contact number
	 */
	public void setAlternateMobile(String alternateMobile) {
		this.alternateMobile = alternateMobile;
	}

	/**
	 * Gets user status.
	 * 
	 * @return status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Sets user status.
	 * 
	 * @param status user status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Gets last login timestamp.
	 * 
	 * @return last login time
	 */
	public Timestamp getLastLogin() {
		return lastLogin;
	}

	/**
	 * Sets last login timestamp.
	 * 
	 * @param lastLogin login time
	 */
	public void setLastLogin(Timestamp lastLogin) {
		this.lastLogin = lastLogin;
	}

	/**
	 * Gets unsuccessful login attempts.
	 * 
	 * @return login attempt count
	 */
	public Integer getUnsucessfullLoginAttempt() {
		return unsucessfullLoginAttempt;
	}

	/**
	 * Sets unsuccessful login attempts.
	 * 
	 * @param unsucessfullLoginAttempt count of failed attempts
	 */
	public void setUnsucessfullLoginAttempt(Integer unsucessfullLoginAttempt) {
		this.unsucessfullLoginAttempt = unsucessfullLoginAttempt;
	}

	/**
	 * Gets image ID.
	 * 
	 * @return image ID
	 */
	public Long getImageId() {
		return imageId;
	}

	/**
	 * Sets image ID.
	 * 
	 * @param imageId image reference ID
	 */
	public void setImageId(Long imageId) {
		this.imageId = imageId;
	}

	/**
	 * Returns full name of the user.
	 * 
	 * @return full name
	 */
	public String getName() {
		return firstName + " " + lastName;
	}

	/**
	 * Returns unique key for validation.
	 * 
	 * @return unique key (loginId)
	 */
	@Override
	public String getUniqueKey() {
		return "loginId";
	}

	/**
	 * Returns unique value for validation.
	 * 
	 * @return loginId
	 */
	@Override
	public String getUniqueValue() {
		return loginId;
	}

	/**
	 * Returns label for display messages.
	 * 
	 * @return label string
	 */
	@Override
	public String getLabel() {
		return "Login Id";
	}
	
	/**
	 * Returns table name.
	 * 
	 * @return table name
	 */
	@Override
	public String getTableName() {
		return "User";
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