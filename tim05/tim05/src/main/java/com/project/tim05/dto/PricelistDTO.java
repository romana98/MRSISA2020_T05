package com.project.tim05.dto;


public class PricelistDTO {
	
   private int id;
   private String apt_type;
   private double price;
   private int clinic_id;
   
public PricelistDTO(String apt_type, double price, int clinic_id) {
	super();
	this.apt_type = apt_type;
	this.price = price;
	this.clinic_id = clinic_id;
}

public PricelistDTO() {
	super();
}

public String getApt_type() {
	return apt_type;
}

public void setApt_type(String apt_type) {
	this.apt_type = apt_type;
}

public double getPrice() {
	return price;
}

public void setPrice(double price) {
	this.price = price;
}

public int getClinic_id() {
	return clinic_id;
}

public void setClinic_id(int clinic_id) {
	this.clinic_id = clinic_id;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}
   
}