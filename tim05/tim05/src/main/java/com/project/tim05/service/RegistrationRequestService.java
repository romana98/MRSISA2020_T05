package com.project.tim05.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.tim05.model.RegistrationRequest;
import com.project.tim05.model.User;
import com.project.tim05.repository.RegistrationRequestRepository;
import com.project.tim05.repository.UserRepository;

@Service
public class RegistrationRequestService {
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RegistrationRequestRepository rrr;
	
	@Autowired
	private UserRepository ur;

	public int addRegistrationRequest(RegistrationRequest rr) {
		try {
			rr.setPassword(passwordEncoder.encode(rr.getPassword()));
			rrr.save(rr);
			User u1 = ur.findByEmail(rr.getEmail());
			User u2 = ur.findByInsurance_number(rr.getInsurance_number());
			if(u1 != null) {
				return 1;
			}else if(u2 != null) {
				return 2;
			}
			
		} catch (Exception e) {
			
			RegistrationRequest r = rrr.findByEmail(rr.getEmail());
			RegistrationRequest r2 = rrr.findByInsurance_number(rr.getInsurance_number());
			if(r != null) {
				return 1;
			}else if(r2 != null) {
				return 2;
			}
		}
		
		return 0;	
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
