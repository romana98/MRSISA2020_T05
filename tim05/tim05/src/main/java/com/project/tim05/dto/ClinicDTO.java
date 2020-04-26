package com.project.tim05.dto;

import java.util.*;

public class ClinicDTO {
   private String name;
   private String address;
   private String description;
 
   public ClinicDTO() {
		super();
		
	}

	public ClinicDTO(String name, List<Double> ratings, String address, String description) {
		super();
		this.name = name;
		this.address = address;
		this.description = description;
		
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
	

}