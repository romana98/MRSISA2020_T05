package com.project.tim05.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.tim05.model.RegistrationRequest;
import com.project.tim05.repository.RegistrationRequestRepository;

@Service
public class RegistrationRequestService {
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RegistrationRequestRepository rrr;

	public int addRegistrationRequest(RegistrationRequest rr) {
		try {
			rr.setPassword(passwordEncoder.encode(rr.getPassword()));
			rrr.save(rr);
			
		} catch (Exception e) {
			
			return 0;
		}
		
		return 1;	
	}

	public List<RegistrationRequest> getRequests(){
		return rrr.findAll();
	}
	
	@Transactional
	public int removeRegistrationRequest(RegistrationRequest rr) {
		
		int success = 0;
		try {
			rrr.deleteByEmail(rr.getEmail());
			success = 1;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			success = 0;
		}
		
		return success;
	}

}
