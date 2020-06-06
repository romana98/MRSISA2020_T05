package com.project.tim05.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.tim05.model.LeaveRequest;

public interface LeaveRequestRespository extends JpaRepository<LeaveRequest, Integer>{
	void deleteByEmail(String email);
}
