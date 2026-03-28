package com.rays.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rays.common.BaseServiceImpl;
import com.rays.dao.StudentDAOInt;
import com.rays.dto.StudentDTO;
import com.rays.dto.UserDTO;

/**
 * Service implementation class for Student.
 * This class extends BaseServiceImpl to use common CRUD operations
 * and implements StudentServiceInt for student-related business logic.
 * 
 * It acts as a bridge between controller and DAO layer.
 * 
 * Annotated with @Service to define it as a service component
 * and @Transactional to manage transactions.
 * 
 * @author Rishabh Shrivastava
 */
@Service
@Transactional
public class StudentServiceImpl extends BaseServiceImpl<StudentDTO, StudentDAOInt> implements StudentServiceInt {

}