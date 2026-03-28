package com.rays.common;

/**
 * Interface for dropdown list representation.
 * 
 * This interface is implemented by DTO classes to provide
 * key-value pairs for dropdown components in UI.
 * 
 * Key is usually an identifier (like ID),
 * and Value is display text.
 * 
 * @author Rishabh Shrivastava
 */
public interface DropdownList {

	/**
	 * Returns key for dropdown.
	 * 
	 * @return key value
	 */
	public String getKey();

	/**
	 * Returns display value for dropdown.
	 * 
	 * @return display value
	 */
	public String getValue();

}