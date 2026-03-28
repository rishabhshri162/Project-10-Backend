package com.rays.ctl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rays.common.BaseCtl;
import com.rays.common.ORSResponse;
import com.rays.dto.CourseDTO;
import com.rays.dto.SubjectDTO;
import com.rays.dto.TimeTableDTO;
import com.rays.form.TimeTableForm;
import com.rays.service.CourseServiceInt;
import com.rays.service.SubjectServiceInt;
import com.rays.service.TimeTableServiceInt;

/**
 * TimeTable Controller class for handling TimeTable-related APIs.
 * 
 * This controller extends BaseCtl to reuse common CRUD operations
 * such as save, get, delete, and search.
 * 
 * It also provides preload API to fetch course and subject list
 * for dropdowns.
 * 
 * URL Mapping: /TimeTable
 * 
 * @author Rishabh Shrivastava
 */
@RestController
@RequestMapping(value = "TimeTable")
public class TimeTableCtl extends BaseCtl<TimeTableForm, TimeTableDTO, TimeTableServiceInt> {

	@Autowired
	SubjectServiceInt subjectService;

	@Autowired
	CourseServiceInt courseService;

	/**
	 * Loads course and subject list for dropdown.
	 * 
	 * @return ORSResponse containing courseList and subjectList
	 */
	@GetMapping("preload")
	public ORSResponse preload() {
		ORSResponse res = new ORSResponse(true);
		List<CourseDTO> list = courseService.search(new CourseDTO(), userContext);
		List<SubjectDTO> list1 = subjectService.search(new SubjectDTO(), userContext);
		res.addResult("courseList", list);
		res.addResult("subjectList", list1);
		return res;
	}
}