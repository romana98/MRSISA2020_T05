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
import org.springframework.transaction.annotation.Transactional;

import com.project.tim05.dto.AppointmentDTO;
import com.project.tim05.dto.AppointmentTypeDTO;
import com.project.tim05.dto.DoctorDTO;
import com.project.tim05.dto.PatientDTO;
import com.project.tim05.model.Appointment;
import com.project.tim05.repository.AppointmentRespository;


@Service
public class AppointmentService {
	
	@Autowired
	private AppointmentRespository ar;

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
	
	@Transactional
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
	
	
	public void updateAppointment(Appointment a) {
		ar.save(a);
	}
	
}
