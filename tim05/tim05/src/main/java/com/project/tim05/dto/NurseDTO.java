package com.project.tim05.dto;


import java.util.*;

public class NurseDTO extends MedicalStaffDTO {
	
   private ArrayList<MedicineDTO> medicine;
   
   
   
   public NurseDTO() {
	super();
}

public NurseDTO(ArrayList<MedicineDTO> medicine) {
	super();
	this.medicine = medicine;
}

public java.util.Collection<MedicineDTO> getMedicine() {
      if (medicine == null)
         medicine = new ArrayList<MedicineDTO>();
      return medicine;
   }
   
   public Iterator<MedicineDTO> getIteratorMedicine() {
      if (medicine == null)
         medicine = new ArrayList<MedicineDTO>();
      return medicine.iterator();
   }
   
   public void setMedicine(ArrayList<MedicineDTO> newMedicine) {
      removeAllMedicine();
      for (Iterator<MedicineDTO> iter = newMedicine.iterator(); iter.hasNext();)
         addMedicine((MedicineDTO)iter.next());
   }
   
   public void addMedicine(MedicineDTO newMedicine) {
      if (newMedicine == null)
         return;
      if (this.medicine == null)
         this.medicine = new ArrayList<MedicineDTO>();
      if (!this.medicine.contains(newMedicine))
         this.medicine.add(newMedicine);
   }
   
   public void removeMedicine(MedicineDTO oldMedicine) {
      if (oldMedicine == null)
         return;
      if (this.medicine != null)
         if (this.medicine.contains(oldMedicine))
            this.medicine.remove(oldMedicine);
   }
   
   public void removeAllMedicine() {
      if (medicine != null)
         medicine.clear();
   }

}