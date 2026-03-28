package com.rays.common;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rays.exception.DatabaseException;
import com.rays.exception.DuplicateRecordException;

/**
 * Base Service implementation class providing common business logic.
 * 
 * This class is extended by all service classes to reuse common
 * operations like add, update, delete, save, find, and search.
 * 
 * It interacts with DAO layer and manages transactions.
 * 
 * T represents DTO class
 * D represents DAO interface
 * 
 * @author Rishabh Shrivastava
 */
public class BaseServiceImpl<T extends BaseDTO, D extends BaseDAOInt<T>> implements BaseServiceInt<T> {

	@Autowired
	protected D baseDao;

	/**
	 * Adds a new record.
	 * 
	 * @param dto data object
	 * @param userContext user context
	 * @return generated ID
	 * @throws DuplicateRecordException if duplicate record found
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public long add(T dto, UserContext userContext) throws DuplicateRecordException {
		long pk = baseDao.add(dto, userContext);
		return pk;
	}

	/**
	 * Updates an existing record.
	 * 
	 * @param dto data object
	 * @param userContext user context
	 * @throws DuplicateRecordException if duplicate record found
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void update(T dto, UserContext userContext) throws DuplicateRecordException {

		T oldDto = baseDao.findByPK(dto.getId(), userContext);

		if (oldDto != null) {
			dto.setCreatedBy(oldDto.getCreatedBy());
			dto.setCreatedDatetime(oldDto.getCreatedDatetime());
		}

		baseDao.update(dto, userContext);
	}

	/**
	 * Saves a record (add or update).
	 * 
	 * @param dto data object
	 * @param userContext user context
	 * @return record ID
	 * @throws DuplicateRecordException if duplicate record found
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public long save(T dto, UserContext userContext) throws DuplicateRecordException {
		Long id = dto.getId();
		if (id != null && id > 0) {
			update(dto, userContext);
		} else {
			id = add(dto, userContext);
		}
		return id;
	}

	/**
	 * Finds record by ID.
	 * 
	 * @param id record ID
	 * @param userContext user context
	 * @return DTO object
	 */
	@Transactional(readOnly = true)
	public T findById(long id, UserContext userContext) {
		T dto = baseDao.findByPK(id, userContext);
		return dto;
	}

	/**
	 * Deletes record by ID.
	 * 
	 * @param id record ID
	 * @param userContext user context
	 * @return deleted DTO
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public T delete(long id, UserContext userContext) {
		T dto = findById(id, userContext);
		if (dto == null) {
			throw new DatabaseException("Record not found");
		}
		baseDao.delete(dto, userContext);
		return dto;
	}

	/**
	 * Finds record using unique key.
	 * 
	 * @param attribute field name
	 * @param val value
	 * @param userContext user context
	 * @return DTO object
	 */
	@Transactional(readOnly = true)
	public T findByUniqueKey(String attribute, String val, UserContext userContext) {
		T dto = baseDao.findByUniqueKey(attribute, val, userContext);
		return dto;
	}

	/**
	 * Searches records with pagination.
	 * 
	 * @param dto data object
	 * @param pageNo page number
	 * @param pageSize page size
	 * @param userContext user context
	 * @return list of records
	 */
	@Transactional(readOnly = true)
	public List<T> search(T dto, int pageNo, int pageSize, UserContext userContext) {
		return baseDao.search(dto, pageNo, pageSize, userContext);
	}

	/**
	 * Searches records without pagination.
	 * 
	 * @param dto data object
	 * @param userContext user context
	 * @return list of records
	 */
	@Transactional(readOnly = true)
	public List<T> search(T dto, UserContext userContext) {
		return baseDao.search(dto, userContext);
	}
}