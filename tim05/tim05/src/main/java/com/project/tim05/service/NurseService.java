package com.project.tim05.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.tim05.dto.DiseaseDTO;
import com.project.tim05.dto.LeaveRequestDTO;
import com.project.tim05.model.Authority;
import com.project.tim05.model.Clinic;
import com.project.tim05.model.LeaveRequest;
import com.project.tim05.model.Nurse;
import com.project.tim05.model.Patient;
import com.project.tim05.model.WorkCalendar;
import com.project.tim05.repository.NurseRepository;
import com.project.tim05.repository.PatientRepository;
import com.project.tim05.repository.WorkCalendarRespository;
import com.project.tim05.service.initializeAndUnproxy;

@Service
public class NurseService {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private NurseRepository nr;
	
	@Autowired
	private PatientRepository pr;
	
	@Autowired
	private WorkCalendarRespository wcr;
	
	@Autowired
	private AuthorityService authService;
	
	public int editProfile(Nurse nurse) {
		int flag = 0;
		try {
			//Connection connection = DriverManager.getConnection("jdbc:postgresql://ec2-54-247-89-181.eu-west-1.compute.amazonaws.com:5432/d1d2a9u0egu6ja", "xslquaksjvvetl", "791a6dd69c36471adccf1118066dae6841cf2b7145d82831471fdd6640e5d99a");
			Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "");
	        
	        if(nurse.getPassword().length()!=0) {
				nurse.setPassword(passwordEncoder.encode(nurse.getPassword()));
	        	String query = "UPDATE users set password = ?, name = ?, surname = ? WHERE email = ?;";
		        PreparedStatement ps = connection.prepareStatement(query);
				ps.setString(1, nurse.getPassword());
				ps.setString(2, nurse.getName());
				ps.setString(3, nurse.getSurname());
				ps.setString(4, nurse.getEmail());
				flag = ps.executeUpdate();
				
				ps.close();
				connection.close();
				return flag;
	        }else {
	        	String query = "UPDATE users set name = ?, surname = ? WHERE email = ?;";
		        PreparedStatement ps = connection.prepareStatement(query);
				ps.setString(1, nurse.getName());
				ps.setString(2, nurse.getSurname());
				ps.setString(3, nurse.getEmail());
				flag = ps.executeUpdate();
				
				ps.close();
				connection.close();
				return flag;
	        }
			
		} catch (SQLException e) {
			return flag;
		}	
	}
	
	public List<Nurse> getNurses(){
		return nr.findAll();
	}
	
	public int addNurse(Nurse nurse) {
		int flag = 0;
		
		try {
			nurse.setPassword(passwordEncoder.encode(nurse.getPassword()));
			List<Authority> auth = authService.findByname("ROLE_NURSE");
			nurse.setAuthorities(auth);
			nr.save(nurse);
			flag = 1;
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			return flag;
		}
		return flag;
		
	}
	
	public Nurse getNurse(String email) {
		return nr.findByEmail(email);
	}

	public Clinic getClinic(String email) {
		Nurse n = nr.findByEmail(email);
		Clinic c = initializeAndUnproxy.initAndUnproxy(n.getClinic());
		return c;
	}

	public int addLeave(LeaveRequest l) {
		int flag = 0;
		
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			java.util.Date start = null;
			java.util.Date end = null;
			try {
				start = formatter.parse(l.getStartDate());
				end = formatter.parse(l.getEndDate());
			} catch (ParseException e) {
				
				e.printStackTrace();
			}
	
			Calendar start_cal = Calendar.getInstance();
			start_cal.setTime(start);
			Calendar end_cal = Calendar.getInstance();
			end_cal.setTime(end);
			end_cal.add(Calendar.DATE, 1);
	
			java.sql.Date sql = null;
			for (java.util.Date date = start_cal.getTime(); start_cal.before(end_cal); start_cal.add(Calendar.DATE, 1), date = start_cal.getTime()) {
				sql = new java.sql.Date(date.getTime());
				WorkCalendar wc = new WorkCalendar("00:00", "23:59", sql, true);
				wc.setNurse(nr.findByEmail(l.getEmail()));
				wcr.save(wc);  
				
				
			}
			flag = 1;
		}catch (Exception e) {
			System.out.println(e.getMessage());
			flag = 0;
		}
		return flag;
	}

	public String canAccess(String email, int id) {
		String s = "nope";
		try {
			Patient p = pr.findByEmail(email);
			p.setMedicalRecord(initializeAndUnproxy.initAndUnproxy(p.getMedicalRecord()));
			// Connection conn =
			// DriverManager.getConnection("jdbc:postgresql://ec2-54-247-89-181.eu-west-1.compute.amazonaws.com:5432/d1d2a9u0egu6ja",
			// "xslquaksjvvetl",
			// "791a6dd69c36471adccf1118066dae6841cf2b7145d82831471fdd6640e5d99a");
			Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "");

			PreparedStatement st = conn
					.prepareStatement("SELECT * FROM appointment_medicines WHERE nurse = ?");
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			List<Integer> app_ids = new ArrayList<Integer>();
			
			while(rs.next())
				app_ids.add(rs.getInt("appointment"));
			
			if(app_ids.isEmpty())
				return s;
			
			st = conn
					.prepareStatement("SELECT * FROM appointments WHERE patient = ?");
			st.setInt(1, p.getId());
			rs = st.executeQuery();
			List<Integer> aps = new ArrayList<Integer>();
			
			while(rs.next())
				app_ids.add(rs.getInt("appointment"));
			
			if(app_ids.isEmpty())
				return s;
			
			for (Integer a_i : app_ids) {
				for (Integer a : aps) {
					if(a_i == a)
						return s = "yep";
				}
			}
				

			rs.close();
			st.close();
			conn.close();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}
	

}
