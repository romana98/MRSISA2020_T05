package com.project.tim05.dto;


import java.util.*;

public class PatientDTO {
   
   private String email;
   private String password;
   private String name;
   private String surname;
   private String address;
   private String city;
   private String town;
   private int number;
   private int insuranceId;
   
   public MedicalRecordDTO medicalRecord;
   public ArrayList<AppointmentDTO> appointment;
   public ArrayList<ClinicDTO> clinic;
   
   public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getSurname() {
	return surname;
}

public void setSurname(String surname) {
	this.surname = surname;
}

public String getAddress() {
	return address;
}

public void setAddress(String address) {
	this.address = address;
}

public String getCity() {
	return city;
}

public void setCity(String city) {
	this.city = city;
}

public String getTown() {
	return town;
}

public void setTown(String town) {
	this.town = town;
}

public int getNumber() {
	return number;
}

public void setNumber(int number) {
	this.number = number;
}

public int getInsuranceId() {
	return insuranceId;
}

public void setInsuranceId(int insuranceId) {
	this.insuranceId = insuranceId;
}

public MedicalRecordDTO getMedicalRecord() {
	return medicalRecord;
}

public void setMedicalRecord(MedicalRecordDTO medicalRecord) {
	this.medicalRecord = medicalRecord;
}

public PatientDTO() {
	super();
}

public PatientDTO(String email, String password, String name, String surname, String address, String city, String town,
		int number, int insuranceId, MedicalRecordDTO medicalRecord, ArrayList<AppointmentDTO> appointment,
		ArrayList<ClinicDTO> clinic) {
	super();
	this.email = email;
	this.password = password;
	this.name = name;
	this.surname = surname;
	this.address = address;
	this.city = city;
	this.town = town;
	this.number = number;
	this.insuranceId = insuranceId;
	this.medicalRecord = medicalRecord;
	this.appointment = appointment;
	this.clinic = clinic;
}

public ArrayList<AppointmentDTO> getAppointment() {
      if (appointment == null)
         appointment = new ArrayList<AppointmentDTO>();
      return appointment;
   }
   
   public Iterator<AppointmentDTO> getIteratorAppointment() {
      if (appointment == null)
         appointment = new ArrayList<AppointmentDTO>();
      return appointment.iterator();
   }
   

   public void setAppointment(ArrayList<AppointmentDTO> newAppointment) {
      removeAllAppointment();
      for (Iterator<AppointmentDTO> iter = newAppointment.iterator(); iter.hasNext();)
         addAppointment((AppointmentDTO)iter.next());
   }
   
   public void addAppointment(AppointmentDTO newAppointment) {
      if (newAppointment == null)
         return;
      if (this.appointment == null)
         this.appointment = new ArrayList<AppointmentDTO>();
      if (!this.appointment.contains(newAppointment))
         this.appointment.add(newAppointment);
   }
   
   public void removeAppointment(AppointmentDTO oldAppointment) {
      if (oldAppointment == null)
         return;
      if (this.appointment != null)
         if (this.appointment.contains(oldAppointment))
            this.appointment.remove(oldAppointment);
   }
   
   public void removeAllAppointment() {
      if (appointment != null)
         appointment.clear();
   }
   
   public ArrayList<ClinicDTO> getClinic() {
      if (clinic == null)
         clinic = new ArrayList<ClinicDTO>();
      return clinic;
   }
   
   public Iterator<ClinicDTO> getIteratorClinic() {
      if (clinic == null)
         clinic = new ArrayList<ClinicDTO>();
      return clinic.iterator();
   }
   
   public void setClinic(ArrayList<ClinicDTO> newClinic) {
      removeAllClinic();
      for (Iterator<ClinicDTO> iter = newClinic.iterator(); iter.hasNext();)
         addClinic((ClinicDTO)iter.next());
   }
   
   public void addClinic(ClinicDTO newClinic) {
      if (newClinic == null)
         return;
      if (this.clinic == null)
         this.clinic = new ArrayList<ClinicDTO>();
      if (!this.clinic.contains(newClinic))
         this.clinic.add(newClinic);
   }
   
   public void removeClinic(ClinicDTO oldClinic) {
      if (oldClinic == null)
         return;
      if (this.clinic != null)
         if (this.clinic.contains(oldClinic))
            this.clinic.remove(oldClinic);
   }
   
   public void removeAllClinic() {
      if (clinic != null)
         clinic.clear();
   }

}