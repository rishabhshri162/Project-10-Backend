package com.rays.exception;

/**
 * Custom exception class for database-related errors.
 * This class extends RuntimeException to handle unchecked exceptions
 * that occur during database operations.
 * 
 * It is used to represent issues like database connectivity,
 * query execution failure, or data access errors.
 * 
 * @author Rishabh Shrivastava
 */
public class DatabaseException extends RuntimeException {

	/**
	 * Constructs a new DatabaseException with the specified message.
	 * 
	 * @param msg the detail message describing the exception
	 */
	public DatabaseException(String msg) {
		super(msg);
	}
}