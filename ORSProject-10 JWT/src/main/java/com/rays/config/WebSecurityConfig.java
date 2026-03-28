package com.rays.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * WebSecurityConfig class for configuring Spring Security.
 * 
 * This class defines security rules for the application such as:
 * 
 * - Disabling CSRF protection
 * - Allowing public access to specific endpoints
 * - Securing all other endpoints
 * - Enabling stateless session management (JWT आधारित)
 * - Adding JWT filter before authentication filter
 * 
 * It ensures that APIs are protected using JWT authentication.
 * 
 * @author Rishabh Shrivastava
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JWTRequestFilter jwtRequestFilter;

	/**
	 * Configures HTTP security settings.
	 * 
	 * @param http HttpSecurity object
	 * @throws Exception if error occurs
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
				.authorizeRequests()
				.antMatchers("/Auth/**", "/User/profilePic/**").permitAll()
				.anyRequest().authenticated()
				.and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
		http.cors();
	}
}