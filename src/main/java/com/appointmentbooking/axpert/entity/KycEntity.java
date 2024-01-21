package com.appointmentbooking.axpert.entity;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "kyc")
@NoArgsConstructor
@Getter
@Setter
public class KycEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long kycuserid;
	private Integer amount;
	private String education;
	private Integer experince;
	private String timeslots;
	private String expertise;
	private Integer status;// 1- kyc unverified 2- kyc verfied
	@ManyToMany
	@JoinTable(name = "user_kyc", joinColumns = @JoinColumn(name = "kyc_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	private Set<UserEntity> users;
	private Integer mobilenumber;
	private String countrycode;
	
	
}
