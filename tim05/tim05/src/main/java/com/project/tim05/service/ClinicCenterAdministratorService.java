package com.project.tim05.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.tim05.model.ClinicCenterAdministrator;
import com.project.tim05.repository.ClinicCenterAdministratorRespository;;

@Service
public class ClinicCenterAdministratorService {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private ClinicCenterAdministratorRespository ccar;

	public int addClinicCenterAdministrator(ClinicCenterAdministrator admincl) {
		try {
			
			admincl.setPassword(passwordEncoder.encode(admincl.getPassword()));
			ccar.save(admincl);
			
		} catch (Exception e) {
			
			return 0;
		}
		
		return 1;
	}
	
	public List<ClinicCenterAdministrator> getClinicCenterAdministrators(){
		return ccar.findAll();
	}

	public int editClinicCenterAdministrator(ClinicCenterAdministrator cadmin) {
		int flag = 0;
		try {
			
			Connection connection = DriverManager.getConnection("jdbc:postgresql://ec2-54-247-89-181.eu-west-1.compute.amazonaws.com:5432/d1d2a9u0egu6ja", "xslquaksjvvetl", "791a6dd69c36471adccf1118066dae6841cf2b7145d82831471fdd6640e5d99a");
			//Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "");
	        
	        if(cadmin.getPassword().length() != 0)
	        {
	        	String query = "UPDATE users set password = ?, name = ?, surname = ?, last_password_reset_date = ? WHERE email = ?;";
	 	        PreparedStatement ps = connection.prepareStatement(query);
	 	        ps.setString(1, passwordEncoder.encode(cadmin.getPassword()));
	 			ps.setString(2, cadmin.getName());
	 			ps.setString(3, cadmin.getSurname());
	 			ps.setTimestamp(4, new Timestamp(new Date().getTime()));
	 			ps.setString(5, cadmin.getEmail());
	 		
	 			flag = ps.executeUpdate();
	 			ps.close();
	        }
	        else
	        {
	        	String query = "UPDATE users set name = ?, surname = ? WHERE email = ?;";
	 	        PreparedStatement ps = connection.prepareStatement(query);
	 			ps.setString(1, cadmin.getName());
	 			ps.setString(2, cadmin.getSurname());
	 			ps.setString(3, cadmin.getEmail());
	 		
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

}
