package com.project.tim05.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.tim05.dto.LeaveRequestDTO;
import com.project.tim05.model.LeaveRequest;
import com.project.tim05.repository.LeaveRequestRespository;

@Service
public class LeaveRequestService {

	@Autowired
	private LeaveRequestRespository lrr;

	public int addLeaveRequest(LeaveRequestDTO lrDTO) {
		//Connection conn = DriverManager.getConnection("jdbc:postgresql://ec2-54-247-89-181.eu-west-1.compute.amazonaws.com:5432/d1d2a9u0egu6ja", "xslquaksjvvetl", "791a6dd69c36471adccf1118066dae6841cf2b7145d82831471fdd6640e5d99a");
		try {
			Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "");
	        
			PreparedStatement st = conn.prepareStatement("SELECT * FROM leave_requests WHERE email = ? and start_date >= ? and end_date <= ?");
			st.setString(1, lrDTO.getEmail());
			st.setString(2, lrDTO.getStartDate());
			st.setString(3, lrDTO.getEndDate());
			ResultSet rs = st.executeQuery();
			
			if(!rs.next())
			{
				lrr.save(new LeaveRequest(lrDTO.getStartDate(), lrDTO.getEndDate(), lrDTO.getEmail(), lrDTO.getName(), lrDTO.getSurname()));

				conn.close();
				rs.close();
				st.close();	
				return 1;
			
				
			}

			conn.close();
			rs.close();
			st.close();	
			
		
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return 0;
		}
		return 0;
	}
}
