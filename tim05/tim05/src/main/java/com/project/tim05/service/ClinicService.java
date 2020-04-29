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
	
	public int addClinic(Clinic clinic) {
		try {
			
			cr.save(clinic);
			
		} catch (Exception e) {
			
			return 0;
		}
		
		return 1;	
	}
	
	public List<Clinic> getClinics(){
		return cr.findAll();
	}
	
	public Clinic getClinic(ClinicDTO clinic){
		Clinic c = null;
		try {
			Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "");
			
			PreparedStatement st = conn.prepareStatement("SELECT * FROM clinics WHERE name = ? and address = ?");
			st.setString(1, clinic.getName());
			st.setString(2, clinic.getAddress());
			ResultSet rs = st.executeQuery();
			
			if(!rs.next())
				return c;
			
			c = new Clinic();
			c.setId(rs.getInt("clinic_id")); c.setName(rs.getString("name"));
			c.setAddress(rs.getString("address")); c.setDescription(rs.getString("description"));
			
			rs.close();
			st.close();		
			
		} catch (SQLException e) {
			
			return c;
		}
	
		return c;
	}
}