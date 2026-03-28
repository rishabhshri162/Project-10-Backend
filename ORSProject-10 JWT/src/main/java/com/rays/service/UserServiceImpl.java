package com.rays.service;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rays.common.BaseServiceImpl;
import com.rays.common.UserContext;
import com.rays.dao.UserDAOInt;
import com.rays.dto.UserDTO;

/**
 * Service implementation class for User. This class extends BaseServiceImpl to
 * use common CRUD operations and implements UserServiceInt for user-related
 * business logic.
 * 
 * It acts as a bridge between controller and DAO layer.
 * 
 * Annotated with @Service to define it as a service component
 * and @Transactional to manage transactions.
 * 
 * This class provides additional functionalities like authentication,
 * registration, password management, and login tracking.
 * 
 * @author Rishabh Shrivastava
 */
@Service
@Transactional
public class UserServiceImpl extends BaseServiceImpl<UserDTO, UserDAOInt> implements UserServiceInt {

	/**
	 * Finds a user by loginId.
	 * 
	 * @param login       the loginId of the user
	 * @param userContext user context information
	 * @return UserDTO object if found
	 */
	@Transactional(readOnly = true)
	public UserDTO findByLoginId(String login, UserContext userContext) {
		return baseDao.findByUniqueKey("loginId", login, userContext);
	}

	/**
	 * Registers a new user.
	 * 
	 * @param dto         user data
	 * @param userContext user context information
	 * @return registered UserDTO with generated id
	 */
	@Override
	public UserDTO register(UserDTO dto, UserContext userContext) {

		Long id = add(dto, userContext);

		dto.setId(id);

		return dto;
	}

	/**
	 * Authenticates user with loginId and password.
	 * 
	 * @param loginId  the loginId of the user
	 * @param password the password of the user
	 * @return UserDTO if authentication is successful, otherwise null
	 */
	@Override
	public UserDTO authenticate(String loginId, String password) {

		UserDTO dto = findByLoginId(loginId, null);

		if (dto != null) {
			UserContext userContext = new UserContext(dto);
			if (password.equals(dto.getPassword())) {
				dto.setLastLogin(new Timestamp((new Date()).getTime()));
				dto.setUnsucessfullLoginAttempt(0);
				update(dto, userContext);
				return dto;
			} else {
				dto.setUnsucessfullLoginAttempt(1 + dto.getUnsucessfullLoginAttempt());
				update(dto, userContext);
			}
		}
		return null;
	}

	/**
	 * Handles forgot password functionality.
	 * 
	 * @param loginId the loginId of the user
	 * @return UserDTO if user exists, otherwise null
	 */
	@Override
	public UserDTO forgotPassword(String loginId) {

		UserDTO dto = findByLoginId(loginId, null);
		if (dto == null) {
			return null;
		}
		return dto;
	}

	/**
	 * Changes user password.
	 * 
	 * @param loginId     the loginId of the user
	 * @param oldPassword current password
	 * @param newPassword new password to be set
	 * @param userContext user context information
	 * @return updated UserDTO if successful, otherwise null
	 */
	@Override
	public UserDTO changePassword(String loginId, String oldPassword, String newPassword, UserContext userContext) {

		UserDTO dto = findByLoginId(loginId, null);

		if (dto != null && oldPassword.equals(dto.getPassword())) {
			dto.setPassword(newPassword);
			update(dto, userContext);
			return dto;
		} else {
			return null;
		}
	}
}