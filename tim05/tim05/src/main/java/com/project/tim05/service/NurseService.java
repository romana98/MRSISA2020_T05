package com.project.tim05.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.project.tim05.model.Doctor;
import com.project.tim05.model.Nurse;
import com.project.tim05.repository.NurseRepository;

@Service
public class NurseService {
	
	private NurseRepository nr;
	
	public void editProfile(Nurse nurse) {
		try {
	        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "");
	        String query = "UPDATE nurses set password = ?, name = ?, surname = ? WHERE email = ?;";
	        PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, nurse.getPassword());
			ps.setString(2, nurse.getName());
			ps.setString(3, nurse.getSurname());
			ps.setString(4, nurse.getEmail());
			int num = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public List<Nurse> getNurses(){
		return nr.findAll();
	}
	
	public int addNurse(Nurse nurse) {
		try {
			nr.save(nurse);
			return 1;
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
		
	}

}
