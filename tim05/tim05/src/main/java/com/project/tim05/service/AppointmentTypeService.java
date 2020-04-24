package com.project.tim05.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.tim05.repository.AppointmentTypeRespository;

@Service
public class AppointmentTypeService {

	@Autowired
	private AppointmentTypeRespository aca;
}
