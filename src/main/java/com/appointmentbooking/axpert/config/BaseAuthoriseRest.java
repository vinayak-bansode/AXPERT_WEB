package com.appointmentbooking.axpert.config;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component("BaseAuthoriseRest")
public class BaseAuthoriseRest {
	public ResponseEntity<String> createJsonResponse(String jsonString, HttpStatus httpStatus) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "application/json; charset=utf-8");
		headers.add("Access-Control-Allow-Origin", "http://localhost:3000");
		headers.add("Access-Control-Allow-Credentials", "true");
		return new ResponseEntity<String>(jsonString, headers, httpStatus);
	}

}
