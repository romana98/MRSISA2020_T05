package com.project.tim05.dto;


public class NurseDTO extends MedicalStaffDTO {
   
   private ClinicDTO clinic;
   
   public NurseDTO() {
	super();
   }

	public ClinicDTO getClinic() {
		return clinic;
	}
	
	public void setClinic(ClinicDTO clinic) {
		this.clinic = clinic;
	}
	   

}