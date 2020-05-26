package com.project.tim05.api;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.tim05.dto.AppointmentTypeDTO;
import com.project.tim05.model.AppointmentType;
import com.project.tim05.model.ClinicAdministrator;
import com.project.tim05.service.AppointmentTypeService;
import com.project.tim05.service.ClinicAdministratorService;

@CrossOrigin(origins = "https://localhost:4200")
@RequestMapping("/appointmentType")
@RestController
public class AppointmentTypeController<T> {

	private final AppointmentTypeService ats;
	private final ClinicAdministratorService cas;

	@Autowired
	public AppointmentTypeController(AppointmentTypeService ats, ClinicAdministratorService cas) {
		super();
		this.ats = ats;
		this.cas = cas;
	}
	
	@PostMapping("/addAppointmentType")
	@PreAuthorize("hasRole('CLINIC_ADMIN')")
	public ResponseEntity<Object> addAppointmentType(@Valid @RequestBody AppointmentTypeDTO atDTO) {
		
		AppointmentType at = new AppointmentType(atDTO.getName());
		ClinicAdministrator ca = cas.getClinicAdmin(atDTO.getAdmin_id());
		at.setClinic(ca.getClinic());
		
		
		
		int flag = ats.addAppointmentType(at);
		
		if(flag == 0)
			return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
		else
			return ResponseEntity.ok(ats.getClinicAppointmentTypes(ca.getClinic().getId()));
		
	}
	
	@GetMapping("/getAllApointmentTypes")
	@PreAuthorize("hasRole('CLINIC_ADMIN') || hasRole('PATIENT')")
	public ResponseEntity<List<AppointmentTypeDTO>> getAllTypes(){
		return ResponseEntity.ok(ats.getAppointmentTypes());
	}
	
	@GetMapping("/getAppointmentTypes")
	@PreAuthorize("hasRole('CLINIC_ADMIN') || hasRole('PATIENT')")
	public ResponseEntity<List<AppointmentTypeDTO>> getAppointmetTypes(String admin_id){
		ClinicAdministrator ca = cas.getClinicAdmin(Integer.parseInt(admin_id));
		return ResponseEntity.ok(ats.getClinicAppointmentTypes(ca.getClinic().getId()));
	}
	
	@DeleteMapping("/deleteAppointmentType")
	@PreAuthorize("hasRole('CLINIC_ADMIN')")
	public ResponseEntity<T> deleteAppointmentType(@RequestParam String aptype_id){
		int flag = ats.deleteAppointmentType(Integer.parseInt(aptype_id));
		if(flag != 0) {
			return ResponseEntity.status(HttpStatus.OK).body(null);
		}
		else {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
		}
	}
	
	@PostMapping("/editAppointmentType")
	@PreAuthorize("hasRole('CLINIC_ADMIN')")
	public ResponseEntity<Object> editAppointmentType(@Valid @RequestBody AppointmentTypeDTO atDTO) {
		if (ats.changeAppointmentType(atDTO.getId(), atDTO.getName()) == null) {
			return ResponseEntity.ok(null);

		}
		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);

		
		
	}
	
	@GetMapping("/searchAppointmentTypes")
	@PreAuthorize("hasRole('CLINIC_ADMIN')")
	public ResponseEntity<List<AppointmentTypeDTO>> searchAppointmentTypes(String searchInput){
		ArrayList<AppointmentTypeDTO> search = new ArrayList<AppointmentTypeDTO>();
		search = ats.search(searchInput);		
		return ResponseEntity.ok(search);
	}
	
	/*@GetMapping("/getClinicAppointmenTypes")
	@PreAuthorize("hasRole('CLINIC_ADMIN')")
	public ResponseEntity<List<AppointmentType>> getClinicAppointmentTypes(@RequestParam String clinic_id){
		return ResponseEntity.ok(ats.getClinicAppointmentTypes(Integer.parseInt(clinic_id)));
	}*/
}

