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
import com.project.tim05.model.Clinic;
import com.project.tim05.model.Nurse;
import com.project.tim05.repository.NurseRepository;
import com.project.tim05.service.initializeAndUnproxy;

@Service
public class NurseService {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private NurseRepository nr;
	
	@Autowired
	private AuthorityService authService;
	
	public int editProfile(Nurse nurse) {
		int flag = 0;
		try {
			Connection connection = DriverManager.getConnection("jdbc:postgresql://ec2-54-247-89-181.eu-west-1.compute.amazonaws.com:5432/d1d2a9u0egu6ja", "xslquaksjvvetl", "791a6dd69c36471adccf1118066dae6841cf2b7145d82831471fdd6640e5d99a");
			
	        if(nurse.getPassword().length()!=0) {
				nurse.setPassword(passwordEncoder.encode(nurse.getPassword()));
	        	String query = "UPDATE users set password = ?, name = ?, surname = ? WHERE email = ?;";
		        PreparedStatement ps = connection.prepareStatement(query);
				ps.setString(1, nurse.getPassword());
				ps.setString(2, nurse.getName());
				ps.setString(3, nurse.getSurname());
				ps.setString(4, nurse.getEmail());
				flag = ps.executeUpdate();
				
				ps.close();
				connection.close();
				return flag;
	        }else {
	        	String query = "UPDATE users set name = ?, surname = ? WHERE email = ?;";
		        PreparedStatement ps = connection.prepareStatement(query);
				ps.setString(1, nurse.getName());
				ps.setString(2, nurse.getSurname());
				ps.setString(3, nurse.getEmail());
				flag = ps.executeUpdate();
				
				ps.close();
				connection.close();
				return flag;
	        }
			
		} catch (SQLException e) {
			return flag;
		}	
	}
	
	public List<Nurse> getNurses(){
		return nr.findAll();
	}
	
	public int addNurse(Nurse nurse) {
		int flag = 0;
		
		try {
			nurse.setPassword(passwordEncoder.encode(nurse.getPassword()));
			List<Authority> auth = authService.findByname("ROLE_NURSE");
			nurse.setAuthorities(auth);
			nr.save(nurse);
			flag = 1;
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			return flag;
		}
		return flag;
		
	}
	
	public Nurse getNurse(String email) {
		return nr.findByEmail(email);
	}

	public Clinic getClinic(String email) {
		Nurse n = nr.findByEmail(email);
		Clinic c = initializeAndUnproxy.initAndUnproxy(n.getClinic());
		return c;
	}
	

}
