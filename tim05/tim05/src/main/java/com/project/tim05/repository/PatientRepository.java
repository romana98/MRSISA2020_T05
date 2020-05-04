package com.project.tim05.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.tim05.model.Patient;

public interface PatientRepository extends JpaRepository<Patient, Integer> {
	Patient findByEmail(String email);
}
