package com.project.tim05.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.tim05.model.Diagnosis;

public interface DiagnosisRespository extends JpaRepository<Diagnosis, Integer> {

}
