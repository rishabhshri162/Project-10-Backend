package com.rays.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rays.common.BaseServiceImpl;
import com.rays.common.UserContext;
import com.rays.dao.UserDAOInt;
import com.rays.dto.UserDTO;
import com.rays.email.EmailBuilder;
import com.rays.email.EmailMessage;
import com.rays.email.EmailServiceInt;

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

	@Autowired
	private EmailServiceInt emailservice;

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

		baseDao.add(dto, userContext);

		HashMap<String, String> map = new HashMap<>();
		map.put("login", dto.getLoginId());
		map.put("password", dto.getPassword());
		map.put("firstName", dto.getFirstName());

		EmailMessage msg = new EmailMessage();
		msg.setTo(dto.getLoginId());
		msg.setSubject("User Registration Successful");
		msg.setMessage(EmailBuilder.getUserRegistrationMessage(map));
		msg.setMessageType(EmailMessage.HTML_MSG);

		emailservice.sendMail(msg);
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
	public boolean forgotPassword(String loginId) {

		UserDTO dto = findByLoginId(loginId, null);

		if (dto == null) {
			return false;
		}

		// Data map banayenge
		HashMap<String, String> map = new HashMap<>();

		map.put("firstName", dto.getFirstName());
		map.put("lastName", dto.getLastName());
		map.put("login", dto.getLoginId());
		map.put("password", dto.getPassword());

		// HTML message generate
		String message = EmailBuilder.getForgetPasswordMessage(map);

		// Email object create
		EmailMessage email = new EmailMessage();

		email.setTo(dto.getLoginId());
		email.setSubject("Your Password has been forgotten.....");
		email.setMessage(message);
		email.setMessageType(EmailMessage.HTML_MSG);

		// Send mail
		emailservice.sendMail(email);

		return true;
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

		dto.setCreatedBy(userContext.getLoginId());

		// Check user exist + old password match
		if (dto != null && oldPassword.equals(dto.getPassword())) {

			// Update password
			dto.setPassword(newPassword);
			update(dto, userContext);

			// Prepare email data
			HashMap<String, String> map = new HashMap<>();

			map.put("firstName", dto.getFirstName());
			map.put("lastName", dto.getLastName());
			map.put("login", dto.getLoginId());
			map.put("password", dto.getPassword());

			// Generate HTML message
			String message = EmailBuilder.getChangePasswordMessage(map);

			// Create Email object
			EmailMessage email = new EmailMessage();
			email.setTo(dto.getLoginId());
			email.setSubject("ORS Password Changed Successfully");
			email.setMessage(message);
			email.setMessageType(EmailMessage.HTML_MSG);

			// Send mail
			emailservice.sendMail(email);

			return dto;

		} else {
			return null;
		}
	}
}