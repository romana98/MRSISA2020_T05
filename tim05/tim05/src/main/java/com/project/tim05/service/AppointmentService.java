package com.project.tim05.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.tim05.dto.AppointmentDTO;
import com.project.tim05.dto.AppointmentTypeDTO;
import com.project.tim05.dto.ClinicDTO;
import com.project.tim05.dto.DiagnosisDTO;
import com.project.tim05.dto.DoctorDTO;
import com.project.tim05.dto.HallDTO;
import com.project.tim05.dto.PatientDTO;
import com.project.tim05.dto.WorkCalendarDTO;
import com.project.tim05.model.Appointment;
import com.project.tim05.model.AppointmentType;
import com.project.tim05.model.Clinic;
import com.project.tim05.model.ClinicAdministrator;
import com.project.tim05.model.Diagnosis;
import com.project.tim05.model.Doctor;
import com.project.tim05.model.Hall;
import com.project.tim05.model.Patient;
import com.project.tim05.model.WorkCalendar;
import com.project.tim05.repository.AppointmentRespository;
import com.project.tim05.repository.AppointmentTypeRespository;
import com.project.tim05.repository.ClinicAdministratorRespository;
import com.project.tim05.repository.DiagnosisRespository;
import com.project.tim05.repository.DoctorRepository;
import com.project.tim05.repository.HallRepository;
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
	
	@Autowired
	private DiagnosisRespository ddr;

	@Autowired
	private ClinicAdministratorRespository car;
	
	@Autowired
	private HallRepository hr;

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
		Appointment a = ar.findById(id).orElse(null);
		if(a != null)
		{
			a.setAppointmentType(initializeAndUnproxy.initAndUnproxy(a.getAppointmentType()));
			a.setDoctor(initializeAndUnproxy.initAndUnproxy(a.getDoctor()));
			a.setPatient(initializeAndUnproxy.initAndUnproxy(a.getPatient()));
		}
		return a;
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
				if(p == null)
				{
					p = new Patient(); p.setName(""); p.setSurname(""); p.setId(0); p.setEmail(""); p.setPassword(""); p.setInsurance_number("");
				}
				AppointmentDTO w = new AppointmentDTO(rs.getTimestamp("date_time").toString(), new AppointmentTypeDTO(at.getName()), new PatientDTO(p.getName(), p.getSurname()));
				w.setId(rs.getInt("appointment_id"));
				w.getPatient().setInsurance_number(p.getInsurance_number());
				w.getPatient().setPassword(Integer.toString(p.getId()));;
				w.getPatient().setEmail(p.getEmail());
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
	
	
	public void updateAppointment(Appointment a) {
		ar.save(a);
	}

	public int cancelAppointment(int appointment_id) {
		int flag = 0;
		try {
			Appointment a = this.getAppointmentById(appointment_id);
			
			long MILLIS_PER_DAY = 24 * 60 * 60 * 1000L;
			if(Math.abs(a.getDateTime().getTime() - new Date().getTime()) < MILLIS_PER_DAY)
			{
				return -5;
			}
			
			
			//Connection conn = DriverManager.getConnection("jdbc:postgresql://ec2-54-247-89-181.eu-west-1.compute.amazonaws.com:5432/d1d2a9u0egu6ja", "xslquaksjvvetl", "791a6dd69c36471adccf1118066dae6841cf2b7145d82831471fdd6640e5d99a");
			Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "");
			PreparedStatement ps = conn.prepareStatement("DELETE from public.appointments where appointment_id = ?");
			ps.setInt(1, appointment_id);
			flag = ps.executeUpdate();
			
			String time = "";
					
			if(a.getDateTime().getMinutes() < 10)
			{
				time =Integer.toString(a.getDateTime().getHours()) +":0" +  Integer.toString(a.getDateTime().getMinutes());
				
			}
			else
			{
				time =Integer.toString(a.getDateTime().getHours()) +":" +  Integer.toString(a.getDateTime().getMinutes());
				
			}
			
			
			ps = conn.prepareStatement("SELECT * from work_calendars where doctor = ? and date = ? and start_time = ?");
			ps.setInt(1, a.getDoctor().getId());
			ps.setString(3, time);
			a.getDateTime().setHours(0); a.getDateTime().setMinutes(0);
			ps.setTimestamp(2, new Timestamp(a.getDateTime().getTime()));
			ResultSet rs = ps.executeQuery();
			
			if(!rs.next())
				return 0;
			
			ps = conn.prepareStatement("DELETE  from medical_staff_work_calendar where work_calendar_work_cal_id = ?");
			ps.setInt(1, rs.getInt("work_cal_id"));
			
			flag = ps.executeUpdate();
			

			System.out.println(Integer.toString(a.getDateTime().getHours()) +":" +  Integer.toString(a.getDateTime().getMinutes()));
			ps = conn.prepareStatement("DELETE  from work_calendars where doctor = ? and date = ? and start_time = ?");
			ps.setInt(1, a.getDoctor().getId());
			ps.setString(3, time);
			ps.setTimestamp(2, new Timestamp(a.getDateTime().getTime()));
			flag = ps.executeUpdate();
			conn.close();
			ps.close();	
			
			
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return flag;
		}
	
		return flag;
	}

	public List<AppointmentDTO> getClinicPredefinedAppointments(int clinic_id) {
		List<AppointmentDTO> as = new ArrayList<AppointmentDTO>();
		try {
			//Connection conn = DriverManager.getConnection("jdbc:postgresql://ec2-54-247-89-181.eu-west-1.compute.amazonaws.com:5432/d1d2a9u0egu6ja", "xslquaksjvvetl", "791a6dd69c36471adccf1118066dae6841cf2b7145d82831471fdd6640e5d99a");
			Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "");
	        
			PreparedStatement st = conn.prepareStatement("SELECT * from public.appointments where predefined = true and patient is null and clinic = ?");
			st.setInt(1, clinic_id);
			ResultSet rs = st.executeQuery();
			
			while(rs.next())
			{
				AppointmentType at = atr.findById(rs.getInt("appointment_type")).orElse(null);
				Hall h = hr.findById(rs.getInt("hall")).orElse(null);
				Doctor d = dr.findById(rs.getInt("doctor"));
				AppointmentDTO w = new AppointmentDTO(rs.getTimestamp("date_time").toString(), new HallDTO(h.getName(), h.getNumber()), new AppointmentTypeDTO(at.getName()), new DoctorDTO(d.getName(), d.getSurname()), rs.getDouble("price"));
				w.setId(rs.getInt("appointment_id"));
				w.setDuration(rs.getInt("duration"));
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

	public int reservePredefined(int appId, Integer patientId) {
		int flag = 0;
		
		Appointment a = this.getAppointmentById(appId);
		
		if(a.getPatient() != null)
		{
			return 0;
		}
		Clinic c = initializeAndUnproxy.initAndUnproxy(a.getClinic());
		
		
		
		try {
			//Connection conn = DriverManager.getConnection("jdbc:postgresql://ec2-54-247-89-181.eu-west-1.compute.amazonaws.com:5432/d1d2a9u0egu6ja", "xslquaksjvvetl", "791a6dd69c36471adccf1118066dae6841cf2b7145d82831471fdd6640e5d99a");
			Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "");
			
			PreparedStatement ps = conn.prepareStatement("SELECT * from patients_clinics where patient_user_id = ? and clinics_clinic_id = ?");
			ps.setInt(1, patientId);
			ps.setInt(2, c.getId());
			ResultSet rs = ps.executeQuery();
			
			if(!rs.next())
			{
				 ps = conn.prepareStatement("INSERT INTO patients_clinics (patient_user_id, clinics_clinic_id) VALUES (?, ?) ");
				ps.setInt(1, patientId);
				ps.setInt(2, c.getId());
				flag = ps.executeUpdate();
				
			}
			
			ps = conn.prepareStatement("UPDATE appointments set patient = ? where appointment_id = ?");
			ps.setInt(1, patientId);
			ps.setInt(2, appId);
			flag = ps.executeUpdate();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			flag = 0;
		}
		
		return flag;
	}

	public int setAppointment(AppointmentDTO ap) {
		int flag = 0;
		try {
			//Connection conn = DriverManager.getConnection("jdbc:postgresql://ec2-54-247-89-181.eu-west-1.compute.amazonaws.com:5432/d1d2a9u0egu6ja", "xslquaksjvvetl", "791a6dd69c36471adccf1118066dae6841cf2b7145d82831471fdd6640e5d99a");
			Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "");
			PreparedStatement ps = conn.prepareStatement("UPDATE appointments set description = ?, diagnosis = ?, finished = true where appointment_id = ?");
			ps.setString(1, ap.getDescription());
			ps.setInt(2, Integer.parseInt(ap.getTime()));
			ps.setInt(3, ap.getId());
			flag = ps.executeUpdate();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			flag = 0;
		}
		
		return flag;
	}

	public List<AppointmentDTO> getAppointments(int id) {
		
		List<AppointmentDTO> as = new ArrayList<AppointmentDTO>();
		try {
			//Connection conn = DriverManager.getConnection("jdbc:postgresql://ec2-54-247-89-181.eu-west-1.compute.amazonaws.com:5432/d1d2a9u0egu6ja", "xslquaksjvvetl", "791a6dd69c36471adccf1118066dae6841cf2b7145d82831471fdd6640e5d99a");
			Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "");
	        
			PreparedStatement st = conn.prepareStatement("SELECT * from public.appointments where finished = true and patient = ?");
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			
			while(rs.next())
			{
				AppointmentType at = atr.findById(rs.getInt("appointment_type")).orElse(null);
				Hall h = hr.findById(rs.getInt("hall")).orElse(null);
				Doctor d = dr.findById(rs.getInt("doctor"));
				Diagnosis dd = ddr.findById(rs.getInt("diagnosis")).orElse(null);
				AppointmentDTO w = new AppointmentDTO(rs.getTimestamp("date_time").toString(), new HallDTO(h.getName(), h.getNumber()), new AppointmentTypeDTO(at.getName()), new DoctorDTO(d.getId(), d.getName(), d.getSurname()), rs.getDouble("price"));
				w.setId(rs.getInt("appointment_id"));
				w.setDuration(rs.getInt("duration"));
				DiagnosisDTO ddDTO = new DiagnosisDTO(dd.getName(), dd.getDescription());
				ddDTO.setDate(w.getDate());
				w.setDiagnosis(ddDTO);
				w.setDescription(rs.getString("description"));
				ClinicDTO dto = new ClinicDTO();
				dto.setId(rs.getInt("clinic"));
				w.setClinic(dto);
				w.setDoctor_id(d.getId());
				w.setRatedClinic(rs.getBoolean("rated_clinic"));
				w.setRatedDoctor(rs.getBoolean("rated_doctor"));
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

	public int saveDescription(Integer appId, String desc, Integer id) {
		
		int flag = 0;
		try {
			//Connection conn = DriverManager.getConnection("jdbc:postgresql://ec2-54-247-89-181.eu-west-1.compute.amazonaws.com:5432/d1d2a9u0egu6ja", "xslquaksjvvetl", "791a6dd69c36471adccf1118066dae6841cf2b7145d82831471fdd6640e5d99a");
			Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "");
			PreparedStatement ps = conn.prepareStatement("UPDATE appointments set description = ? where appointment_id = ? and doctor = ?");
			ps.setString(1, desc);
			ps.setInt(2, appId);
			ps.setInt(3, id);
			flag = ps.executeUpdate();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			flag = 0;
		}
		
		return flag;
	}

	public List<AppointmentDTO> getReportAppointments(Integer id) {
		List<AppointmentDTO> as = new ArrayList<AppointmentDTO>();
		try {
			ClinicAdministrator ca = car.findById(id).orElse(null);
			if(ca == null)
				return as;
			
			ca.setClinic(initializeAndUnproxy.initAndUnproxy(ca.getClinic()));
			//Connection conn = DriverManager.getConnection("jdbc:postgresql://ec2-54-247-89-181.eu-west-1.compute.amazonaws.com:5432/d1d2a9u0egu6ja", "xslquaksjvvetl", "791a6dd69c36471adccf1118066dae6841cf2b7145d82831471fdd6640e5d99a");
			Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "");

			PreparedStatement st = conn.prepareStatement("SELECT * from public.appointments where finished = true and clinic = ?");
			st.setInt(1, ca.getClinic().getId());
			ResultSet rs = st.executeQuery();
			
			while(rs.next())
			{
				AppointmentType at = atr.findById(rs.getInt("appointment_type")).orElse(null);;
				Patient p = pr.findById(rs.getInt("patient"));
				if(p == null)
				{
					p = new Patient(); p.setName(""); p.setSurname("");;
				}
				AppointmentDTO w = new AppointmentDTO();
				w.setDate(rs.getTimestamp("date_time").toString());
				w.setPatient(new PatientDTO(p.getName(), p.getSurname()));
				
				Doctor d = dr.findById(rs.getInt("doctor"));
				w.setDoctor(new DoctorDTO(d.getName(), d.getSurname()));
				w.setDuration(rs.getInt("duration"));
				w.setPrice(rs.getDouble("price"));
				
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
