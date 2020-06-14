package com.project.tim05.api;

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

import com.project.tim05.dto.BasicReportDTO;
import com.project.tim05.dto.ClinicAdministratorDTO;
import com.project.tim05.dto.ClinicDTO;
import com.project.tim05.dto.DoctorDTO;
import com.project.tim05.dto.UserTokenStateDTO;
import com.project.tim05.model.Clinic;
import com.project.tim05.model.ClinicAdministrator;
import com.project.tim05.model.RegistrationRequest;
import com.project.tim05.model.User;
import com.project.tim05.service.ClinicAdministratorService;
import com.project.tim05.service.ClinicService;
import com.project.tim05.service.DoctorService;
import com.project.tim05.service.RegistrationRequestService;
import com.project.tim05.service.UserService;

@CrossOrigin(origins = "https://localhost:4200")

@RequestMapping("/clinicAdministrator")
@RestController
public class ClinicAdministratorController<T> {

	@Autowired
	private UserService userService;

	@Autowired
	private AuthenticationManager authenticationManager;

	private final ClinicAdministratorService cas;
	private final ClinicService cs;
	private final RegistrationRequestService rs;
	private final DoctorService ds;

	@Autowired
	public ClinicAdministratorController(DoctorService ds, ClinicAdministratorService cas, ClinicService cs,
			RegistrationRequestService rs) {
		this.cas = cas;
		this.cs = cs;
		this.rs = rs;
		this.ds = ds;
	}

	@PostMapping("/addClinicAdministrator")
	@PreAuthorize("hasRole('CLINIC_CENTER_ADMIN')")
	public ResponseEntity<T> addClinicAdministrator(@Valid @RequestBody ClinicAdministratorDTO cca) {

		User existUser = this.userService.findByEmail(cca.getEmail());
		RegistrationRequest existUser1 = this.rs.findByEmail(cca.getEmail());
		if (existUser != null || existUser1 != null) {
			ResponseEntity.status(HttpStatus.CONFLICT).body(null);
		}

		Clinic cl = cs.getClinic(cca.getClinic());
		if (cl == null || cca.getPassword().length() < 8)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

		ClinicAdministrator cadmin = new ClinicAdministrator(cca.getName(), cca.getSurname(), cca.getEmail(),
				cca.getPassword(), cl);

		int flag = cas.addClinicAdministrator(cadmin);

		if (flag == 0)
			return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
		else
			return ResponseEntity.status(HttpStatus.OK).body(null);
	}

	@GetMapping("/getClinicAdministrator")
	@PreAuthorize("hasRole('CLINIC_ADMIN')")
	public ResponseEntity<ClinicAdministratorDTO> getClinicAdministrator() {

		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
		ClinicAdministrator ca = (ClinicAdministrator) currentUser.getPrincipal();
		String email = ca.getEmail();

		ClinicAdministrator c = cas.getClinicAdmin(email);

		if (c == null)
			return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
		else {
			ClinicAdministratorDTO cDTO = new ClinicAdministratorDTO(c.getName(), c.getSurname(), c.getEmail(),
					new ClinicDTO(c.getClinic().getName(), c.getClinic().getAddress(), c.getClinic().getDescription()));
			return ResponseEntity.status(HttpStatus.OK).body(cDTO);
		}

	}

	@PostMapping("/editClinicAdministrator")
	@PreAuthorize("hasRole('CLINIC_ADMIN')")
	public ResponseEntity<UserTokenStateDTO> editClinicAdministrator(@Valid @RequestBody ClinicAdministratorDTO cca) {

		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
		ClinicAdministrator ca = ((ClinicAdministrator) currentUser.getPrincipal());
		String email = ca.getEmail();

		if (!email.equals(cca.getEmail()))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

		Clinic cl = cs.getClinic(cca.getClinic());
		if (cl == null || (cca.getPassword().length() > 0 && cca.getPassword().length() < 8))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

		ClinicAdministrator cadmin = new ClinicAdministrator(cca.getName(), cca.getSurname(), cca.getEmail(),
				cca.getPassword(), cl);
		int flag = cas.editClinicAdministrator(cadmin);

		if (cca.getPassword().length() != 0 && flag != 0) {
			Authentication authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(ca.getEmail(), cca.getPassword()));

			SecurityContextHolder.getContext().setAuthentication(authentication);

		}

		if (flag == 0)
			return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
		else {

			return ResponseEntity.status(HttpStatus.OK).body(null);
		}
	}

	@GetMapping("/getAdminsClinic")
	@PreAuthorize("hasRole('CLINIC_ADMIN')")
	public int getClinics(@RequestParam String admin_id) {
		return cas.getClinicAdmin(Integer.parseInt(admin_id)).getClinic().getId();
	}
	
	@GetMapping("/getClinic")
	@PreAuthorize("hasRole('CLINIC_ADMIN')")
	public ResponseEntity<ClinicDTO> getClinics() {
		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
		ClinicAdministrator ca = (ClinicAdministrator) currentUser.getPrincipal();
		String email = ca.getEmail();

		ClinicAdministrator c = cas.getClinicAdmin(email);
		
		
		ClinicDTO cl = new ClinicDTO(c.getClinic().getName(), c.getClinic().getAddress(), c.getClinic().getDescription());
		
		
		if(cl.equals(null))
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(cl);
		}
		else
		{
			return ResponseEntity.status(HttpStatus.OK).body(cl);
		}
		
		
	}

	// api endpoint prima parametar pretrage, njegovu vrednost i id klinike kojoj
	// pripada admin koji poziva
	@GetMapping("/searchDoctors")
	@PreAuthorize("hasRole('CLINIC_ADMIN')")
	public ResponseEntity<List<DoctorDTO>> searchDoctors(@RequestParam String parameter, String value,
			String clinic_id) {
		// pozivanje metode za pretrazivanje po bazi iz servisa
		List<DoctorDTO> dtos = ds.searchDoctors(parameter, value, Integer.parseInt(clinic_id));
		if (dtos.isEmpty()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(dtos);
		}
		return ResponseEntity.ok(dtos);
	}
	
	@GetMapping("/getClinicAvgRate")
	@PreAuthorize("hasRole('CLINIC_ADMIN')")
	public ResponseEntity<BasicReportDTO> getClinicInfo(){
		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
		ClinicAdministrator ca = (ClinicAdministrator) currentUser.getPrincipal();
		Clinic clinic = ca.getClinic();
		BasicReportDTO info = cas.getClinicInfo(clinic);
		if(info == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}else {
			return ResponseEntity.status(HttpStatus.OK).body(info);
		}
	}
	
	@GetMapping("/getDoctorInfo")
	@PreAuthorize("hasRole('CLINIC_ADMIN')")
	public ResponseEntity<ArrayList<DoctorDTO>> getDoctorInfo(){
		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
		ClinicAdministrator ca = (ClinicAdministrator) currentUser.getPrincipal();
		Clinic clinic = ca.getClinic();
		BasicReportDTO info = cas.getDoctorInfo(clinic);
		if(info == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}else {
			return ResponseEntity.status(HttpStatus.OK).body(info.getDoctors());
		}
	}
	
	@PostMapping("/getIncome")
	@PreAuthorize("hasRole('CLINIC_ADMIN')")
	public ResponseEntity<Double> getIncome(@RequestBody BasicReportDTO dto){
		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
		ClinicAdministrator ca = (ClinicAdministrator) currentUser.getPrincipal();
		Clinic clinic = ca.getClinic();
		BasicReportDTO info = cas.getIncomes(clinic, dto);
		if(info == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}else {
			return ResponseEntity.status(HttpStatus.OK).body(info.getIncome());
		}
	}

}
