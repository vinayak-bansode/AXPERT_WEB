package com.appointmentbooking.axpert.firebase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.stereotype.Component;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

@Component
public class FirebaseInitialize {

	public void Iniliazefirebase() throws IOException {
		FileInputStream serviceAccount;
		try {
			serviceAccount = new FileInputStream("./firebasekey.json");

			FirebaseOptions options = FirebaseOptions.builder()
					.setCredentials(GoogleCredentials.fromStream(serviceAccount))
					.setStorageBucket("axpertappointmentbooking.appspot.com") // Your storage bucket URL
					.build();

			FirebaseApp.initializeApp(options);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new IOException(e.getMessage());
		}
	}
}
