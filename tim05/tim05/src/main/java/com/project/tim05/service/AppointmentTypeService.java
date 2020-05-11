package com.project.tim05.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.tim05.dto.AppointmentTypeDTO;
import com.project.tim05.model.AppointmentType;
import com.project.tim05.repository.AppointmentTypeRespository;

@Service
public class AppointmentTypeService {

	@Autowired
	private AppointmentTypeRespository atr;

	
	public int addAppointmentType(AppointmentType at) {
		int flag = 0;
		try {
			atr.save(at);
			flag = 1;
		}
		catch(Exception e){
			return flag;
		}
		return flag;
		
	}
	
	public ArrayList<AppointmentTypeDTO> getAppointmentTypes(){
		ArrayList<AppointmentTypeDTO> apps = new ArrayList<AppointmentTypeDTO>();
		for(AppointmentType at : atr.findAll()) {
			AppointmentTypeDTO adto = new AppointmentTypeDTO();
			adto.setName(at.getName());
			adto.setId(at.getId());
			adto.setAdmin_id(at.getClinicAdmin().getId());
			apps.add(adto);
		}
		return apps;
	}
	
	public AppointmentType getAppointmentTypebyId(int id) {
		return atr.findById(id).orElse(null);
	}
	
	
}
