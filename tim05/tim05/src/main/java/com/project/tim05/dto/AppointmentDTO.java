package com.project.tim05.dto;

import java.util.*;

public class AppointmentDTO {
 
   private Date dateTime;
   private int duration;
   private double price;
   private boolean request;
   private boolean finished;
   
   private ClinicDTO clinic;
   private HallDTO hall;
   private DoctorDTO doctor;
   private AppointmentTypeDTO appointmentType;
   private ArrayList<MedicineDTO> medicines;
   private ArrayList<DiagnosisDTO> diagnosises;
   private ArrayList<DiseaseDTO> diseases;
   
   
   
   public AppointmentDTO() {
	super();
	this.medicines = new ArrayList<>();
	this.diagnosises = new ArrayList<>();
	this.diseases = new ArrayList<>();
}

public AppointmentDTO(Date dateTime, int duration, double price, boolean request, boolean finished, ClinicDTO clinic,
		HallDTO hall, DoctorDTO doctor, AppointmentTypeDTO appointmentType, ArrayList<MedicineDTO> medicine,
		ArrayList<DiagnosisDTO> diagnosis, ArrayList<DiseaseDTO> disease) {
	super();
	this.dateTime = dateTime;
	this.duration = duration;
	this.price = price;
	this.request = request;
	this.finished = finished;
	this.clinic = clinic;
	this.hall = hall;
	this.doctor = doctor;
	this.appointmentType = appointmentType;
	this.medicines = medicine;
	this.diagnosises = diagnosis;
	this.diseases = disease;
}

public ArrayList<MedicineDTO> getMedicine() {
      if (medicines == null)
         medicines = new ArrayList<MedicineDTO>();
      return medicines;
   }
   
   public Iterator<MedicineDTO> getIteratorMedicine() {
      if (medicines == null)
         medicines = new ArrayList<MedicineDTO>();
      return medicines.iterator();
   }
   
   public void setMedicine(ArrayList<MedicineDTO> newMedicine) {
      removeAllMedicine();
      for (Iterator<MedicineDTO> iter = newMedicine.iterator(); iter.hasNext();)
         addMedicine((MedicineDTO)iter.next());
   }
   
   public void addMedicine(MedicineDTO newMedicine) {
      if (newMedicine == null)
         return;
      if (this.medicines == null)
         this.medicines = new ArrayList<MedicineDTO>();
      if (!this.medicines.contains(newMedicine))
         this.medicines.add(newMedicine);
   }
   
   public void removeMedicine(MedicineDTO oldMedicine) {
      if (oldMedicine == null)
         return;
      if (this.medicines != null)
         if (this.medicines.contains(oldMedicine))
            this.medicines.remove(oldMedicine);
   }
   
   public void removeAllMedicine() {
      if (medicines != null)
         medicines.clear();
   }
   public ArrayList<DiagnosisDTO> getDiagnosis() {
      if (diagnosises == null)
         diagnosises = new ArrayList<DiagnosisDTO>();
      return diagnosises;
   }
   
   public Iterator<DiagnosisDTO> getIteratorDiagnosis() {
      if (diagnosises == null)
         diagnosises = new ArrayList<DiagnosisDTO>();
      return diagnosises.iterator();
   }
   
   public void setDiagnosis(ArrayList<DiagnosisDTO> newDiagnosis) {
      removeAllDiagnosis();
      for (Iterator<DiagnosisDTO> iter = newDiagnosis.iterator(); iter.hasNext();)
         addDiagnosis((DiagnosisDTO)iter.next());
   }
   
   public Date getDateTime() {
	return dateTime;
}

public void setDateTime(Date dateTime) {
	this.dateTime = dateTime;
}

public int getDuration() {
	return duration;
}

public void setDuration(int duration) {
	this.duration = duration;
}

public double getPrice() {
	return price;
}

public void setPrice(double price) {
	this.price = price;
}

public boolean isRequest() {
	return request;
}

public void setRequest(boolean request) {
	this.request = request;
}

public boolean isFinished() {
	return finished;
}

public void setFinished(boolean finished) {
	this.finished = finished;
}

public ClinicDTO getClinic() {
	return clinic;
}

public void setClinic(ClinicDTO clinic) {
	this.clinic = clinic;
}

public HallDTO getHall() {
	return hall;
}

public void setHall(HallDTO hall) {
	this.hall = hall;
}

public DoctorDTO getDoctor() {
	return doctor;
}

public void setDoctor(DoctorDTO doctor) {
	this.doctor = doctor;
}

public AppointmentTypeDTO getAppointmentType() {
	return appointmentType;
}

public void setAppointmentType(AppointmentTypeDTO appointmentType) {
	this.appointmentType = appointmentType;
}

public ArrayList<DiseaseDTO> getDisease() {
	return diseases;
}

public void setDisease(ArrayList<DiseaseDTO> disease) {
	this.diseases = disease;
}

   public void addDiagnosis(DiagnosisDTO newDiagnosis) {
      if (newDiagnosis == null)
         return;
      if (this.diagnosises == null)
         this.diagnosises = new ArrayList<DiagnosisDTO>();
      if (!this.diagnosises.contains(newDiagnosis))
         this.diagnosises.add(newDiagnosis);
   }
   
   public void removeDiagnosis(DiagnosisDTO oldDiagnosis) {
      if (oldDiagnosis == null)
         return;
      if (this.diagnosises != null)
         if (this.diagnosises.contains(oldDiagnosis))
            this.diagnosises.remove(oldDiagnosis);
   }
   
   public void removeAllDiagnosis() {
      if (diagnosises != null)
         diagnosises.clear();
   }

}