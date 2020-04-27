package com.project.tim05.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

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

	public List<RegistrationRequest> getRequests(){
		return rrr.findAll();
	}
	
	public int removeRegistrationRequest(RegistrationRequest rr) {
		
		int success = 0;
		try {
	        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "");
	        
	        String query = "DELETE FROM registration_requests WHERE password = ? and name = ? and surname = ? and address = ? and city = ? and country = ? and phone_number = ? and insurance_number = ? and email = ?";
	        PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, rr.getPassword());
			ps.setString(2, rr.getName());
			ps.setString(3, rr.getSurname());
			ps.setString(4, rr.getAddress());
			ps.setString(5, rr.getCity());
			ps.setString(6, rr.getCountry());
			ps.setString(7, rr.getPhone_number());
			ps.setString(8, rr.getInsurance_number());
			ps.setString(9, rr.getEmail());
			
			success = ps.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return success;
	}

}
