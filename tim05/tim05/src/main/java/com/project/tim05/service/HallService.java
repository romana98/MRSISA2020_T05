package com.project.tim05.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.tim05.model.Hall;
import com.project.tim05.repository.HallRepository;

@Service
public class HallService {
	
	@Autowired
	private HallRepository hr;
	
	public int addHall(Hall hall) {
		
		Connection conn;
		try {
			conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "");
		
			PreparedStatement st;
			
			st = conn.prepareStatement("SELECT * FROM halls WHERE name = ? and number = ? and clinic = ?");
			
			st.setString(1, hall.getName());
			st.setInt(2, hall.getNumber());
			st.setInt(3, hall.getClinic().getId());
			ResultSet rs = st.executeQuery();
			if(!rs.next()) {
				hr.save(hall);
				return 1;
			}else {
				return 0;
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return 0;
		
	}
	

}
