package com.rays.common;

import java.util.List;

/**
 * Base DAO interface defining common database operations.
 * 
 * This interface is implemented by all DAO classes to provide
 * standard CRUD operations such as add, update, delete, find,
 * and search.
 * 
 * T represents DTO class.
 * 
 * @author Rishabh Shrivastava
 */
public interface BaseDAOInt<T extends BaseDTO> {

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
	 * Deletes a record.
	 * 
	 * @param dto data object
	 * @param userContext user context
	 */
	public void delete(T dto, UserContext userContext);

	/**
	 * Finds record by primary key.
	 * 
	 * @param pk primary key
	 * @param userContext user context
	 * @return DTO object
	 */
	public T findByPK(long pk, UserContext userContext);

	/**
	 * Finds record using unique field.
	 * 
	 * @param attribute field name
	 * @param val value
	 * @param userContext user context
	 * @return DTO object
	 */
	public T findByUniqueKey(String attribute, Object val, UserContext userContext);

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