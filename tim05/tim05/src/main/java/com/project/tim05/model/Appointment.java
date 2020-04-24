package com.project.tim05.model;

import java.util.*;

import javax.persistence.*;


@Entity
@Table(name="appointments")
public class Appointment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "appointment_id", unique=true, nullable = false)
	private Integer id;
	
	@Column(name = "dateTime", nullable = false)
	private Date dateTime;
   
	@Column(name = "duration", nullable = false)
	private int duration;
   
	@Column(name = "price", nullable = false)
	private double price;
   
	@Column(name = "request", nullable = false)
	private boolean request;
   
	@Column(name = "finished", nullable = false)
	private boolean finished;
		
	@ManyToOne(cascade = {CascadeType.ALL}, fetch=FetchType.LAZY)
	@JoinColumn(name="appointment_type", referencedColumnName="appointment_type_id", nullable=false)
	private AppointmentType appointmentType;
   
	@ManyToOne(cascade = {CascadeType.ALL}, fetch=FetchType.LAZY)
	@JoinColumn(name="clinic", referencedColumnName="clinic_id", nullable=false)
	private Clinic clinic;
	
	@ManyToOne(cascade = {CascadeType.ALL}, fetch=FetchType.LAZY)
	@JoinColumn(name="hall", referencedColumnName="hall_id", nullable=false)
	private Hall hall;
	
	@ManyToOne(cascade = {CascadeType.ALL}, fetch=FetchType.LAZY)
	@JoinColumn(name="doctor", referencedColumnName="doctor_id", nullable=false)
	private Doctor doctor;
	
	@OneToMany(cascade = {CascadeType.ALL}, fetch=FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name = "medicine_id")
	private Set<Medicine> medicines = new HashSet<Medicine>();
   
	@OneToMany(cascade = {CascadeType.ALL}, fetch=FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name = "diagnosis_id")
	private Set<Diagnosis> diagnosises = new HashSet<Diagnosis>();
   
	@OneToMany(cascade = {CascadeType.ALL}, fetch=FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name = "disease_id")
	private Set<Disease> diseases = new HashSet<Disease>();
	
public Appointment() {
	super();
}
public Appointment(Integer id, Date dateTime, int duration, double price, boolean request, boolean finished,
		Clinic clinic, Hall hall, Doctor doctor, AppointmentType appointmentType, Set<Medicine> medicines,
		Set<Diagnosis> diagnosises, Set<Disease> diseases) {
	super();
	this.id = id;
	this.dateTime = dateTime;
	this.duration = duration;
	this.price = price;
	this.request = request;
	this.finished = finished;
	this.clinic = clinic;
	this.hall = hall;
	this.doctor = doctor;
	this.appointmentType = appointmentType;
	this.medicines = medicines;
	this.diagnosises = diagnosises;
	this.diseases = diseases;
}

public Date getDateTime() {
	return dateTime;
}
public void setDateTime(Date dateTime) {
	this.dateTime = dateTime;
}
public int getDuration() {
	return duration;
}
public void setDuration(int duration) {
	this.duration = duration;
}
public double getPrice() {
	return price;
}
public void setPrice(double price) {
	this.price = price;
}
public boolean isRequest() {
	return request;
}
public void setRequest(boolean request) {
	this.request = request;
}
public boolean isFinished() {
	return finished;
}
public void setFinished(boolean finished) {
	this.finished = finished;
}
public Clinic getClinic() {
	return clinic;
}
public void setClinic(Clinic clinic) {
	this.clinic = clinic;
}
public Hall getHall() {
	return hall;
}
public void setHall(Hall hall) {
	this.hall = hall;
}
public Doctor getDoctor() {
	return doctor;
}
public void setDoctor(Doctor doctor) {
	this.doctor = doctor;
}
public AppointmentType getAppointmentType() {
	return appointmentType;
}
public void setAppointmentType(AppointmentType appointmentType) {
	this.appointmentType = appointmentType;
}
public Set<Medicine> getMedicines() {
	return medicines;
}
public void setMedicines(Set<Medicine> medicines) {
	this.medicines = medicines;
}
public Set<Diagnosis> getDiagnosises() {
	return diagnosises;
}
public void setDiagnosises(Set<Diagnosis> diagnosises) {
	this.diagnosises = diagnosises;
}
public Set<Disease> getDiseases() {
	return diseases;
}
public void setDiseases(Set<Disease> diseases) {
	this.diseases = diseases;
}
   
   
   
   
 
}