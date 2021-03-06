package com.project.tim05.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.Version;

@Entity
@Table(name = "doctors")
@OnDelete(action = OnDeleteAction.CASCADE)
public class Doctor extends MedicalStaff {

	private static final long serialVersionUID = 1L;

	@ElementCollection
	@CollectionTable(name="Ratings_Doctor", joinColumns=@JoinColumn(name="user_id"))
	@Column(name="ratings")
	private List<Double> ratings = new ArrayList<Double>();

	@Column(name = "active")
	private boolean active;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "clinic_admin", referencedColumnName = "user_id", nullable = true)
	private ClinicAdministrator clinicAdministrator;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "clinic", referencedColumnName = "clinic_id", nullable = true)
	private Clinic clinic;

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "doctor")
	private Set<Appointment> appointments = new HashSet<Appointment>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "appointment_type", referencedColumnName = "appointment_type_id", nullable = true)
	private AppointmentType appointmentType;
	
	@ManyToMany(cascade={CascadeType.ALL}, fetch=FetchType.LAZY)
	private List<Appointment> appointments_many = new ArrayList<Appointment>();
	
	@Version
	private Long version;
	
	public Doctor() {
		super();
		this.active = true;
		this.ratings = new ArrayList<Double>();
	}
	
	//vraca avg rate
	public double getRate() {
		
		double rate = 0.0;
		double zbir = 0.0;
		if(this.ratings.size() == 0) {
			return 0.0;
		}
		for(Double d : this.ratings) {
			zbir+=d;
		}
		rate = zbir/this.ratings.size();
		return rate;
	}

	public List<Double> getRatings() {
		return ratings;
	}



	public void setRatings(List<Double> ratings) {
		this.ratings = ratings;
	}



	public void addAppointment(Appointment ap, WorkCalendar wc) {
		this.appointments.add(ap);
		this.getWorkCalendar().add(wc);
	}

	public Doctor(Integer id, AppointmentType appointmentType) {
		super();
		this.appointmentType = appointmentType;
	}

	public AppointmentType getAppointmentType() {
		return appointmentType;
	}

	public void setAppointmentType(AppointmentType appointmentType) {
		this.appointmentType = appointmentType;
	}

	public Clinic getClinic() {
		return clinic;
	}

	public void setClinic(Clinic clinic) {
		this.clinic = clinic;
	}

	public Set<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(Set<Appointment> appointments) {
		this.appointments = appointments;
	}

	public ClinicAdministrator getClinicAdmin() {
		return clinicAdministrator;
	}

	public void setClinicAdmin(ClinicAdministrator clinicAdmin) {
		this.clinicAdministrator = clinicAdmin;
	}

}
