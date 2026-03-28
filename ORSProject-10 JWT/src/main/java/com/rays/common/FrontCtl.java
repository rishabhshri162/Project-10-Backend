package com.rays.common;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * Front Controller interceptor for handling request validation.
 * 
 * This class intercepts all incoming HTTP requests before they reach
 * the controller and performs common tasks such as:
 * 
 * - Setting CORS headers
 * - Handling preflight (OPTIONS) requests
 * - Validating user session
 * 
 * If session is not found, it returns an unauthorized response.
 * 
 * @author Rishabh Shrivastava
 */
@Component
public class FrontCtl extends HandlerInterceptorAdapter {

	/**
	 * Intercepts request before controller execution.
	 * 
	 * @param request HTTP request
	 * @param response HTTP response
	 * @param handler handler object
	 * @return true if request should proceed, false otherwise
	 * @throws Exception if error occurs
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
		response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type");
		response.setHeader("Access-Control-Allow-Credentials", "true");

		if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
			response.setStatus(HttpServletResponse.SC_OK);
			return false;
		}

		HttpSession session = request.getSession();

		if ((UserContext) session.getAttribute("userContext") == null) {
			
			response.setContentType("application/json");
			
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			
			PrintWriter out = response.getWriter();
			
			out.print("{\"success\":\"false\",\"error\":\"OOPS! Your session has been expired\"}");
			out.close();
			return false;
		}
		return true;
	}
}