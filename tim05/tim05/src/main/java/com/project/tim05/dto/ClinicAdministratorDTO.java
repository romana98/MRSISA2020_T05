package com.project.tim05.dto;

import java.util.*;

public class ClinicAdministratorDTO {
   private String name;
   private String surname;
   private String password;
   private String email;
   
   private ArrayList<AppointmentDTO> appointments;
   private ClinicDTO clinics;
   private ArrayList<Hall> halls;
   private ArrayList<DoctorDTO> doctors;
   private ArrayList<AppointmentTypeDTO> appointmentTypes;
   
   
   public ClinicAdministratorDTO() {
	super();
	this.appointments = new ArrayList<>();
	this.halls = new ArrayList<>();
	this.doctors = new ArrayList<>();
	this.appointmentTypes = new ArrayList<>();
}

public ClinicAdministratorDTO(String name, String surname, String password, String email,
		ArrayList<AppointmentDTO> appointment, ClinicDTO clinic, ArrayList<Hall> hall, ArrayList<DoctorDTO> doctor,
		ArrayList<AppointmentTypeDTO> appointmentType) {
	super();
	this.name = name;
	this.surname = surname;
	this.password = password;
	this.email = email;
	this.appointments = appointment;
	this.clinics = clinic;
	this.halls = hall;
	this.doctors = doctor;
	this.appointmentTypes = appointmentType;
}

public ArrayList<AppointmentDTO> getAppointment() {
      if (appointments == null)
         appointments = new ArrayList<AppointmentDTO>();
      return appointments;
   }
   
  public Iterator<AppointmentDTO> getIteratorAppointment() {
      if (appointments == null)
         appointments = new ArrayList<AppointmentDTO>();
      return appointments.iterator();
   }
   
   public void setAppointment(ArrayList<AppointmentDTO> newAppointment) {
      removeAllAppointment();
      for (Iterator<AppointmentDTO> iter = newAppointment.iterator(); iter.hasNext();)
         addAppointment((AppointmentDTO)iter.next());
   }
   
   public void addAppointment(AppointmentDTO newAppointment) {
      if (newAppointment == null)
         return;
      if (this.appointments == null)
         this.appointments = new ArrayList<AppointmentDTO>();
      if (!this.appointments.contains(newAppointment))
         this.appointments.add(newAppointment);
   }
   
   public void removeAppointment(AppointmentDTO oldAppointment) {
      if (oldAppointment == null)
         return;
      if (this.appointments != null)
         if (this.appointments.contains(oldAppointment))
            this.appointments.remove(oldAppointment);
   }
   
   public void removeAllAppointment() {
      if (appointments != null)
         appointments.clear();
   }
   public ArrayList<Hall> getHall() {
      if (halls == null)
         halls = new ArrayList<Hall>();
      return halls;
   }
   
   public Iterator<Hall> getIteratorHall() {
      if (halls == null)
         halls = new ArrayList<Hall>();
      return halls.iterator();
   }
   
   public void setHall(ArrayList<Hall> newHall) {
      removeAllHall();
      for (Iterator<Hall> iter = newHall.iterator(); iter.hasNext();)
         addHall((Hall)iter.next());
   }
   
   public void addHall(Hall newHall) {
      if (newHall == null)
         return;
      if (this.halls == null)
         this.halls = new ArrayList<Hall>();
      if (!this.halls.contains(newHall))
         this.halls.add(newHall);
   }
   
   public void removeHall(Hall oldHall) {
      if (oldHall == null)
         return;
      if (this.halls != null)
         if (this.halls.contains(oldHall))
            this.halls.remove(oldHall);
   }
   
   public void removeAllHall() {
      if (halls != null)
         halls.clear();
   }
   public ArrayList<DoctorDTO> getDoctor() {
      if (doctors == null)
         doctors = new ArrayList<DoctorDTO>();
      return doctors;
   }
   
   public Iterator<DoctorDTO> getIteratorDoctor() {
      if (doctors == null)
         doctors = new ArrayList<DoctorDTO>();
      return doctors.iterator();
   }
   
   public void setDoctor(ArrayList<DoctorDTO> newDoctor) {
      removeAllDoctor();
      for (Iterator<DoctorDTO> iter = newDoctor.iterator(); iter.hasNext();)
         addDoctor((DoctorDTO)iter.next());
   }
   
   public void addDoctor(DoctorDTO newDoctor) {
      if (newDoctor == null)
         return;
      if (this.doctors == null)
         this.doctors = new ArrayList<DoctorDTO>();
      if (!this.doctors.contains(newDoctor))
         this.doctors.add(newDoctor);
   }
   
   public void removeDoctor(DoctorDTO oldDoctor) {
      if (oldDoctor == null)
         return;
      if (this.doctors != null)
         if (this.doctors.contains(oldDoctor))
            this.doctors.remove(oldDoctor);
   }
   
   public void removeAllDoctor() {
      if (doctors != null)
         doctors.clear();
   }
   public ArrayList<AppointmentTypeDTO> getAppointmentType() {
      if (appointmentTypes == null)
         appointmentTypes = new ArrayList<AppointmentTypeDTO>();
      return appointmentTypes;
   }
   
   public Iterator<AppointmentTypeDTO> getIteratorAppointmentType() {
      if (appointmentTypes == null)
         appointmentTypes = new ArrayList<AppointmentTypeDTO>();
      return appointmentTypes.iterator();
   }
   
   public void setAppointmentType(ArrayList<AppointmentTypeDTO> newAppointmentType) {
      removeAllAppointmentType();
      for (Iterator<AppointmentTypeDTO> iter = newAppointmentType.iterator(); iter.hasNext();)
         addAppointmentType((AppointmentTypeDTO)iter.next());
   }
   
   public void addAppointmentType(AppointmentTypeDTO newAppointmentType) {
      if (newAppointmentType == null)
         return;
      if (this.appointmentTypes == null)
         this.appointmentTypes = new ArrayList<AppointmentTypeDTO>();
      if (!this.appointmentTypes.contains(newAppointmentType))
         this.appointmentTypes.add(newAppointmentType);
   }
   
   public void removeAppointmentType(AppointmentTypeDTO oldAppointmentType) {
      if (oldAppointmentType == null)
         return;
      if (this.appointmentTypes != null)
         if (this.appointmentTypes.contains(oldAppointmentType))
            this.appointmentTypes.remove(oldAppointmentType);
   }
   
   public void removeAllAppointmentType() {
      if (appointmentTypes != null)
         appointmentTypes.clear();
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

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public ClinicDTO getClinic() {
	return clinics;
}

public void setClinic(ClinicDTO clinic) {
	this.clinics = clinic;
}
   
   

}