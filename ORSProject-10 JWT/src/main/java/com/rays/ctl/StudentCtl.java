package com.rays.ctl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rays.common.BaseCtl;
import com.rays.common.ORSResponse;
import com.rays.dto.CollegeDTO;
import com.rays.dto.StudentDTO;
import com.rays.form.StudentForm;
import com.rays.service.CollegeServiceInt;
import com.rays.service.StudentServiceInt;

/**
 * Student Controller class for handling Student-related APIs.
 * 
 * This controller extends BaseCtl to reuse common CRUD operations
 * such as save, get, delete, and search.
 * 
 * It also provides preload API to fetch college list for dropdown.
 * 
 * URL Mapping: /Student
 * 
 * @author Rishabh Shrivastava
 */
@RestController
@RequestMapping(value = "Student")
public class StudentCtl extends BaseCtl<StudentForm, StudentDTO, StudentServiceInt> {
	
	@Autowired
	private CollegeServiceInt collegeService;

	/**
	 * Loads college list for dropdown.
	 * 
	 * @return ORSResponse containing college list
	 */
	@GetMapping("preload")
	public ORSResponse preload() {
		ORSResponse res = new ORSResponse(true);
		List<CollegeDTO> list = collegeService.search(new CollegeDTO(), userContext);
		res.addResult("collegeList", list);
		return res;
	}
}