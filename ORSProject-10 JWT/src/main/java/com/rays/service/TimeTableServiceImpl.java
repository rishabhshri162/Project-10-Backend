package com.rays.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.rays.common.BaseServiceImpl;
import com.rays.dao.TimeTableDAOInt;
import com.rays.dto.TimeTableDTO;

/**
 * Service implementation class for TimeTable.
 * This class extends BaseServiceImpl to use common CRUD operations
 * and implements TimeTableServiceInt for timetable-related business logic.
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
public class TimeTableServiceImpl extends BaseServiceImpl<TimeTableDTO, TimeTableDAOInt> implements TimeTableServiceInt{
	
	
	

}