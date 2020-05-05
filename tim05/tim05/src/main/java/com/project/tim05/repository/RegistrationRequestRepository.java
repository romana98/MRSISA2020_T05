package com.project.tim05.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.tim05.model.RegistrationRequest;

public interface RegistrationRequestRepository extends JpaRepository<RegistrationRequest, Integer>{
	void deleteByEmail(String email);
	RegistrationRequest findByEmail(String email);
	RegistrationRequest findByInsurance_number(String insurance_number);
}
