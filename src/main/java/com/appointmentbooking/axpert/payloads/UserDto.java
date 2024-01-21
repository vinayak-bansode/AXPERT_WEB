package com.appointmentbooking.axpert.payloads;

import java.util.Set;

import org.springframework.util.StringUtils;

import com.appointmentbooking.axpert.entity.KycEntity;
import com.appointmentbooking.axpert.entity.UserEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

	private Long id;
	private String name;
	private String password;
	private String email;
	private String authtype;
	private String token;
	private Long status;// 1- kyc unverified 2- kyc verfied
	private Set<KycEntity> kycEntities;
	private String expert;
	private Long experinceyear;
	private Long rating;
	private String role;

	public static UserEntity convertreqtoUserEntity(UserDto registerreqObj) {

		UserEntity user = new UserEntity();

		if (registerreqObj.getId() != null) {

			user.setId(registerreqObj.getId());
		}
		if (StringUtils.hasText(registerreqObj.getName())) {

			user.setName(registerreqObj.getName());
		}
		if (StringUtils.hasText(registerreqObj.getEmail())) {

			user.setEmail(registerreqObj.getEmail());
		}
		if (StringUtils.hasText(registerreqObj.getPassword())) {

			user.setPassword(registerreqObj.getPassword());
		}
		if (StringUtils.hasText(registerreqObj.getAuthtype())) {

			user.setAuthtype(registerreqObj.getAuthtype());
		}
		if (StringUtils.hasText(registerreqObj.getToken())) {

			user.setToken(registerreqObj.getToken());
		}
		if (StringUtils.hasText(registerreqObj.getExpert())) {
			user.setExpert(registerreqObj.getExpert());
		}
		if (registerreqObj.getRating() != null) {
			user.setRating(registerreqObj.getRating());
		}
		if (registerreqObj.getStatus() != null) {
			user.setStatus(registerreqObj.getStatus());
		}
	
		return user;
	}

	public static UserDto convertUserDto(UserEntity registerreqObj) {

		UserDto user = new UserDto();

		if (registerreqObj.getId() != null) {

			user.setId(registerreqObj.getId());
		}
		if (StringUtils.hasText(registerreqObj.getName())) {

			user.setName(registerreqObj.getName());
		}
		if (StringUtils.hasText(registerreqObj.getEmail())) {

			user.setEmail(registerreqObj.getEmail());
		}
		if (StringUtils.hasText(registerreqObj.getPassword())) {

			user.setPassword(registerreqObj.getPassword());
		}
		if (StringUtils.hasText(registerreqObj.getAuthtype())) {

			user.setAuthtype(registerreqObj.getAuthtype());
		}
		if (StringUtils.hasText(registerreqObj.getToken())) {

			user.setToken(registerreqObj.getToken());
		}
		if (StringUtils.hasText(registerreqObj.getExpert())) {
			user.setExpert(registerreqObj.getExpert());
		}
		if (registerreqObj.getRating() != null) {
			user.setRating(registerreqObj.getRating());
		}
		if (registerreqObj.getStatus() != null) {
			user.setStatus(registerreqObj.getStatus());
		}
		if(registerreqObj.getRole() != null) {
			user.setRole(registerreqObj.getRole());
		}
		return user;
	}

}
