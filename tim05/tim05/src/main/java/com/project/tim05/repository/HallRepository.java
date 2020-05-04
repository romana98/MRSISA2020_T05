package com.project.tim05.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.tim05.model.Hall;

public interface HallRepository extends JpaRepository<Hall, Integer> {
	
	List<Hall> getByNumber(int number);
	List<Hall> getByName(String name);

}
