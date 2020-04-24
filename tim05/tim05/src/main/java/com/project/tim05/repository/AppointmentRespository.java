package com.project.tim05.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.tim05.model.Appointment;

public interface AppointmentRespository  extends JpaRepository<Appointment, Integer> {

}
