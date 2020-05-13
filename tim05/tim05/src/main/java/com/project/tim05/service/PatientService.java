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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.tim05.model.Authority;
import com.project.tim05.model.Clinic;
import com.project.tim05.model.MedicalRecord;
import com.project.tim05.model.Patient;
import com.project.tim05.repository.PatientRepository;


@Service
public class PatientService {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthorityService authService;
	
	@Autowired
	private ClinicService cs;

	
	@Autowired
	private PatientRepository pa;
	
	public int editPatient(Patient patient) {
		int flag = 0;
		try {
			//Connection connection = DriverManager.getConnection("jdbc:postgresql://ec2-54-247-89-181.eu-west-1.compute.amazonaws.com:5432/d1d2a9u0egu6ja", "xslquaksjvvetl", "791a6dd69c36471adccf1118066dae6841cf2b7145d82831471fdd6640e5d99a");
			Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "");
	        
	        if(patient.getPassword().length() != 0) {
	        	patient.setPassword(passwordEncoder.encode(patient.getPassword()));
		        String query = "UPDATE users set password = ?, name = ?, surname = ? where user_id = ?;";
		        PreparedStatement ps = connection.prepareStatement(query);
				ps.setString(1, patient.getPassword());
				ps.setString(2, patient.getName());
				ps.setString(3, patient.getSurname());
				ps.setInt(4, patient.getId());
				flag = ps.executeUpdate();
				ps.close();
				
				String query2 = "UPDATE patients set address = ?, city = ?, country = ?, phone_number = ? where user_id = ?;";
		        PreparedStatement ps2 = connection.prepareStatement(query2);
				ps2.setString(1, patient.getAddress());
				ps2.setString(2, patient.getCity());
				ps2.setString(3, patient.getCountry());
				ps2.setString(4, patient.getPhone_number());
				ps2.setInt(5, patient.getId());
				flag = ps2.executeUpdate();
				ps2.close();
				
				connection.close();
				return 1;
	        }else {
	        	String query = "UPDATE users set name = ?, surname = ? where user_id = ?;";
		        PreparedStatement ps = connection.prepareStatement(query);
				ps.setString(1, patient.getName());
				ps.setString(2, patient.getSurname());
				ps.setInt(3, patient.getId());
				flag = ps.executeUpdate();
				ps.close();
				
				String query2 = "UPDATE patients set address = ?, city = ?, country = ?, phone_number = ? where user_id = ?;";
		        PreparedStatement ps2 = connection.prepareStatement(query2);
				ps2.setString(1, patient.getAddress());
				ps2.setString(2, patient.getCity());
				ps2.setString(3, patient.getCountry());
				ps2.setString(4, patient.getPhone_number());
				ps2.setInt(5, patient.getId());
				flag = ps2.executeUpdate();
				ps2.close();
				
				connection.close();
				return 1;
	        }
		} catch (SQLException e) {
			e.printStackTrace();
			//return flag;
		}
		return flag;	
	}
	
	public List<Patient> getPatients(){
		return pa.findAll();
	}
	
	public int getPatientId(String email)
	{
		int id = -1;
		try {
			Patient p = pa.findByEmail(email);
			id = p.getId();
			
			
		} catch (Exception e) {
			
			return id;
		}
		return id;
	}
	
	
	public int addPatient(Patient patient) {
		try {
			Date date = new Date();
			List<Authority> auth = authService.findByname("ROLE_PATIENT");
			patient.setAuthorities(auth);
			patient.setPassword(patient.getPassword());
			patient.setLastPasswordResetDate(new Timestamp(date.getTime()));
			patient.setMedicalRecord(new MedicalRecord());
			
			patient.getClinics().add(cs.getClinicbyId(1));

			patient.setEnabled(true);
			pa.save(patient);
			
		} catch (Exception e) {
			
			return 0;
		}
		
		return 1;
	}
	
	public List<Patient> getPatients(Clinic cl)
	{
		List<Patient> patients = new ArrayList<Patient>();
		
		try {
			//Connection conn = DriverManager.getConnection("jdbc:postgresql://ec2-54-247-89-181.eu-west-1.compute.amazonaws.com:5432/d1d2a9u0egu6ja", "xslquaksjvvetl", "791a6dd69c36471adccf1118066dae6841cf2b7145d82831471fdd6640e5d99a");
			Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "");
	        
			PreparedStatement st = conn.prepareStatement("SELECT * FROM patients_clinics WHERE patients_clinics.clinics_clinic_id = ?");
			st.setInt(1, cl.getId());
			ResultSet rs = st.executeQuery();
			
			while(rs.next())
			{	
				patients.add(pa.findById(rs.getInt("patient_user_id")));	
			}
			
			rs.close();
			st.close();		
			conn.close();
			
		} catch (SQLException e) {
			
			System.out.println(e.getMessage());
			return patients;
		}
		
		
		return patients;
		
	}
	
	public Patient getPatient(String email)
	{
		try {
			Patient ca = pa.findByEmail(email);
			return ca;
			
		} catch (Exception e) {
			
			return null;
		}
	}

}
