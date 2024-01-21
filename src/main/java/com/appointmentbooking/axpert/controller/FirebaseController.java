package com.appointmentbooking.axpert.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.appointmentbooking.axpert.firebase.Firebaseservice;



@Controller
@RequestMapping("/firebaseservice/")
public class FirebaseController {
	@Autowired
	private Firebaseservice firebaseservice;

	 @PostMapping("uploadProfilePhoto")
	    public String uploadProfilePhoto(@RequestParam("userId") String userId, @RequestParam("file") MultipartFile file) throws Exception {
	        try {
	            // Convert MultipartFile to File
	            File convertedFile = convertMultiPartToFile(file);

	            // Now you can use convertedFile in your uploadFile method
	            String photoUrl = firebaseservice.uploadFile(convertedFile,userId);

	            // Optionally, you can delete the temporary file after uploading
	            convertedFile.delete();

	            return "Profile photo uploaded successfully for user " + userId + "! URL: " + photoUrl;
	        } catch (IOException e) {
	            return "Error uploading profile photo: " + e.getMessage();
	        }
	    }

	    private File convertMultiPartToFile(MultipartFile file) throws IOException {
	        File convertedFile = new File(file.getOriginalFilename());
	        Files.copy(file.getInputStream(), convertedFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
	        return convertedFile;
	    }
}
