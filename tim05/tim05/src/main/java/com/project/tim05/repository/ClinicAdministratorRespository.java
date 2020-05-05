package com.project.tim05.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.tim05.model.ClinicAdministrator;

public interface ClinicAdministratorRespository extends JpaRepository<ClinicAdministrator, Integer> {
	ClinicAdministrator findByEmail(String email);
}
