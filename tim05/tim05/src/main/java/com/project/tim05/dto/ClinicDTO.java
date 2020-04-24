package com.project.tim05.dto;

import java.util.*;

public class ClinicDTO {
   private String name;
   private List<Double> ratings;
   private String address;
   private String description;
   
   private Set<DoctorDTO> doctors;
   private Set<HallDTO> halls;
   private Set<PricelistDTO> pricelist;
   private Set<AppointmentDTO> appointments;
   
   public ClinicDTO() {
		super();
		this.doctors = new HashSet<>();
		this.halls = new HashSet<>();
		this.pricelist = new HashSet<PricelistDTO>();
		this.appointments = new HashSet<>();
	}

	public ClinicDTO(String name, List<Double> ratings, String address, String description, HashSet<DoctorDTO> doctors,
			HashSet<HallDTO> halls, HashSet<PricelistDTO> pricelist, HashSet<AppointmentDTO> appointments) {
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
   
   public java.util.Collection<HallDTO> getHall() {
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
   
public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public List<Double> getRatings() {
	return ratings;
}

public void setRatings(List<Double> ratings) {
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

public Set<PricelistDTO> getPricelist() {
	return pricelist;
}

public void setPricelist(HashSet<PricelistDTO> pricelist) {
	this.pricelist = pricelist;
}

public void removeAllAppointment() {
      if (appointments != null)
         appointments.clear();
   }

}