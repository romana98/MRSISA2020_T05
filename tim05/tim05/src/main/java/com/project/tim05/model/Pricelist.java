package com.project.tim05.model;


import javax.persistence.*;


@Entity
@Table(name="pricelists")
public class Pricelist {
   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pricelist_id", unique=true, nullable = false)
	private Integer id;
	
	@Column(name = "price", nullable = false)
	private double price;
	
	@ManyToOne(cascade = {CascadeType.ALL}, fetch=FetchType.LAZY)
	@JoinColumn(name="appointment_type", referencedColumnName="appointment_type_id", nullable=false)
	private AppointmentType appointmentType;
	
	@ManyToOne(cascade = {CascadeType.ALL}, fetch=FetchType.LAZY)
	@JoinColumn(name="clinic", referencedColumnName="clinic_id", nullable=true)
	private Clinic clinic;
   
		
	public Pricelist(AppointmentType appointmentType, double price) {
		super();
		this.appointmentType = appointmentType;
		this.price = price;
	}
	public Pricelist() {
		super();
	}
	public AppointmentType getAppointmentType() {
		return appointmentType;
	}
	public void setAppointmentType(AppointmentType appointmentType) {
		this.appointmentType = appointmentType;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Clinic getClinic() {
		return clinic;
	}
	public void setClinic(Clinic clinic) {
		this.clinic = clinic;
	}
	
	
	
	

}