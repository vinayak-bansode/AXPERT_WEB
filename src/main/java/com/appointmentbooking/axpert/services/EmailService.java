package com.appointmentbooking.axpert.services;

public interface EmailService {

    boolean handleForgotPasswordRequest(String useremail, String htmlString);

}
