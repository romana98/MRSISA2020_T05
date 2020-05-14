package com.project.tim05.service;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.tim05.dto.DoctorDTO;
import com.project.tim05.model.Authority;
import com.project.tim05.model.Doctor;
import com.project.tim05.repository.DoctorRepository;

@Service
public class DoctorService {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthorityService authService;
	
	@Autowired
	private DoctorRepository dr;
	
	public int addDoctor(Doctor doctor) {
		int flag = 0;
		try {
			doctor.setPassword(passwordEncoder.encode(doctor.getPassword()));
			List<Authority> auth = authService.findByname("ROLE_DOCTOR");
			doctor.setAuthorities(auth);
			dr.save(doctor);	
			flag = 1;
		}
		catch(Exception e) {
			return flag;
		}
		return flag;
		
	}
	
	public List<Doctor> getDoctors(){
		return dr.findAll();
	}
	
	public int editProfile(Doctor doctor) {
		int flag = 0;
		try {
			Connection connection = DriverManager.getConnection("jdbc:postgresql://ec2-54-247-89-181.eu-west-1.compute.amazonaws.com:5432/d1d2a9u0egu6ja", "xslquaksjvvetl", "791a6dd69c36471adccf1118066dae6841cf2b7145d82831471fdd6640e5d99a");
			//Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "");
	        
	        if(doctor.getPassword().length()!=0) {
				doctor.setPassword(passwordEncoder.encode(doctor.getPassword()));
	        	String query = "UPDATE users set password = ?, name = ?, surname = ? WHERE email = ?;";
		        PreparedStatement ps = connection.prepareStatement(query);
				ps.setString(1, doctor.getPassword());
				ps.setString(2, doctor.getName());
				ps.setString(3, doctor.getSurname());
				ps.setString(4, doctor.getEmail());
				flag = ps.executeUpdate();
				
				ps.close();
				connection.close();
				return flag;
	        }else {
	        	String query = "UPDATE users set name = ?, surname = ? WHERE email = ?;";
		        PreparedStatement ps = connection.prepareStatement(query);
				ps.setString(1, doctor.getName());
				ps.setString(2, doctor.getSurname());
				ps.setString(3, doctor.getEmail());
				flag = ps.executeUpdate();
				
				ps.close();
				connection.close();
				return flag;
	        }
			
		} catch (SQLException e) {
			return flag;
		}	
	}
	
	public Doctor getDoctorbyID(int id) {
		return dr.findById(id);
	}
	
	public ArrayList<DoctorDTO> getDoctors(int clinic_id, int appointment_id){
		try {
			Connection connection = DriverManager.getConnection("jdbc:postgresql://ec2-54-247-89-181.eu-west-1.compute.amazonaws.com:5432/d1d2a9u0egu6ja", "xslquaksjvvetl", "791a6dd69c36471adccf1118066dae6841cf2b7145d82831471fdd6640e5d99a");
			//Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "");
	        
			String query = "SELECT * FROM doctors WHERE appointment_type = ? and clinic= ?;";
	        PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, appointment_id);
			ps.setInt(2, clinic_id);
			ResultSet rs = ps.executeQuery();
			ArrayList<DoctorDTO> doctors = new ArrayList<DoctorDTO>();
			while(rs.next()) {
				Doctor dr1 = dr.findById(rs.getInt("user_id"));
				DoctorDTO drdto = new DoctorDTO();
				drdto.setName(dr1.getName());
				drdto.setId(dr1.getId());
				drdto.setSurname(dr1.getSurname());
				doctors.add(drdto);
			}
			connection.close();
			ps.close();
			rs.close();
			return doctors;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}	
		
	}
	
	public Doctor getDoctor(String email) {
		return dr.findByEmail(email);
	}
}
