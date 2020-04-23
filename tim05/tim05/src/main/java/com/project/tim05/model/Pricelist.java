package com.project.tim05.model;


import java.util.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.project.tim05.dto.AppointmentTypeDTO;

public class Pricelist {
   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private AppointmentType appointmentType;
	@Column(name = "price", nullable = false)
	private double price;
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
	
	
	
	

}