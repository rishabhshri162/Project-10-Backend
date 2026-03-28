package com.rays.ctl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rays.common.BaseCtl;
import com.rays.common.ORSResponse;
import com.rays.dto.MarksheetDTO;
import com.rays.dto.StudentDTO;
import com.rays.form.MarksheetForm;
import com.rays.service.MarksheetServiceInt;
import com.rays.service.StudentServiceInt;

/**
 * Marksheet Controller class for handling Marksheet-related APIs.
 * 
 * This controller extends BaseCtl to reuse common CRUD operations
 * such as save, get, delete, and search.
 * 
 * It also provides additional APIs for:
 * - Preloading student list
 * - Searching marksheet by roll number
 * - Fetching merit list
 * 
 * URL Mapping: /Marksheet
 * 
 * @author Rishabh Shrivastava
 */
@RestController
@RequestMapping(value = "Marksheet")
public class MarksheetCtl extends BaseCtl<MarksheetForm, MarksheetDTO, MarksheetServiceInt> {
	
	@Autowired
	private StudentServiceInt studentService;
	
	@Autowired
	private MarksheetServiceInt marksheetService;

	/**
	 * Loads student list for dropdown.
	 * 
	 * @return ORSResponse containing student list
	 */
	@GetMapping("preload")
	public ORSResponse preload() {
		ORSResponse res = new ORSResponse(true);
		List<StudentDTO> list = studentService.search(new StudentDTO(), userContext);
		res.addResult("studentList", list);
		return res;
	}
	
	/**
	 * Finds marksheet by roll number.
	 * 
	 * @param rollNo roll number
	 * @return ORSResponse with marksheet data
	 */
	@GetMapping("rollno/{rollNo}")
	public ORSResponse rollNo(@PathVariable String rollNo) {
		ORSResponse res = new ORSResponse(true);
		MarksheetDTO dto = baseService.findByRollNo(rollNo, userContext);
		if (dto != null) {
			res.addData(dto);
		} else {
			res.setSuccess(false);
			res.addMessage("Record not found");
		}
		return res;
	}

	/**
	 * Retrieves merit list of marksheets.
	 * 
	 * @return ORSResponse containing merit list
	 */
	@GetMapping("meritlist")
	public ORSResponse getMeritList() {
		System.out.println("getMeritList run on ctl");
		List<MarksheetDTO> list = baseService.getMeritList(userContext);
		ORSResponse res = new ORSResponse(true);
		res.addResult("list",list);
		return res;
	}
}