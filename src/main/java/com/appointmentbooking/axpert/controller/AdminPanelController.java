package com.appointmentbooking.axpert.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class AdminPanelController {

	@RequestMapping(value = "/getalluserslist", method = RequestMethod.GET)
	public void requestMethodName() {

	}

}
