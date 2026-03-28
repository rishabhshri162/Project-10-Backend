package com.rays.service;

import com.rays.common.BaseServiceInt;
import com.rays.dto.CollegeDTO;

/**
 * College Service interface.
 * 
 * This interface extends BaseServiceInt to provide standard
 * business operations for CollegeDTO.
 * 
 * It acts as a bridge between controller and DAO layer.
 * 
 * Additional college-specific methods can be defined here
 * if required.
 * 
 * @author Rishabh Shrivastava
 */
public interface CollegeServiceInt extends BaseServiceInt<CollegeDTO> {

}