package com.project.tim05.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.tim05.model.Hall;
import com.project.tim05.repository.HallRepository;

@Service
public class HallService {
	
	@Autowired
	private HallRepository hr;
	
	public int addHall(Hall hall) {
		
		List<Hall> halls1 = hr.getByName(hall.getName());
		List<Hall> halls2 = hr.getByNumber(hall.getNumber());
		
		for(Hall h : halls1) {
			if(halls2.contains(h)) {
				return 0;
			}
		}
		hr.save(hall);
		return 1;
		
		
	}
	
	public Hall getHallbyId(int id) {
		return hr.findById(id).orElse(null);
	}
	
	public ArrayList<Hall> getClinicHalls(int id){
		Connection conn;
		try {
			conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "");
		
			PreparedStatement st;
			
			st = conn.prepareStatement("SELECT * FROM halls WHERE clinic = ?");
			
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			ArrayList<Hall> lh = new ArrayList<Hall>();
			while(rs.next()) {
				Hall new_hall = new Hall();
				new_hall.setId(rs.getInt("hall_id"));
				new_hall.setName(rs.getString("name"));
				new_hall.setNumber(rs.getInt("number"));
				lh.add(new_hall);
			}
			conn.close();
			return lh;
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}	
		
		return null;	
	}
	
	public int deleteHall(int id) {
		int success = 0;
		try {
	        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "");
	        
	        String query = "DELETE FROM halls WHERE hall_id = ?";
	        PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, id);
			
			success = ps.executeUpdate();
		} catch (SQLException e) {
			
			success = 0;
		}
		
		return success;
	}

}
