package com.project.tim05.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class HallDTO {
   
	@NotBlank 
	@NotNull
	@Pattern(regexp="([A-Z][a-z]+[ ]*){1,}")
    private String name;
	 
	
	@NotNull
	@Min(value=0)
    private int number;
	    
    public HallDTO(){
    	
    }
	
    public HallDTO(String name, int number) {
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

}