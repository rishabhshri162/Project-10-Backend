package com.rays.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.rays.common.BaseServiceImpl;
import com.rays.dao.FacultyDAOInt;
import com.rays.dto.FacultyDTO;

/**
 * Service implementation class for Faculty.
 * This class extends BaseServiceImpl to use common CRUD operations
 * and implements FacultyServiceInt for faculty-related business logic.
 * 
 * It acts as a bridge between controller and DAO layer.
 * 
 * Annotated with @Service to define it as a service component
 * and @Transactional to handle transaction management.
 * 
 * @author Rishabh Shrivastava
 */
@Service
@Transactional
public class FacultyServiceImpl extends BaseServiceImpl<FacultyDTO, FacultyDAOInt> implements FacultyServiceInt {

}