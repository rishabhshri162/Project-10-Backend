package com.rays.exception;

/**
 * Custom exception class for duplicate record errors.
 * This class extends RuntimeException to handle unchecked exceptions
 * when a duplicate record is found in the database.
 * 
 * It is typically used when trying to insert or update data
 * that violates unique constraints.
 * 
 * @author Rishabh Shrivastava
 */
public class DuplicateRecordException extends RuntimeException {

	/**
	 * Constructs a new DuplicateRecordException with the specified message.
	 * 
	 * @param msg the detail message describing the exception
	 */
	public DuplicateRecordException(String msg) {
		super(msg);
	}

}