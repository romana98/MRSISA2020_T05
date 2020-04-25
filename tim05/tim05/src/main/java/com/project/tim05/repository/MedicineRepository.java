package com.project.tim05.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.tim05.model.Medicine;

public interface MedicineRepository extends JpaRepository<Medicine, Integer>{

}
