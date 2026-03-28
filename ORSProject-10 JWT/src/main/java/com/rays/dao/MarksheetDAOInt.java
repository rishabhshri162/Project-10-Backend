package com.rays.dao;

import java.util.List;

import com.rays.common.BaseDAOInt;
import com.rays.dto.MarksheetDTO;

/**
 * Marksheet DAO interface.
 * 
 * This interface extends BaseDAOInt to provide standard
 * CRUD operations for MarksheetDTO.
 * 
 * It also defines custom method to fetch merit list.
 * 
 * @author Rishabh Shrivastava
 */
public interface MarksheetDAOInt extends BaseDAOInt<MarksheetDTO> {
	
	/**
	 * Retrieves merit list of marksheets.
	 * 
	 * @return list of top marksheets
	 */
	public List<MarksheetDTO> getMeritList();

}