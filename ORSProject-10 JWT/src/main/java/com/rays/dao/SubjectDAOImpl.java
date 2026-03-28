package com.rays.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rays.common.BaseDAOImpl;
import com.rays.common.UserContext;
import com.rays.dto.CourseDTO;
import com.rays.dto.SubjectDTO;

/**
 * Subject DAO implementation class.
 * 
 * This class provides database operations for SubjectDTO.
 * It extends BaseDAOImpl to reuse common CRUD functionalities.
 * 
 * It also populates course name using course ID and
 * applies dynamic search filters for subject records.
 * 
 * @author Rishabh Shrivastava
 */
@Repository
public class SubjectDAOImpl extends BaseDAOImpl<SubjectDTO> implements SubjectDAOInt{

	@Autowired
	CourseDAOInt courseDao;
	
	/**
	 * Returns SubjectDTO class type.
	 */
	@Override
	public Class<SubjectDTO> getDTOClass() {
		return SubjectDTO.class;
	}

	/**
	 * Populates course name using course ID.
	 * 
	 * @param dto subject DTO
	 * @param userContext user context
	 */
	@Override
	protected void populate(SubjectDTO dto, UserContext userContext) {
		if (dto.getCourseId() != 0) {
			CourseDTO courseDTO = courseDao.findByPK(dto.getCourseId(), userContext);
			if (courseDTO != null) {
				dto.setCourseName(courseDTO.getName());
			}
		}
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
	protected List<Predicate> getWhereClause(SubjectDTO dto, CriteriaBuilder builder, Root<SubjectDTO> qRoot) {

		List<Predicate> whereCondition = new ArrayList<Predicate>();

		if (!isEmptyString(dto.getName())) {
			whereCondition.add(builder.like(qRoot.get("name"), dto.getName() + "%"));
		}

		if (!isEmptyString(dto.getCourseName())) {
			whereCondition.add(builder.like(qRoot.get("courseName"), dto.getCourseName() + "%"));
		}

		return whereCondition;
	}
}