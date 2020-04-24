package com.project.tim05.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.tim05.model.Clinic;

public interface ClinicRespository extends JpaRepository<Clinic, Integer> {

}
