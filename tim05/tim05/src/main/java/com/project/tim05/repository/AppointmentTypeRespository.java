package com.project.tim05.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.tim05.model.AppointmentType;
import com.project.tim05.model.Authority;

public interface AppointmentTypeRespository  extends JpaRepository<AppointmentType, Integer> {
	AppointmentType findByName(String name);
}
