package com.rays.ctl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rays.common.BaseCtl;
import com.rays.dto.CollegeDTO;
import com.rays.form.CollegeForm;
import com.rays.service.CollegeServiceInt;

/**
 * College Controller class for handling College-related APIs.
 * 
 * This controller extends BaseCtl to reuse common CRUD operations
 * such as save, get, delete, and search.
 * 
 * It maps requests related to College module.
 * 
 * URL Mapping: /College
 * 
 * @author Rishabh Shrivastava
 */
@RestController
@RequestMapping(value = "College")
public class CollegeCtl extends BaseCtl<CollegeForm, CollegeDTO, CollegeServiceInt>{

}