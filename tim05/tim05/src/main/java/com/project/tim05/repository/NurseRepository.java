package com.project.tim05.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.tim05.model.Nurse;

public interface NurseRepository extends JpaRepository<Nurse, Integer>{
	Nurse findByEmail(String email);
}
