package com.rays.ctl;

import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rays.common.BaseCtl;
import com.rays.common.DropdownList;
import com.rays.common.ORSResponse;
import com.rays.common.UserContext;
import com.rays.dto.AttachmentDTO;
import com.rays.dto.RoleDTO;
import com.rays.dto.UserDTO;
import com.rays.form.ChangePasswordForm;
import com.rays.form.ForgetPasswordForm;
import com.rays.form.MyProfileForm;
import com.rays.form.UserForm;
import com.rays.service.AttachmentServiceInt;
import com.rays.service.RoleServiceInt;
import com.rays.service.UserServiceInt;

/**
 * User Controller class for handling User-related APIs.
 * 
 * This controller extends BaseCtl to reuse common CRUD operations
 * such as save, get, delete, and search.
 * 
 * It also provides APIs for:
 * - Loading role dropdown
 * - Updating user profile
 * - Changing password
 * - Uploading and downloading profile picture
 * 
 * URL Mapping: /User
 * 
 * @author Rishabh Shrivastava
 */
@RestController
@RequestMapping(value = "User")
public class UserCtl extends BaseCtl<UserForm, UserDTO, UserServiceInt> {

	@Autowired
	RoleServiceInt roleService = null;

	@Autowired
	AttachmentServiceInt attachmentService;

	/**
	 * Loads role list for dropdown.
	 * 
	 * @return ORSResponse containing role list
	 */
	@GetMapping("preload")
	public ORSResponse preload() {
		ORSResponse res = new ORSResponse(true);
		RoleDTO dto = new RoleDTO();
		List<DropdownList> list = roleService.search(dto, userContext);
		res.addResult("roleList", list);
		return res;
	}

	/**
	 * Updates user profile.
	 * 
	 * @param form profile form
	 * @param bindingResult validation result
	 * @return ORSResponse with status message
	 */
	@PostMapping("myProfile")
	public ORSResponse myProfile(@RequestBody @Valid MyProfileForm form, BindingResult bindingResult) {
	    ORSResponse res = validate(bindingResult);
	    if (!res.isSuccess()) {
	        return res;
	    }

	    System.out.println("UserId from context: " + userContext.getUserId());
	    
	    UserDTO dto = baseService.findById(userContext.getUserId(), userContext);
	    
	    System.out.println("DTO found: " + dto);
	    if (dto == null) {
	        res.setSuccess(false);
	        res.addMessage("User not found! Please login again.");
	        return res;
	    }

	    dto.setFirstName(form.getFirstName());
	    dto.setLastName(form.getLastName());
	    dto.setDob(form.getDob());
	    dto.setPhone(form.getPhone());
	    dto.setGender(form.getGender());
	    baseService.update(dto, userContext);
	    res.setSuccess(true);
	    res.addMessage("Your Profile updated successfully..!!");
	    return res;
	}

	/**
	 * Changes user password.
	 * 
	 * @param form change password form
	 * @param bindingResult validation result
	 * @return ORSResponse with status message
	 */
	@PostMapping("changePassword")
	public ORSResponse changePassword(@RequestBody @Valid ChangePasswordForm form, BindingResult bindingResult) {

		ORSResponse res = validate(bindingResult);

		if (!res.isSuccess()) {
			return res;
		}

		UserDTO changedDto = baseService.changePassword(form.getLoginId(), form.getOldPassword(), form.getNewPassword(),
				userContext);

		if (changedDto == null) {
			res.setSuccess(false);
			res.addMessage("Invalid old password");
			return res;
		}

		res.setSuccess(true);
		res.addMessage("Password has been changed");

		return res;
	}

	/**
	 * Uploads profile picture.
	 * 
	 * @param userId user ID
	 * @param file image file
	 * @param req HTTP request
	 * @return ORSResponse with image ID
	 */
	@PostMapping("/profilePic/{userId}")
	public ORSResponse uploadPic(@PathVariable Long userId, @RequestParam("file") MultipartFile file,
			HttpServletRequest req) {

		AttachmentDTO attachmentDto = new AttachmentDTO(file);
		
		attachmentDto.setDescription("profile pic");

		attachmentDto.setUserId(userId);

		UserDTO userDto = baseService.findById(userId, userContext);

		if (userDto.getImageId() != null && userDto.getImageId() > 0) {
			attachmentDto.setId(userDto.getImageId());
		}

		Long imageId = attachmentService.save(attachmentDto, userContext);

		if (userDto.getImageId() == null) {
			userDto.setImageId(imageId);
			baseService.update(userDto, userContext);
		}

		ORSResponse res = new ORSResponse();
		res.addResult("imageId", imageId);

		return res;
	}

	/**
	 * Downloads profile picture.
	 * 
	 * @param userId user ID
	 * @param response HTTP response
	 */
	@GetMapping("/profilePic/{userId}")
	public void downloadPic(@PathVariable Long userId, HttpServletResponse response) {

		try {

			UserDTO userDto = baseService.findById(userId, null);

			AttachmentDTO attachmentDTO = null;

			if (userDto != null) {
				attachmentDTO = attachmentService.findById(userDto.getImageId(), null);
			}

			if (attachmentDTO != null) {
				response.setContentType(attachmentDTO.getType());
				OutputStream out = response.getOutputStream();
				out.write(attachmentDTO.getDoc());
				out.close();
			} else {
				response.getWriter().write("ERROR: File not found");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}