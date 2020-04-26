package com.project.tim05.dto;

public class HallDTO {
   
    private String name;
    private int number;
    private String admin;
    
    public HallDTO(){
    	
    }
	
    public HallDTO(String name, int number, String admin) {
		super();
		this.name = name;
		this.number = number;
		this.setAdmin(admin);
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

	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}
}