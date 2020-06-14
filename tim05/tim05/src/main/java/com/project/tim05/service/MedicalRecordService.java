package com.project.tim05.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.tim05.repository.MedicalRecordRepository;

@Transactional(readOnly = false)
@Service
public class MedicalRecordService {
	
	@Autowired
	private MedicalRecordRepository mrr;
	
	

}
