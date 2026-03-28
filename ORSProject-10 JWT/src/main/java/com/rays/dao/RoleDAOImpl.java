package com.rays.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.rays.common.BaseDAOImpl;
import com.rays.dto.RoleDTO;

/**
 * Role DAO implementation class.
 * 
 * This class provides database operations for RoleDTO.
 * It extends BaseDAOImpl to reuse common CRUD functionalities.
 * 
 * It defines dynamic search conditions based on role ID and name.
 * 
 * @author Rishabh Shrivastava
 */
@Repository
public class RoleDAOImpl extends BaseDAOImpl<RoleDTO> implements RoleDAOInt {

	/**
	 * Returns RoleDTO class type.
	 */
	@Override
	public Class<RoleDTO> getDTOClass() {
		return RoleDTO.class;
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
	protected List<Predicate> getWhereClause(RoleDTO dto, CriteriaBuilder builder, Root<RoleDTO> qRoot) {

		List<Predicate> whereCondition = new ArrayList<Predicate>();

		if (!isZeroNumber(dto.getId())) {
			whereCondition.add(builder.equal(qRoot.get("id"), dto.getId()));
		}

		if (!isEmptyString(dto.getName())) {
			whereCondition.add(builder.like(qRoot.get("name"), dto.getName() + "%"));
		}

		return whereCondition;
	}
}