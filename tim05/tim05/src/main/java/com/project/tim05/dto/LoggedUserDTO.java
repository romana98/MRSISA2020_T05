package com.project.tim05.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

public class LoggedUserDTO {
	
	@NotBlank 
	@NotNull
	@Pattern(regexp="[a-z]+[a-z0-9._]*[a-z0-9]+@[a-z]*.com")
	private String email;
	
	@NotBlank 
	@NotNull
	@Length(min = 8)
	private String password;
	
	private String type;
	
	public LoggedUserDTO() {
		super();
	}

	public LoggedUserDTO(@NotBlank @NotNull @Pattern(regexp = "[a-z]+[a-z0-9._]*[a-z0-9]+@[a-z]*.com") String email,
			@NotBlank @NotNull @Length(min = 8) String password, String type) {
		super();
		this.email = email;
		this.password = password;
		this.setType(type);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
