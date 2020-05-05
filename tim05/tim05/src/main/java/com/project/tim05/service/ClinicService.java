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

import com.project.tim05.dto.ClinicDTO;
import com.project.tim05.dto.PatientClinicsDTO;
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
			
			conn.close();
			rs.close();
			st.close();		
			
		} catch (SQLException e) {
			
			return c;
		}
	
		return c;
	}
	
	public Clinic getClinicbyId(int id) {
		return cr.findById(id).orElse(null);
	}
	
	public ArrayList<PatientClinicsDTO> getPatientClinics(String date, int appointmentType_id, String clinicAddress, int avg_rate_lowest, int avg_rate_highest){
		
		//trenutno ovi gore podaci ce svi biti null ali u nastavku kada se bude pretraga implementirala stizace pravi podaci
		ArrayList<PatientClinicsDTO> clinics = new ArrayList<PatientClinicsDTO>();
		
		try {
			Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "");
			
			PreparedStatement st = conn.prepareStatement("SELECT * FROM clinics");
			//kada se budu prosledjivali pravi parametri ovde treba staviti parametre sql poziva
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				PatientClinicsDTO pc = new PatientClinicsDTO();
				pc.setName(rs.getString("name"));
				pc.setAddress(rs.getString("address"));
				//ovde treba izracunati na osnovu pronadjenih klinika njihov avg_rating i iz cenovnika pogledati zapravo cenu
				pc.setAvg_rating(0);
				pc.setPrice(0);
				clinics.add(pc);
				
			}
			
			rs.close();
			st.close();		
			conn.close();
			
		} catch (SQLException e) {
			
			return clinics;
		}	
		
		return clinics;
	}
	
}
