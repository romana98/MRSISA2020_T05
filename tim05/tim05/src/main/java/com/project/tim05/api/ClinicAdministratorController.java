package com.project.tim05.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.tim05.dto.ClinicAdministratorDTO;
import com.project.tim05.dto.ClinicDTO;
import com.project.tim05.dto.UserTokenStateDTO;
import com.project.tim05.model.Clinic;
import com.project.tim05.model.ClinicAdministrator;
import com.project.tim05.model.RegistrationRequest;
import com.project.tim05.model.User;
import com.project.tim05.service.ClinicAdministratorService;
import com.project.tim05.service.ClinicService;
import com.project.tim05.service.RegistrationRequestService;
import com.project.tim05.service.UserService;

//@CrossOrigin(origins = "https://eclinic05.herokuapp.com")
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
	
	@Autowired
	public ClinicAdministratorController(ClinicAdministratorService cas, ClinicService cs, RegistrationRequestService rs) {
		this.cas = cas;
		this.cs = cs;
		this.rs = rs;
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
		if(cl == null || cca.getPassword().length() < 8)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		
		ClinicAdministrator cadmin = new ClinicAdministrator(cca.getName(), cca.getSurname(), cca.getEmail(), cca.getPassword(), cl);

		int flag = cas.addClinicAdministrator(cadmin);
		
		if(flag == 0)
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
		
		if(c == null)
			return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
		else
		{
			ClinicAdministratorDTO cDTO = new ClinicAdministratorDTO(c.getName(), c.getSurname(), c.getEmail(), new ClinicDTO(c.getClinic().getName(), c.getClinic().getAddress(), c.getClinic().getDescription()));
			return ResponseEntity.status(HttpStatus.OK).body(cDTO);
		}
	
	}
	
	@PostMapping("/editClinicAdministrator")
	@PreAuthorize("hasRole('CLINIC_ADMIN')")
	public ResponseEntity<UserTokenStateDTO> editClinicAdministrator(@Valid @RequestBody ClinicAdministratorDTO cca) {
		
		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
		ClinicAdministrator ca = ((ClinicAdministrator) currentUser.getPrincipal());
		String email = ca.getEmail();

		if(!email.equals(cca.getEmail()))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

		Clinic cl = cs.getClinic(cca.getClinic());
		if(cl == null || (cca.getPassword().length() > 0 && cca.getPassword().length() < 8))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

		
		ClinicAdministrator cadmin = new ClinicAdministrator(cca.getName(), cca.getSurname(), cca.getEmail(), cca.getPassword(), cl);
		int flag = cas.editClinicAdministrator(cadmin);
		
		if(cca.getPassword().length() != 0 && flag != 0)
		{
			Authentication authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(ca.getEmail(),cca.getPassword()));

			SecurityContextHolder.getContext().setAuthentication(authentication);
				
		}
		
		
		if(flag == 0)
			return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
		else
		{
			
			return ResponseEntity.status(HttpStatus.OK).body(null);
		}
	}
	

	
	@GetMapping("/getAdminsClinic")
	@PreAuthorize("hasRole('CLINIC_ADMIN')")
	public int getClinics(@RequestParam String admin_id){
		return cas.getClinicAdmin(Integer.parseInt(admin_id)).getClinic().getId();
	}
	
	

}
