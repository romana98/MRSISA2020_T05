package com.project.tim05.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.tim05.model.ClinicAdministrator;
import com.project.tim05.repository.ClinicAdministratorRespository;

@Service
public class ClinicAdministratorService {
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private ClinicAdministratorRespository car;
	
	public int addClinicAdministrator(ClinicAdministrator admincl) {
		
		try {
			admincl.setPassword(passwordEncoder.encode(admincl.getPassword()));
			car.save(admincl);
			
		} catch (Exception e) {
			
			return 0;
		}
		
		return 1;		
	}
	
public int editClinicAdministrator(ClinicAdministrator admincl) {
	
		int flag = 0;
		try {
			
	        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "");
	        String query = "UPDATE clinic_admins set name = ?, surname = ? WHERE email = ?;";
	        PreparedStatement ps = connection.prepareStatement(query);
	        
			ps.setString(1, admincl.getName());
			ps.setString(2, admincl.getSurname());
			ps.setString(3, admincl.getEmail());
			
			flag = ps.executeUpdate();
			
		} catch (SQLException e) {
			return flag;
		}	
			
		return flag;		
	}
	
	public List<ClinicAdministrator> getClinicAdministrators(){
		return car.findAll();
	}
}
