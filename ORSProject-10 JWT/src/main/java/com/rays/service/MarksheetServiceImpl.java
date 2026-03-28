package com.rays.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rays.common.BaseServiceImpl;
import com.rays.common.UserContext;
import com.rays.dao.MarksheetDAOInt;
import com.rays.dto.MarksheetDTO;

/**
 * Service implementation class for Marksheet. This class extends
 * BaseServiceImpl to use common CRUD operations and implements
 * MarksheetServiceInt for marksheet-specific logic.
 * 
 * It acts as a bridge between controller and DAO layer.
 * 
 * Annotated with @Service to define it as a service component
 * and @Transactional to manage transactions.
 * 
 * This class provides additional methods like finding marksheet by name, roll
 * number, and fetching merit list.
 * 
 * @author Rishabh Shrivastava
 */
@Service
@Transactional
public class MarksheetServiceImpl extends BaseServiceImpl<MarksheetDTO, MarksheetDAOInt>
		implements MarksheetServiceInt {

	/**
	 * Finds a marksheet by student name.
	 * 
	 * @param name    the name of the student
	 * @param context user context information
	 * @return MarksheetDTO object if found
	 */
	@Transactional(readOnly = true)
	public MarksheetDTO findByName(String name, UserContext context) {
		return baseDao.findByUniqueKey("name", name, context);
	}

	/**
	 * Finds a marksheet by roll number.
	 * 
	 * @param rollNo  the roll number of the student
	 * @param context user context information
	 * @return MarksheetDTO object if found
	 */
	@Override
	public MarksheetDTO findByRollNo(String rollNo, UserContext context) {
		return baseDao.findByUniqueKey("rollNo", rollNo, context);
	}

	/**
	 * Returns the merit list of students.
	 * 
	 * @param context user context information
	 * @return list of MarksheetDTO sorted by marks
	 */
	@Override
	public List<MarksheetDTO> getMeritList(UserContext context) {
		return baseDao.getMeritList();
	}

}