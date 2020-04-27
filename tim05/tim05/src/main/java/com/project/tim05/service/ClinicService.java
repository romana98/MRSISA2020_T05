package com.project.tim05.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.tim05.dto.ClinicDTO;
import com.project.tim05.model.Clinic;
import com.project.tim05.repository.ClinicRespository;

@Service
public class ClinicService {

	@Autowired
	private ClinicRespository cr;
	
	public void addClinic(Clinic clinic) {
		cr.save(clinic);
	}
	
	public List<Clinic> getClinics(){
		return cr.findAll();
	}
	
	public Clinic getClinic(ClinicDTO clinic){
		Clinic c = new Clinic();
		try {
			Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "");
			
			PreparedStatement st = conn.prepareStatement("SELECT * FROM clinics WHERE name = ? and address = ?");
			st.setString(1, clinic.getName());
			st.setString(2, clinic.getAddress());
			ResultSet rs = st.executeQuery();
			rs.next();
			
			c.setId(rs.getInt("clinic_id")); c.setName(rs.getString("name"));
			c.setAddress(rs.getString("address")); c.setDescription(rs.getString("description"));
			
			rs.close();
			st.close();		
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	
		return c;
	}
}
