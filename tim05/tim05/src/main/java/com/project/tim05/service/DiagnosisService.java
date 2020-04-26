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
	
	public void addDiagnosis(Diagnosis diagnosis) {
		dr.save(diagnosis);
	}
	
	public List<Diagnosis> getDiagnosises(){
		return dr.findAll();
	}
}
