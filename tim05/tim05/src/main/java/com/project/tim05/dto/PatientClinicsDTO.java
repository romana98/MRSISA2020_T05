package com.project.tim05.dto;

public class PatientClinicsDTO {

	String name;
	String address;
	double avg_rating;
	int price;

	public PatientClinicsDTO() {
		super();
	}

	public PatientClinicsDTO(String name, String address, double avg_rating, int price) {
		super();
		this.name = name;
		this.address = address;
		this.avg_rating = avg_rating;
		this.price = price;
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

	public double getAvg_rating() {
		return avg_rating;
	}

	public void setAvg_rating(double avg_rating) {
		this.avg_rating = avg_rating;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

}
