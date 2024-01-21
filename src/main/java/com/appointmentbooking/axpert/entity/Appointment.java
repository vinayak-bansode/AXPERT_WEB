package com.appointmentbooking.axpert.entity;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "appointment")
@NoArgsConstructor
@Getter
@Setter
public class Appointment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Example: Auto-generated ID
	private int id;
	private int userid;
	@Column(nullable = true)
	private int appointerid;
	private String title;
	private String description;
	@Column(nullable = true)
	private Long date; // or LocalDateTime or LocalDate depending on your requirement
	private String status; // accepted or rejected
	@Column(nullable = true)
	private int appointerRating; 
	private String attachmentFilePath; // Or any appropriate data type to store attachment information


}
