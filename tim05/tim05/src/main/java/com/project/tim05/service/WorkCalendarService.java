package com.project.tim05.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.tim05.dto.WorkCalendarDTO;
import com.project.tim05.model.Clinic;
import com.project.tim05.model.Doctor;
import com.project.tim05.model.Nurse;
import com.project.tim05.model.WorkCalendar;
import com.project.tim05.repository.DoctorRepository;
import com.project.tim05.repository.NurseRepository;
import com.project.tim05.repository.WorkCalendarRespository;
@Service
public class WorkCalendarService{
	
	@Autowired
	private WorkCalendarRespository wcr;
	
	
	@Autowired
	private DoctorRepository dr;
	
	@Autowired NurseRepository nr;

	
	public int addCalendar(WorkCalendar wc) {
		wcr.save(wc);
		
		return 0;
	}


	public List<WorkCalendarDTO> getWorkCalendar(String email) {
		
		List<WorkCalendarDTO> wc = new ArrayList<WorkCalendarDTO>();
		try {
			//Connection conn = DriverManager.getConnection("jdbc:postgresql://ec2-54-247-89-181.eu-west-1.compute.amazonaws.com:5432/d1d2a9u0egu6ja", "xslquaksjvvetl", "791a6dd69c36471adccf1118066dae6841cf2b7145d82831471fdd6640e5d99a");
			Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "");
	        Doctor d = dr.findByEmail(email);
	        Nurse n = nr.findByEmail(email);
	        int id1 = -1, id2 = -1;
	        if(d != null)  id1 = d.getId();
	        if(n != null) id2 = n.getId();
	        
			PreparedStatement st = conn.prepareStatement("SELECT * from public.work_calendars where doctor = ? or nurse = ?");
			st.setInt(1, id1);
			st.setInt(2, id2);
			ResultSet rs = st.executeQuery();
			
			while(rs.next())
			{
				WorkCalendarDTO w = new WorkCalendarDTO(rs.getString("start_time"), rs.getString("end_time"), new Date(rs.getTimestamp("date").getTime()), rs.getBoolean("leave"), rs.getBoolean("request"));
				wc.add(w);
				
			}
			
			
			conn.close();
			rs.close();
			st.close();		
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return wc;
		}
	
		return wc;
	}
}
