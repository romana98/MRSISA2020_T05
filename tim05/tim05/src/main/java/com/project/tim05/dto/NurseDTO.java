package com.project.tim05.dto;


import java.util.*;

public class NurseDTO extends MedicalStaffDTO {
	
   private ArrayList<MedicineDTO> medicines;
   
   
   
   public NurseDTO() {
	super();
}

public NurseDTO(ArrayList<MedicineDTO> medicines) {
	super();
	this.medicines = medicines;
}

public java.util.Collection<MedicineDTO> getMedicine() {
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

}