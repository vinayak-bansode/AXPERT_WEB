package com.appointmentbooking.axpert.db;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.appointmentbooking.axpert.entity.UserEntity;



public interface UserRepo extends JpaRepository<UserEntity, Long> {
	 Optional<UserEntity> findByEmail(String email) throws Exception;
	  Boolean existsByEmail(String email);

}
