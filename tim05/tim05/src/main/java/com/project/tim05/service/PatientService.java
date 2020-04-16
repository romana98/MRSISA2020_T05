package com.project.tim05.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.tim05.model.Patient;
import com.project.tim05.repository.PatientRepository;


@Service
public class PatientService {
	
	//Connection conn;
	@Autowired
	private PatientRepository pa;
	
	public void editPatient(Patient patient) {
		//try {
			//PreparedStatement ps = conn.prepareStatement("UPDATE pa set email = ?, set password = ?, set name = ?, set surname = ?, set address = ?, set city = ?, set country = ?, set phone_number = ?, set insurance_number = ? WHERE email = ?");
			//ps.setString(1, patient.getEmail());
			//ps.setString(2, patient.getPassword());
			//ps.setString(3, patient.getName());
			//ps.setString(4, patient.getSurname());
			//ps.setString(5, patient.getAddress());
			//ps.setString(6, patient.getCity());
			//ps.setString(7, patient.getCountry());
			//ps.setString(8, patient.getPhone_number());
			//ps.setString(9, patient.getInsurance_number());
			//ps.setString(10, patient.getEmail());
			//ps.executeUpdate();
		//} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		//}
		
		
	}
	
	public List<Patient> getPatients(){
		return pa.findAll();
	}
	
	public void addPatient(Patient patient) {
		pa.save(patient);
	}

}
