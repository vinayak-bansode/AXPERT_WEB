package com.appointmentbooking.axpert.utils;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.appointmentbooking.axpert.payloads.UserDto;

import net.sf.json.JSONObject;

@Component("AppsJsonParser")
public class AppsJsonParser {

	public JSONObject createUserFromUserEntity(UserDto userEntity) {
		
		JSONObject jsonObject = new JSONObject();
		
		if(userEntity.getId()!=null)
		{
			jsonObject.put("id", userEntity.getId());
		}
		if(StringUtils.hasText(userEntity.getName()))
		{
			jsonObject.put("name", userEntity.getName());
		}
		if(StringUtils.hasText(userEntity.getPassword()))
		{
			jsonObject.put("password",userEntity.getPassword());
		}
		if(StringUtils.hasText(userEntity.getEmail()))
		{
			jsonObject.put("email",userEntity.getEmail());
		}
		if(StringUtils.hasText(userEntity.getAuthtype()))
		{
			jsonObject.put("authtype",userEntity.getAuthtype());
		}
		if(StringUtils.hasText(userEntity.getToken()))
		{
			jsonObject.put("token",userEntity.getToken());
		}
		if(StringUtils.hasText(userEntity.getExpert()))
		{
			jsonObject.put("expert",userEntity.getExpert());
		}
		if(userEntity.getStatus()!=null)
		{
			jsonObject.put("status",userEntity.getStatus());
		}
		
		

		return jsonObject;
	}
}

