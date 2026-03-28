package com.rays.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rays.common.BaseServiceImpl;
import com.rays.common.UserContext;
import com.rays.dao.RoleDAOInt;
import com.rays.dto.RoleDTO;

/**
 * Service implementation class for Role.
 * This class extends BaseServiceImpl to use common CRUD operations
 * and implements RoleServiceInt for role-specific business logic.
 * 
 * It acts as a bridge between controller and DAO layer.
 * 
 * Annotated with @Service to define it as a service component
 * and @Transactional to manage transactions.
 * 
 * This class provides additional functionality to find role by name.
 * 
 * @author Rishabh Shrivastava
 */
@Service
@Transactional
public class RoleServiceImpl extends BaseServiceImpl<RoleDTO, RoleDAOInt> implements RoleServiceInt {

	/**
	 * Finds a role by its name.
	 * 
	 * @param name         the name of the role
	 * @param userContext  user context information
	 * @return RoleDTO object if found
	 */
	@Transactional(readOnly = true)
	public RoleDTO findByName(String name, UserContext userContext) {
		return baseDao.findByUniqueKey("name", name, userContext);
	}
}