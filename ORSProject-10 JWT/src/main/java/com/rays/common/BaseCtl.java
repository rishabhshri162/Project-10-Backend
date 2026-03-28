package com.rays.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.rays.dto.UserDTO;

/**
 * Base Controller class providing common CRUD operations.
 * 
 * This class is extended by all controllers to reuse common functionalities
 * like save, get, delete, and search operations.
 * 
 * It works with generic types:
 * F = Form class
 * T = DTO class
 * S = Service interface
 * 
 * It also handles validation, user context, and response formatting.
 * 
 * @author Rishabh Shrivastava
 */
public class BaseCtl<F extends BaseForm, T extends BaseDTO, S extends BaseServiceInt<T>> {

	@Autowired
	protected S baseService;

	@Value("${page.size}")
	private int pageSize = 0;

	protected UserContext userContext = null;

	/**
	 * Sets user context for every request.
	 * If no user is found, a default user is assigned.
	 */
	@ModelAttribute
	public void setUserContext() {
		userContext = UserContextHolder.getContext();
		if (userContext == null) {
			UserDTO dto = new UserDTO();
			dto.setLoginId("teamdotdev@gmail.com"); // fallback
			userContext = new UserContext(dto);
		}
	}

	/**
	 * Validates request data and collects field errors.
	 * 
	 * @param bindingResult validation result
	 * @return ORSResponse containing validation status and errors
	 */
	public ORSResponse validate(BindingResult bindingResult) {

		ORSResponse res = new ORSResponse(true);

		if (bindingResult.hasErrors()) {

			res.setSuccess(false);

			Map<String, String> errors = new HashMap<String, String>();

			List<FieldError> list = bindingResult.getFieldErrors();

			list.forEach(e -> {
				errors.put(e.getField(), e.getDefaultMessage());
			});
			res.addInputError(errors);
		}
		return res;
	}

	/**
	 * Saves or updates a record.
	 * 
	 * @param form form data
	 * @param bindingResult validation result
	 * @return ORSResponse with result status
	 */
	@PostMapping("/save")
	public ORSResponse save(@RequestBody @Valid F form, BindingResult bindingResult) {

		ORSResponse res = validate(bindingResult);

		if (res.isSuccess() == false) {
			return res;
		}

			T dto = (T) form.getDto();
			
			//update method
			if (dto.getId() != null && dto.getId() > 0) {

				T existDto1 = (T) baseService.findByUniqueKey(dto.getUniqueKey(), dto.getUniqueValue(), userContext);

				if (existDto1 != null && dto.getId() != existDto1.getId()) {
					res.setSuccess(false);
					res.addMessage(dto.getLabel() + " already exist");
					return res;
				}
				baseService.update(dto, userContext);
				res.addData(dto.getId());
				res.addMessage(dto.getTableName() + " updated successfully..!!");

				// ADD method
			} else {
				if (dto.getUniqueKey() != null && !dto.getUniqueKey().equals("")) {
					T existDto = (T) baseService.findByUniqueKey(dto.getUniqueKey(), dto.getUniqueValue(), userContext);
					if (existDto != null) {
						res.setSuccess(false);
						res.addMessage(dto.getLabel() + " already exist");
						return res;
					}
				}
				baseService.add(dto, userContext);
				res.setSuccess(true);
				res.addMessage(dto.getTableName() + " added successfully..!!");
			}
		return res;
	}
	/**
	 * Retrieves a record by ID.
	 * 
	 * @param id record ID
	 * @return ORSResponse with data or error message
	 */
	@GetMapping("get/{id}")
	public ORSResponse get(@PathVariable long id) {

		ORSResponse res = new ORSResponse(true);

		T dto = baseService.findById(id, userContext);

		if (dto != null) {
			res.addData(dto);

		} else {
			res.setSuccess(false);
			res.addMessage("Record not found");
		}
		return res;
	}

	/**
	 * Deletes multiple records by IDs.
	 * 
	 * @param ids array of record IDs
	 * @param pageNo current page number
	 * @param form form data
	 * @return ORSResponse with updated list
	 */
	@PostMapping("deleteMany/{ids}")
	public ORSResponse deleteMany(@PathVariable String[] ids, @RequestParam("pageNo") String pageNo,
			@RequestBody F form) {

		ORSResponse res = new ORSResponse(true);

		for (String id : ids) {
			baseService.delete(Long.parseLong(id), userContext);
		}

		T dto = (T) form.getDto();

		List<T> list = baseService.search(dto, Integer.parseInt(pageNo), pageSize, userContext);

		List<T> nextList = baseService.search(dto, Integer.parseInt(pageNo + 1), pageSize, userContext);

		if (list.size() == 0) {
			res.setSuccess(false);
			res.addMessage("Record not found..!!");
		} else {
			res.setSuccess(true);
			res.addMessage("Records Deleted Successfully");
			res.addData(list);
			res.addResult("nextListSize", nextList.size());
		}

		return res;
	}

	/**
	 * Searches records with pagination.
	 * 
	 * @param form search form
	 * @param pageNo page number
	 * @return ORSResponse with result list
	 */
	@RequestMapping(value = "/search/{pageNo}", method = { RequestMethod.GET, RequestMethod.POST })
	public ORSResponse search(@RequestBody F form, @PathVariable int pageNo) {

		pageNo = (pageNo < 0) ? 0 : pageNo;

		T dto = (T) form.getDto();

		ORSResponse res = new ORSResponse(true);

		List<T> list = baseService.search(dto, pageNo, pageSize, userContext);

		List<T> nextList = baseService.search(dto, pageNo + 1, pageSize, userContext);

		if (list.size() == 0) {
			res.setSuccess(false);
			res.addMessage("Record not found..!!");
		} else {
			res.setSuccess(true);
			res.addData(list);
			res.addResult("nextListSize", nextList.size());
		}
		return res;
	}
}