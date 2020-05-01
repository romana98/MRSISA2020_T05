package com.project.tim05.dto;

import javax.validation.constraints.NotNull;

public class NurseDTO extends MedicalStaffDTO {
   
	@NotNull
   private int clinic_id;
   
   public NurseDTO() {
	super();
   }

	public int getClinic_id() {
		return clinic_id;
	}
	
	public void setClinic_id(int clinic_id) {
		this.clinic_id = clinic_id;
	}

	
	   

}