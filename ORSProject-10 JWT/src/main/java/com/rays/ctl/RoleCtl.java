package com.rays.ctl;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rays.common.BaseCtl;
import com.rays.common.DropdownList;
import com.rays.common.ORSResponse;
import com.rays.dto.RoleDTO;
import com.rays.form.RoleForm;
import com.rays.service.RoleServiceInt;

/**
 * Role Controller class for handling Role-related APIs.
 * 
 * This controller extends BaseCtl to reuse common CRUD operations
 * such as save, get, delete, and search.
 * 
 * It also provides additional APIs for:
 * - Loading role dropdown list
 * - Finding role by name
 * 
 * URL Mapping: /Role
 * 
 * @author Rishabh Shrivastava
 */
@RestController
@RequestMapping(value = "Role")
public class RoleCtl extends BaseCtl<RoleForm, RoleDTO, RoleServiceInt> {
	
	/**
	 * Loads role list for dropdown.
	 * 
	 * @return ORSResponse containing role list
	 */
	@GetMapping("preload")
	public ORSResponse preload() {
		ORSResponse res = new ORSResponse(true);
		RoleDTO dto = new RoleDTO();
		List<DropdownList> list = baseService.search(dto, userContext);
		res.addResult("roleList", list);
		return res;
	}
	
	/**
	 * Finds role by name.
	 * 
	 * @param name role name
	 * @return ORSResponse with role data
	 */
	@GetMapping("name/{name}")
	public ORSResponse get(@PathVariable String name) {
		ORSResponse res = new ORSResponse(true);
		RoleDTO dto = baseService.findByName(name, userContext);
		if (dto != null) {
			res.addData(dto);
		} else {
			res.setSuccess(false);
			res.addMessage("Record not found");
		}
		return res;
	}
}