package com.appointmentbooking.axpert;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.appointmentbooking.axpert.config.RequestInterceptor;

@SpringBootApplication
public class AppointmentbookingApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(AppointmentbookingApplication.class, args);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestInterceptor());
    }

    @Bean
    public RequestInterceptor requestInterceptor() {
        return new RequestInterceptor();
    }
}