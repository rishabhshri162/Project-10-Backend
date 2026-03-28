package com.rays.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rays.dto.UserDTO;

/**
 * Service class for loading user-specific data during authentication. This
 * class implements UserDetailsService provided by Spring Security.
 * 
 * It is used by the authentication manager to load user details from the
 * database based on username (loginId).
 * 
 * If the user is not found, it throws UsernameNotFoundException.
 * 
 * It also provides a PasswordEncoder bean for encoding passwords.
 * 
 * This class plays an important role in JWT-based authentication.
 * 
 * @author Rishabh Shrivastava
 */
@Service
public class JWTUserDetailsService implements UserDetailsService {

	/**
	 * Creates and returns a PasswordEncoder bean using BCrypt algorithm.
	 * 
	 * @return PasswordEncoder instance
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	UserServiceInt userService;

	/**
	 * Loads user details by username.
	 * 
	 * It fetches user data from the database using loginId and converts it into
	 * Spring Security UserDetails object.
	 * 
	 * @param username the username (loginId) of the user
	 * @return UserDetails object containing user information
	 * @throws UsernameNotFoundException if user is not found
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserDTO user = userService.findByLoginId(username, null);

		if (user == null) {
			throw new UsernameNotFoundException("User not found with username : " + username);
		}

		return User.builder().username(user.getLoginId()).password(passwordEncoder().encode(user.getPassword()))
				.roles("USER").build();
	}
}