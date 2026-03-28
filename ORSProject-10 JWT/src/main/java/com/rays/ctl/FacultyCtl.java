package com.rays.ctl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rays.common.BaseCtl;
import com.rays.common.DropdownList;
import com.rays.common.ORSResponse;
import com.rays.dto.CollegeDTO;
import com.rays.dto.CourseDTO;
import com.rays.dto.FacultyDTO;
import com.rays.dto.SubjectDTO;
import com.rays.form.FacultyForm;
import com.rays.service.CollegeServiceInt;
import com.rays.service.CourseServiceInt;
import com.rays.service.FacultyServiceInt;
import com.rays.service.SubjectServiceInt;

/**
 * Faculty Controller class for handling Faculty-related APIs.
 * 
 * This controller extends BaseCtl to reuse common CRUD operations
 * such as save, get, delete, and search.
 * 
 * It also provides preload API to fetch dropdown data like
 * course, subject, and college list.
 * 
 * URL Mapping: /Faculty
 * 
 * @author Rishabh Shrivastava
 */
@RestController
@RequestMapping(value = "Faculty")
public class FacultyCtl extends BaseCtl<FacultyForm, FacultyDTO, FacultyServiceInt> {

	@Autowired
	private CourseServiceInt courseService;

	@Autowired
	private SubjectServiceInt subjectService;

	@Autowired
	private CollegeServiceInt collegeService;

	/**
	 * Loads dropdown data for Faculty form.
	 * 
	 * @return ORSResponse containing course, subject, and college lists
	 */
	@GetMapping("/preload")
	public ORSResponse preload() {
		ORSResponse res = new ORSResponse(true);

		List<DropdownList> courseList = courseService.search(new CourseDTO(), userContext);
		List<DropdownList> subjectList = subjectService.search(new SubjectDTO(), userContext);
		List<DropdownList> collegeList = collegeService.search(new CollegeDTO(), userContext);

		res.addResult("courseList", courseList);
		res.addResult("subjectList", subjectList);
		res.addResult("collegeList", collegeList);

		return res;
	}
}