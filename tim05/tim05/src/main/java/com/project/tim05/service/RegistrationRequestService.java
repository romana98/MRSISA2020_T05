package com.project.tim05.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

	public int addRegistrationRequest(RegistrationRequest rr) throws SQLException {
		try {
			rr.setPassword(passwordEncoder.encode(rr.getPassword()));
			User u1 = ur.findByEmail(rr.getEmail());
			
			Connection conn = DriverManager.getConnection("jdbc:postgresql://ec2-54-247-89-181.eu-west-1.compute.amazonaws.com:5432/d1d2a9u0egu6ja", "xslquaksjvvetl", "791a6dd69c36471adccf1118066dae6841cf2b7145d82831471fdd6640e5d99a");
			PreparedStatement st = conn.prepareStatement("SELECT * FROM patients WHERE insurance_number = ?");
			st.setString(1, rr.getInsurance_number());
			ResultSet rs = st.executeQuery();
			
			int u2 = -1;
			if(rs.next())
			{
				u2 = rs.getInt("user_id");	
			}
			
			rs.close();
			st.close();		
			conn.close();
					
			if(u1 != null) {
				return 1;
			}else if(u2 != -1) {
				return 2;
			}
			rrr.save(rr);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			RegistrationRequest r = rrr.findByEmail(rr.getEmail());
			
			Connection conn = DriverManager.getConnection("jdbc:postgresql://ec2-54-247-89-181.eu-west-1.compute.amazonaws.com:5432/d1d2a9u0egu6ja", "xslquaksjvvetl", "791a6dd69c36471adccf1118066dae6841cf2b7145d82831471fdd6640e5d99a");
			PreparedStatement st = conn.prepareStatement("SELECT * FROM registration_requests WHERE insurance_number = ?");
			st.setString(1, rr.getInsurance_number());
			ResultSet rs = st.executeQuery();
			
			int r2 = -1;
			if(rs.next())
			{
				r2 = rs.getInt("registration_request_id");	
			}
			
			rs.close();
			st.close();		
			conn.close();
					
		
		
			if(r != null) {
				return 1;
			}else if(r2 != -1) {
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

	
	public RegistrationRequest findByEmail(String email) {
		return rrr.findByEmail(email);
	}

}
