package com.project.tim05.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.tim05.dto.DiseaseDTO;
import com.project.tim05.dto.MedicineDTO;
import com.project.tim05.dto.AppointmentDTO;
import com.project.tim05.dto.AppointmentTypeDTO;
import com.project.tim05.dto.DoctorDTO;
import com.project.tim05.dto.HallDTO;
import com.project.tim05.dto.PatientDTO;
import com.project.tim05.model.Appointment;
import com.project.tim05.model.AppointmentType;
import com.project.tim05.model.Authority;
import com.project.tim05.model.Clinic;
import com.project.tim05.model.Disease;
import com.project.tim05.model.Doctor;
import com.project.tim05.model.Hall;
import com.project.tim05.model.MedicalRecord;
import com.project.tim05.model.Patient;
import com.project.tim05.repository.AppointmentRespository;
import com.project.tim05.repository.AppointmentTypeRespository;
import com.project.tim05.repository.DoctorRepository;
import com.project.tim05.repository.HallRepository;
import com.project.tim05.repository.PatientRepository;

@Service
public class PatientService {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AuthorityService authService;

	@Autowired
	private ClinicService cs;

	@Autowired
	private PatientRepository pa;
	
	@Autowired
	private AppointmentRespository ar;
	
	@Autowired
	private DoctorRepository dr;
	
	@Autowired
	private HallRepository hr;
	
	@Autowired
	private AppointmentTypeRespository atr;

	public int editPatient(Patient patient) {
		int flag = 0;
		try {
			// Connection connection =
			// DriverManager.getConnection("jdbc:postgresql://ec2-54-247-89-181.eu-west-1.compute.amazonaws.com:5432/d1d2a9u0egu6ja",
			// "xslquaksjvvetl",
			// "791a6dd69c36471adccf1118066dae6841cf2b7145d82831471fdd6640e5d99a");
			Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres",
					"");

			if (patient.getPassword().length() != 0) {
				patient.setPassword(passwordEncoder.encode(patient.getPassword()));
				String query = "UPDATE users set password = ?, name = ?, surname = ? where user_id = ?;";
				PreparedStatement ps = connection.prepareStatement(query);
				ps.setString(1, patient.getPassword());
				ps.setString(2, patient.getName());
				ps.setString(3, patient.getSurname());
				ps.setInt(4, patient.getId());
				flag = ps.executeUpdate();
				ps.close();

				String query2 = "UPDATE patients set address = ?, city = ?, country = ?, phone_number = ? where user_id = ?;";
				PreparedStatement ps2 = connection.prepareStatement(query2);
				ps2.setString(1, patient.getAddress());
				ps2.setString(2, patient.getCity());
				ps2.setString(3, patient.getCountry());
				ps2.setString(4, patient.getPhone_number());
				ps2.setInt(5, patient.getId());
				flag = ps2.executeUpdate();
				ps2.close();

				connection.close();
				return 1;
			} else {
				String query = "UPDATE users set name = ?, surname = ? where user_id = ?;";
				PreparedStatement ps = connection.prepareStatement(query);
				ps.setString(1, patient.getName());
				ps.setString(2, patient.getSurname());
				ps.setInt(3, patient.getId());
				flag = ps.executeUpdate();
				ps.close();

				String query2 = "UPDATE patients set address = ?, city = ?, country = ?, phone_number = ? where user_id = ?;";
				PreparedStatement ps2 = connection.prepareStatement(query2);
				ps2.setString(1, patient.getAddress());
				ps2.setString(2, patient.getCity());
				ps2.setString(3, patient.getCountry());
				ps2.setString(4, patient.getPhone_number());
				ps2.setInt(5, patient.getId());
				flag = ps2.executeUpdate();
				ps2.close();

				connection.close();
				return 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// return flag;
		}
		return flag;
	}

	public List<Patient> getPatients() {
		return pa.findAll();
	}
	
	//metoda prima parametar po kom se pretrazuje, vrednost tog parametra i id klinike pozivaoca
	public List<PatientDTO> searchPatients(String param, String value, int clinic_id) {
		List<PatientDTO> patients = new ArrayList<PatientDTO>();

		PreparedStatement st = null;
		Connection conn = null;

		// provera po kom parametru treba da se radi pretrazivanje
		try {
			// Connection conn =
			// DriverManager.getConnection("jdbc:postgresql://ec2-54-247-89-181.eu-west-1.compute.amazonaws.com:5432/d1d2a9u0egu6ja",
			// "xslquaksjvvetl",
			// "791a6dd69c36471adccf1118066dae6841cf2b7145d82831471fdd6640e5d99a");
			conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "");

			if (param.equals("insurance_number")) {
				st = conn.prepareStatement("SELECT * FROM public.patients p\r\n"
						+ "LEFT JOIN public.patients_clinics c ON p.user_id = c.patient_user_id\r\n"
						+ "where clinics_clinic_id = ? and insurance_number = ?::varchar");
				
				st.setInt(1, clinic_id);
				st.setInt(2, Integer.parseInt(value));
				
			} else if (param.equals("name")) {
				st = conn.prepareStatement("SELECT * FROM public.patients_clinics p LEFT JOIN public.users c \r\n"
						+ "ON p.patient_user_id = c.user_id \r\n" + "where clinics_clinic_id = ? and name like ?");
			
				st.setInt(1, clinic_id);
				st.setString(2, value);
				
			} else {
				st = conn.prepareStatement("SELECT * FROM public.patients_clinics p LEFT JOIN public.users c \r\n"
						+ "ON p.patient_user_id = c.user_id \r\n" + "where clinics_clinic_id = ? and surname like ?");
			
				st.setInt(1, clinic_id);
				st.setString(2, value);
			}
			
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				PatientDTO pdto = new PatientDTO();
				Patient p = pa.findById(rs.getInt("patient_user_id"));
				pdto.setName(p.getName());
				pdto.setSurname(p.getSurname());
				pdto.setInsurance_number(p.getInsurance_number());
				pdto.setEmail(p.getEmail());
				pdto.setCity(p.getCity());
				patients.add(pdto);
			}

			rs.close();
			st.close();
			conn.close();

		} catch (Exception e) {
			return patients;
		}

		return patients;

	}
	
	public List<PatientDTO> filterPatients(String filter, List<PatientDTO> patients_to_filter){
		List<PatientDTO> dtos = new ArrayList<PatientDTO>();
		for (PatientDTO pdto : patients_to_filter) {
			if(pdto.getCity().equalsIgnoreCase(filter)) {
				dtos.add(pdto);
			}
		}
		return dtos;
	}

	public int getPatientId(String email) {
		int id = -1;
		try {
			Patient p = pa.findByEmail(email);
			id = p.getId();

		} catch (Exception e) {

			return id;
		}
		return id;
	}
	
	public Patient getPatientById(int id) {
		return pa.findById(id);
	}

	public int addPatient(Patient patient) {
		try {
			Date date = new Date();
			List<Authority> auth = authService.findByname("ROLE_PATIENT");
			patient.setAuthorities(auth);
			patient.setPassword(patient.getPassword());
			patient.setLastPasswordResetDate(new Timestamp(date.getTime()));
			
			MedicalRecord mr = new MedicalRecord();
			mr.getDiseases().add(new Disease("Blood type", mr));
			mr.getDiseases().add(new Disease("Height", mr));
			mr.getDiseases().add(new Disease("Weight", mr));
			mr.getDiseases().add(new Disease("Alergy", mr));
			patient.setMedicalRecord(mr);

			patient.getClinics().add(cs.getClinicbyId(1));

			pa.save(patient);

		} catch (Exception e) {

			return 0;
		}

		return 1;
	}

	public List<Patient> getPatients(Clinic cl) {
		List<Patient> patients = new ArrayList<Patient>();

		try {
			// Connection conn =
			// DriverManager.getConnection("jdbc:postgresql://ec2-54-247-89-181.eu-west-1.compute.amazonaws.com:5432/d1d2a9u0egu6ja",
			// "xslquaksjvvetl",
			// "791a6dd69c36471adccf1118066dae6841cf2b7145d82831471fdd6640e5d99a");
			Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "");

			PreparedStatement st = conn
					.prepareStatement("SELECT * FROM patients_clinics WHERE patients_clinics.clinics_clinic_id = ?");
			st.setInt(1, cl.getId());
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				patients.add(pa.findById(rs.getInt("patient_user_id")));
			}

			rs.close();
			st.close();
			conn.close();

		} catch (SQLException e) {

			System.out.println(e.getMessage());
			return patients;
		}

		return patients;

	}
	
	public List<String> getCities(int clinic_id) {
		List<String> cities = new ArrayList<String>();
	
		Clinic c = cs.getClinicbyId(clinic_id);
		
		
		List<Patient> patients = getPatients(c);

		for (Patient pdto : patients) {
			if (!cities.contains(pdto.getCity())) {
				cities.add(pdto.getCity());
			}
		}
		
		return cities;

	}

	public Patient getPatient(String email) {
		try {
			Patient ca = pa.findByEmail(email);
			return ca;

		} catch (Exception e) {

			return null;
		}
	}

	public int activateAccount(String email, int id) {
		int flag = 0;
		try {

			// Connection connection =
			// DriverManager.getConnection("jdbc:postgresql://ec2-54-247-89-181.eu-west-1.compute.amazonaws.com:5432/d1d2a9u0egu6ja",
			// "xslquaksjvvetl",
			// "791a6dd69c36471adccf1118066dae6841cf2b7145d82831471fdd6640e5d99a");
			Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres",
					"");

			String query = "UPDATE users set enabled = ? WHERE email = ? and user_id = ?;";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setBoolean(1, true);
			ps.setString(2, email);
			ps.setInt(3, id);

			flag = ps.executeUpdate();
			ps.close();

			connection.close();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return flag;
		}

		return flag;
	}


	public List<DiseaseDTO> getDisease(String email) {
		List<DiseaseDTO> dtos = new ArrayList<DiseaseDTO>();
		try {
			Patient p = pa.findByEmail(email);
			p.setMedicalRecord(initializeAndUnproxy.initAndUnproxy(p.getMedicalRecord()));
			// Connection conn =
			// DriverManager.getConnection("jdbc:postgresql://ec2-54-247-89-181.eu-west-1.compute.amazonaws.com:5432/d1d2a9u0egu6ja",
			// "xslquaksjvvetl",
			// "791a6dd69c36471adccf1118066dae6841cf2b7145d82831471fdd6640e5d99a");
			Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "");

			PreparedStatement st = conn
					.prepareStatement("SELECT * FROM diseases WHERE medical_record = ?");
			st.setInt(1, p.getMedicalRecord().getId());
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				dtos.add(new DiseaseDTO(rs.getString("name"), rs.getString("value")));
			}

			rs.close();
			st.close();
			conn.close();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		
		return dtos;
	}

	public int setDisease(List<DiseaseDTO> ds, String email) {
		int flag = 0;
		ds.remove(4);
		for (DiseaseDTO d : ds) {
			try {
				Patient p = pa.findByEmail(email);
				p.setMedicalRecord(initializeAndUnproxy.initAndUnproxy(p.getMedicalRecord()));
				// Connection conn =
				// DriverManager.getConnection("jdbc:postgresql://ec2-54-247-89-181.eu-west-1.compute.amazonaws.com:5432/d1d2a9u0egu6ja",
				// "xslquaksjvvetl",
				// "791a6dd69c36471adccf1118066dae6841cf2b7145d82831471fdd6640e5d99a");
				Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "");

				String query = "UPDATE diseases set value = ? WHERE name = ? and medical_record = ?;";
				PreparedStatement ps = conn.prepareStatement(query);
				ps.setString(1, d.getValue());
				ps.setString(2, d.getName());
				ps.setInt(3, p.getMedicalRecord().getId());

				flag = ps.executeUpdate();
				ps.close();

				conn.close();
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
			
		}
		
		return flag;
	}

	public int setMedicine(List<MedicineDTO> ms) {
		
		int flag = 0;
		int appId = ms.get(ms.size()-1).getId();
		ms.remove(ms.size()-1);
		for (MedicineDTO d : ms) {
			try {
				// Connection conn =
				// DriverManager.getConnection("jdbc:postgresql://ec2-54-247-89-181.eu-west-1.compute.amazonaws.com:5432/d1d2a9u0egu6ja",
				// "xslquaksjvvetl",
				// "791a6dd69c36471adccf1118066dae6841cf2b7145d82831471fdd6640e5d99a");
				Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "");

				String query = "INSERT INTO appointment_medicines (appointment, medicine_medicine_id) VALUES (?, ?)";
				PreparedStatement ps = conn.prepareStatement(query);
				ps.setInt(1, appId);
				ps.setInt(2, d.getId());

				flag = ps.executeUpdate();
				ps.close();

				conn.close();
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
			
		}
		
		return flag;
	}
	public ArrayList<AppointmentDTO> getIncomingAppointments(Patient p) {
		ArrayList<AppointmentDTO> result = new ArrayList<AppointmentDTO>();
		for(Appointment a : p.getAppointments()) {
			if(a.isFinished() == false) {
				AppointmentDTO dto = new AppointmentDTO();
				AppointmentType at = initializeAndUnproxy.initAndUnproxy(a.getAppointmentType());
				Doctor d = initializeAndUnproxy.initAndUnproxy(a.getDoctor());
				Hall h = initializeAndUnproxy.initAndUnproxy(a.getHall());
				dto.setDate(a.getDateTime().toString().split(" ")[0]);
				dto.setTime(a.getDateTime().toString().split(" ")[1].split("\\.")[0].substring(0,5));
				dto.setDuration(a.getDuration());
				dto.setPrice(a.getPrice());
				DoctorDTO ddto = new DoctorDTO();
				ddto.setName(d.getName());
				ddto.setSurname(d.getSurname());
				dto.setDoctor(ddto);
				HallDTO hdto = new HallDTO();
				hdto.setName(h.getName());
				hdto.setNumber(h.getNumber());
				dto.setHall(hdto);
				AppointmentTypeDTO atdto = new AppointmentTypeDTO();
				atdto.setName(at.getName());
				dto.setAppointmentType(atdto);
				result.add(dto);
			}
		}
		return result;
	}

}
