package com.project.tim05.dto;

import java.util.*;

public class ClinicDTO {
   private String name;
   private Collections ratings;
   private String address;
   private String description;
   
   private ArrayList<DoctorDTO> doctors;
   private ArrayList<Hall> halls;
   private HashMap<AppointmentTypeDTO, Double> pricelist;
   private ArrayList<AppointmentDTO> appointments;
   
   public ClinicDTO() {
		super();
		this.doctors = new ArrayList<>();
		this.halls = new ArrayList<>();
		this.pricelist = new HashMap<AppointmentTypeDTO, Double>();
		this.appointments = new ArrayList<>();
	}

	public ClinicDTO(String name, Collections ratings, String address, String description, ArrayList<DoctorDTO> doctors,
			ArrayList<Hall> halls, HashMap<AppointmentTypeDTO, Double> pricelist, ArrayList<AppointmentDTO> appointments) {
		super();
		this.name = name;
		this.ratings = ratings;
		this.address = address;
		this.description = description;
		this.doctors = doctors;
		this.halls = halls;
		this.pricelist = pricelist;
		this.appointments = appointments;
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
   
   public java.util.Collection<Hall> getHall() {
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
   
public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public Collections getRatings() {
	return ratings;
}

public void setRatings(Collections ratings) {
	this.ratings = ratings;
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

public HashMap<AppointmentTypeDTO, Double> getPricelist() {
	return pricelist;
}

public void setPricelist(HashMap<AppointmentTypeDTO, Double> pricelist) {
	this.pricelist = pricelist;
}

public void removeAllAppointment() {
      if (appointments != null)
         appointments.clear();
   }

}