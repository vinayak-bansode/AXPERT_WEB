package com.appointmentbooking.axpert.services;

import java.util.List;

import com.appointmentbooking.axpert.payloads.UserDto;

public interface UserService {

	UserDto registerUser(UserDto user) throws Exception;

	UserDto updateUser(UserDto user, String useremail) throws Exception;

	UserDto getUserbyid(Long userid);

	List<UserDto> getAllUsers();
	
	void deleteUser(Long userid) throws Exception;

	UserDto loginuser(UserDto user) throws Exception;

	Boolean forgetpassword(String email, String password) throws Exception;

	
	
	

}
