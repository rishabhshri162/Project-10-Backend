package com.rays.common;

import com.rays.dto.UserDTO;

/**
 * UserContext class holds information about the logged-in user.
 * 
 * This class is used to maintain user-related details such as
 * userId, loginId, name, roleId, and roleName during request processing.
 * 
 * It is mainly used for session management and passing user information
 * across layers (Controller → Service → DAO).
 * 
 * @author Rishabh Shrivastava
 */
public class UserContext {

	private Long userId = 0L;
	private String loginId = "root";
	private String name = null;
	private Long roleId = 0L;
	private String roleName = "root";

	private UserDTO userDTO = null;

	/**
	 * Default constructor.
	 */
	public UserContext() {
	}

	/**
	 * Parameterized constructor to initialize user context using UserDTO.
	 * 
	 * @param dto user data transfer object
	 */
	public UserContext(UserDTO dto) {
		this.userDTO = dto;
		this.userId = dto.getId();
		this.loginId = dto.getLoginId();
		this.name = dto.getName();
		this.roleId = dto.getRoleId();
		this.roleName = dto.getRoleName();
	}

	/**
	 * Gets user ID.
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * Sets user ID.
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * Gets login ID.
	 */
	public String getLoginId() {
		return loginId;
	}

	/**
	 * Sets login ID.
	 */
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	/**
	 * Gets user name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets user name.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets role ID.
	 */
	public Long getRoleId() {
		return roleId;
	}

	/**
	 * Sets role ID.
	 */
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	/**
	 * Gets role name.
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 * Sets role name.
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	/**
	 * Gets UserDTO object.
	 */
	public UserDTO getUserDTO() {
		return userDTO;
	}

	/**
	 * Sets UserDTO object.
	 */
	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}
}