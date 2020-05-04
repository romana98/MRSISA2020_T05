package com.project.tim05.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.tim05.model.Appointment;
import com.project.tim05.repository.AppointmentRespository;


@Service
public class AppointmentService {
	
	@Autowired
	private AppointmentRespository ar;

	public int addAppointment(Appointment appointment) {
		int flag = 0;
		try {
			ar.save(appointment);
			flag = 1;
			
		} catch (Exception e) {
			return flag;
		}
		
		return flag;
	}
	
	public List<Appointment> getAppointments(){
		return ar.findAll();
	}
	
	
}
