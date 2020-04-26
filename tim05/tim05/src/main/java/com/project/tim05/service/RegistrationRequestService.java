package com.project.tim05.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.tim05.model.RegistrationRequest;
import com.project.tim05.repository.RegistrationRequestRepository;

@Service
public class RegistrationRequestService {

	@Autowired
	private RegistrationRequestRepository rrr;

	public void addRegistrationRequest(RegistrationRequest rr) {
		rrr.save(rr);
	}

}
