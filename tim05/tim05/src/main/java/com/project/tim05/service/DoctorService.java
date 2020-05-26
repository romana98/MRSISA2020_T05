package com.project.tim05.service;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.tim05.dto.DoctorDTO;
import com.project.tim05.model.Authority;
import com.project.tim05.model.Clinic;
import com.project.tim05.model.Doctor;
import com.project.tim05.model.Nurse;
import com.project.tim05.repository.DoctorRepository;

@Service
public class DoctorService {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthorityService authService;
	
	@Autowired
	private DoctorRepository dr;
	
	public int addDoctor(Doctor doctor) {
		int flag = 0;
		try {
			doctor.setPassword(passwordEncoder.encode(doctor.getPassword()));
			List<Authority> auth = authService.findByname("ROLE_DOCTOR");
			doctor.setAuthorities(auth);
			dr.save(doctor);	
			flag = 1;
		}
		catch(Exception e) {
			return flag;
		}
		return flag;
		
	}
	
	public List<Doctor> getDoctors(){
		return dr.findAll();
	}
	
	public int editProfile(Doctor doctor) {
		int flag = 0;
		try {
			//Connection connection = DriverManager.getConnection("jdbc:postgresql://ec2-54-247-89-181.eu-west-1.compute.amazonaws.com:5432/d1d2a9u0egu6ja", "xslquaksjvvetl", "791a6dd69c36471adccf1118066dae6841cf2b7145d82831471fdd6640e5d99a");
			Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "");
	        
	        if(doctor.getPassword().length()!=0) {
				doctor.setPassword(passwordEncoder.encode(doctor.getPassword()));
	        	String query = "UPDATE users set password = ?, name = ?, surname = ? WHERE email = ? and active = TRUE;";
		        PreparedStatement ps = connection.prepareStatement(query);
				ps.setString(1, doctor.getPassword());
				ps.setString(2, doctor.getName());
				ps.setString(3, doctor.getSurname());
				ps.setString(4, doctor.getEmail());
				flag = ps.executeUpdate();
				
				ps.close();
				connection.close();
				return flag;
	        }else {
	        	String query = "UPDATE users set name = ?, surname = ? WHERE email = ? and active = TRUE;";
		        PreparedStatement ps = connection.prepareStatement(query);
				ps.setString(1, doctor.getName());
				ps.setString(2, doctor.getSurname());
				ps.setString(3, doctor.getEmail());
				flag = ps.executeUpdate();
				
				ps.close();
				connection.close();
				return flag;
	        }
			
		} catch (SQLException e) {
			return flag;
		}	
	}
	
	public Doctor getDoctorbyID(int id) {
		return dr.findById(id);
	}
	
	//metoda vraca doktore koji pripadaju klinici sa id clinic_Id koji vrse preglede tipa appointment_id
	public ArrayList<DoctorDTO> getDoctors(int clinic_id, int appointment_id){
		try {
			//Connection connection = DriverManager.getConnection("jdbc:postgresql://ec2-54-247-89-181.eu-west-1.compute.amazonaws.com:5432/d1d2a9u0egu6ja", "xslquaksjvvetl", "791a6dd69c36471adccf1118066dae6841cf2b7145d82831471fdd6640e5d99a");
			Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "");
	        
			String query = "SELECT * FROM doctors WHERE appointment_type = ? and clinic= ? and active = TRUE;";
	        PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, appointment_id);
			ps.setInt(2, clinic_id);
			ResultSet rs = ps.executeQuery();
			ArrayList<DoctorDTO> doctors = new ArrayList<DoctorDTO>();
			while(rs.next()) {
				Doctor dr1 = dr.findById(rs.getInt("user_id"));
				DoctorDTO drdto = new DoctorDTO();
				drdto.setName(dr1.getName());
				drdto.setId(dr1.getId());
				drdto.setSurname(dr1.getSurname());
				doctors.add(drdto);
			}
			connection.close();
			ps.close();
			rs.close();
			return doctors;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}	
		
	}
	//vraca doktore koji pripadaju klinici sa id -> clinic_Id
	public ArrayList<DoctorDTO> getClinicsDoctors(int clinic_id){
		try {
			//Connection connection = DriverManager.getConnection("jdbc:postgresql://ec2-54-247-89-181.eu-west-1.compute.amazonaws.com:5432/d1d2a9u0egu6ja", "xslquaksjvvetl", "791a6dd69c36471adccf1118066dae6841cf2b7145d82831471fdd6640e5d99a");
			Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "");
	        
			String query = "SELECT * FROM doctors WHERE clinic= ? and active = TRUE;";
	        PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, clinic_id);
			ResultSet rs = ps.executeQuery();
			ArrayList<DoctorDTO> doctors = new ArrayList<DoctorDTO>();
			while(rs.next()) {
				Doctor dr1 = dr.findById(rs.getInt("user_id"));
				DoctorDTO drdto = new DoctorDTO();
				drdto.setName(dr1.getName());
				drdto.setId(dr1.getId());
				drdto.setSurname(dr1.getSurname());
				drdto.setEmail(dr1.getEmail());
				drdto.setAppointmentTypeName(dr1.getAppointmentType().getName());
				doctors.add(drdto);
			}
			connection.close();
			ps.close();
			rs.close();
			return doctors;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}	
		
	}
	//metoda proverava da li pacijent sa datim id-jem ima zakazanih pregleda i ako nema brise ga
	//vraca broj izmenjenih redova, 0 ako nije uspesno
	public int deleteDoctor(int id) {
		int success = 0;
		try {
			//Connection connection = DriverManager.getConnection("jdbc:postgresql://ec2-54-247-89-181.eu-west-1.compute.amazonaws.com:5432/d1d2a9u0egu6ja", "xslquaksjvvetl", "791a6dd69c36471adccf1118066dae6841cf2b7145d82831471fdd6640e5d99a");
			Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "");
	        
	        String query = "UPDATE doctors set active = FALSE WHERE user_id = ?";
	        PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, id);
			
			success = ps.executeUpdate();
			
			connection.close();
			ps.close();
			
		} catch (SQLException e) {
			
			success = 0;
		}
		
		return success;
	}
	
	public Doctor getDoctor(String email) {
		return dr.findByEmail(email);
	}
	
	public Clinic getClinic(String email) {
		Doctor d = dr.findByEmail(email);
		Clinic c = initializeAndUnproxy.initAndUnproxy(d.getClinic());
		return c;
	}
	
	public List<DoctorDTO> searchDoctors(String param, String value, int clinic_id){
		List<DoctorDTO> doctors = new ArrayList<DoctorDTO>();

		PreparedStatement st = null;
		Connection conn = null;

		// provera po kom parametru treba da se radi pretrazivanje
		try {
			// Connection conn =
			// DriverManager.getConnection("jdbc:postgresql://ec2-54-247-89-181.eu-west-1.compute.amazonaws.com:5432/d1d2a9u0egu6ja",
			// "xslquaksjvvetl",
			// "791a6dd69c36471adccf1118066dae6841cf2b7145d82831471fdd6640e5d99a");
			conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "");

			if (param.equals("name")) {
				st = conn.prepareStatement("SELECT * FROM public.doctors d LEFT JOIN public.users c \r\n" + 
						"ON d.user_id = c.user_id where clinic = ? and name like ?;");
			
			} else if (param.equals("surname")){
				st = conn.prepareStatement("SELECT * FROM public.doctors d LEFT JOIN public.users c \r\n" + 
						"ON d.user_id = c.user_id where clinic = ? and surname like ?;");
			
			}
			else {
				st = conn.prepareStatement("SELECT * FROM public.doctors d LEFT JOIN public.users c \r\n" + 
						"ON d.user_id = c.user_id where clinic = ? and email like ?;");
			}
			
			st.setInt(1, clinic_id);
			st.setString(2, "%" + value + "%");
			
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				DoctorDTO ddto = new DoctorDTO();
				Doctor d = dr.findById(rs.getInt("user_id"));
				ddto.setId(d.getId());
				ddto.setName(d.getName());
				ddto.setSurname(d.getSurname());
				ddto.setEmail(d.getEmail());
				ddto.setAppointmentTypeName(d.getAppointmentType().getName());
				doctors.add(ddto);
			}

			rs.close();
			st.close();
			conn.close();

		} catch (Exception e) {
			return doctors;
		}

		return doctors;
	}
	
}
