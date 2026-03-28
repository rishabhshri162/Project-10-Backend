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
import com.rays.dto.CollegeDTO;
import com.rays.dto.StudentDTO;

/**
 * Student DAO implementation class.
 * 
 * This class provides database operations for StudentDTO.
 * It extends BaseDAOImpl to reuse common CRUD functionalities.
 * 
 * It also populates college name using college ID and
 * applies dynamic search filters for student records.
 * 
 * @author Rishabh Shrivastava
 */
@Repository
public class StudentDAOImpl extends BaseDAOImpl<StudentDTO> implements StudentDAOInt {

	@Autowired
	CollegeDAOInt collegeService;

	/**
	 * Returns StudentDTO class type.
	 */
	@Override
	public Class<StudentDTO> getDTOClass() {
		return StudentDTO.class;
	}

	/**
	 * Populates college name using college ID.
	 * 
	 * @param dto student DTO
	 * @param userContext user context
	 */
	@Override
	protected void populate(StudentDTO dto, UserContext userContext) {
		CollegeDTO collegeDTO = collegeService.findByPK(dto.getCollegeId(), userContext);
		if (collegeDTO != null) {
			dto.setCollegeName(collegeDTO.getName());
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
	protected List<Predicate> getWhereClause(StudentDTO dto, CriteriaBuilder builder, Root<StudentDTO> qRoot) {

		List<Predicate> whereCondition = new ArrayList<Predicate>();

		if (!isEmptyString(dto.getEnrolNo())) {
			whereCondition.add(builder.like(qRoot.get("enrolNo"), dto.getEnrolNo() + "%"));
		}
		
		if (!isEmptyString(dto.getFirstName())) {
			whereCondition.add(builder.like(qRoot.get("firstName"), dto.getFirstName() + "%"));
		}

		if (!isEmptyString(dto.getCollegeName())) {
			whereCondition.add(builder.like(qRoot.get("collegeName"), dto.getCollegeName() + "%"));
		}

		if (!isEmptyString(dto.getEmail())) {
			whereCondition.add(builder.like(qRoot.get("email"), dto.getEmail() + "%"));
		}

		if (isNotNull(dto.getDob())) {
			whereCondition.add(builder.equal(qRoot.get("dob"), dto.getDob()));
		}

		if (!isEmptyString(dto.getPhoneNo())) {
			whereCondition.add(builder.like(qRoot.get("phoneNo"), dto.getPhoneNo() + "%"));
		}

		return whereCondition;
	}
}