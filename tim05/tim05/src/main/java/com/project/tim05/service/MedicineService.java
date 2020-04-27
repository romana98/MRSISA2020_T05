package com.project.tim05.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.tim05.model.Medicine;
import com.project.tim05.repository.MedicineRepository;

@Service
public class MedicineService {
	
	@Autowired
	private MedicineRepository mr;
	
	public void addMedicine(Medicine medicine) {
		mr.save(medicine);
	}
	
	public List<Medicine> getMedicines(){
		return mr.findAll();
	}

}
