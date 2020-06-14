package com.project.tim05.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.tim05.dto.LeaveRequestDTO;
import com.project.tim05.model.LeaveRequest;
import com.project.tim05.repository.DoctorRepository;
import com.project.tim05.repository.LeaveRequestRespository;
import com.project.tim05.repository.NurseRepository;

@Transactional(readOnly = false)
@Service
public class LeaveRequestService {

	@Autowired
	private LeaveRequestRespository lrr;
	
	@Autowired
	private NurseRepository ns;
	
	@Autowired
	private DoctorRepository ds;
	

	public int addLeaveRequest(LeaveRequestDTO lrDTO) {
		//Connection conn = DriverManager.getConnection("jdbc:postgresql://ec2-54-247-89-181.eu-west-1.compute.amazonaws.com:5432/d1d2a9u0egu6ja", "xslquaksjvvetl", "791a6dd69c36471adccf1118066dae6841cf2b7145d82831471fdd6640e5d99a");
		try {
			Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "");
			
			PreparedStatement st = conn.prepareStatement("SELECT * FROM leave_requests WHERE email = ?");
			st.setString(1, lrDTO.getEmail());
			ResultSet rs = st.executeQuery();
			boolean r = true;
			
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			java.util.Date start_l = null;
			java.util.Date end_l = null;
			try {
				start_l = formatter.parse(lrDTO.getStartDate());
				end_l = formatter.parse(lrDTO.getEndDate());
			} catch (ParseException e) {
				
				e.printStackTrace();
			}
			
			Date start_ls = new java.sql.Date(start_l.getTime());
			Date end_ls = new java.sql.Date(end_l.getTime());
			
			r = true;
			while(rs.next())
			{
				
				
				Date start = null;
				Date end = null;
				try {
					java.util.Date temp = formatter.parse(rs.getString("start_date"));
					start = new java.sql.Date(temp.getTime());
					temp =  formatter.parse(rs.getString("end_date"));
					end = new java.sql.Date(temp.getTime());
				} catch (ParseException e) {
					e.printStackTrace();
				}
				
				
				if(start.after(start_ls) && start.before(end_ls))
					r = false;
				if(end.after(start_ls) && end.before(end_ls))
					r = false;
				
				if(start.after(start_ls) && end.before(end_ls))
					r = false;
				
				if(start.before(end_ls) && end.after(end_ls))
					r = false;
				
				if(start.equals(start_ls) && end.equals(end_ls))
					r = false;
				
				
			}
			
			PreparedStatement st1 = conn.prepareStatement("SELECT * FROM work_calendars WHERE doctor = ? or nurse = ?");
			
			try{st1.setInt(1, ds.findByEmail(lrDTO.getEmail()).getId());}
			catch (Exception e) {
				st1.setInt(1, -1);
			}
			
			try{st1.setInt(2, ns.findByEmail(lrDTO.getEmail()).getId());}
			catch (Exception e) {
				st1.setInt(2, -1);
			}
			ResultSet rs1 = st1.executeQuery();
			boolean r1 = true;
			
			while(rs1.next())
			{
				
				Date date = rs1.getDate("date");
				
				if(start_ls.before(date) && end_ls.after(date))
				{ r1 = false; }
			}
			
			if(r && r1)
			{
				lrr.save(new LeaveRequest(lrDTO.getStartDate(), lrDTO.getEndDate(), lrDTO.getEmail(), lrDTO.getName(), lrDTO.getSurname()));

				conn.close();
				rs.close();
				st.close();	
				rs1.close();
				st1.close();	
				return 1;
			
				
			}

			conn.close();
			rs.close();
			st.close();
			rs1.close();
			st1.close();
			
		
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return 0;
		}
		return 0;
	}

	public List<LeaveRequest> getRequests() {
		return lrr.findAll();
	}

	@Transactional
	public int removeLeaveRequest(LeaveRequest lr) {
		int success = 0;
		try {
			lrr.deleteByEmail(lr.getEmail());
			success = 1;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			success = 0;
		}
		
		return success;
	}
}
