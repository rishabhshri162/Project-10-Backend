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
import com.rays.dto.MarksheetDTO;
import com.rays.dto.StudentDTO;

/**
 * Marksheet DAO implementation class.
 * 
 * This class provides database operations for MarksheetDTO.
 * It extends BaseDAOImpl to reuse common CRUD functionalities.
 * 
 * It also provides additional functionality such as:
 * - Fetching merit list based on total marks
 * - Populating student name from student ID
 * - Applying dynamic search filters
 * 
 * @author Rishabh Shrivastava
 */
@Repository
public class MarksheetDAOImpl extends BaseDAOImpl<MarksheetDTO> implements MarksheetDAOInt {

	@Autowired
	StudentDAOInt studentDao;

	/**
	 * Retrieves merit list ordered by total marks.
	 * 
	 * @return list of top marksheets
	 */
	@Override
	public List<MarksheetDTO> getMeritList() {
		List list = super.marksheetMeritList("from MarksheetDTO order by (physics+chemistry+maths) desc", null);
		return list;
	}

	/**
	 * Returns MarksheetDTO class type.
	 */
	@Override
	public Class<MarksheetDTO> getDTOClass() {
		return MarksheetDTO.class;
	}

	/**
	 * Populates student name using student ID.
	 * 
	 * @param dto marksheet DTO
	 * @param userContext user context
	 */
	@Override
	protected void populate(MarksheetDTO dto, UserContext userContext) {
		if (dto.getStudentId() != null) {
			StudentDTO studentDTO = studentDao.findByPK(dto.getStudentId(), userContext);
			if (studentDTO != null) {
				dto.setName(studentDTO.getFirstName() + " " + studentDTO.getLastName());
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
	protected List<Predicate> getWhereClause(MarksheetDTO dto, CriteriaBuilder builder, Root<MarksheetDTO> qRoot) {

		List<Predicate> whereCondition = new ArrayList<Predicate>();

		if (!isEmptyString(dto.getName())) {
			whereCondition.add(builder.like(qRoot.get("name"), dto.getName() + "%"));
		}

		if (!isEmptyString(dto.getRollNo())) {
			whereCondition.add(builder.like(qRoot.get("rollNo"), dto.getRollNo() + "%"));
		}

		if (!isZeroNumber(dto.getStudentId())) {
			whereCondition.add(builder.equal(qRoot.get("studentId"), dto.getStudentId()));
		}

		return whereCondition;
	}
}