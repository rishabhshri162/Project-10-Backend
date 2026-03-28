package com.rays.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.rays.common.BaseDAOImpl;
import com.rays.dto.AttachmentDTO;

/**
 * Attachment DAO implementation class.
 * 
 * This class provides database operations for AttachmentDTO.
 * It extends BaseDAOImpl to reuse common CRUD functionalities.
 * 
 * Currently, no additional filtering conditions are applied
 * in the where clause.
 * 
 * @author Rishabh Shrivastava
 */
@Repository
public class AttachmentDAOImpl extends BaseDAOImpl<AttachmentDTO> implements AttachmentDAOInt {

	/**
	 * Returns AttachmentDTO class type.
	 */
	@Override
	public Class<AttachmentDTO> getDTOClass() {
		return AttachmentDTO.class;
	}

	/**
	 * Builds where clause for search queries.
	 * 
	 * @param dto data object
	 * @param builder criteria builder
	 * @param qRoot root entity
	 * @return list of predicates
	 */
	@Override
	protected List<Predicate> getWhereClause(AttachmentDTO dto, CriteriaBuilder builder, Root<AttachmentDTO> qRoot) {
		List<Predicate> whereCondition = new ArrayList<Predicate>();
		return whereCondition;
	}
}