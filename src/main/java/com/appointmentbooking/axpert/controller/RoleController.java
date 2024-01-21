package com.appointmentbooking.axpert.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.appointmentbooking.axpert.config.BaseAuthoriseRest;
import com.appointmentbooking.axpert.services.RoleService;
import com.appointmentbooking.axpert.utils.Constants;
import com.appointmentbooking.axpert.utils.Message;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/axpertadmin/")
public class RoleController {

	@Autowired
	private RoleService roleService;

	@Autowired
	BaseAuthoriseRest baseAuthoriseRest;

	//http://localhost:8080/axpertadmin/assign-admin/1
	@PostMapping("assign-admin/{userId}")
	public ResponseEntity<?> assignAdminRole(@PathVariable Long userId) throws Exception {
		JSONObject jsonObject = new JSONObject();
		try {

			this.roleService.assignAdminRole(userId);
			jsonObject.put(Constants.statuskey, true);
			jsonObject.put(Constants.messagekey, "Role assigned admin");
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put(Constants.statuskey, false);
			jsonObject.put(Constants.errorkey, e.getMessage());
		}

		return baseAuthoriseRest.createJsonResponse(jsonObject.toString(), HttpStatus.OK);
	}

}
