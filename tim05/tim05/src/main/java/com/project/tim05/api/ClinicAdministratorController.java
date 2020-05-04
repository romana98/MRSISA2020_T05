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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.tim05.dto.ClinicAdministratorDTO;
import com.project.tim05.dto.UserTokenStateDTO;
import com.project.tim05.model.Clinic;
import com.project.tim05.model.ClinicAdministrator;
import com.project.tim05.model.User;
import com.project.tim05.service.ClinicAdministratorService;
import com.project.tim05.service.ClinicService;
import com.project.tim05.service.UserService;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/clinicAdministrator")
@RestController
public class ClinicAdministratorController<T> {
	
	@Autowired
	private UserService userService;


	@Autowired
	private AuthenticationManager authenticationManager;
	
	private final ClinicAdministratorService cas;
	private final ClinicService cs;
	
	@Autowired
	public ClinicAdministratorController(ClinicAdministratorService cas, ClinicService cs) {
		this.cas = cas;
		this.cs = cs;
	}
	
	@PostMapping("/addClinicAdministrator")
	@PreAuthorize("hasRole('CLINIC_CENTER_ADMIN')")
	public ResponseEntity<T> addClinicAdministrator(@Valid @RequestBody ClinicAdministratorDTO cca) {
		
		User existUser = this.userService.findByEmail(cca.getEmail());
		if (existUser != null) {
			 ResponseEntity.status(HttpStatus.CONFLICT).body(null);
		}
		
		Clinic cl = cs.getClinic(cca.getClinic());
		if(cl == null)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		
		ClinicAdministrator cadmin = new ClinicAdministrator(cca.getName(), cca.getSurname(), cca.getEmail(), cca.getPassword(), cl);
		cadmin.setEnabled(true);;
		int flag = cas.addClinicAdministrator(cadmin);
		
		if(flag == 0)
			return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
		else
			return ResponseEntity.status(HttpStatus.OK).body(null);
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
		if(cl == null)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		
		ClinicAdministrator cadmin = new ClinicAdministrator(cca.getName(), cca.getSurname(), cca.getEmail(), cca.getPassword(), cl);
		int flag = cas.editClinicAdministrator(cadmin);
		
		if(flag == 0)
			return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
		else
		{
			Authentication authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(ca.getEmail(),cca.getPassword()));

			SecurityContextHolder.getContext().setAuthentication(authentication);
			
			return ResponseEntity.status(HttpStatus.OK).body(null);
		}
	}
	
	

}
