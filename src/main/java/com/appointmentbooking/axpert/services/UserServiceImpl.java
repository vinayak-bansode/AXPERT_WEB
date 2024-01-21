package com.appointmentbooking.axpert.services;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.appointmentbooking.axpert.db.RoleRepository;
import com.appointmentbooking.axpert.db.UserRepo;
import com.appointmentbooking.axpert.entity.Role;
import com.appointmentbooking.axpert.entity.UserEntity;
import com.appointmentbooking.axpert.payloads.UserDto;

import jakarta.annotation.security.RolesAllowed;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private RoleRepository roleRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public UserDto registerUser(UserDto user) throws Exception {
		try {
			if (!userRepo.existsByEmail(user.getEmail())) {
				UserEntity userEntity = UserDto.convertreqtoUserEntity(user);
				// Assuming that 'ROLE_USER' is already created and available in the database
				Optional<Role> userRole = roleRepository.findByName("ROLE_USER");
				if (userRole.isEmpty()) {
					throw new Exception("ROLE_USER not found in the database");
				}
				Set<Role> roles = new HashSet<>();
				userRole.ifPresent(roles::add);
				userEntity.setRoles(roles);
				userEntity.setRole("ROLE_USER"); // Set the role name here if needed
				UserEntity savedUser = userRepo.save(userEntity);

				return UserDto.convertUserDto(savedUser);
			} else {
				throw new Exception("User Already Registered");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.toString());
		}
	}

	@Override
	public UserDto updateUser(UserDto user, String useremail) throws Exception {
		UserEntity userexists = this.userRepo.findByEmail(useremail)
				.orElseThrow(() -> new Exception("User Not Found"));
		try {
			if (userexists != null) {
				userexists = this.userRepo.save(UserDto.convertreqtoUserEntity(user));
			} else {
				throw new Exception("User Not Found");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.toString());
		}
		return UserDto.convertUserDto(userexists);
	}

	@Override
	public UserDto getUserbyid(Long userid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserDto> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUser(Long userid) throws Exception {
		try {
			userRepo.deleteById(userid);
		} catch (EmptyResultDataAccessException ex) {
			// Handling when user with the given ID doesn't exist
			throw new Exception("User not found with ID: " + userid);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}

	}

	@Override
	public UserDto loginuser(UserDto user) throws Exception {
		UserEntity userExists = this.userRepo.findByEmail(user.getEmail())
	            .orElseThrow(() -> new Exception("User Not Found"));

	    // Hash the incoming password for comparison
	    String rawPassword = user.getPassword();


	    if (userExists != null && rawPassword.matches(userExists.getPassword())) {
	        return UserDto.convertUserDto(userExists);
	    } else {
	        throw new Exception("Invalid user credentials");
	    }

	}

	@Override
	public Boolean forgetpassword(String email, String password) throws Exception {
		UserEntity userExists = this.userRepo.findByEmail(email)
				.orElseThrow(() -> new Exception("User Not Found"));

		userExists.setPassword(password);
		this.userRepo.save(userExists);
		return true;
	}

	

}
