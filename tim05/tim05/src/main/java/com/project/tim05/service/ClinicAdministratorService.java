package com.project.tim05.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.tim05.dto.BasicReportDTO;
import com.project.tim05.dto.DoctorDTO;
import com.project.tim05.model.Appointment;
import com.project.tim05.model.Authority;
import com.project.tim05.model.Clinic;
import com.project.tim05.model.ClinicAdministrator;
import com.project.tim05.model.Doctor;
import com.project.tim05.repository.AppointmentRespository;
import com.project.tim05.repository.ClinicAdministratorRespository;
import com.project.tim05.repository.DoctorRepository;

@Service
public class ClinicAdministratorService {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthorityService authService;

	@Autowired
	private ClinicAdministratorRespository car;
	
	@Autowired
	private DoctorRepository dr;
	
	@Autowired
	private AppointmentRespository ar;
	
	public int addClinicAdministrator(ClinicAdministrator admincl) {
		
		try {
			admincl.setPassword(passwordEncoder.encode(admincl.getPassword()));
			List<Authority> auth = authService.findByname("ROLE_CLINIC_ADMIN");
			admincl.setAuthorities(auth);

			car.save(admincl);
			
		} catch (Exception e) {
			
			return 0;
		}
		
		return 1;		
	}
	
	public int editClinicAdministrator(ClinicAdministrator admincl) {
	
		int flag = 0;
		try {
			
			//Connection connection = DriverManager.getConnection("jdbc:postgresql://ec2-54-247-89-181.eu-west-1.compute.amazonaws.com:5432/d1d2a9u0egu6ja", "xslquaksjvvetl", "791a6dd69c36471adccf1118066dae6841cf2b7145d82831471fdd6640e5d99a");
			Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "");
	        
	        if(admincl.getPassword().length() != 0)
	        {
	        	String query = "UPDATE users set password = ?, name = ?, surname = ?, last_password_reset_date = ? WHERE email = ?;";
	 	        PreparedStatement ps = connection.prepareStatement(query);
	 	        ps.setString(1, passwordEncoder.encode(admincl.getPassword()));
	 			ps.setString(2, admincl.getName());
	 			ps.setString(3, admincl.getSurname());
	 			ps.setTimestamp(4, new Timestamp(new Date().getTime()));
	 			ps.setString(5, admincl.getEmail());
	 		
	 			flag = ps.executeUpdate();
	 			ps.close();
	        }
	        else
	        {
	        	String query = "UPDATE users set name = ?, surname = ? WHERE email = ?;";
	 	        PreparedStatement ps = connection.prepareStatement(query);
	 			ps.setString(1, admincl.getName());
	 			ps.setString(2, admincl.getSurname());
	 			ps.setString(3, admincl.getEmail());
	 		
	 			flag = ps.executeUpdate();
	 			ps.close();
	        	
	        }
	        
	        
	       
			
			connection.close();
			
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return flag;
		}	
			
		return flag;		
	}
	
	public List<ClinicAdministrator> getClinicAdministrators(){
		return car.findAll();
	}
	
	public ClinicAdministrator getClinicAdmin(String email)
	{
		try {
			ClinicAdministrator ca = car.findByEmail(email);
			ca.setClinic(initializeAndUnproxy.initAndUnproxy(ca.getClinic()));
			return ca;
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public List<String> getClinicAdminsClinic(int clinic_id)
	{
		List<String> emails = new ArrayList<String>();
		try {
			//Connection conn = DriverManager.getConnection("jdbc:postgresql://ec2-54-247-89-181.eu-west-1.compute.amazonaws.com:5432/d1d2a9u0egu6ja", "xslquaksjvvetl", "791a6dd69c36471adccf1118066dae6841cf2b7145d82831471fdd6640e5d99a");
			Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "");
			PreparedStatement st = conn.prepareStatement("SELECT * FROM public.clinic_admins ca LEFT JOIN public.users c ON ca.user_id = c.user_id where clinic = ?;");
			
			
			st.setInt(1, clinic_id);
			ResultSet rs = st.executeQuery();
			
			while(rs.next())
			{
				emails.add(rs.getString("email"));
				
			}
			
			
			conn.close();
			rs.close();
			st.close();		
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return emails;
		}
	
		return emails;
		
	}
	
	public ClinicAdministrator getClinicAdmin(int id) {
		return car.findById(id).orElse(null);
	}

	public BasicReportDTO getClinicInfo(Clinic clinic) {
		BasicReportDTO report = new BasicReportDTO();
		//AVG RATE clinic
		report.setClinicAverageRate(clinic.getAverageRating());
		report.setClinic_name(clinic.getName());
		return report;
	}

	public BasicReportDTO getDoctorInfo(Clinic clinic) {
		BasicReportDTO report = new BasicReportDTO();
			
		try {
			//Connection conn = DriverManager.getConnection("jdbc:postgresql://ec2-54-247-89-181.eu-west-1.compute.amazonaws.com:5432/d1d2a9u0egu6ja", "xslquaksjvvetl", "791a6dd69c36471adccf1118066dae6841cf2b7145d82831471fdd6640e5d99a");
			Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "");
			PreparedStatement st = conn.prepareStatement("Select * from doctors where clinic = ?;");
			st.setInt(1, clinic.getId());
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next())
			{
				DoctorDTO dtodoctor = new DoctorDTO();
				Doctor d = initializeAndUnproxy.initAndUnproxy(dr.findById(rs.getInt("user_id")));
				dtodoctor.setAverage_rate(d.getRate());
				dtodoctor.setEmail(d.getEmail());
				dtodoctor.setName(d.getName());
				dtodoctor.setSurname(d.getSurname());
				//avg rates doctors
				report.getDoctors().add(dtodoctor);
			}
			
			conn.close();
			rs.close();
			st.close();		
			return report;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public BasicReportDTO getIncomes(Clinic clinic, BasicReportDTO dto) {
		BasicReportDTO report = new BasicReportDTO();
		
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Date from = null;
			Date to = null;
			try {
				String new_from = "";
				new_from += dto.getFrom().substring(0,10) + " " + dto.getFrom().substring(11,19);
				String new_to = "";
				new_to += dto.getTo().substring(0,10) + " " + dto.getTo().substring(11,19);
				from = formatter.parse(new_from);
				to = formatter.parse(new_to);
			}catch(Exception e) {
				e.printStackTrace();
				return null;
			}
			//Connection conn = DriverManager.getConnection("jdbc:postgresql://ec2-54-247-89-181.eu-west-1.compute.amazonaws.com:5432/d1d2a9u0egu6ja", "xslquaksjvvetl", "791a6dd69c36471adccf1118066dae6841cf2b7145d82831471fdd6640e5d99a");
			Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "");
			PreparedStatement st = conn.prepareStatement("Select * from appointments where clinic = ? and date_time >= ? and date_time <= ?;");
			st.setInt(1, clinic.getId());
			st.setTimestamp(2, new Timestamp(from.getTime()));
			st.setTimestamp(3, new Timestamp(to.getTime()));
			
			ResultSet rs = st.executeQuery();
			double income = 0.0;
			while(rs.next())
			{
				income += rs.getDouble("price");
			}
			//INCOME clinic
			report.setIncome(income);
			
			conn.close();
			rs.close();
			st.close();
			return report;
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	
	
}


