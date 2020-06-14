package com.project.tim05.api;

import java.util.ArrayList;

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

import com.project.tim05.dto.AppointmentTypeDTO;
import com.project.tim05.dto.PricelistDTO;
import com.project.tim05.model.AppointmentType;
import com.project.tim05.model.ClinicAdministrator;
import com.project.tim05.model.Pricelist;
import com.project.tim05.repository.PricelistRepository;
import com.project.tim05.service.AppointmentTypeService;
import com.project.tim05.service.PricelistService;

//@CrossOrigin(origins = "https://localhost:4200")
@CrossOrigin(origins = "https://eclinic05.herokuapp.com")
@RequestMapping("/pricelist")
@RestController
public class PricelistController<T> {

	private final PricelistService ps;
	private final PricelistRepository pr;
	private final AppointmentTypeService ats;
	
	@Autowired
	public PricelistController(AppointmentTypeService ats,PricelistRepository pr, PricelistService ps) {
		this.ps = ps;
		this.pr = pr;
		this.ats= ats;
	}
	
	@GetMapping("/getAptTypes")
	@PreAuthorize("hasRole('CLINIC_ADMIN')")
	public ResponseEntity<ArrayList<AppointmentTypeDTO>> getAptTypes(){
		ArrayList<AppointmentTypeDTO> dtos = new ArrayList<AppointmentTypeDTO>();
		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
		ClinicAdministrator ca = (ClinicAdministrator) currentUser.getPrincipal();
		//IMPLEMENTIRAJ
		dtos = ps.getAptTypes(ca.getClinic().getId());
		return ResponseEntity.status(HttpStatus.OK).body(dtos);
	}
	
	@GetMapping("/getPriceList")
	@PreAuthorize("hasRole('CLINIC_ADMIN')")
	public ResponseEntity<ArrayList<PricelistDTO>> getPricelists(){
		ArrayList<PricelistDTO> dtos = new ArrayList<PricelistDTO>();
		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
		ClinicAdministrator ca = (ClinicAdministrator) currentUser.getPrincipal();
		//IMPLEMENTIRAJ
		dtos = ps.getPricelists(ca.getClinic().getId());
		return ResponseEntity.status(HttpStatus.OK).body(dtos);
	}
	
	@DeleteMapping("/deletePrice")
	@PreAuthorize("hasRole('CLINIC_ADMIN')")
	public ResponseEntity<T> deletePrice(@RequestParam String apt_type){
		
		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
		ClinicAdministrator ca = (ClinicAdministrator) currentUser.getPrincipal();
		//IMPLEMENTIRAJ
		int flag = ps.deletePrice(apt_type, ca.getClinic().getId());
		if(flag == 0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		return ResponseEntity.status(HttpStatus.OK).body(null);
		
	}
	
	@PostMapping("/addPrice")
	@PreAuthorize("hasRole('CLINIC_ADMIN')")
	public ResponseEntity<T> addPrice(@Valid @RequestBody PricelistDTO dto){
		
		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
		ClinicAdministrator ca = (ClinicAdministrator) currentUser.getPrincipal();
		dto.setClinic_id(ca.getClinic().getId());
		//IMPLEMENTIRAJ
		int flag = ps.addPrice(dto);
		if(flag == 0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}
	
	@PostMapping("/editPricelist")
	@PreAuthorize("hasRole('CLINIC_ADMIN')")
	public ResponseEntity<T> editPrice(@Valid @RequestBody PricelistDTO dto){
		
		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
		ClinicAdministrator ca = (ClinicAdministrator) currentUser.getPrincipal();
		dto.setClinic_id(ca.getClinic().getId());
		//IMPLEMENTIRAJ
		int flag = ps.editPrice(dto);
		if(flag == 0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}
	
	@GetMapping("/getAptypePrice")
	@PreAuthorize("hasRole('CLINIC_ADMIN')")
	public ResponseEntity<Double> getAppointmentTypePrice(@RequestParam String appointment_type_id, String clinic_id){
		
		AppointmentType at = ats.getAppointmentTypebyId(Integer.parseInt(appointment_type_id));
		
		ArrayList<PricelistDTO> pcs = ps.getPricelists(Integer.parseInt(clinic_id));
		for(PricelistDTO p : pcs) {
			if(p.getApt_type().equalsIgnoreCase(at.getName())) {
				return ResponseEntity.status(HttpStatus.OK).body(p.getPrice());

			}
		}
		
		
		return ResponseEntity.status(HttpStatus.OK).body(Double.parseDouble("100"));
	}
	
}
