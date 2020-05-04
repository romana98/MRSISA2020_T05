package com.project.tim05.dto;

// DTO za login
public class JwtAuthenticationRequestDTO {
	
    private String email;
    private String password;

    public JwtAuthenticationRequestDTO() {
        super();
    }

    public JwtAuthenticationRequestDTO(String username, String password) {
        this.setEmail(username);
        this.setPassword(password);
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String username) {
        this.email = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
