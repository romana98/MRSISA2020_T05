package com.project.tim05.api;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.tim05.dto.LoggedUserDTO;
import com.project.tim05.model.ClinicAdministrator;
import com.project.tim05.model.ClinicCenterAdministrator;
import com.project.tim05.model.Doctor;
import com.project.tim05.model.Nurse;
import com.project.tim05.model.Patient;
import com.project.tim05.service.ClinicAdministratorService;
import com.project.tim05.service.ClinicCenterAdministratorService;
import com.project.tim05.service.DoctorService;
import com.project.tim05.service.LoggingService;
import com.project.tim05.service.NurseService;
import com.project.tim05.service.PatientService;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/login")
@RestController
public class LoggingController<T> {
	
	private final PatientService ps;
	private final DoctorService ds;
	private final NurseService ns;
	private final ClinicAdministratorService cas;
	private final ClinicCenterAdministratorService ccas;
	
	@Autowired
	public LoggingController(PatientService ps, DoctorService ds, NurseService ns, ClinicAdministratorService cas, ClinicCenterAdministratorService ccas) {
		this.ps = ps;
		this.ds = ds;
		this.ns = ns;
		this.cas = cas;
		this.ccas = ccas;
	}
	
	@PostMapping("/loginUser")
	public ResponseEntity<T> login(@Valid @RequestBody LoggedUserDTO lu, HttpServletRequest hsr){
		
		if(lu.getType().equals("patient")) {
			List<Patient> patients = ps.getPatients();
			for(Patient p : patients) {
				if(p.getEmail().equals(lu.getEmail()) && p.getPassword().equals(lu.getPassword())) {
					hsr.getSession(true);
					hsr.getSession().setAttribute("logged_in", p);
					hsr.getSession().setAttribute("logged_in_type", "patient");
					return ResponseEntity.status(HttpStatus.OK).body(null);
				}
			}
		}else if(lu.getType().equals("doctor")) {
			List<Doctor> doctors = ds.getDoctors();
			for(Doctor d : doctors) {
				if(d.getEmail().equals(lu.getEmail()) && d.getPassword().equals(lu.getPassword())) {
					hsr.getSession(true);
					hsr.getSession().setAttribute("logged_in", d);
					hsr.getSession().setAttribute("logged_in_type", "doctor");
					return ResponseEntity.status(HttpStatus.OK).body(null);
				}
			}
		}else if(lu.getType().equals("nurse")) {
			List<Nurse> nurses = ns.getNurses();
			for(Nurse n : nurses) {
				if(n.getEmail().equals(lu.getEmail()) && n.getPassword().equals(lu.getPassword())) {
					hsr.getSession(true);
					hsr.getSession().setAttribute("logged_in", n);
					hsr.getSession().setAttribute("logged_in_type", "nurse");
					return ResponseEntity.status(HttpStatus.OK).body(null);
				}
			}
		}else if(lu.getType().equals("clinic administrator")) {
			List<ClinicAdministrator> clinicadmins = cas.getClinicAdministrators();
			for(ClinicAdministrator ca : clinicadmins) {
				if(ca.getEmail().equals(lu.getEmail()) && ca.getPassword().equals(lu.getPassword())) {
					hsr.getSession(true);
					hsr.getSession().setAttribute("logged_in", ca);
					hsr.getSession().setAttribute("logged_in_type", "clinic_admin");
					return ResponseEntity.status(HttpStatus.OK).body(null);
				}
			}
		}else if(lu.getType().equals("clinic center administrator")) {
			List<ClinicCenterAdministrator> cliniccenteradmins = ccas.getClinicCenterAdministrators();
			for(ClinicCenterAdministrator cca : cliniccenteradmins) {
				if(cca.getEmail().equals(lu.getEmail()) && cca.getPassword().equals(lu.getPassword())) {
					hsr.getSession(true);
					hsr.getSession().setAttribute("logged_in", cca);
					hsr.getSession().setAttribute("logged_in_type", "clinic_center_admin");
					return ResponseEntity.status(HttpStatus.OK).body(null);
				}
			}
		}
		
		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
		
	}
	
	
}
