package com.appointmentbooking.axpert.firebase;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class FirebaseStorageServiceImpl implements Firebaseservice {

	public String uploadFile(File file, String fileName) throws IOException {
		String folderPath = "profile-photo/";
		BlobId blobId = BlobId.of("axpertappointmentbooking.appspot.com", folderPath + fileName);
		BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("media").build();
		Credentials credentials = GoogleCredentials.fromStream(
				new FileInputStream("C://Users//vinayak//Documents//GitHub//newworkspace//axpert//firebasekey.json"));
		Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
		storage.create(blobInfo, Files.readAllBytes(file.toPath()));
		String imageUrl = "https://storage.googleapis.com/axpertappointmentbooking.appspot.com/"
				+ URLEncoder.encode(fileName, StandardCharsets.UTF_8);

		return imageUrl;
	}

	private String getExtension(String fileName) {
		return fileName.substring(fileName.lastIndexOf("."));
	}

	public InputStream downloadFile(String fileName) throws IOException {
		BlobId blobId = BlobId.of("axpertappointmentbooking.appspot.com", fileName + ".png");
		Storage storage = StorageOptions.getDefaultInstance().getService();
		Blob blob = storage.get(blobId);

		// Return the InputStream of the blob
		return new ByteArrayInputStream(blob.getContent());
	}

	private byte[] getCredentialsBytes() throws IOException {
		return Files.readAllBytes(
				Paths.get("C://Users//vinayak//Documents//GitHub//newworkspace//axpert//firebasekey.json"));
	}

}