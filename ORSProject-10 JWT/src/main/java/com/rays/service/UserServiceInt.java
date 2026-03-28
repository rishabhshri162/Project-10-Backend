package com.rays.service;

import com.rays.common.BaseServiceInt;
import com.rays.common.UserContext;
import com.rays.dto.UserDTO;

/**
 * Service interface for User.
 * This interface extends BaseServiceInt to provide standard CRUD operations
 * for UserDTO.
 * 
 * It defines additional methods for user-related business logic
 * such as login, registration, authentication, and password management.
 * 
 * @author Rishabh Shrivastava
 */
public interface UserServiceInt extends BaseServiceInt<UserDTO> {

	/**
	 * Finds user by loginId.
	 * 
	 * @param name         the loginId of the user
	 * @param userContext  user context information
	 * @return UserDTO object if found
	 */
	public UserDTO findByLoginId(String name, UserContext userContext);

	/**
	 * Registers a new user.
	 * 
	 * @param dto          user data
	 * @param userContext  user context information
	 * @return registered UserDTO
	 */
	public UserDTO register(UserDTO dto, UserContext userContext);

	/**
	 * Authenticates user credentials.
	 * 
	 * @param loginId   the loginId of the user
	 * @param password  the password of the user
	 * @return UserDTO if authentication is successful, otherwise null
	 */
	public UserDTO authenticate(String loginId, String password);

	/**
	 * Handles forgot password functionality.
	 * 
	 * @param loginId the loginId of the user
	 * @return UserDTO if user exists, otherwise null
	 */
	public UserDTO forgotPassword(String loginId);

	/**
	 * Changes user password.
	 * 
	 * @param loginId     the loginId of the user
	 * @param oldPassword current password
	 * @param newPassword new password to be set
	 * @param userContext user context information
	 * @return updated UserDTO if successful, otherwise null
	 */
	public UserDTO changePassword(String loginId, String oldPassword, String newPassword, UserContext userContext);

}