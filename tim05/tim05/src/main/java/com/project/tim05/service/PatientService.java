package com.project.tim05.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.tim05.model.Patient;
import com.project.tim05.repository.PatientRepository;


@Service
public class PatientService {
	
	@Autowired
	private PatientRepository pa;
	
	public void editPatient(Patient patient) {
		
		try {
	        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "");
	        String query = "UPDATE patient set password = ?, name = ?, surname = ?, address = ?, city = ?, country = ?, phone_number = ?, insurance_number = ? WHERE email = ?;";
	        PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, patient.getPassword());
			ps.setString(2, patient.getName());
			ps.setString(3, patient.getSurname());
			ps.setString(4, patient.getAddress());
			ps.setString(5, patient.getCity());
			ps.setString(6, patient.getCountry());
			ps.setString(7, patient.getPhone_number());
			ps.setString(8, patient.getInsurance_number());
			ps.setString(9, patient.getEmail());
			int num = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public List<Patient> getPatients(){
		return pa.findAll();
	}
	
	public void addPatient(Patient patient) {
		pa.save(patient);
	}

}
