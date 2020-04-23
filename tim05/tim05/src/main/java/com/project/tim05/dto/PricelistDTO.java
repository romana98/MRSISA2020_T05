package com.project.tim05.dto;

import java.util.*;

public class PricelistDTO {
	
   private AppointmentTypeDTO appointmentType;
   private double price;

   public PricelistDTO(AppointmentTypeDTO appointmentType, double price) {
	super();
	this.appointmentType = appointmentType;
	this.price = price;
}

   public PricelistDTO() {
	super();
}

	public AppointmentTypeDTO getAppointmentType() {
		return appointmentType;
	}
	
	public void setAppointmentType(AppointmentTypeDTO appointmentType) {
		this.appointmentType = appointmentType;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
   
   
   
   
}