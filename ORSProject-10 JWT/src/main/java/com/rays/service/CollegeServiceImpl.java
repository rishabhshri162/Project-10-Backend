package com.rays.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rays.common.BaseServiceImpl;
import com.rays.dao.CollegeDAOInt;
import com.rays.dto.CollegeDTO;
import com.rays.service.CollegeServiceInt;

/**
 * College Service implementation class.
 * 
 * This class provides business logic for CollegeDTO.
 * It extends BaseServiceImpl to reuse common CRUD operations.
 * 
 * It acts as a service layer between controller and DAO
 * for college-related operations.
 * 
 * @author Rishabh Shrivastava
 */
@Service
@Transactional
public class CollegeServiceImpl extends BaseServiceImpl<CollegeDTO, CollegeDAOInt> implements CollegeServiceInt {

}