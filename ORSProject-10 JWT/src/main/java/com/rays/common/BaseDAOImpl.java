package com.rays.common;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Base DAO implementation class providing common database operations.
 * 
 * This class is extended by all DAO classes to reuse common CRUD logic
 * such as add, update, delete, find, and search operations.
 * 
 * It uses JPA EntityManager and Criteria API for dynamic query building.
 * 
 * T represents DTO class.
 * 
 * @author Rishabh Shrivastava
 */
public abstract class BaseDAOImpl<T extends BaseDTO> implements BaseDAOInt<T> {

	@PersistenceContext
	protected EntityManager entityManager;

	/**
	 * Returns the DTO class type.
	 * 
	 * @return Class of DTO
	 */
	public abstract Class<T> getDTOClass();

	/**
	 * Creates where clause conditions for search queries.
	 * 
	 * @param dto data transfer object
	 * @param builder criteria builder
	 * @param qRoot root entity
	 * @return list of predicates
	 */
	protected abstract List<Predicate> getWhereClause(T dto, CriteriaBuilder builder, Root<T> qRoot);

	/**
	 * Populates additional fields before database operations.
	 * Can be overridden in child classes.
	 * 
	 * @param dto data object
	 * @param userContext user context
	 */
	protected void populate(T dto, UserContext userContext) {
	}

	/**
	 * Adds a new record to database.
	 * 
	 * @param dto data object
	 * @param userContext user context
	 * @return generated ID
	 */
	public long add(T dto, UserContext userContext) {

		dto.setCreatedBy(userContext.getLoginId());
		dto.setCreatedDatetime(new Timestamp(new Date().getTime()));
		dto.setModifiedBy(userContext.getLoginId());
		dto.setModifiedDatetime(new Timestamp(new Date().getTime()));

		populate(dto, userContext);

		entityManager.persist(dto);

		return dto.getId();
	}

	/**
	 * Updates an existing record.
	 * 
	 * @param dto data object
	 * @param userContext user context
	 */
	public void update(T dto, UserContext userContext) {

		dto.setModifiedBy(userContext.getLoginId());
		dto.setModifiedDatetime(new Timestamp(new Date().getTime()));

		populate(dto, userContext);

		entityManager.merge(dto);
	}

	/**
	 * Deletes a record from database.
	 * 
	 * @param dto data object
	 * @param userContext user context
	 */
	public void delete(T dto, UserContext userContext) {
		entityManager.remove(dto);
	}

	/**
	 * Finds a record by primary key.
	 * 
	 * @param pk primary key
	 * @param userContext user context
	 * @return DTO object
	 */
	public T findByPK(long pk, UserContext userContext) {
		T dto = entityManager.find(getDTOClass(), pk);
		return dto;
	}

	/**
	 * Finds a record using unique attribute.
	 * 
	 * @param attribute field name
	 * @param val value
	 * @param userContext user context
	 * @return DTO object
	 */
	public T findByUniqueKey(String attribute, Object val, UserContext userContext) {

		Class<T> dtoClass = getDTOClass();

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();

		CriteriaQuery<T> cq = builder.createQuery(dtoClass);

		Root<T> qRoot = cq.from(dtoClass);

		Predicate condition = builder.equal(qRoot.get(attribute), val);

		cq.where(condition);

		TypedQuery<T> query = entityManager.createQuery(cq);

		List<T> list = query.getResultList();

		T dto = null;

		if (list.size() > 0) {
			dto = list.get(0);
		}

		return dto;
	}

	/**
	 * Creates criteria query for search operation.
	 * 
	 * @param dto data object
	 * @param userContext user context
	 * @return typed query
	 */
	protected TypedQuery<T> createCriteria(T dto, UserContext userContext) {

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();

		CriteriaQuery<T> cq = builder.createQuery(getDTOClass());

		Root<T> qRoot = cq.from(getDTOClass());

		cq.select(qRoot);

		List<Predicate> whereClause = getWhereClause(dto, builder, qRoot);

		cq.where(whereClause.toArray(new Predicate[whereClause.size()]));

		TypedQuery<T> query = entityManager.createQuery(cq);

		return query;
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
	public List search(T dto, int pageNo, int pageSize, UserContext userContext) {

		TypedQuery<T> query = createCriteria(dto, userContext);

		if (pageSize > 0) {
			query.setFirstResult(pageNo * pageSize);
			query.setMaxResults(pageSize);
		}

		List list = query.getResultList();
		return list;
	}

	/**
	 * Returns top 10 records using HQL query.
	 * 
	 * @param hql query string
	 * @param userContext user context
	 * @return list of records
	 */
	public List marksheetMeritList(String hql, UserContext userContext) {
		Query q = entityManager.createQuery(hql);
		q.setFirstResult(0);
		q.setMaxResults(10);
		List l = q.getResultList();
		return l;
	}

	/**
	 * Searches records without pagination.
	 * 
	 * @param dto data object
	 * @param userContext user context
	 * @return list of records
	 */
	public List search(T dto, UserContext userContext) {
		return search(dto, 0, 0, userContext);
	}

	/**
	 * Checks if string is empty.
	 */
	protected boolean isEmptyString(String val) {
		return val == null || val.trim().length() == 0;
	}

	/**
	 * Checks if Double is zero.
	 */
	protected boolean isZeroNumber(Double val) {
		return val == null || val == 0;
	}

	/**
	 * Checks if Long is zero.
	 */
	protected boolean isZeroNumber(Long val) {
		return val == null || val == 0;
	}

	/**
	 * Checks if Integer is zero.
	 */
	protected boolean isZeroNumber(Integer val) {
		return val == null || val == 0;
	}

	/**
	 * Checks if object is not null.
	 */
	protected boolean isNotNull(Object val) {
		return val != null;
	}
}