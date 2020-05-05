package com.project.tim05.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.tim05.model.Authority;
import com.project.tim05.model.ClinicAdministrator;
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
			
	        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "");
	        String query = "UPDATE users set password = ?, name = ?, surname = ?, last_password_reset_date = ? WHERE email = ?;";
	        PreparedStatement ps = connection.prepareStatement(query);
	        
	        ps.setString(1, passwordEncoder.encode(admincl.getPassword()));
			ps.setString(2, admincl.getName());
			ps.setString(3, admincl.getSurname());
			ps.setTimestamp(4, admincl.getLastPasswordResetDate());
			ps.setString(5, admincl.getEmail());
		
			flag = ps.executeUpdate();
			
			connection.close();
			ps.close();
			
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

}
