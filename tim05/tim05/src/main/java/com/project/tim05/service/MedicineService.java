package com.project.tim05.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.tim05.repository.MedicineRepository;

@Service
public class MedicineService {
	
	@Autowired
	private MedicineRepository mr;

}
