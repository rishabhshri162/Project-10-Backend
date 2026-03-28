package com.rays.ctl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rays.common.BaseCtl;
import com.rays.dto.CourseDTO;
import com.rays.form.CourseForm;
import com.rays.service.CourseServiceInt;

/**
 * Course Controller class for handling Course-related APIs.
 * 
 * This controller extends BaseCtl to reuse common CRUD operations
 * such as save, get, delete, and search.
 * 
 * It handles requests related to Course module.
 * 
 * URL Mapping: /Course
 * 
 * @author Rishabh Shrivastava
 */
@RestController
@RequestMapping(value = "Course")
public class CourseCtl extends BaseCtl<CourseForm, CourseDTO, CourseServiceInt>{

}