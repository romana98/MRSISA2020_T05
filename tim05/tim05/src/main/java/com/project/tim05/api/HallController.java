package com.project.tim05.api;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

import com.project.tim05.dto.AppointmentDTO;
import com.project.tim05.dto.DoctorDTO;
import com.project.tim05.dto.HallDTO;
import com.project.tim05.dto.NewAppointmentDTO;
import com.project.tim05.model.Appointment;
import com.project.tim05.model.AppointmentType;
import com.project.tim05.model.ClinicAdministrator;
import com.project.tim05.model.Doctor;
import com.project.tim05.model.Hall;
import com.project.tim05.model.User;
import com.project.tim05.model.WorkCalendar;
import com.project.tim05.service.AppointmentService;
import com.project.tim05.service.ClinicAdministratorService;
import com.project.tim05.service.DoctorService;
import com.project.tim05.service.EmailService;
import com.project.tim05.service.HallService;
import com.project.tim05.service.WorkCalendarService;
import com.project.tim05.service.initializeAndUnproxy;

@CrossOrigin(origins = "https://localhost:4200")
@RequestMapping("/halls")
@RestController
public class HallController<T> {

	private final HallService hs;
	private final ClinicAdministratorService cas;
	private final AppointmentService as;
	private final DoctorService ds;
	private final WorkCalendarService wcs;
	private final EmailService es;

	@Autowired
	public HallController(WorkCalendarService wcs, EmailService es, DoctorService ds, AppointmentService as, HallService hs, ClinicAdministratorService cas) {
		this.hs = hs;
		this.cas = cas;
		this.as = as;
		this.ds = ds;
		this.wcs = wcs;
		this.es = es;
	}

	@PostMapping("/addHall")
	@PreAuthorize("hasRole('CLINIC_ADMIN')")
	public ResponseEntity<T> addHall(@Valid @RequestBody HallDTO hall) {
		System.out.println("Add hall is called!");
		Hall h = new Hall();
		h.setName(hall.getName());
		h.setNumber(hall.getNumber());
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User currentUser = (User) authentication.getPrincipal();
		ClinicAdministrator ca = cas.getClinicAdmin(currentUser.getEmail());
		h.setClinic(ca.getClinic());
		h.setClinicAdmin(ca);

		int flag = hs.addHall(h);

		if (flag == 0)
			return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
		else
			return ResponseEntity.status(HttpStatus.OK).body(null);
	}

	@GetMapping("/getClinicHall")
	@PreAuthorize("hasRole('CLINIC_ADMIN')")
	public ResponseEntity<List<HallDTO>> getHalls(@RequestParam String clinic_id) {
		List<HallDTO> hss = new ArrayList<HallDTO>();
		ArrayList<Hall>h = hs.getClinicHalls(Integer.parseInt(clinic_id));
		for (Hall hall : h) {
			hss.add(new HallDTO(hall.getId(), hall.getName(), hall.getNumber()));
		}
		return ResponseEntity.ok(hss);
	}
	
	/*@GetMapping("/getClinicHall")
	@PreAuthorize("hasRole('CLINIC_ADMIN')")
	public ResponseEntity<List<Hall>> getHalls(@RequestParam String clinic_id) {
		return ResponseEntity.ok(hs.getClinicHalls(Integer.parseInt(clinic_id)));
	}*/

	@DeleteMapping("/deleteHall")
	@PreAuthorize("hasRole('CLINIC_ADMIN')")
	public ResponseEntity<T> deleteHall(@RequestParam String hall_id) {
		int flag = hs.deleteHall(Integer.parseInt(hall_id));
		if (flag != 0) {
			return ResponseEntity.status(HttpStatus.OK).body(null);
		} else {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
		}

	}

	@GetMapping("/editHall")
	@PreAuthorize("hasRole('CLINIC_ADMIN')")
	public ResponseEntity<Object> editAppointmentType(@RequestParam String name, String number, String id) {
		if (hs.editHall(name, Integer.parseInt(number), Integer.parseInt(id)) == 1) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(null);
		}
	}

	@GetMapping("/getFirstTime")
	// @PreAuthorize("hasRole('CLINIC_ADMIN')")
	public ResponseEntity<Object> getFirstTime(@RequestParam String hall_id, String appointment_id,
			String clinic_admin_id) {

		int clinic_id = cas.getClinicAdmin(Integer.parseInt(clinic_admin_id)).getClinic().getId();

		Appointment ap = as.getAppointmentById(Integer.parseInt(appointment_id));

		Hall h = hs.getHallbyId(Integer.parseInt(hall_id));

		String a = hs.getFirstTime(ap, h, clinic_id);

		Doctor dr = initializeAndUnproxy.initAndUnproxy(ap.getDoctor());
		Set<WorkCalendar> wcs = initializeAndUnproxy.initAndUnproxy(dr.getWorkCalendar());

		int app_start = Integer.parseInt(a.substring(11, 16).split(":")[0]) * 60
				+ Integer.parseInt(a.substring(11, 16).split(":")[1]);
		int app_end = app_start + ap.getDuration();
		int dr_start = Integer.parseInt(dr.getWorkStart().split(":")[0]) * 60
				+ Integer.parseInt(dr.getWorkStart().split(":")[1]);
		int dr_end = Integer.parseInt(dr.getWorkEnd().split(":")[0]) * 60
				+ Integer.parseInt(dr.getWorkEnd().split(":")[1]);

		boolean busy = false;

		if ((app_start < dr_start || app_start > dr_end) || (app_end < dr_start || app_end > dr_end)) {
			busy = true;
		}

		for (WorkCalendar wc : wcs) {
			if (a.substring(0, 10).equalsIgnoreCase(wc.getDate().toString().substring(0, 10))) {
				int wc_start = Integer.parseInt(wc.getStart_time().split(":")[0]) * 60
						+ Integer.parseInt(wc.getStart_time().split(":")[1]);
				int wc_end = Integer.parseInt(wc.getEnd_time().split(":")[0]) * 60
						+ Integer.parseInt(wc.getEnd_time().split(":")[1]);
				if ((app_start > wc_start && app_start < wc_end) || (app_end > wc_start && app_end < wc_end)
						|| (app_start == wc_start && app_end == wc_end)) {
					busy = true;
				}
			}
		}
		System.out.println(busy);

		ArrayList<DoctorDTO> free_doctors = new ArrayList<DoctorDTO>();

		NewAppointmentDTO nadto = new NewAppointmentDTO();

		if (busy) {
			AppointmentType at = initializeAndUnproxy.initAndUnproxy(ap.getAppointmentType());
			ArrayList<Doctor> doctors = initializeAndUnproxy
					.initAndUnproxy(ds.getClinicDoctorsbyAppointmentType(at.getId(), clinic_id));
			for (Doctor d : doctors) {
				boolean available = true;
				Set<WorkCalendar> work_times = initializeAndUnproxy.initAndUnproxy(d.getWorkCalendar());
				for (WorkCalendar wc : work_times) {
					if (a.substring(0, 10).equalsIgnoreCase(wc.getDate().toString().substring(0, 10))) {
						int wc_start = Integer.parseInt(wc.getStart_time().split(":")[0]) * 60
								+ Integer.parseInt(wc.getStart_time().split(":")[1]);
						int wc_end = Integer.parseInt(wc.getEnd_time().split(":")[0]) * 60
								+ Integer.parseInt(wc.getEnd_time().split(":")[1]);
						if ((app_start > wc_start && app_start < wc_end) || (app_end > wc_start && app_end < wc_end)
								|| (app_start == wc_start && app_end == wc_end)) {
							available = false;
						}
					}
				}
				if (available) {
					DoctorDTO ddto = new DoctorDTO();
					ddto.setId(d.getId());
					ddto.setName(d.getName());
					ddto.setSurname(d.getSurname());
					free_doctors.add(ddto);
				}
			}

		}
		nadto.setDate(a);
		nadto.setDoctors(free_doctors);

		return ResponseEntity.status(HttpStatus.OK).body(nadto);

	}

	@GetMapping("/getAvailabileHalls")
	// @PreAuthorize("hasRole('CLINIC_ADMIN')")
	public ResponseEntity<Object> getAvailableHalls(@RequestParam String param_name, String param_value, String date,
			String clinic_admin_id) {

		int clinic_id = cas.getClinicAdmin(Integer.parseInt(clinic_admin_id)).getClinic().getId();

		// formiranje datuma kakav mi odgovara
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		java.util.Date new_date = null;
		try {
			new_date = formatter.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ArrayList<HallDTO> dtos = hs.getAvailableHalls(param_name, param_value, new_date, clinic_id);

		return ResponseEntity.status(HttpStatus.OK).body(dtos);

	}

	@GetMapping("/reserveHall")
	// @PreAuthorize("hasRole('CLINIC_ADMIN')")
	public ResponseEntity<Object> reserve(@RequestParam String hall_id, String appointment_id, String date,
			String clinic_admin_id) {

		ArrayList<AppointmentDTO> dtos = new ArrayList<AppointmentDTO>();

		int clinic_id = cas.getClinicAdmin(Integer.parseInt(clinic_admin_id)).getClinic().getId();

		// formiranje datuma kakav mi odgovara
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		java.util.Date new_date = null;
		try {
			new_date = formatter.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Hall h = hs.getHallbyId(Integer.parseInt(hall_id));
		Appointment a = as.getAppointmentById(Integer.parseInt(appointment_id));

		int success = hs.reserveHall(h, a, new_date, clinic_id);

		if (success == 0) {
			String textStart = "Appointment approved: date and start time:" + a.getDateTime().toString() + 
					" appointment_type: " + a.getAppointmentType().getName() + ".";
			String text = "?appointment_id=" + a.getId();
			
			try {
			es.sendAppointmentApprovalMail(a.getDoctor().getEmail(), textStart, text);
			es.sendAppointmentApprovalMail(a.getPatient().getEmail(), textStart, text);
			}catch (Exception e) {
				ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
			}
			return ResponseEntity.status(HttpStatus.OK).body(null);

		} else if (success == 2) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
		}
		
		
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

	}
	
	@GetMapping("/reserveNewHall")
	// @PreAuthorize("hasRole('CLINIC_ADMIN')")
	public ResponseEntity<Object> reserveNewHall(@RequestParam String hall_id, String appointment_id, String date,
			String doctor_id) {

		// formiranje datuma kakav mi odgovara
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		java.util.Date new_date = null;
		try {
			new_date = formatter.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Hall h = hs.getHallbyId(Integer.parseInt(hall_id));
		Appointment a = as.getAppointmentById(Integer.parseInt(appointment_id));
		
		int app_start = hs.getTimeMinutes(a.getDateTime());
		int app_end = app_start + a.getDuration();
		
		Doctor d = initializeAndUnproxy.initAndUnproxy(a.getDoctor());
		Set<WorkCalendar> times = initializeAndUnproxy.initAndUnproxy(d.getWorkCalendar());
		WorkCalendar target_wc = null;
		for(WorkCalendar wc: times) {
			int wc_start = Integer.parseInt(wc.getStart_time().split(":")[0])*60 + Integer.parseInt(wc.getStart_time().split(":")[1]);
			int wc_end = Integer.parseInt(wc.getEnd_time().split(":")[0])*60 + Integer.parseInt(wc.getEnd_time().split(":")[1]);
			if (wc_start == app_start && wc_end == app_end) {
				target_wc = wc;
				break;
			}
		}
		
		if (Integer.parseInt(doctor_id)!=-1) {
			target_wc.setDate(new_date);
			target_wc.setDoctor(ds.getDoctorbyID(Integer.parseInt(doctor_id)));
			target_wc.setRequest(false);
			a.setDoctor(ds.getDoctorbyID(Integer.parseInt(doctor_id)));
		}
		
		a.setHall(h);
		a.setRequest(false);
		Set<Appointment> apps = h.getAppointments();
		apps.add(a);
		h.setAppointments(apps);
		
		as.updateAppointment(a);
		hs.updateHall(h);
		wcs.updateCalendar(target_wc);
		
		String textStart = "Appointment approved: date and start time:" + a.getDateTime().toString() + 
				" appointment_type: " + a.getAppointmentType().getName() + ".";
		String text = "?appointment_id=" + a.getId();
		
		try {
		es.sendAppointmentApprovalMail(a.getDoctor().getEmail(), textStart, text);
		es.sendAppointmentApprovalMail(a.getPatient().getEmail(), textStart, text);
		
		}catch (Exception e) {
			ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		
		//prvo nadji doktora kod kog treba da obrises iz wc taj termin pregleda
		

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

	}
}
