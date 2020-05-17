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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.tim05.dto.ClinicCenterAdministratorDTO;
import com.project.tim05.dto.UserTokenStateDTO;
import com.project.tim05.model.ClinicCenterAdministrator;
import com.project.tim05.model.RegistrationRequest;
import com.project.tim05.model.User;
import com.project.tim05.service.ClinicCenterAdministratorService;
import com.project.tim05.service.RegistrationRequestService;
import com.project.tim05.service.UserService;

@CrossOrigin(origins = "https://localhost:4200")
@RequestMapping("/clinicCenterAdministrator")
@RestController
public class ClinicCenterAdministratorController<T> {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	private final ClinicCenterAdministratorService ccas;
	private final RegistrationRequestService rs;
	
	@Autowired
	public ClinicCenterAdministratorController(ClinicCenterAdministratorService ccas, RegistrationRequestService rs) {
		this.ccas = ccas;
		this.rs = rs;
	}
	
	@PostMapping("/addClinicCenterAdministrator")
	@PreAuthorize("hasRole('CLINIC_CENTER_ADMIN')")
	public ResponseEntity<T> addClinicCenterAdministrator(@Valid @RequestBody ClinicCenterAdministratorDTO cca) {
		
		User existUser = this.userService.findByEmail(cca.getEmail());
		RegistrationRequest existUser1 = this.rs.findByEmail(cca.getEmail());
		if (existUser != null || existUser1 != null || cca.getPassword().length() < 8) {
			 ResponseEntity.status(HttpStatus.CONFLICT).body(null);
		}
		
		
		ClinicCenterAdministrator ccadmin = new ClinicCenterAdministrator(cca.getName(), cca.getSurname(), cca.getEmail(), cca.getPassword());
		int flag = ccas.addClinicCenterAdministrator(ccadmin);
		
		if(flag == 0)
			return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
		else
			return ResponseEntity.status(HttpStatus.OK).body(null);
	
	}
	
	@GetMapping("/getClinicCenterAdministrator")
	@PreAuthorize("hasRole('CLINIC_CENTER_ADMIN')")
	public ResponseEntity<ClinicCenterAdministrator> getClinicCenterAdministrator() {
		
		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
		ClinicCenterAdministrator cca = (ClinicCenterAdministrator) currentUser.getPrincipal();
		
		ClinicCenterAdministrator cDTO = new ClinicCenterAdministrator(cca.getName(), cca.getSurname(), cca.getEmail(), cca.getPassword());
		return ResponseEntity.status(HttpStatus.OK).body(cDTO);
		
	
	}
	
	@PostMapping("/editClinicCenterAdministrator")
	@PreAuthorize("hasRole('CLINIC_CENTER_ADMIN')")
	public ResponseEntity<UserTokenStateDTO> editClinicCenterAdministrator(@Valid @RequestBody ClinicCenterAdministratorDTO cca) {
		
		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
		ClinicCenterAdministrator user = ((ClinicCenterAdministrator) currentUser.getPrincipal());
		String email = user.getEmail();

		if(!email.equals(user.getEmail()))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		
		ClinicCenterAdministrator cadmin = new ClinicCenterAdministrator(cca.getName(), cca.getSurname(), cca.getEmail(), cca.getPassword());
		int flag = ccas.editClinicCenterAdministrator(cadmin);
		
		if(cca.getPassword().length() != 0 && flag != 0)
		{
			Authentication authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(cca.getEmail(),cca.getPassword()));

			SecurityContextHolder.getContext().setAuthentication(authentication);
				
		}
		
		
		if(flag == 0)
			return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
		else
		{
			
			return ResponseEntity.status(HttpStatus.OK).body(null);
		}
	}
	
}
