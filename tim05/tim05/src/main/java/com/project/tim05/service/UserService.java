package com.project.tim05.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.tim05.model.User;
import com.project.tim05.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	public User findByEmail(String email) throws UsernameNotFoundException {
		User u = userRepository.findByEmail(email);
		return u;
	}

	public User findById(Long id) throws AccessDeniedException {
		User u = userRepository.findById(id).orElseGet(null);
		return u;
	}

	public List<User> findAll() throws AccessDeniedException {
		List<User> result = userRepository.findAll();
		return result;
	}
	
	public int changeInitialPassword(String email, String password) {
		int flag = 0;
		try {
			
			Connection connection = DriverManager.getConnection("jdbc:postgresql://ec2-54-247-89-181.eu-west-1.compute.amazonaws.com:5432/d1d2a9u0egu6ja", "xslquaksjvvetl", "791a6dd69c36471adccf1118066dae6841cf2b7145d82831471fdd6640e5d99a");
			//Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "");
	        
	      
	        	String query = "UPDATE users set password = ?, last_password_reset_date = ?, enabled = ? WHERE email = ?;";
	 	        PreparedStatement ps = connection.prepareStatement(query);
	 	        ps.setString(1, passwordEncoder.encode(password));
	 			ps.setTimestamp(2, new Timestamp(new Date().getTime()));
	 			ps.setBoolean(3, true);
	 			ps.setString(4, email);
	 		
	 			flag = ps.executeUpdate();
	 			ps.close();
	        
	        			
			connection.close();
			
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return flag;
		}	
			
		return flag;		
	}

	/*public User save(RegistrationRequest userRequest) {
		User u = new User();
		u.setEmail(userRequest.getEmail());
		// pre nego sto postavimo lozinku u atribut hesiramo je
		u.setPassword(passwordEncoder.encode(userRequest.getPassword()));
		u.setName(userRequest.getName());
		u.setSurname(userRequest.getSurname());
		u.setEnabled(true);
		
		List<Authority> auth = authService.findByname("ROLE_USER");
		// u primeru se registruju samo obicni korisnici i u skladu sa tim im se i dodeljuje samo rola USER
		u.setAuthorities(auth);
		
		u = this.userRepository.save(u);
		return u;
	}*/
}
