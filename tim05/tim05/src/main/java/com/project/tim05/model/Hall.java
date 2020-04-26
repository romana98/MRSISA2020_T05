package com.project.tim05.model;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="halls")
public class Hall {
	
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "hall_id", unique=true, nullable = false)
	private Integer id;
   
   @Column(name = "name", nullable = false)
   private String name;
   @Column(name = "number", nullable = false)
   private int number;
   
    @ManyToOne(cascade = {CascadeType.ALL}, fetch=FetchType.LAZY)
	@JoinColumn(name="clinic", referencedColumnName="clinic_id", nullable=true)
	private Clinic clinic;
    
    @ManyToOne(cascade = {CascadeType.ALL}, fetch=FetchType.LAZY)
	@JoinColumn(name="clinic_admin", referencedColumnName="clinic_admin_id", nullable=true)
	private ClinicAdministrator clinicAdministrator;
   
   
   @OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.LAZY, mappedBy="hall")
	private Set<Appointment> appointments = new HashSet<Appointment>();

   
   public Hall() {
	   super();
   }
   
   public Hall(String name, int number) {
	   super();
	   this.name = name;
	   this.number = number;
   }
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getNumber() {
		return number;
	}
	
	public void setNumber(int number) {
		this.number = number;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Set<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(Set<Appointment> appointments) {
		this.appointments = appointments;
	}

	public Clinic getClinic() {
		return clinic;
	}

	public void setClinic(Clinic clinic) {
		this.clinic = clinic;
	}

	public ClinicAdministrator getClinicAdmin() {
		return clinicAdministrator;
	}

	public void setClinicAdmin(ClinicAdministrator clinicAdmin) {
		this.clinicAdministrator = clinicAdmin;
	}
   
   

}