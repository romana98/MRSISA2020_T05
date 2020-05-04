package com.project.tim05.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.tim05.model.Nurse;
import com.project.tim05.repository.NurseRepository;

@Service
public class NurseService {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private NurseRepository nr;
	
	public int editProfile(Nurse nurse) {
		int flag = 0;
		try {
			nurse.setPassword(passwordEncoder.encode(nurse.getPassword()));
	        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "");
	        String query = "UPDATE nurses set password = ?, name = ?, surname = ? WHERE email = ?;";
	        PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, nurse.getPassword());
			ps.setString(2, nurse.getName());
			ps.setString(3, nurse.getSurname());
			ps.setString(4, nurse.getEmail());
			flag = ps.executeUpdate();
			ps.close();
			connection.close();
			return flag;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return flag;
		}	
	}
	
	public List<Nurse> getNurses(){
		return nr.findAll();
	}
	
	public int addNurse(Nurse nurse) {
		try {
			nurse.setPassword(passwordEncoder.encode(nurse.getPassword()));
			nr.save(nurse);
			return 1;
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
		
	}

}
