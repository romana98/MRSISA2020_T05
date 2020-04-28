package com.project.tim05.model;

import javax.persistence.*;

@Entity
@Table(name="registration_requests")
public class RegistrationRequest {
   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "registration_request_id", unique=true, nullable = false)
	private Integer id;
	
	@Column(name = "email", unique = true, nullable = false)
	private String email;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "surname", nullable = false)
	private String surname;
	
	@Column(name = "address", nullable = false)
	private String address;
	
	@Column(name = "city", nullable = false)
	private String city;
	
	@Column(name = "country", nullable = false)
	private String country;
	
	@Column(name = "phone_number", nullable = false)
	private String phone_number;
	
	@Column(name = "insurance_number", nullable = false)
	private String insurance_number;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="clinic_center_admin", referencedColumnName="clinic_center_admin_id", nullable=true)
	private ClinicCenterAdministrator clinicCenterAdministrator;
   
	
	public RegistrationRequest(String email, String password, String name, String surname, String address, String city,
			String country, String number, String insuranceId) {
		super();
		this.email = email;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.address = address;
		this.city = city;
		this.country = country;
		this.phone_number = number;
		this.insurance_number = insuranceId;
	}
	public RegistrationRequest() {
		super();
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	public String getInsurance_number() {
		return insurance_number;
	}
	public void setInsurance_number(String insurance_number) {
		this.insurance_number = insurance_number;
	}
	public ClinicCenterAdministrator getClinicCenterAdministrator() {
		return clinicCenterAdministrator;
	}
	public void setClinicCenterAdministrator(ClinicCenterAdministrator clinicCenterAdministrator) {
		this.clinicCenterAdministrator = clinicCenterAdministrator;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
}