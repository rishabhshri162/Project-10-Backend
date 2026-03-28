package com.rays.common;

import java.util.List;

/**
 * Base Service interface defining business operations.
 * 
 * This interface is implemented by all service classes to provide
 * common functionalities like add, update, save, delete, find, and search.
 * 
 * It acts as a bridge between Controller and DAO layer.
 * 
 * T represents DTO class.
 * 
 * @author Rishabh Shrivastava
 */
public interface BaseServiceInt<T extends BaseDTO> {

	/**
	 * Adds a new record.
	 * 
	 * @param dto data object
	 * @param userContext user context
	 * @return generated ID
	 */
	public long add(T dto, UserContext userContext);

	/**
	 * Updates an existing record.
	 * 
	 * @param dto data object
	 * @param userContext user context
	 */
	public void update(T dto, UserContext userContext);

	/**
	 * Saves a record (add or update).
	 * 
	 * @param dto data object
	 * @param userContext user context
	 * @return record ID
	 */
	public long save(T dto, UserContext userContext);

	/**
	 * Deletes a record by ID.
	 * 
	 * @param id record ID
	 * @param userContext user context
	 * @return deleted DTO
	 */
	public T delete(long id, UserContext userContext);

	/**
	 * Finds record by ID.
	 * 
	 * @param id record ID
	 * @param userContext user context
	 * @return DTO object
	 */
	public T findById(long id, UserContext userContext);

	/**
	 * Finds record using unique key.
	 * 
	 * @param attribute field name
	 * @param val value
	 * @param userContext user context
	 * @return DTO object
	 */
	public T findByUniqueKey(String attribute, String val, UserContext userContext);

	/**
	 * Searches records with pagination.
	 * 
	 * @param dto data object
	 * @param pageNo page number
	 * @param pageSize page size
	 * @param userContext user context
	 * @return list of records
	 */
	public List search(T dto, int pageNo, int pageSize, UserContext userContext);

	/**
	 * Searches records without pagination.
	 * 
	 * @param dto data object
	 * @param userContext user context
	 * @return list of records
	 */
	public List search(T dto, UserContext userContext);

}