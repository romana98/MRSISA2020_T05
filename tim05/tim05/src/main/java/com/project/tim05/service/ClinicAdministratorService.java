package com.project.tim05.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.tim05.dto.DoctorDTO;
import com.project.tim05.dto.PatientDTO;
import com.project.tim05.model.Authority;
import com.project.tim05.model.ClinicAdministrator;
import com.project.tim05.model.Patient;
import com.project.tim05.repository.ClinicAdministratorRespository;

@Service
public class ClinicAdministratorService {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthorityService authService;

	@Autowired
	private ClinicAdministratorRespository car;
	
	public int addClinicAdministrator(ClinicAdministrator admincl) {
		
		try {
			admincl.setPassword(passwordEncoder.encode(admincl.getPassword()));
			List<Authority> auth = authService.findByname("ROLE_CLINIC_ADMIN");
			admincl.setAuthorities(auth);

			car.save(admincl);
			
		} catch (Exception e) {
			
			return 0;
		}
		
		return 1;		
	}
	
public int editClinicAdministrator(ClinicAdministrator admincl) {
	
		int flag = 0;
		try {
			
			//Connection connection = DriverManager.getConnection("jdbc:postgresql://ec2-54-247-89-181.eu-west-1.compute.amazonaws.com:5432/d1d2a9u0egu6ja", "xslquaksjvvetl", "791a6dd69c36471adccf1118066dae6841cf2b7145d82831471fdd6640e5d99a");
			Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "");
	        
	        if(admincl.getPassword().length() != 0)
	        {
	        	String query = "UPDATE users set password = ?, name = ?, surname = ?, last_password_reset_date = ? WHERE email = ?;";
	 	        PreparedStatement ps = connection.prepareStatement(query);
	 	        ps.setString(1, passwordEncoder.encode(admincl.getPassword()));
	 			ps.setString(2, admincl.getName());
	 			ps.setString(3, admincl.getSurname());
	 			ps.setTimestamp(4, new Timestamp(new Date().getTime()));
	 			ps.setString(5, admincl.getEmail());
	 		
	 			flag = ps.executeUpdate();
	 			ps.close();
	        }
	        else
	        {
	        	String query = "UPDATE users set name = ?, surname = ? WHERE email = ?;";
	 	        PreparedStatement ps = connection.prepareStatement(query);
	 			ps.setString(1, admincl.getName());
	 			ps.setString(2, admincl.getSurname());
	 			ps.setString(3, admincl.getEmail());
	 		
	 			flag = ps.executeUpdate();
	 			ps.close();
	        	
	        }
	        
	        
	       
			
			connection.close();
			
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return flag;
		}	
			
		return flag;		
	}
	
	public List<ClinicAdministrator> getClinicAdministrators(){
		return car.findAll();
	}
	
	public ClinicAdministrator getClinicAdmin(String email)
	{
		try {
			ClinicAdministrator ca = car.findByEmail(email);
			return ca;
			
		} catch (Exception e) {
			
			return null;
		}
	}
	
	public ClinicAdministrator getClinicAdmin(int id) {
		return car.findById(id).orElse(null);
	}
	

}
