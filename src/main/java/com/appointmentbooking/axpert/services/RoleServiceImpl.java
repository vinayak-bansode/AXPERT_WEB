package com.appointmentbooking.axpert.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appointmentbooking.axpert.db.RoleRepository;
import com.appointmentbooking.axpert.db.UserRepo;
import com.appointmentbooking.axpert.entity.Role;
import com.appointmentbooking.axpert.entity.UserEntity;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	EntityManager entityManager;
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	RoleRepository repository;
	
	
	@Override
	@Transactional
	public void assignAdminRole(Long userId) throws Exception {
		try {
			int updatedCount = entityManager
					.createQuery("UPDATE UserEntity u SET u.role = 'ROLE_ADMIN' WHERE u.id = :userId")
					.setParameter("userId", userId)
					.executeUpdate();

			if (updatedCount != 1) {
				throw new Exception("Failed to assign ROLE_ADMIN to user with ID: " + userId);
			}else {
			Optional<UserEntity> userEntity = userRepo.findById(userId);
			String roleid = "2";
			Role role = repository.getById(Long.parseLong(roleid));
			userEntity.ifPresent(
					t -> t.addRole(role)
					);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}

	}
}
