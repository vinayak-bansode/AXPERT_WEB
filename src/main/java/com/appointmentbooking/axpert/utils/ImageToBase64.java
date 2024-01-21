package com.appointmentbooking.axpert.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;

import org.springframework.stereotype.Component;

@Component("imagetobase64")
public class ImageToBase64 {

    /**
     * @param imagepath
     * @return
     */
    public String imageinbase64convert(String imagepath) {
        String jsonPayload = "";
        try {
            // Read the image file
            File file = new File(imagepath);
            byte[] fileContent = Files.readAllBytes(file.toPath());

            // Convert image byte array to Base64 string
            String base64Image = Base64.getEncoder().encodeToString(fileContent);

            // Create JSON payload with the Base64 image string
            jsonPayload = "{ \"image\": \"" + base64Image + "\" }";

            // Print the JSON payload (you would send this as part of your email content)
            System.out.println("JSON Payload: " + jsonPayload);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonPayload;
    }
}
