package com.project.tim05.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.tim05.dto.AppointmentDTO;
import com.project.tim05.dto.AppointmentTypeDTO;
import com.project.tim05.dto.DoctorDTO;
import com.project.tim05.dto.PatientDTO;
import com.project.tim05.dto.WorkCalendarDTO;
import com.project.tim05.model.Appointment;
import com.project.tim05.model.AppointmentType;
import com.project.tim05.model.Doctor;
import com.project.tim05.model.Patient;
import com.project.tim05.repository.AppointmentRespository;
import com.project.tim05.repository.AppointmentTypeRespository;
import com.project.tim05.repository.DoctorRepository;
import com.project.tim05.repository.PatientRepository;


@Service
public class AppointmentService {
	
	@Autowired
	private AppointmentRespository ar;
	
	@Autowired
	private DoctorRepository dr;
	
	@Autowired
	private AppointmentTypeRespository atr;
	
	@Autowired
	private PatientRepository pr;

	public int addAppointment(Appointment appointment) {
		int flag = 0;
		try {
			ar.save(appointment);
			flag = 1;
			
		} catch (Exception e) {
			return flag;
		}
		
		return flag;
	}
	
	public List<Appointment> getAppointments(){
		return ar.findAll();
	}
	
	public Appointment getAppointmentById(int id) {
		return ar.findById(id).orElse(null);
	}
	
	public ArrayList<AppointmentDTO> getAppointmentRequests(Date date, int clinic){
		Connection conn;
		try {
			// conn =
			// DriverManager.getConnection("jdbc:postgresql://ec2-54-247-89-181.eu-west-1.compute.amazonaws.com:5432/d1d2a9u0egu6ja",
			// "xslquaksjvvetl",
			// "791a6dd69c36471adccf1118066dae6841cf2b7145d82831471fdd6640e5d99a");
			conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "");

			PreparedStatement st;

			st = conn.prepareStatement(
					"SELECT * FROM public.appointments where date_time between ? and ? and clinic = ? and request = true");
			java.sql.Date sd = new java.sql.Date(date.getTime());
			st.setDate(1, sd);

			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.add(Calendar.DATE, 1);
			java.sql.Date sd1 = new java.sql.Date(c.getTimeInMillis());
			st.setDate(2, sd1);

			st.setInt(3, clinic);

			ResultSet rs = st.executeQuery();
			ArrayList<AppointmentDTO> appointments = new ArrayList<AppointmentDTO>();
			while (rs.next()) {
				Appointment ap = getAppointmentById(rs.getInt("appointment_id"));
				AppointmentDTO adto = new AppointmentDTO();
				adto.setId(ap.getId());
				adto.setDate(ap.getDateTime().toString().split(" ")[0]);
				String new_time = ap.getDateTime().toString().split(" ")[1];
				adto.setTime(new_time.split("\\.")[0].substring(0,5));
				adto.setDuration(ap.getDuration());
				PatientDTO pdto = new PatientDTO();
				pdto.setName(ap.getPatient().getName());
				pdto.setSurname(ap.getPatient().getSurname());
				adto.setPatient(pdto);
				DoctorDTO ddto = new DoctorDTO();
				ddto.setName(ap.getDoctor().getName());
				ddto.setSurname(ap.getDoctor().getSurname());
				ddto.setId(ap.getDoctor().getId());
				adto.setDoctor(ddto);
				AppointmentTypeDTO atdto = new AppointmentTypeDTO();
				atdto.setName(ap.getAppointmentType().getName());
				atdto.setId(ap.getAppointmentType().getId());
				adto.setAppointmentType(atdto);
				appointments.add(adto);
				
			}
			return appointments;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return new ArrayList<AppointmentDTO>();
	}

	public List<AppointmentDTO> getDoctorAppointments(String email) {
		List<AppointmentDTO> as = new ArrayList<AppointmentDTO>();
		try {
			//Connection conn = DriverManager.getConnection("jdbc:postgresql://ec2-54-247-89-181.eu-west-1.compute.amazonaws.com:5432/d1d2a9u0egu6ja", "xslquaksjvvetl", "791a6dd69c36471adccf1118066dae6841cf2b7145d82831471fdd6640e5d99a");
			Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "");
	        Doctor d = dr.findByEmail(email);
			PreparedStatement st = conn.prepareStatement("SELECT * from public.appointments where request = false and doctor = ?");
			st.setInt(1, d.getId());
			ResultSet rs = st.executeQuery();
			
			while(rs.next())
			{
				AppointmentType at = atr.findById(rs.getInt("appointment_type")).orElse(null);;
				Patient p = pr.findById(rs.getInt("patient"));
				AppointmentDTO w = new AppointmentDTO(rs.getTimestamp("date_time").toString(), new AppointmentTypeDTO(at.getName()), new PatientDTO(p.getName(), p.getSurname()));
				as.add(w);
				
			}
			
			
			conn.close();
			rs.close();
			st.close();		
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return as;
		}
	
		return as;
	}
	
	
}
