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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.tim05.dto.DoctorDTO;
import com.project.tim05.dto.PatientDTO;
import com.project.tim05.model.AppointmentType;
import com.project.tim05.model.Clinic;
import com.project.tim05.model.Doctor;
import com.project.tim05.model.Patient;
import com.project.tim05.model.RegistrationRequest;
import com.project.tim05.service.AppointmentTypeService;
import com.project.tim05.service.ClinicService;
import com.project.tim05.service.DoctorService;
import com.project.tim05.service.PatientService;
import com.project.tim05.service.RegistrationRequestService;

//@CrossOrigin(origins = "https://localhost:4200")
@CrossOrigin(origins = "https://eclinic05.herokuapp.com")
@RequestMapping("/doctors")
@RestController
public class DoctorController {

	private final DoctorService ds;
	private final AppointmentTypeService ats;
	private final ClinicService cs;
	private final RegistrationRequestService rs;
	private final PatientService ps;

	@Autowired
	public DoctorController(PatientService ps, DoctorService ds, AppointmentTypeService ats, ClinicService cs,
			RegistrationRequestService rs) {
		this.ds = ds;
		this.ats = ats;
		this.cs = cs;
		this.rs = rs;
		this.ps = ps;
	}

	@GetMapping("/getPatients")
	@PreAuthorize("hasRole('DOCTOR')")
	public List<PatientDTO> getPatients() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Doctor ms = (Doctor) authentication.getPrincipal();

		List<Patient> pss = ps.getPatients(ds.getClinic(ms.getEmail()));

		List<PatientDTO> psDTO = new ArrayList<PatientDTO>();
		for (Patient p : pss) {
			psDTO.add(new PatientDTO(p.getEmail(), p.getName(), p.getSurname(), p.getAddress(), p.getCity(),
					p.getCountry(), p.getPhone_number(), p.getInsurance_number()));
		}
		return psDTO;
	}

	@GetMapping("/getDoctors")
	public List<Doctor> getHelloWorld() {
		return ds.getDoctors();
	}
	
	@GetMapping("/getClinicsDoctors")
	public List<DoctorDTO> getDoctors(@RequestParam String clinic_id) {
		return ds.getClinicsDoctors(Integer.parseInt(clinic_id));
	}
	
	@GetMapping("/getAvailableDoctors")
	public List<DoctorDTO> getAvailableDoctors(@RequestParam String clinic_id, String appointmentType_id, String date ) {
		
		ArrayList<Doctor> doctors = ds.getClinicDoctorsbyAppointmentType(Integer.parseInt(appointmentType_id),Integer.parseInt(clinic_id));	
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		java.util.Date date1 = null;
		try {
			date1 = formatter.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		java.sql.Date sd = new java.sql.Date(date1.getTime());

		
		ArrayList<DoctorDTO> doctors_filtered = new ArrayList<DoctorDTO>();
		
		for(Doctor dr : doctors) {
			if (ds.getAvailableTime(sd, dr).size() > 0) {
				DoctorDTO ddto = new DoctorDTO();
				ddto.setId(dr.getId());
				ddto.setName(dr.getName());
				ddto.setSurname(dr.getSurname());
				ddto.setAverage_rate(0);
				ddto.setAppointmentTypeName(dr.getAppointmentType().getName());
				ddto.setAvailable_times(ds.getAvailableTime(sd, dr));
				ddto.setTime(ddto.getAvailable_times().get(0));
				doctors_filtered.add(ddto);
	
			}
		}
		
		return doctors_filtered;
	}

	@PostMapping("/addDoctor")
	@PreAuthorize("hasRole('CLINIC_ADMIN')")
	public ResponseEntity<Object> addDoctor(@Valid @RequestBody DoctorDTO doctor) {
		Doctor dr = new Doctor();

		RegistrationRequest existUser = this.rs.findByEmail(doctor.getEmail());
		if (existUser != null) {
			ResponseEntity.status(HttpStatus.CONFLICT).body(null);
		}

		AppointmentType at = ats.getAppointmentTypebyId(doctor.getAppointment_type_id());

		Clinic c = cs.getClinicbyId(doctor.getClinic_id());

		if (at == null || c == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}

		dr.setClinic(c);
		dr.setAppointmentType(at);
		dr.setName(doctor.getName());
		dr.setSurname(doctor.getSurname());
		dr.setEmail(doctor.getEmail());
		dr.setPassword(doctor.getPassword());
		dr.setWorkStart(doctor.getWorkStart());
		dr.setWorkEnd(doctor.getWorkEnd());

		int flag = ds.addDoctor(dr);

		if (flag == 0)
			return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
		else
			return ResponseEntity.status(HttpStatus.OK).body(null);

	}

	@GetMapping("/getDoctorsAppointment")
	@PreAuthorize("hasRole('DOCTOR') || hasRole('CLINIC_ADMIN')")
	public ResponseEntity<ArrayList<DoctorDTO>> getDoctors(@RequestParam String clinic_id, String appointment_type_id) {
		//TODO proveri koji doktori su slobodni za odabrani termin
		return ResponseEntity.ok(ds.getDoctors(Integer.parseInt(clinic_id), Integer.parseInt(appointment_type_id)));
	}
	
	@DeleteMapping("/deleteDoctor")
	@PreAuthorize("hasRole('DOCTOR') || hasRole('CLINIC_ADMIN')")
	public ResponseEntity<ArrayList<DoctorDTO>> deleteDoctor(@RequestParam String doctor_id) {
		if(ds.deleteDoctor(Integer.parseInt(doctor_id)) != 0) {
			return ResponseEntity.ok(null);
		}
		else {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
		}
	}

}
