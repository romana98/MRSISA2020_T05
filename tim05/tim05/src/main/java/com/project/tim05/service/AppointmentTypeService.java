package com.project.tim05.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.tim05.model.AppointmentType;
import com.project.tim05.repository.AppointmentTypeRespository;

@Service
public class AppointmentTypeService {

	@Autowired
	private AppointmentTypeRespository atr;

	
	public int addAppointmentType(AppointmentType at) {
		atr.save(at);
		return 1;
	}
	
	public List<AppointmentType> getAppointmentTypes(){
		return atr.findAll();
	}
	
	public AppointmentType getAppointmentTypebyId(int id) {
		return atr.findById(id).orElse(null);
	}
	
	
}
