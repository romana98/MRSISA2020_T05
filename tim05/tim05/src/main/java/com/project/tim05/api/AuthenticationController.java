package com.project.tim05.api;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.tim05.dto.JwtAuthenticationRequestDTO;
import com.project.tim05.dto.UserTokenStateDTO;
import com.project.tim05.model.User;
import com.project.tim05.security.TokenUtils;
import com.project.tim05.service.CustomUserDetailsService;
import com.project.tim05.service.UserService;

//Kontroler zaduzen za autentifikaciju korisnika
@RestController
@CrossOrigin(origins = "https://eclinic05.herokuapp.com")
@RequestMapping(value = "/auth")
public class AuthenticationController {

	@Autowired
	private TokenUtils tokenUtils;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	@Autowired
	private UserService userService;	
	

	// Prvi endpoint koji pogadja korisnik kada se loguje.
	// Tada zna samo svoje korisnicko ime i lozinku i to prosledjuje na backend.
	@PostMapping("/login")
	public ResponseEntity<UserTokenStateDTO> createAuthenticationToken(@RequestBody JwtAuthenticationRequestDTO authenticationRequest) {
		Authentication authentication = null;
		try {
			authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),
							authenticationRequest.getPassword()));
		} catch (DisabledException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
		}
		

		// Ubaci korisnika u trenutni security kontekst
		SecurityContextHolder.getContext().setAuthentication(authentication);
	
		// Kreiraj token za tog korisnika
		User user = (User) authentication.getPrincipal();
		String role = "";
		for (GrantedAuthority e : user.getAuthorities()) {
			 role = e.getAuthority();
		}
		String jwt = tokenUtils.generateToken(user.getEmail(),user.getId(), role );
		int expiresIn = tokenUtils.getExpiredIn();

		// Vrati token kao odgovor na uspesnu autentifikaciju
		return ResponseEntity.ok(new UserTokenStateDTO(jwt, expiresIn));
	}

	

	// U slucaju isteka vazenja JWT tokena, endpoint koji se poziva da se token osvezi
	@PostMapping(value = "/refresh")
	public ResponseEntity<UserTokenStateDTO> refreshAuthenticationToken(HttpServletRequest request) {

		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		User user = (User) this.userDetailsService.loadUserByUsername(username);

		if (this.tokenUtils.canTokenBeRefreshed(token, user.getLastPasswordResetDate())) {
			String refreshedToken = tokenUtils.refreshToken(token);
			int expiresIn = tokenUtils.getExpiredIn();

			return ResponseEntity.ok(new UserTokenStateDTO(refreshedToken, expiresIn));
		} else {
			UserTokenStateDTO userTokenState = new UserTokenStateDTO();
			return ResponseEntity.badRequest().body(userTokenState);
		}
	}

	@PostMapping(value = "/changePassword")
	public ResponseEntity<?> changePassword(@RequestBody PasswordChanger passwordChanger) {
		int flag = -1;
		
		flag = userService.changeInitialPassword(passwordChanger.email, passwordChanger.password);
		
		if(flag == 0)
			return ResponseEntity.badRequest().body(null);
		
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(passwordChanger.email,
						passwordChanger.password));
		
		// Ubaci korisnika u trenutni security kontekst
		SecurityContextHolder.getContext().setAuthentication(authentication);
	
		// Kreiraj token za tog korisnika
		User user = (User) authentication.getPrincipal();
		String role = "";
		for (GrantedAuthority e : user.getAuthorities()) {
			 role = e.getAuthority();
		}
		String jwt = tokenUtils.generateToken(user.getEmail(),user.getId(), role );
		int expiresIn = tokenUtils.getExpiredIn();

		// Vrati token kao odgovor na uspesnu autentifikaciju
		return ResponseEntity.ok(new UserTokenStateDTO(jwt, expiresIn));
	}

	static class PasswordChanger {
		public String password;
		public String email;
	}
}