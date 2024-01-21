package com.appointmentbooking.axpert.utils;

import org.springframework.stereotype.Component;

@Component("Errormessage")
public class ErrorMessage {
    public static String Exist = "User email Already Registered";
    public static String somethingwrong = "Something went wrong";

    public static String validparameter = "Please enter valid parameter";
    public static String wrongpassaword = "Please enter correct password";

    public static String forgotpassword = "Password not changed";
    public static String conformpassword = "Please enter valid parameter";
    public static String servererror = "Internal server Error";

}
