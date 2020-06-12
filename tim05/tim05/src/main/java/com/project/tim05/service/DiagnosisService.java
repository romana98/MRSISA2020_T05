package com.project.tim05.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.tim05.model.Diagnosis;
import com.project.tim05.repository.DiagnosisRespository;

@Service
public class DiagnosisService {

	@Autowired
	private DiagnosisRespository dr;
	@Transactional(readOnly = false)
	public int addDiagnosis(Diagnosis diagnosis) {
		try {
			
			dr.save(diagnosis);
			
		} catch (Exception e) {
			
			return 0;
		}
		
		return 1;	
	}
	
	@Transactional(readOnly = true)
	public List<Diagnosis> getDiagnosises(){
		return dr.findAll();
	}
	
	/*@Transactional(readOnly = false)
	@Modifying
	public int updateDiagnosis(String a) {
		//Diagnosis d = dr.getOne(1);
		//d.setDescription(a);
		try {
		Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "");
        connection.setAutoCommit(false);
		try{Thread.sleep(5000);}catch(InterruptedException e){System.out.println(e);}

		String query = "UPDATE public.diagnosises SET description = 'aba', version = 1";
        PreparedStatement ps = connection.prepareStatement(query);
		ps.executeUpdate();
		try{Thread.sleep(5000);}catch(InterruptedException e){System.out.println(e);}
		connection.commit();
		
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		//try{Thread.sleep(5000);}catch(InterruptedException e){System.out.println(e);}
		//dr.save(d);
		return 0;
	}*/
	
}
