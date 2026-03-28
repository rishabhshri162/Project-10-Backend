package com.rays.common;

import java.util.HashMap;
import java.util.Map;

/**
 * ORSResponse class used to standardize API responses.
 * 
 * This class is used to send response data from backend to frontend
 * in a structured format including success status, messages, data,
 * and input errors.
 * 
 * It stores response values in a key-value map.
 * 
 * Common keys:
 * - inputerror : validation errors
 * - message    : success or error message
 * - data       : actual response data
 * 
 * @author Rishabh Shrivastava
 */
public class ORSResponse {

	public static final String INPUT_ERROR = "inputerror";
	public static final String MESSAGE = "message";
	public static final String DATA = "data";

	private Map<String, Object> result = new HashMap<String, Object>();

	public boolean success = false;

	/**
	 * Default constructor.
	 */
	public ORSResponse() {
	}

	/**
	 * Constructor with success flag.
	 * 
	 * @param success status
	 */
	public ORSResponse(boolean success) {
		this.success = success;
	}

	/**
	 * Constructor with success flag and message.
	 * 
	 * @param success status
	 * @param message message text
	 */
	public ORSResponse(boolean success, String message) {
		this.success = success;
		addMessage(message);
	}

	/**
	 * Constructor with success, message and data.
	 * 
	 * @param success status
	 * @param message message text
	 * @param value data value
	 */
	public ORSResponse(boolean success, String message, Object value) {
		this.success = success;
		addMessage(message);
		addData(value);
	}

	/**
	 * Returns success status.
	 */
	public boolean isSuccess() {
		return success;
	}

	/**
	 * Sets success status.
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}

	/**
	 * Returns result map.
	 */
	public Map<String, Object> getResult() {
		return result;
	}

	/**
	 * Sets result map.
	 */
	public void setResult(Map<String, Object> result) {
		this.result = result;
	}

	/**
	 * Adds input error details.
	 */
	public void addInputError(Object value) {
		result.put(INPUT_ERROR, value);
	}

	/**
	 * Adds message to response.
	 */
	public void addMessage(Object value) {
		result.put(MESSAGE, value);
	}

	/**
	 * Adds data to response.
	 */
	public void addData(Object value) {
		result.put(DATA, value);
	}

	/**
	 * Adds custom key-value pair to response.
	 */
	public void addResult(String key, Object value) {
		result.put(key, value);
	}
}