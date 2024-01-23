package com.appointmentbooking.axpert.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.appointmentbooking.axpert.utils.UserSessionManager;

@Controller

public class Axpert {
	@Autowired
	private UserSessionManager userSessionManager;

	@GetMapping("")
	public String showWelcomePage() {
		
		if (userSessionManager.isLoggedIn()) {
            // User is logged in, redirect to welcome page
            return "welcome.html";
        } else {
            return "index";
        }
	}

	@GetMapping("/Login.html")
	public String Loginpage() {
		if (userSessionManager.isLoggedIn()) {
            // User is logged in, redirect to welcome page
            return "welcome.html";
        } else {
            return "Login";
        }
	}

	@GetMapping("/registerpage.html")
	public String registerpage() {
		if (userSessionManager.isLoggedIn()) {
            // User is logged in, redirect to welcome page
            return "welcome.html";
        } else {
            return "registerpage";
        }
	}

	@GetMapping("/welcome.html")
	public String welcompage() {
		return "welcome.html";
	}
}
