package com.project.tim05.service;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.tim05.model.Doctor;
import com.project.tim05.repository.DoctorRepository;

@Service
public class DoctorService {

	@Autowired
	private DoctorRepository dr;
	
	public void addDoctor(Doctor doctor) {
		dr.save(doctor);
	}
	
	public List<Doctor> getDoctors(){
		return dr.findAll();
	}
	
	public void editProfile(Doctor doctor) {
		try {
	        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "miloradpostgre123");
	        String query = "UPDATE doctors set password = ?, name = ?, surname = ? WHERE email = ?;";
	        PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, doctor.getPassword());
			ps.setString(2, doctor.getName());
			ps.setString(3, doctor.getSurname());
			ps.setString(4, doctor.getEmail());
			int num = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}
