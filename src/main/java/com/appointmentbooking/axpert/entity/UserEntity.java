package com.appointmentbooking.axpert.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import jakarta.persistence.*;

@Entity
@Table(name = "user", uniqueConstraints = { @UniqueConstraint(columnNames = "email") })
@Data
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	
	private String password;
	private String role;

	private String email;
	private String authtype;
	private String token;

	@Column(nullable = true)
	private Long status;// 1- kyc unverified 2- kyc verfied
	@ManyToMany(mappedBy = "users")
	private Set<KycEntity> kycEntities;

	@Column(nullable = true)
	private String expert;

	@Column(nullable = true)
	private Long experinceyear;

	@Column(nullable = true)
	private Long rating;

	public String getAuthtype() {
		return authtype;
	}

	public void setAuthtype(String authtype) {
		this.authtype = authtype;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(long i) {
		this.status = i;
	}

	// Getters and setters for other fields

	public Set<KycEntity> getKycEntities() {
		return kycEntities;
	}

	public void setKycEntities(Set<KycEntity> kycEntities) {
		this.kycEntities = kycEntities;
	}

	public void updateKycStatus() {
		if (this.kycEntities != null && !this.kycEntities.isEmpty()) {
			boolean allVerified = this.kycEntities.stream().allMatch(kyc -> kyc.getStatus() == 2);
			this.setStatus(allVerified ? 2 : 1);
		} else {
			this.setStatus(1); // Assuming status is unverified if no KYC records are linked
		}
	}

	public String getExpert() {
		return expert;
	}

	public void setExpert(String expert) {
		this.expert = expert;
	}

	public Long getExperinceyear() {
		return experinceyear;
	}

	public void setExperinceyear(Long experinceyear) {
		this.experinceyear = experinceyear;
	}

	public Long getRating() {
		return rating;
	}

	public void setRating(Long long1) {
		this.rating = long1;
	}

	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();
    
    public void addRole(Role role) {
        roles.add(role);
        role.getUsers().add(this);
    }
    
    public void removeRole(Role role) {
        roles.remove(role);
        role.getUsers().remove(this);
    }


	public void setStatus(Long status) {
		this.status = status;
	}
	 
	
}
