package com.project.tim05.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.tim05.model.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Integer>{
	Doctor findByEmail(String email);
}
