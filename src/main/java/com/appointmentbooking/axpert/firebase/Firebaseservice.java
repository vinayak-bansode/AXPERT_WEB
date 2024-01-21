package com.appointmentbooking.axpert.firebase;

import java.io.File;
import java.io.InputStream;

public interface Firebaseservice {
	String uploadFile(File file, String fileName) throws Exception;
	InputStream downloadFile(String fileName) throws Exception;
}
