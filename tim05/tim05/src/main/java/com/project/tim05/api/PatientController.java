package com.project.tim05.api;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.tim05.dto.AppointmentDTO;
import com.project.tim05.dto.AppointmentTypeDTO;
import com.project.tim05.dto.DiseaseDTO;
import com.project.tim05.dto.DoctorDTO;
import com.project.tim05.dto.MedicineDTO;
import com.project.tim05.dto.PatientClinicsDTO;
import com.project.tim05.dto.PatientDTO;
import com.project.tim05.model.Appointment;
import com.project.tim05.model.Clinic;
import com.project.tim05.model.Disease;
import com.project.tim05.model.Doctor;
import com.project.tim05.model.MedicalStaff;
import com.project.tim05.model.Medicine;
import com.project.tim05.model.Patient;
import com.project.tim05.model.RegistrationRequest;
import com.project.tim05.model.User;
import com.project.tim05.service.AppointmentService;
import com.project.tim05.service.ClinicService;
import com.project.tim05.service.DoctorService;
import com.project.tim05.service.EmailService;
import com.project.tim05.service.NurseService;
import com.project.tim05.service.PatientService;
import com.project.tim05.service.RegistrationRequestService;
import com.project.tim05.service.UserService;

@CrossOrigin(origins = "https://localhost:4200")

@RequestMapping("/patients")
@RestController
public class PatientController<T> {

	@Autowired
	private UserService userService;

	@Autowired
	private AuthenticationManager authenticationManager;

	private final PatientService ps;
	private final AppointmentService as;
	private final RegistrationRequestService rrs;
	private final EmailService es;
	private final ClinicService cs;
	private final DoctorService ds;
	private final NurseService ns;

	@Autowired
	public PatientController(DoctorService ds, NurseService ns, AppointmentService as, PatientService ps, RegistrationRequestService rrs, EmailService es,
			ClinicService cs) {
		this.ps = ps;
		this.as = as;
		this.rrs = rrs;
		this.es = es;
		this.cs = cs;
		this.ds = ds;
		this.ns = ns;
	}

	@GetMapping("/getPatients")
	@PreAuthorize("hasRole('CLINIC_CENTER_ADMIN')")
	public List<Patient> getPatients() {
		return ps.getPatients();
	}
	
	@GetMapping("/getDisease")
	@PreAuthorize("hasRole('DOCTOR') || hasRole('NURSE')")
	public List<DiseaseDTO> getDisease(@RequestParam String email) {
		return ps.getDisease(email);
	}
	
	@GetMapping("/getDiseasePatient")
	@PreAuthorize("hasRole('PATIENT')")
	public List<DiseaseDTO> getDiseasePatient() {
		Authentication current = SecurityContextHolder.getContext().getAuthentication();
		Patient currentUser = (Patient) current.getPrincipal();
		
		return ps.getDisease(currentUser.getEmail());
	}
	
	@PostMapping("/setMedicine")
	@PreAuthorize("hasRole('DOCTOR')")
	public ResponseEntity<T> setMedicine(@RequestBody List<MedicineDTO> ms) {
		 int flag = ps.setMedicine(ms);
		
		if (flag == 0) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(null);
		}
	}
	
	@GetMapping("/canAccessMedicalRecord")
	@PreAuthorize("hasRole('DOCTOR') || hasRole('NURSE')")
	public ResponseEntity<DiseaseDTO> canAccessMedicalRecord(@RequestParam String email) {
		Authentication current = SecurityContextHolder.getContext().getAuthentication();
		MedicalStaff currentUser = (MedicalStaff) current.getPrincipal();
		
		String s = "nope";
		
		if(currentUser.getClass() == Doctor.class)
			s = ds.canAccess(email, currentUser.getId());
		else
		{
			s = ns.canAccess(email, currentUser.getId());
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(new DiseaseDTO(s));
	}
	
	@PostMapping("/setDisease")
	@PreAuthorize("hasRole('DOCTOR')")
	public ResponseEntity<T> setDisease(@RequestBody List<DiseaseDTO> ds) {
		DiseaseDTO d = ds.get(4);
		 int flag = ps.setDisease(ds, d.getName());
		
		if (flag == 0) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(null);
		}
	}
	
	@PostMapping("/setAppointment")
	@PreAuthorize("hasRole('DOCTOR')")
	public ResponseEntity<T> setAppointment(@RequestBody AppointmentDTO ap) {
	
		 int flag = as.setAppointment(ap);
		
		if (flag == 0) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(null);
		}
	}
	
	@GetMapping("/getPatientDoctor")
	@PreAuthorize("hasRole('DOCTOR') || hasRole('NURSE')")
	public ResponseEntity<PatientDTO> getPatient(@RequestParam String email) {
		Authentication current = SecurityContextHolder.getContext().getAuthentication();
		MedicalStaff currentUser = (MedicalStaff) current.getPrincipal();
		
		
		Patient p = ps.getPatient(email);
		if(p == null)
			return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
		else {
			PatientDTO pdto = new PatientDTO();
			pdto.setAddress(p.getAddress());
			pdto.setCity(p.getCity());
			pdto.setCountry(p.getCountry());
			pdto.setEmail(p.getEmail());
			pdto.setInsurance_number(p.getInsurance_number());
			pdto.setName(p.getName());
			pdto.setPhone_number(p.getPhone_number());
			pdto.setSurname(p.getSurname());
			
			
			if(currentUser.getClass() == Doctor.class)
				pdto.setPassword(ds.canStartAppointment(currentUser.getId(), p.getId()));
			else
				pdto.setPassword("f");
			return ResponseEntity.status(HttpStatus.OK).body(pdto);
		} 
	}
	
	@GetMapping("/canStartApp")
	@PreAuthorize("hasRole('DOCTOR')")
	public ResponseEntity<String> canStartApp(@RequestParam String p_id, String a_id) {
		Authentication current = SecurityContextHolder.getContext().getAuthentication();
		Doctor currentUser = (Doctor) current.getPrincipal();
			String s = ds.canStartAppointmentCalendar(currentUser.getId(), Integer.parseInt(p_id), Integer.parseInt(a_id));
			return ResponseEntity.status(HttpStatus.OK).body(s);
	}
	
	

	@PostMapping("/editPatient")
	@PreAuthorize("hasRole('PATIENT')")
	public ResponseEntity<T> editPatient(@Valid @RequestBody PatientDTO patient) {

		Authentication current = SecurityContextHolder.getContext().getAuthentication();
		Patient currentUser = (Patient) current.getPrincipal();

		if (!currentUser.getEmail().equals(patient.getEmail()))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

		if (patient.getPassword().length() > 0 && patient.getPassword().length() < 8)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

		Patient p = new Patient();
		p.setAddress(patient.getAddress());
		p.setCity(patient.getCity());
		p.setCountry(patient.getCountry());
		p.setEmail(patient.getEmail());
		p.setInsurance_number(patient.getInsurance_number());
		p.setName(patient.getName());
		p.setPassword(patient.getPassword());
		p.setPhone_number(patient.getPhone_number());
		p.setSurname(patient.getSurname());
		p.setId(currentUser.getId());
		int flag = ps.editPatient(p);

		if (patient.getPassword().length() != 0 && flag != 0) {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(currentUser.getEmail(), patient.getPassword()));

			SecurityContextHolder.getContext().setAuthentication(authentication);

		}

		if (flag == 0) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(null);
		}

	}

	static class activateAccount {
		public int id;
		public String email;
	}

	@PostMapping("/activate")
	public ResponseEntity<T> activate(@RequestBody activateAccount aa) {

		User existUser = this.userService.findByEmail(aa.email);
		if (existUser == null) {
			ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}

		int flag = ps.activateAccount(aa.email, aa.id);

		if (flag == 0)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		else {
			return ResponseEntity.status(HttpStatus.OK).body(null);
		}

	}

	@PostMapping("/addPatient")
	@PreAuthorize("hasRole('CLINIC_CENTER_ADMIN')")
	public ResponseEntity<T> addPatient(@Valid @RequestBody PatientDTO p) {

		User existUser = this.userService.findByEmail(p.getEmail());
		if (existUser != null) {
			ResponseEntity.status(HttpStatus.CONFLICT).body(null);
		}

		int flag = ps.addPatient(new Patient(p.getEmail(), p.getPassword(), p.getName(), p.getSurname(), p.getAddress(),
				p.getCity(), p.getCountry(), p.getPhone_number(), p.getInsurance_number()));
		int flag1 = rrs.removeRegistrationRequest(
				new RegistrationRequest(p.getEmail(), p.getPassword(), p.getName(), p.getSurname(), p.getAddress(),
						p.getCity(), p.getCountry(), p.getPhone_number(), p.getInsurance_number()));

		if (flag1 == 0)
			return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
		else if (flag == 0)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		else {
			try {
				es.sendAcceptanceeMail(p.getEmail(), ps.getPatientId(p.getEmail()));
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

			}
			return ResponseEntity.status(HttpStatus.OK).body(null);
		}

	}

	
	@GetMapping("/searchDoctors")
	@PreAuthorize("hasRole('PATIENT')")
	public ResponseEntity<List<DoctorDTO>> searchDoctors(@RequestParam String doctorName, String doctorSurname, String rateFrom, String rateTo, String date, String clinic_id, String appointmentType_id){
		
		
		ArrayList<DoctorDTO> result = new ArrayList<DoctorDTO>();
		
		//doktori 
		ArrayList<Doctor> doctorsClinicsApts = ds.getClinicDoctorsbyAppointmentType(Integer.parseInt(appointmentType_id), Integer.parseInt(clinic_id));
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		java.util.Date date1 = null;
		try {
			date1 = formatter.parse(date);
		} catch (ParseException e) {
			
			e.printStackTrace();
		}

		java.sql.Date sd = new java.sql.Date(date1.getTime());
		
		ArrayList<Doctor> doctorsDate = new ArrayList<Doctor>();
		
		for (Doctor dr : doctorsClinicsApts) {
			if (ds.getAvailableTime(sd, dr).size() > 0) {

				doctorsDate.add(dr);

			}
		}
		
		
		ArrayList<Doctor> searchName = ds.searchDoctorsByParameters(doctorsDate, "name", doctorName);
		ArrayList<Doctor> searchSurname = ds.searchDoctorsByParameters(doctorsDate, "surname", doctorSurname);
		ArrayList<Doctor> searchRateFrom = ds.searchDoctorsByParameters(doctorsDate, "ratefrom", rateFrom);
		ArrayList<Doctor> searchRateTo  = ds.searchDoctorsByParameters(doctorsDate, "rateto", rateTo);
		
		for(Doctor d : doctorsDate) {
			if(ds.checkIfDoctorExists(d, searchName) && ds.checkIfDoctorExists(d, searchSurname) && ds.checkIfDoctorExists(d, searchRateFrom) && ds.checkIfDoctorExists(d, searchRateTo)) {
				DoctorDTO dto = new DoctorDTO();
				dto.setName(d.getName());
				dto.setSurname(d.getSurname());
				dto.setAverage_rate(d.getRate());
				dto.setAvailable_times(ds.getAvailableTime(sd, d));
				result.add(dto);
			}
		}
		
		if (result.size() == 0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
		}
		else {
			return ResponseEntity.ok(result);	
		}
	}
	
	@GetMapping("/getClinics")
	@PreAuthorize("hasRole('PATIENT')")
	public ResponseEntity<List<PatientClinicsDTO>> getClinics(@RequestParam String date, String appointmentType_id, String address_param, String low_rate, String high_rate) {

		ArrayList<Doctor> doctors = ds.getDoctorsbyAppointmentType(Integer.parseInt(appointmentType_id));
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		java.util.Date date1 = null;
		try {
			date1 = formatter.parse(date);
		} catch (ParseException e) {
			
			e.printStackTrace();
		}

		java.sql.Date sd = new java.sql.Date(date1.getTime());

		ArrayList<Doctor> doctors_filtered = new ArrayList<Doctor>();
		ArrayList<Clinic> clinics = new ArrayList<Clinic>();

		for (Doctor dr : doctors) {
			if (ds.getAvailableTime(sd, dr).size() > 0) {
				
				doctors_filtered.add(dr);

				if (!clinics.contains(dr.getClinic())) {
					clinics.add(dr.getClinic());

				}
			}
		}
		ArrayList<Clinic> filteredClinics = new ArrayList<Clinic>();
		if(address_param != null && low_rate != null && high_rate != null) {
			filteredClinics = cs.filterClinicsByParams(clinics, address_param, Integer.parseInt(low_rate), Integer.parseInt(high_rate));
		}else {
			filteredClinics = clinics;
		}		
		
		ArrayList<PatientClinicsDTO> pcdtos = new ArrayList<PatientClinicsDTO>();

		for (Clinic cl : filteredClinics) {
			PatientClinicsDTO pcdto = new PatientClinicsDTO();

			pcdto.setId(cl.getId());
			pcdto.setName(cl.getName());
			pcdto.setAddress(cl.getAddress());
			pcdto.setAvg_rating(0);
			pcdto.setPrice(0);

			pcdtos.add(pcdto);
		}
		
		if (pcdtos.size() == 0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(pcdtos);
		}
		else {
			return ResponseEntity.ok(pcdtos);	
		}
	}

	@GetMapping("/getPatient")
	@PreAuthorize("hasRole('PATIENT')")
	public PatientDTO getData() {

		Authentication current = SecurityContextHolder.getContext().getAuthentication();
		Patient currentUser = (Patient) current.getPrincipal();

		Patient p = ps.getPatient(currentUser.getEmail());

		PatientDTO pdto = new PatientDTO();
		pdto.setAddress(p.getAddress());
		pdto.setCity(p.getCity());
		pdto.setCountry(p.getCountry());
		pdto.setEmail(p.getEmail());
		pdto.setInsurance_number(p.getInsurance_number());
		pdto.setName(p.getName());
		pdto.setPassword(p.getPassword());
		pdto.setPhone_number(p.getPhone_number());
		pdto.setSurname(p.getSurname());
		return pdto;

	}
	
	@GetMapping("/getIncomingAppointments")
	@PreAuthorize("hasRole('PATIENT')")
	public ResponseEntity<ArrayList<AppointmentDTO>> getIncomingAppointments(){
		
		Authentication current = SecurityContextHolder.getContext().getAuthentication();
		Patient currentUser = (Patient) current.getPrincipal();

		Patient p = ps.getPatient(currentUser.getEmail());
	
		ArrayList<AppointmentDTO> dtos = ps.getIncomingAppointments(p);
		
		return ResponseEntity.status(HttpStatus.OK).body(dtos);
		
	
	}

}
