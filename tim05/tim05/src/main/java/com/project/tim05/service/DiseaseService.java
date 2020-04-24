package com.project.tim05.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.tim05.repository.DiseaseRespository;

@Service
public class DiseaseService {
	
	@Autowired
	private DiseaseRespository dr;

}
