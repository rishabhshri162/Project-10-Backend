package com.rays.service;

import com.rays.common.BaseServiceInt;
import com.rays.common.UserContext;
import com.rays.dto.RoleDTO;

/**
 * Service interface for Role.
 * This interface extends BaseServiceInt to provide standard CRUD operations
 * for RoleDTO.
 * 
 * It defines additional method for role-related business logic.
 * 
 * @author Rishabh Shrivastava
 */
public interface RoleServiceInt extends BaseServiceInt<RoleDTO> {

	/**
	 * Finds role by name.
	 * 
	 * @param name         the name of the role
	 * @param userContext  user context information
	 * @return RoleDTO object if found
	 */
	public RoleDTO findByName(String name, UserContext userContext);

}