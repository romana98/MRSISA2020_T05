package com.project.tim05.model;


import java.util.*;

import javax.persistence.*;

@Entity
@Table(name="clinics", uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "address"})})
public class Clinic {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "clinic_id", unique=true, nullable = false)
	private Integer id;
	
	@Column(name = "name", nullable = false)
	private String name;
   
	@Column(name = "address", nullable = false)
	private String address;
   
	@Column(name = "description", nullable = false)
	private String description;
	
	@ElementCollection
	@CollectionTable(name="Ratings", joinColumns=@JoinColumn(name="clinic_id"))
	@Column(name="ratings")
	private List<Double> ratings = new ArrayList<Double>();
  
	@ManyToOne(cascade = {CascadeType.MERGE}, fetch=FetchType.LAZY)
	@JoinColumn(name="clinic_center_admin", referencedColumnName="clinic_center_admin_id", nullable=true)
	private ClinicCenterAdministrator clinicCenterAdministrator;
   
	@ManyToOne(cascade = {CascadeType.MERGE}, fetch=FetchType.LAZY)
	@JoinColumn(name="patient", referencedColumnName="patient_id", nullable=true)
	private Patient patient;
	

	@OneToMany(cascade={CascadeType.MERGE}, fetch=FetchType.LAZY, mappedBy="clinic")
	private Set<ClinicAdministrator> clinicAdmin = new HashSet<ClinicAdministrator>();
	
	@OneToMany(cascade={CascadeType.MERGE}, fetch=FetchType.LAZY, mappedBy="clinic")
	private Set<Doctor> doctors = new HashSet<Doctor>();
	
	@OneToMany(cascade={CascadeType.MERGE}, fetch=FetchType.LAZY, mappedBy="clinic")
	private Set<Hall> halls = new HashSet<Hall>();
	
	@OneToMany(cascade={CascadeType.MERGE}, fetch=FetchType.LAZY, mappedBy="clinic")
	private Set<Pricelist> pricelist = new HashSet<Pricelist>();
	
	@OneToMany(cascade={CascadeType.MERGE}, fetch=FetchType.LAZY, mappedBy="clinic")
	private Set<Appointment> appointments = new HashSet<Appointment>();

	public Clinic() {
		super();
	}
	
	public Clinic(String name, String address, String description) {
		this.name = name;
		this.address = address;
		this.description = description;
	}

	public Clinic(Integer id, String name, String address, String description, ArrayList<Double> ratings,
			Set<Doctor> doctors, Set<Hall> halls, Set<Pricelist> pricelist, Set<Appointment> appointments) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.description = description;
		this.ratings = ratings;
		this.doctors = doctors;
		this.halls = halls;
		this.pricelist = pricelist;
		this.appointments = appointments;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Double> getRatings() {
		return ratings;
	}

	public void setRatings(List<Double> ratings) {
		this.ratings = ratings;
	}

	public ClinicCenterAdministrator getClinicCenterAdmin() {
		return clinicCenterAdministrator;
	}

	public void setClinicCenterAdmin(ClinicCenterAdministrator clinicCenterAdmin) {
		this.clinicCenterAdministrator = clinicCenterAdmin;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Set<ClinicAdministrator> getClinicAdmin() {
		return clinicAdmin;
	}

	public void setClinicAdmin(Set<ClinicAdministrator> clinicAdmin) {
		this.clinicAdmin = clinicAdmin;
	}

	public Set<Doctor> getDoctors() {
		return doctors;
	}

	public void setDoctors(Set<Doctor> doctors) {
		this.doctors = doctors;
	}

	public Set<Hall> getHalls() {
		return halls;
	}

	public void setHalls(Set<Hall> halls) {
		this.halls = halls;
	}

	public Set<Pricelist> getPricelist() {
		return pricelist;
	}

	public void setPricelist(Set<Pricelist> pricelist) {
		this.pricelist = pricelist;
	}

	public Set<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(Set<Appointment> appointments) {
		this.appointments = appointments;
	}

	

}