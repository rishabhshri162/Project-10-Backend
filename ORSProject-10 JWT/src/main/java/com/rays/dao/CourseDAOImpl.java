package com.rays.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.rays.common.BaseDAOImpl;
import com.rays.dto.CourseDTO;

/**
 * Course DAO implementation class.
 * 
 * This class provides database operations for CourseDTO.
 * It extends BaseDAOImpl to reuse common CRUD functionalities.
 * 
 * It defines search criteria using where clause for filtering
 * based on fields like name, description, and duration.
 * 
 * @author Rishabh Shrivastava
 */
@Repository
public class CourseDAOImpl extends BaseDAOImpl<CourseDTO> implements CourseDAOInt{

	/**
	 * Returns CourseDTO class type.
	 */
	@Override
	public Class<CourseDTO> getDTOClass() {
		return CourseDTO.class;
	}

	/**
	 * Builds where clause for dynamic search queries.
	 * 
	 * @param dto data object
	 * @param builder criteria builder
	 * @param qRoot root entity
	 * @return list of predicates
	 */
	@Override
	protected List<Predicate> getWhereClause(CourseDTO dto, CriteriaBuilder builder, Root<CourseDTO> qRoot) {

		List<Predicate> whereCondition = new ArrayList<Predicate>();

		if (!isEmptyString(dto.getName())) {
			whereCondition.add(builder.like(qRoot.get("name"), dto.getName() + "%"));
		}

		if (!isEmptyString(dto.getDescription())) {
			whereCondition.add(builder.like(qRoot.get("description"), dto.getDescription() + "%"));
		}

		if (!isEmptyString(dto.getDuration())) {
			whereCondition.add(builder.like(qRoot.get("duration"), dto.getDuration() + "%"));
		}

		return whereCondition;
	}
}