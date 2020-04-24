package com.project.tim05.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.tim05.model.Disease;

public interface DiseaseRespository  extends JpaRepository<Disease, Integer> {

}
