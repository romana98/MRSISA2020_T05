package com.project.tim05.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.tim05.model.AppointmentType;

public interface AppointmentTypeRespository  extends JpaRepository<AppointmentType, Integer> {

}
