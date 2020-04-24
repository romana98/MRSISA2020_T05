package com.project.tim05.dto;

import java.util.*;

public class ClinicAdministratorDTO {
   private String name;
   private String surname;
   private String password;
   private String email;
   
   private ClinicDTO clinics;
   
   private Set<AppointmentDTO> appointments;
   private Set<HallDTO> halls;
   private Set<DoctorDTO> doctors;
   private Set<AppointmentTypeDTO> appointmentTypes;
   
   
   public ClinicAdministratorDTO() {
	super();
	this.appointments = new HashSet<>();
	this.halls = new HashSet<>();
	this.doctors = new HashSet<>();
	this.appointmentTypes = new HashSet<>();
}

public ClinicAdministratorDTO(String name, String surname, String password, String email,
		HashSet<AppointmentDTO> appointment, ClinicDTO clinic, HashSet<HallDTO> hall, HashSet<DoctorDTO> doctor,
		HashSet<AppointmentTypeDTO> appointmentType) {
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

public Set<AppointmentDTO> getAppointment() {
      if (appointments == null)
         appointments = new HashSet<AppointmentDTO>();
      return appointments;
   }
   
  public Iterator<AppointmentDTO> getIteratorAppointment() {
      if (appointments == null)
         appointments = new HashSet<AppointmentDTO>();
      return appointments.iterator();
   }
   
   public void setAppointment(HashSet<AppointmentDTO> newAppointment) {
      removeAllAppointment();
      for (Iterator<AppointmentDTO> iter = newAppointment.iterator(); iter.hasNext();)
         addAppointment((AppointmentDTO)iter.next());
   }
   
   public void addAppointment(AppointmentDTO newAppointment) {
      if (newAppointment == null)
         return;
      if (this.appointments == null)
         this.appointments = new HashSet<AppointmentDTO>();
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
   public Set<HallDTO> getHall() {
      if (halls == null)
         halls = new HashSet<HallDTO>();
      return halls;
   }
   
   public Iterator<HallDTO> getIteratorHall() {
      if (halls == null)
         halls = new HashSet<HallDTO>();
      return halls.iterator();
   }
   
   public void setHall(HashSet<HallDTO> newHall) {
      removeAllHall();
      for (Iterator<HallDTO> iter = newHall.iterator(); iter.hasNext();)
         addHall((HallDTO)iter.next());
   }
   
   public void addHall(HallDTO newHall) {
      if (newHall == null)
         return;
      if (this.halls == null)
         this.halls = new HashSet<HallDTO>();
      if (!this.halls.contains(newHall))
         this.halls.add(newHall);
   }
   
   public void removeHall(HallDTO oldHall) {
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
   public Set<DoctorDTO> getDoctor() {
      if (doctors == null)
         doctors = new HashSet<DoctorDTO>();
      return doctors;
   }
   
   public Iterator<DoctorDTO> getIteratorDoctor() {
      if (doctors == null)
         doctors = new HashSet<DoctorDTO>();
      return doctors.iterator();
   }
   
   public void setDoctor(HashSet<DoctorDTO> newDoctor) {
      removeAllDoctor();
      for (Iterator<DoctorDTO> iter = newDoctor.iterator(); iter.hasNext();)
         addDoctor((DoctorDTO)iter.next());
   }
   
   public void addDoctor(DoctorDTO newDoctor) {
      if (newDoctor == null)
         return;
      if (this.doctors == null)
         this.doctors = new HashSet<DoctorDTO>();
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
   public Set<AppointmentTypeDTO> getAppointmentType() {
      if (appointmentTypes == null)
         appointmentTypes = new HashSet<AppointmentTypeDTO>();
      return appointmentTypes;
   }
   
   public Iterator<AppointmentTypeDTO> getIteratorAppointmentType() {
      if (appointmentTypes == null)
         appointmentTypes = new HashSet<AppointmentTypeDTO>();
      return appointmentTypes.iterator();
   }
   
   public void setAppointmentType(HashSet<AppointmentTypeDTO> newAppointmentType) {
      removeAllAppointmentType();
      for (Iterator<AppointmentTypeDTO> iter = newAppointmentType.iterator(); iter.hasNext();)
         addAppointmentType((AppointmentTypeDTO)iter.next());
   }
   
   public void addAppointmentType(AppointmentTypeDTO newAppointmentType) {
      if (newAppointmentType == null)
         return;
      if (this.appointmentTypes == null)
         this.appointmentTypes = new HashSet<AppointmentTypeDTO>();
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