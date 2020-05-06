package com.project.tim05.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.tim05.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmail(String email);
	
}

