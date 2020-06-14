package com.project.tim05.model;

import java.util.*;

import javax.persistence.*;

@Entity
@Table(name = "appointments")
public class Appointment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "appointment_id", unique = true, nullable = false)
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

	@Column(name = "predefined", nullable = false)
	private boolean predefined;

	@Column(name = "description", length = 10485760)
	private String description;

	@Column(name = "operation", nullable = false)
	private boolean operation;

	@Column(name = "ratedClinic", nullable = false)
	private boolean ratedClinic;
	
	@Column(name = "ratedDoctor", nullable = false)
	private boolean ratedDoctor;
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="appointment_type", referencedColumnName="appointment_type_id", nullable=true)
	private AppointmentType appointmentType;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "clinic", referencedColumnName = "clinic_id", nullable = true)
	private Clinic clinic;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "hall", referencedColumnName = "hall_id", nullable = true)
	private Hall hall;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "doctor", referencedColumnName = "user_id", nullable = true)
	private Doctor doctor;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "clinic_admin", referencedColumnName = "user_id", nullable = true)
	private ClinicAdministrator clinicAdministrator;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "medical_record", referencedColumnName = "medical_record_id", nullable = true)
	private MedicalRecord medicalRecord;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "patient", referencedColumnName = "user_id", nullable = true)
	private Patient patient;

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "appointment")
	private Set<AppointmentMedicine> medicines = new HashSet<AppointmentMedicine>();

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "doctors", referencedColumnName = "doctors_id", nullable = true)
	private Set<Doctor> doctors = new HashSet<Doctor>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "diagnosis", referencedColumnName = "diagnosis_id", nullable = true)
	private Diagnosis diagnosis;

	public Appointment() {
		super();
		this.operation = false;
		this.ratedDoctor = false;
		this.ratedClinic = false;
	}

	public Appointment(Integer id, Date dateTime, int duration, double price, boolean request, boolean predefined,
			Clinic clinic, Hall hall, Doctor doctor, AppointmentType appointmentType,
			Set<AppointmentMedicine> medicines, Diagnosis diagnosises) {
		super();
		this.id = id;
		this.dateTime = dateTime;
		this.duration = duration;
		this.price = price;
		this.request = request;
		this.finished = false;
		this.predefined = predefined;
		this.clinic = clinic;
		this.hall = hall;
		this.doctor = doctor;
		this.appointmentType = appointmentType;
		this.medicines = medicines;
		this.diagnosis = diagnosises;
		this.ratedClinic = false;
		this.ratedDoctor = false;
	}
	
	public boolean isRatedClinic() {
		return ratedClinic;
	}
	public void setRatedClinic(boolean ratedClinic) {
		this.ratedClinic = ratedClinic;
	}
	public boolean isRatedDoctor() {
		return ratedDoctor;
	}
	public void setRatedDoctor(boolean ratedDoctor) {
		this.ratedDoctor = ratedDoctor;
	}
	public ClinicAdministrator getClinicAdministrator() {
		return clinicAdministrator;
	}
	public void setClinicAdministrator(ClinicAdministrator clinicAdministrator) {
		this.clinicAdministrator = clinicAdministrator;
	}
	public Appointment(Integer id, Date dateTime, int duration, double price, boolean request, boolean finished,
			boolean predefined, String description, boolean ratedClinic, boolean ratedDoctor,
			AppointmentType appointmentType, Clinic clinic, Hall hall, Doctor doctor,
			ClinicAdministrator clinicAdministrator, MedicalRecord medicalRecord, Patient patient,
			Set<AppointmentMedicine> medicines, Diagnosis diagnosis) {
		super();
		this.id = id;
		this.dateTime = dateTime;
		this.duration = duration;
		this.price = price;
		this.request = request;
		this.finished = finished;
		this.predefined = predefined;
		this.description = description;
		this.ratedClinic = ratedClinic;
		this.ratedDoctor = ratedDoctor;
		this.appointmentType = appointmentType;
		this.clinic = clinic;
		this.hall = hall;
		this.doctor = doctor;
		this.clinicAdministrator = clinicAdministrator;
		this.medicalRecord = medicalRecord;
		this.patient = patient;
		this.medicines = medicines;
		this.diagnosis = diagnosis;
	}

	public Set<Doctor> getDoctors() {
		return doctors;
	}

	public void setDoctors(Set<Doctor> doctors) {
		this.doctors = doctors;
	}

	public boolean isOperation() {
		return operation;
	}

	public void setOperation(boolean operation) {
		this.operation = operation;
	}

	public boolean isPredefined() {
		return predefined;
	}

	public void setPredefined(boolean predefined) {
		this.predefined = predefined;
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

	public Set<AppointmentMedicine> getMedicines() {
		return medicines;
	}

	public void setMedicines(Set<AppointmentMedicine> medicines) {
		this.medicines = medicines;
	}

	public Diagnosis getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(Diagnosis diagnosis) {
		this.diagnosis = diagnosis;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ClinicAdministrator getClinicAdmin() {
		return clinicAdministrator;
	}

	public void setClinicAdmin(ClinicAdministrator clinicAdmin) {
		this.clinicAdministrator = clinicAdmin;
	}

	public MedicalRecord getMedicalRecord() {
		return medicalRecord;
	}

	public void setMedicalRecord(MedicalRecord medicalRecord) {
		this.medicalRecord = medicalRecord;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}