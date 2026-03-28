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
import com.rays.dto.RoleDTO;
import com.rays.dto.UserDTO;

/**
 * User DAO implementation class.
 * 
 * This class provides database operations for UserDTO.
 * It extends BaseDAOImpl to reuse common CRUD functionalities.
 * 
 * It also handles:
 * - Populating role name and additional user details
 * - Managing user profile image deletion
 * - Applying dynamic search filters
 * 
 * @author Rishabh Shrivastava
 */
@Repository
public class UserDAOImpl extends BaseDAOImpl<UserDTO> implements UserDAOInt{

	@Autowired
	RoleDAOInt roleDao;
	
	@Autowired
	AttachmentDAOInt attachmentDao;

	/**
	 * Returns UserDTO class type.
	 */
	@Override
	public Class<UserDTO> getDTOClass() {
		return UserDTO.class;
	}

	/**
	 * Populates role name, last login, and image ID.
	 * 
	 * @param dto user DTO
	 * @param userContext user context
	 */
	@Override
	protected void populate(UserDTO dto, UserContext userContext) {
		if (dto.getRoleId() != null && dto.getRoleId() > 0) {
			RoleDTO roleDto = roleDao.findByPK(dto.getRoleId(), userContext);
			dto.setRoleName(roleDto.getName());
		}
		if (dto.getId() != null && dto.getId() > 0) {
			UserDTO userData = findByPK(dto.getId(), userContext);
			dto.setLastLogin(userData.getLastLogin());
		}
		if (dto.getId() != null && dto.getId() > 0) {
			UserDTO userData = findByPK(dto.getId(), null);
			dto.setImageId(userData.getImageId());
		}
	}
	
	/**
	 * Deletes user along with associated profile image.
	 * 
	 * @param dto user DTO
	 * @param userContext user context
	 */
	@Override
	public void delete(UserDTO dto, UserContext userContext) {
		if (dto.getImageId() != null && dto.getImageId() > 0) {
			attachmentDao.delete(attachmentDao.findByPK(dto.getImageId(), null), null);
		}
		super.delete(dto, userContext);
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
	protected List<Predicate> getWhereClause(UserDTO dto, CriteriaBuilder builder, Root<UserDTO> qRoot) {

		List<Predicate> whereCondition = new ArrayList<Predicate>();

		if (!isEmptyString(dto.getFirstName())) {
			whereCondition.add(builder.like(qRoot.get("firstName"), dto.getFirstName() + "%"));
		}

		if (!isEmptyString(dto.getLoginId())) {
			whereCondition.add(builder.like(qRoot.get("loginId"), dto.getLoginId() + "%"));
		}

		if (!isZeroNumber(dto.getRoleId())) {
			whereCondition.add(builder.equal(qRoot.get("roleId"), dto.getRoleId()));
		}

		if (isNotNull(dto.getDob())) {
			whereCondition.add(builder.equal(qRoot.get("dob"), dto.getDob()));
		}

		if (!isEmptyString(dto.getStatus())) {
			whereCondition.add(builder.equal(qRoot.get("status"), dto.getStatus()));
		}

		return whereCondition;
	}
}