package com.rays.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.rays.common.BaseDAOImpl;
import com.rays.common.BaseDAOInt;
import com.rays.dto.CollegeDTO;

/**
 * College DAO implementation class.
 * 
 * This class provides database operations for CollegeDTO.
 * It extends BaseDAOImpl to reuse common CRUD functionalities.
 * 
 * It also defines search criteria using where clause for filtering
 * based on fields like name, city, state, address, and phone number.
 * 
 * @author Rishabh Shrivastava
 */
@Repository
public class CollegeDAOImpl extends BaseDAOImpl<CollegeDTO> implements CollegeDAOInt {

	/**
	 * Returns CollegeDTO class type.
	 */
	@Override
	public Class<CollegeDTO> getDTOClass() {
		return CollegeDTO.class;
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
	protected List<Predicate> getWhereClause(CollegeDTO dto, CriteriaBuilder builder, Root<CollegeDTO> qRoot) {

		List<Predicate> whereCondition = new ArrayList<Predicate>();

		if (!isEmptyString(dto.getName())) {
			whereCondition.add(builder.like(qRoot.get("name"), dto.getName() + "%"));
		}

		if (!isEmptyString(dto.getCity())) {
			whereCondition.add(builder.like(qRoot.get("city"), dto.getCity() + "%"));
		}

		if (!isEmptyString(dto.getState())) {
			whereCondition.add(builder.like(qRoot.get("state"), dto.getState() + "%"));
		}

		if (!isEmptyString(dto.getAddress())) {
			whereCondition.add(builder.like(qRoot.get("address"), dto.getAddress() + "%"));
		}

		if (!isEmptyString(dto.getPhoneNo())) {
			whereCondition.add(builder.like(qRoot.get("phoneNo"), dto.getPhoneNo() + "%"));
		}

		return whereCondition;
	}
}