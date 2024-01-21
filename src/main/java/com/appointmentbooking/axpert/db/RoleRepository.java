package com.appointmentbooking.axpert.db;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.appointmentbooking.axpert.entity.Role;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
   Optional<Role> findByName(String name) throws Exception;

}
