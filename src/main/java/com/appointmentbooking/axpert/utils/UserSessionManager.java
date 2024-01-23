package com.appointmentbooking.axpert.utils;

import org.springframework.stereotype.Component;

import com.appointmentbooking.axpert.payloads.UserDto;

@Component
public class UserSessionManager {
	private static UserSessionManager instance;

    private UserDto loggedInUser;

    private UserSessionManager() {
        // Private constructor to prevent instantiation
    }

    public static synchronized UserSessionManager getInstance() {
        if (instance == null) {
            instance = new UserSessionManager();
        }
        return instance;
    }

    public UserDto getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(UserDto user) {
        this.loggedInUser = user;
    }

    public boolean isLoggedIn() {
        return loggedInUser != null;
    }
    
    public void logout() {
        // Clear the logged-in user information on logout
        loggedInUser = null;
    }
}