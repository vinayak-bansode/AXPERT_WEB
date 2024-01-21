package com.appointmentbooking.axpert.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class Axpert {

    @GetMapping("")
    public String showWelcomePage() {
        return "index";
    }

    @GetMapping("/Login.html")
    public String Loginpage() {
        return "Login";
    }

    @GetMapping("/registerpage.html")
    public String registerpage() {
        return "registerpage";
    }

    @GetMapping("/welcome.html")
    public String welcompage() {
        return "welcome.html";
    }
}
