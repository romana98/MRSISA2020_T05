package com.project.tim05.api;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.tim05.dto.AppointmentDTO;
import com.project.tim05.dto.AppointmentRequestDTO;
import com.project.tim05.model.Appointment;
import com.project.tim05.model.AppointmentType;
import com.project.tim05.model.Clinic;
import com.project.tim05.model.ClinicAdministrator;
import com.project.tim05.model.Doctor;
import com.project.tim05.model.Hall;
import com.project.tim05.model.MedicalStaff;
import com.project.tim05.model.Nurse;
import com.project.tim05.model.Patient;
import com.project.tim05.model.WorkCalendar;
import com.project.tim05.repository.AppointmentRespository;
import com.project.tim05.service.AppointmentService;
import com.project.tim05.service.AppointmentTypeService;
import com.project.tim05.service.ClinicAdministratorService;
import com.project.tim05.service.ClinicService;
import com.project.tim05.service.DoctorService;
import com.project.tim05.service.EmailService;
import com.project.tim05.service.HallService;
import com.project.tim05.service.NurseService;
import com.project.tim05.service.PatientService;
import com.project.tim05.service.WorkCalendarService;

//@CrossOrigin(origins = "https://eclinic05.herokuapp.com")

@CrossOrigin(origins = "https://localhost:4200")

@RequestMapping("/appointment")
@RestController
public class AppointmentController<T> {

	private final AppointmentService as;
	private final DoctorService ds;
	private final NurseService ns;
	private final HallService hs;
	private final AppointmentTypeService ats;
	private final ClinicService cs;
	private final WorkCalendarService wcs;
	private final PatientService ps;
	private final ClinicAdministratorService cas;
	private final EmailService es;
	private final AppointmentRespository ar;
	
	@Autowired
	public AppointmentController(AppointmentRespository ar, EmailService es, NurseService ns, ClinicAdministratorService cas,PatientService ps,WorkCalendarService wcs,AppointmentService as, DoctorService ds, HallService hs, AppointmentTypeService ats,ClinicService cs) {
		super();
		this.ar = ar;
		this.es = es;
		this.ns = ns;
		this.as = as;
		this.ds = ds;
		this.hs = hs;
		this.ats = ats;
		this.cs = cs;
		this.wcs = wcs;
		this.ps = ps;
		this.cas = cas;
	}
	
	@PostMapping("/addAppointment")
	@PreAuthorize("hasRole('CLINIC_ADMIN') || hasRole('PATIENT')")
	public ResponseEntity<String> addAppointment(@RequestBody AppointmentDTO adto) {
		Appointment ap = new Appointment();

		
		
		Doctor dr = ds.getDoctorbyID(adto.getDoctor_id());
		Hall hall = hs.getHallbyId(adto.getHall_id());
		AppointmentType at = ats.getAppointmentTypebyId(adto.getAppointmentType_id());
		Clinic c = cs.getClinicbyId(adto.getClinic_id()) ;
	
		if (dr == null || hall == null || at == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		
		//provera da li je doktor zauzet
		
		int dr_start = Integer.parseInt(dr.getWorkStart().split(":")[0]) * 60
				+ Integer.parseInt(dr.getWorkStart().split(":")[1]);
		int dr_end = Integer.parseInt(dr.getWorkEnd().split(":")[0]) * 60
				+ Integer.parseInt(dr.getWorkEnd().split(":")[1]);

		int app_start = Integer.parseInt(adto.getTime().split(":")[0])*60 + Integer.parseInt(adto.getTime().split(":")[1]);
		int app_end = app_start + adto.getDuration();
		
		
		if ((app_start < dr_start || app_start > dr_end) || (app_end < dr_start || app_end > dr_end)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}		

		SimpleDateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat formatter3 = new SimpleDateFormat("yyyy-MM-dd");


		Date date = null;
		Date wc_date = null;
		try {
			date = formatter1.parse(adto.getDate() + " " + adto.getTime());
			wc_date = formatter2.parse(adto.getDate());
		} catch (ParseException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		
		for(WorkCalendar wc : dr.getWorkCalendar()) {
			try {
				if(formatter3.parse(wc.getDate().toString().split(" ")[0]).getTime()!=wc_date.getTime()) {
					continue;
				} 
				else {
					if(wc.getLeave()==true) {
						return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
					}
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int wc_start = Integer.parseInt(wc.getStart_time().split(":")[0]) * 60
					+ Integer.parseInt(wc.getStart_time().split(":")[1]);
			int wc_end = Integer.parseInt(wc.getEnd_time().split(":")[0]) * 60
					+ Integer.parseInt(wc.getEnd_time().split(":")[1]);
			if ((app_start >= wc_start && app_start <= wc_end) || (app_end >= wc_start && app_end <= wc_end)
					|| (app_start == wc_start && app_end == wc_end)) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
			}
		}
		
		
		
		
		
		

		ap.setDateTime(date);
		ap.setDuration(adto.getDuration());
		ap.setPrice(adto.getPrice());
		ap.setRequest(adto.isRequest());
		ap.setPredefined(adto.isPredefined());
		ap.setDoctor(dr);
		ap.setAppointmentType(at);
		ap.setHall(hall);
		ap.setClinic(c);
		
		if(!hs.predefinedHallAvailable(hall, ap, wc_date, c.getId())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

		}

		int flag = as.addAppointment(ap);
		//TODO u doktoru treba da se doda appointment
		

		WorkCalendar wc = new WorkCalendar();
		wc.setDate(wc_date);
		wc.setStart_time(adto.getTime());
		
		
		//racunanje minuta od pocetka dana
		String[] res = wc.getStart_time().split(":");
		int start_minutes = Integer.parseInt(res[0])*60 + Integer.parseInt(res[1]);
		//racunanje krajnjeg broja minuta od pocetka dana
		int end_minutes = start_minutes + adto.getDuration();
		//transliranje krajnjeg broja minuta nazad u oblik "hh:mm"
		//uzima se broj minuta i ostatak pri deljenju sa 60 predstavlja broj minuta koji je preko punog sata
		//a sati se dobijaju tako sto se uzme broj minuta i bez ostatka se podeli sa 60, tako dobijamo sati:minuti
		int end_minute = end_minutes%60;
		int end_hour = end_minutes/60;
		
		dr.getAppointments().add(ap);
		
		wc.setEnd_time(end_hour + ":" + end_minute);
		wc.setDoctor(dr);
		wc.setLeave(false);
		wc.setRequest(false);
		
		dr.getWorkCalendar().add(wc);
		
		wcs.addCalendar(wc);
		
		for(Appointment a : dr.getAppointments()) {
			System.out.println(a.getAppointmentType().getName());
		}

		if (flag == 0)
			return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
		else
			return ResponseEntity.status(HttpStatus.OK).body(null);

	}
	
	@PostMapping("/addAnotherAppointment")
	@PreAuthorize("hasRole('DOCTOR')")
	public ResponseEntity<String> addAnotherAppointment(@RequestBody AppointmentRequestDTO adto) {
		
		int flag = ds.addAnotherApp(adto);
		
		if(flag == 0) {
			return ResponseEntity.status(HttpStatus.OK).body(null);
		}
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

		}

	}
	
	@PostMapping("/cancel")
	public ResponseEntity<T> cancel(@RequestBody String request) {

		
		int flag = as.cancelAppointment(Integer.parseInt(request));

		if (flag == 0)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		else if(flag == -5)
		{
			return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
		}
		else {
			return ResponseEntity.status(HttpStatus.OK).body(null);
		}

	}
	
	@PostMapping("/sendRequest")
	@PreAuthorize("hasRole('CLINIC_ADMIN') || hasRole('PATIENT')")
	public ResponseEntity<String> sendRequest(@RequestBody AppointmentRequestDTO adto){
	        
		int flag = ds.sendRequest(adto);

		if(flag == 0) {
			return ResponseEntity.status(HttpStatus.OK).body(null);
		}
		else {
			return ResponseEntity.status(HttpStatus.OK).body(null);
		}
	}
	
	@GetMapping("/getAppointmentRequests")
	//@PreAuthorize("hasRole('CLINIC_ADMIN')")
	public ResponseEntity<Object> getAvailableHalls(@RequestParam String clinic_admin_id, String date) {
		
		ArrayList<AppointmentDTO> dtos = new ArrayList<AppointmentDTO>();
		
		int clinic_id = cas.getClinicAdmin(Integer.parseInt(clinic_admin_id)).getClinic().getId();
		
		//formiranje datuma kakav mi odgovara
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		java.util.Date new_date = null;
		try {
			new_date = formatter.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dtos = as.getAppointmentRequests(new_date, clinic_id);
		
		
		return ResponseEntity.status(HttpStatus.OK).body(dtos);

	}
	
	
	@GetMapping("/getDoctorAppointments")
	@PreAuthorize("hasRole('DOCTOR') || hasRole('NURSE')")
	public List<AppointmentDTO> getDoctorAppointments() {
		
		Authentication current = SecurityContextHolder.getContext().getAuthentication();
		MedicalStaff currentUser = (MedicalStaff)current.getPrincipal();
		
		Doctor d = ds.getDoctor(currentUser.getEmail());
		Nurse n = ns.getNurse(currentUser.getEmail());
		
		if(n != null)
			return new ArrayList<AppointmentDTO>();

		List<AppointmentDTO> wc = as.getDoctorAppointments(d.getEmail());
		
	
		return wc;

	}
	
	@GetMapping("/getReportAppointments")
	@PreAuthorize("hasRole('CLINIC_ADMIN')")
	public List<AppointmentDTO> getReportAppointments() {
		
		Authentication current = SecurityContextHolder.getContext().getAuthentication();
		ClinicAdministrator currentUser = (ClinicAdministrator)current.getPrincipal();
		
		List<AppointmentDTO> wc = as.getReportAppointments(currentUser.getId());
		
	
		return wc;

	}
	
	
	@GetMapping("/getClinicPredefinedAppointments")
	@PreAuthorize("hasRole('PATIENT')")
	public List<AppointmentDTO> getClinicPredefinedAppointments(@RequestParam String clinicId) {
		List<AppointmentDTO> a = as.getClinicPredefinedAppointments(Integer.parseInt(clinicId));
		
	
		return a;

	}
	
	@GetMapping("/saveDescription")
	@PreAuthorize("hasRole('DOCTOR')")
	public ResponseEntity<T> saveDescription(@RequestParam String appId, String desc) {
		Authentication current = SecurityContextHolder.getContext().getAuthentication();
		Doctor currentUser = (Doctor)current.getPrincipal();
				
		
		int flag = as.saveDescription(Integer.parseInt(appId), desc, currentUser.getId());
		
		if (flag == 0)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		else
			return ResponseEntity.status(HttpStatus.OK).body(null);
		
	}
	
	@GetMapping("/getFinishedAppointmentsMed")
	@PreAuthorize("hasRole('DOCTOR') || hasRole('NURSE')")
	public List<AppointmentDTO> getFinishedAppointmentsMed(@RequestParam String email) {
				
		Patient p = ps.getPatient(email);
		List<AppointmentDTO> a = as.getAppointments(p.getId());
		
		return a;
	}
	
	@GetMapping("/getFinishedAppointments")
	@PreAuthorize("hasRole('PATIENT')")
	public List<AppointmentDTO> getFinishedAppointments() {
		Authentication current = SecurityContextHolder.getContext().getAuthentication();
		Patient currentUser = (Patient)current.getPrincipal();
				
		List<AppointmentDTO> a = as.getAppointments(currentUser.getId());
		
		return a;
	}
	
	@PostMapping("/reservePredefinedAppointment")
	@PreAuthorize("hasRole('PATIENT')")
	public ResponseEntity<T> reservePredefinedAppointment(@RequestBody AppointmentDTO a) {
		
		Authentication current = SecurityContextHolder.getContext().getAuthentication();
		Patient currentUser = (Patient)current.getPrincipal();
		

		int flag = as.reservePredefined(a.getId(), currentUser.getId());
	
		if (flag == 0)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		else
		{
			String text = "Appointment approved: date and start time:" + a.getDate() + 
					" appointment_type: " + a.getAppointmentType().getName() + ".";
			
				try {
					es.sendPredefinedAppointmentNotificationPatient(currentUser.getEmail(), text);
				} catch (Exception e) {
					e.printStackTrace();
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
				}
			

			return ResponseEntity.status(HttpStatus.OK).body(null);
		}
	}
	
	

}
