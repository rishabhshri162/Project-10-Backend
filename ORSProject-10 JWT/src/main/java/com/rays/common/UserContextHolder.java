package com.rays.common;

/**
 * UserContextHolder class manages UserContext using ThreadLocal.
 * 
 * This class stores user-specific data for each request thread.
 * It ensures that user information is isolated per thread and
 * can be accessed globally during request processing.
 * 
 * Commonly used in multi-threaded environments like web applications.
 * 
 * @author Rishabh Shrivastava
 */
public class UserContextHolder {

	private static final ThreadLocal<UserContext> threadLocal = new ThreadLocal<>();

	/**
	 * Sets UserContext for current thread.
	 * 
	 * @param context user context
	 */
	public static void setContext(UserContext context) {
		threadLocal.set(context);
	}

	/**
	 * Gets UserContext for current thread.
	 * 
	 * @return user context
	 */
	public static UserContext getContext() {
		return threadLocal.get();
	}

	/**
	 * Clears UserContext from current thread.
	 */
	public static void clear() {
		threadLocal.remove();
	}
}