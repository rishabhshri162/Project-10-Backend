package com.rays.ctl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rays.common.BaseCtl;
import com.rays.common.DropdownList;
import com.rays.common.ORSResponse;
import com.rays.dto.CourseDTO;
import com.rays.dto.SubjectDTO;
import com.rays.form.SubjectForm;
import com.rays.service.CourseServiceInt;
import com.rays.service.SubjectServiceInt;

/**
 * Subject Controller class for handling Subject-related APIs.
 * 
 * This controller extends BaseCtl to reuse common CRUD operations
 * such as save, get, delete, and search.
 * 
 * It also provides preload API to fetch course list for dropdown.
 * 
 * URL Mapping: /Subject
 * 
 * @author Rishabh Shrivastava
 */
@RestController
@RequestMapping(value = "Subject")
public class SubjectCtl extends BaseCtl<SubjectForm, SubjectDTO, SubjectServiceInt> {

	@Autowired
	private CourseServiceInt courseService;
	
	/**
	 * Loads course list for dropdown.
	 * 
	 * @return ORSResponse containing course list
	 */
	@GetMapping("/preload")
	public ORSResponse preload() {
		ORSResponse res = new ORSResponse(true);
		List<DropdownList> list = courseService.search(new CourseDTO(), userContext);
		res.addResult("courseList", list);
		return res;
	}
}