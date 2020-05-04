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

import com.project.tim05.model.Doctor;
import com.project.tim05.repository.DoctorRepository;

@Service
public class DoctorService {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private DoctorRepository dr;
	
	public int addDoctor(Doctor doctor) {
		int flag = 0;
		try {
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
			doctor.setPassword(passwordEncoder.encode(doctor.getPassword()));
	        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "");
	        String query = "UPDATE doctors set password = ?, name = ?, surname = ? WHERE email = ?;";
	        PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, doctor.getPassword());
			ps.setString(2, doctor.getName());
			ps.setString(3, doctor.getSurname());
			ps.setString(4, doctor.getEmail());
			flag = ps.executeUpdate();
			
			ps.close();
			connection.close();
			return flag;
			
		} catch (SQLException e) {
			return flag;
		}	
	}
	
	public Doctor getDoctorbyID(int id) {
		return dr.findById(id).orElse(new Doctor());
	}
	
	public ArrayList<Doctor> getDoctors(int clinic_id, int appointment_id){
		try {
	        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "");
	        String query = "SELECT * FROM doctors WHERE appointment_type = ? and clinic= ?;";
	        PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, appointment_id);
			ps.setInt(2, clinic_id);
			ResultSet rs = ps.executeQuery();
			ArrayList<Doctor> doctors = new ArrayList<Doctor>();
			while(rs.next()) {
				Doctor dr = new Doctor();
				dr.setId(rs.getInt("staff_id"));
				dr.setName(rs.getString("name"));
				dr.setSurname(rs.getString("surname"));
				dr.setPassword(rs.getString("password"));
				dr.setEmail(rs.getString("email"));
				dr.setWorkStart(rs.getString("work_start"));
				dr.setWorkEnd(rs.getString("work_end"));
				doctors.add(dr);
			}
			connection.close();
			ps.close();
			rs.close();
			return doctors;
		} catch (SQLException e) {
			return null;
		}	
		
	}
}
