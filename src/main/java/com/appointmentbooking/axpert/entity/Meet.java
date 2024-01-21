package com.appointmentbooking.axpert.entity;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "meet")
@NoArgsConstructor
@Getter
@Setter
public class Meet {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int meetid;
	
	int appointerid;
	
	int userid;
	
	String meetlink;
	
	String meetdescription;
	
	Long date;
	
	
 
}
