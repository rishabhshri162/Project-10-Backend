package com.rays.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rays.common.BaseServiceImpl;
import com.rays.dao.CourseDAOInt;
import com.rays.dto.CourseDTO;

/**
 * Service implementation class for Course.
 * This class extends BaseServiceImpl to inherit common CRUD operations
 * and implements CourseServiceInt for course-specific business logic.
 * 
 * It is annotated with @Service to indicate it is a service layer component
 * and @Transactional to manage database transactions automatically.
 * 
 * This class works as a bridge between controller and DAO layer
 * for handling Course related operations.
 * 
 * @author Rishabh Shrivastava
 */

@Service
@Transactional
public class CourseServiceImpl extends BaseServiceImpl<CourseDTO, CourseDAOInt> implements CourseServiceInt{

}
