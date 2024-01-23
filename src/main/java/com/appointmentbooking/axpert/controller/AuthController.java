package com.appointmentbooking.axpert.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.appointmentbooking.axpert.config.BaseAuthoriseRest;
import com.appointmentbooking.axpert.config.SendOtpEmail;
import com.appointmentbooking.axpert.payloads.UserDto;
import com.appointmentbooking.axpert.services.EmailService;
import com.appointmentbooking.axpert.services.UserService;
import com.appointmentbooking.axpert.utils.AppsJsonParser;
import com.appointmentbooking.axpert.utils.Constants;
import com.appointmentbooking.axpert.utils.ErrorMessage;
import com.appointmentbooking.axpert.utils.Message;
import com.appointmentbooking.axpert.utils.UserSessionManager;
import com.google.gson.JsonObject;

import jakarta.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	UserService userservice;

	@Autowired
	SendOtpEmail sendOtpEmail;

	@Autowired
	BaseAuthoriseRest baseAuthoriseRest;

	@Autowired
	EmailService emailService;

	@Autowired
	AppsJsonParser appsJsonParser;

	@Autowired
	private UserSessionManager userSessionManager;

	// http://localhost:8080/auth/register
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody UserDto user) {
		JSONObject jsonObject = new JSONObject();
		try {
			if (user != null && StringUtils.hasText(user.getEmail())) {
				UserDto registeredUser = userservice.registerUser(user);
//				final String token = jwtTokenHelper.generateToken(registeredUser);
				// Set the logged-in user in the UserSessionManager
				userSessionManager.setLoggedInUser(registeredUser);
				registeredUser.setToken("axpertuser");

				jsonObject.put(Constants.statuskey, true);
				jsonObject.put("user", registeredUser);
			} else {
				jsonObject.put(Constants.statuskey, false);
				jsonObject.put(Constants.errorkey, "invalid parameter");
			}

		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put(Constants.statuskey, false);
			jsonObject.put(Constants.errorkey, e.getMessage());

		}

		return baseAuthoriseRest.createJsonResponse(jsonObject.toString(), HttpStatus.OK);
	}

	// http://localhost:8080/auth/login
	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@RequestBody UserDto user) {
		JSONObject jsonObject = new JSONObject();
		try {
			if (user != null && StringUtils.hasText(user.getEmail())) {

				UserDto authenticatedUser = userservice.loginuser(user);
				// Set the logged-in user in the UserSessionManager
				userSessionManager.setLoggedInUser(authenticatedUser);
				authenticatedUser.setToken("axpertuser");
				jsonObject.put(Constants.statuskey, true);
				jsonObject.put("user", authenticatedUser);
			} else {

				jsonObject.put(Constants.statuskey, false);
				jsonObject.put(Constants.errorkey, "invalid parameter");
			}

		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put(Constants.statuskey, false);
			jsonObject.put(Constants.errorkey, e.getMessage());
		}
		return baseAuthoriseRest.createJsonResponse(jsonObject.toString(), HttpStatus.OK);
	}

	/*
	 * @author:aniket bansode
	 * 
	 * @Purpose: forget password
	 * 
	 * @return:user object
	 */
	// http://localhost:8080/auth/forgetpassword/vinayakbansode5@gmail.com/f
	@RequestMapping(value = "forgetpassword/{email}/{password}", method = RequestMethod.POST)
	public ResponseEntity<String> forgetpassword(@PathVariable String email, @PathVariable String password,
			HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject();
		try {
			if (StringUtils.hasText(email)) {
				Boolean forgot = userservice.forgetpassword(email, password);
				if (forgot != null && forgot) {
					jsonObject.put(Constants.statuskey, true);
					jsonObject.put(Constants.messagekey, Message.passwordresetsuccess);
				} else {
					jsonObject.put(Constants.statuskey, false);
					jsonObject.put(Constants.errorkey, ErrorMessage.forgotpassword);
				}
			} else {
				jsonObject.put(Constants.statuskey, false);
				jsonObject.put(Constants.errorkey, ErrorMessage.conformpassword);
			}
		} catch (Exception e) {
			System.out.print(e);
			e.printStackTrace();
			jsonObject.put(Constants.statuskey, false);
			jsonObject.put(Constants.errorkey, ErrorMessage.somethingwrong);
		}
		return baseAuthoriseRest.createJsonResponse(jsonObject.toString(), HttpStatus.OK);
	}

	// http://localhost:8080/auth/sendotp/bansodevinayak2000@gmail.com
	@RequestMapping(value = "sendotp/{email}", method = RequestMethod.POST)

	public ResponseEntity<String> postMethodName(@PathVariable String email, HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject();
		try {
			if (StringUtils.hasText(email)) {
				int otpLength = 6;
				StringBuilder otp = new StringBuilder(otpLength);
				Random random = new Random();
				String newotp;
				for (int i = 0; i < otpLength; i++) {
					otp.append(random.nextInt(10));
				}
				newotp = otp.toString();
				String htmlcontent = sendOtpEmail.getEmailContent(newotp);
				boolean send = emailService.handleForgotPasswordRequest(email, htmlcontent);
				if (send) {
					jsonObject.put(Constants.statuskey, true);
					jsonObject.put("otp", newotp);
					jsonObject.put(Constants.messagekey, Message.email);
				} else {
					throw new Exception(ErrorMessage.servererror);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put(Constants.statuskey, false);
			jsonObject.put(Constants.errorkey, e.getMessage());
		}
		return baseAuthoriseRest.createJsonResponse(jsonObject.toString(), HttpStatus.OK);
	}

	@PostMapping("/logout")
	public ResponseEntity<?> logoutUser() {
		JSONObject jsonObject = new JSONObject();
		try {
			// Perform any additional logout actions if needed

			// Clear the user session
			userSessionManager.logout();

			jsonObject.put(Constants.statuskey, true);
			jsonObject.put(Constants.messagekey, "Logout Succesfully");

		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put(Constants.statuskey, false);
			jsonObject.put(Constants.errorkey, e.getMessage());
		}
		return baseAuthoriseRest.createJsonResponse(jsonObject.toString(), HttpStatus.OK);
	}

}
