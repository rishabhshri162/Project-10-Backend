package com.rays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Main entry point for ORS Project 10 Spring Boot application.
 * 
 * This class bootstraps the application, starts the embedded server, and
 * configures global settings like CORS.
 * 
 * The @SpringBootApplication annotation enables auto-configuration, component
 * scanning, and configuration support.
 * 
 * @author Rishabh Shrivastava
 */
@SpringBootApplication
public class ORSProject10Application {

	/**
	 * Starts the Spring Boot application.
	 * 
	 * @param args command-line arguments
	 */
	public static void main(String[] args) {

		SpringApplication.run(ORSProject10Application.class, args);

		System.out.println("Spring boot application successfully started!...");
	}

	/**
	 * Configures global CORS settings.
	 * 
	 * This allows frontend applications (like Angular) to access backend APIs
	 * running on different origins (domain/port).
	 * 
	 * Allowed: - All URLs (/**) - All origins (*) - Methods: GET, POST, PUT,
	 * DELETE, OPTIONS - All headers - Credentials
	 * 
	 * @return WebMvcConfigurer with CORS configuration
	 */
	@Bean
	public WebMvcConfigurer corsConfig() {
		return new WebMvcConfigurer() {

			@Override
			public void addCorsMappings(CorsRegistry registry) {

				CorsRegistration cors = registry.addMapping("/**").allowedOrigins("*")
						.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS").allowedHeaders("*")
						.allowCredentials(true);
			}
		};
	}
}