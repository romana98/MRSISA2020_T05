package com.project.tim05.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.tim05.model.Diagnosis;
import com.project.tim05.repository.DiagnosisRespository;

@Service
public class DiagnosisService {

	@Autowired
	private DiagnosisRespository dr;
	
	public int addDiagnosis(Diagnosis diagnosis) {
		try {
			
			dr.save(diagnosis);
			
		} catch (Exception e) {
			
			return 0;
		}
		
		return 1;	
	}
	
	public List<Diagnosis> getDiagnosises(){
		return dr.findAll();
	}
}
