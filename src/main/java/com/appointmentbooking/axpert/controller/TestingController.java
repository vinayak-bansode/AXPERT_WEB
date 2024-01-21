package com.appointmentbooking.axpert.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.appointmentbooking.axpert.config.BaseAuthoriseRest;
import com.appointmentbooking.axpert.utils.Constants;
import com.google.gson.JsonObject;

@Controller
@RequestMapping("/testing/")
public class TestingController {
	
	@Autowired
	BaseAuthoriseRest baseAuthoriseRest;
	
	
	//http://localhost:8080/testing/hello
	@PostMapping("hello")
	public ResponseEntity<String> hello() {
		JsonObject jsonObject = new JsonObject();
		try {
			jsonObject.addProperty(Constants.statuskey, false);
			jsonObject.addProperty(Constants.messagekey, "Security working fine");
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.addProperty(Constants.statuskey, false);
			jsonObject.addProperty(Constants.messagekey, e.getMessage());
		}
		return baseAuthoriseRest.createJsonResponse(jsonObject.toString(), HttpStatus.OK);

	}

}
