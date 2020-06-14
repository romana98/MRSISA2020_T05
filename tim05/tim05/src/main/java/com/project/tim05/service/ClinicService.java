package com.project.tim05.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.project.tim05.dto.ClinicDTO;
import com.project.tim05.dto.PatientClinicsDTO;
import com.project.tim05.model.Clinic;
import com.project.tim05.repository.ClinicRespository;

@Transactional(readOnly = false)
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
			//Connection conn = DriverManager.getConnection("jdbc:postgresql://ec2-54-247-89-181.eu-west-1.compute.amazonaws.com:5432/d1d2a9u0egu6ja", "xslquaksjvvetl", "791a6dd69c36471adccf1118066dae6841cf2b7145d82831471fdd6640e5d99a");
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
	
	public ArrayList<PatientClinicsDTO> getPatientClinics(String date, int appointmentType_id){
		
		//trenutno ovi gore podaci ce svi biti null ali u nastavku kada se bude pretraga implementirala stizace pravi podaci
		ArrayList<PatientClinicsDTO> clinics = new ArrayList<PatientClinicsDTO>();
		
		try {
			//Connection conn = DriverManager.getConnection("jdbc:postgresql://ec2-54-247-89-181.eu-west-1.compute.amazonaws.com:5432/d1d2a9u0egu6ja", "xslquaksjvvetl", "791a6dd69c36471adccf1118066dae6841cf2b7145d82831471fdd6640e5d99a");
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

	public int editClinic(Clinic clinic) {
		
		int flag = 0;
		try {
			//Connection conn = DriverManager.getConnection("jdbc:postgresql://ec2-54-247-89-181.eu-west-1.compute.amazonaws.com:5432/d1d2a9u0egu6ja", "xslquaksjvvetl", "791a6dd69c36471adccf1118066dae6841cf2b7145d82831471fdd6640e5d99a");
			Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "");
	        
			String query = "UPDATE clinics set name = ?, address = ?, description = ? WHERE clinic_id = ?;";
 	        PreparedStatement ps = conn.prepareStatement(query);
 	        ps.setString(1, clinic.getName());
 			ps.setString(2, clinic.getAddress());
 			ps.setString(3, clinic.getDescription());
 			ps.setInt(4, clinic.getId());
 			
 		
 			flag = ps.executeUpdate();
 			ps.close();	
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return flag;
		}
	
		return flag;
	}

	public ArrayList<Clinic> filterClinicsByParams(ArrayList<Clinic> clinics, String address_param, int low_rate,
			int high_rate) {
		if(address_param.equals("") && low_rate == 0 && high_rate == 0) {
			return clinics;
		}else if(address_param.equals("") && (low_rate != 0 || high_rate != 0)) {
			ArrayList<Clinic> result = new ArrayList<Clinic>();
			ArrayList<Clinic> find = (ArrayList<Clinic>) cr.findAll();
			for(Clinic c : clinics) {
				for(Clinic c2 : find) {
					if(c.getId() == c2.getId()) {
						double avg = 0.0;
						double zbir = 0.0;
						for(double rating : c.getRatings()) {
							zbir+=rating;
						}
						avg = zbir/c.getRatings().size();
						if(low_rate != 0 && high_rate == 0) {
							if(avg >= low_rate) {
								result.add(c);
								break;
							}
						}else if(low_rate == 0 && high_rate != 0) {
							if(avg <= high_rate) {
								result.add(c);
								break;
							}
						}else {
							if(avg >= low_rate && avg <= high_rate) {
								result.add(c);
								break;
							}
						}
					}
				}
			}
			return result;
		}else {
			ArrayList<Clinic> result = new ArrayList<Clinic>();
			
			try {
				//Connection conn = DriverManager.getConnection("jdbc:postgresql://ec2-54-247-89-181.eu-west-1.compute.amazonaws.com:5432/d1d2a9u0egu6ja", "xslquaksjvvetl", "791a6dd69c36471adccf1118066dae6841cf2b7145d82831471fdd6640e5d99a");
				Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "");
		        
				String query = "SELECT * from clinics where address like ?;";
	 	        PreparedStatement ps = conn.prepareStatement(query);
	 	        ps.setString(1, "%"+address_param+"%");
	 			
	 		
	 	       ResultSet rs = ps.executeQuery();
				
				while(rs.next()) {
					for(Clinic c : clinics) {
						if(rs.getInt("clinic_id") == c.getId()) {
							double zbir = 0;
							double avg = 0.0;
							for(double d : c.getRatings()) {
								zbir += d;
							}
							avg = zbir/c.getRatings().size();
							if(low_rate != 0 && high_rate == 0) {
								if(avg >= low_rate) {
									result.add(c);
									break;
								}
							}else if(low_rate == 0 && high_rate != 0) {
								if(avg <= high_rate) {
									result.add(c);
									break;
								}
							}else {
								if(avg >= low_rate && avg <= high_rate) {
									result.add(c);
									break;
								}
							}
						}
					}
				}
				rs.close();
				ps.close();		
				conn.close();
				return result;
				
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				return result;
			}
		}
	}


	
	
}
