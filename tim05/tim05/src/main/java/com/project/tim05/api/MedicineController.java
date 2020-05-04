package com.project.tim05.api;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.tim05.dto.MedicineDTO;
import com.project.tim05.model.Medicine;
import com.project.tim05.service.MedicineService;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/medicine")
@RestController
public class MedicineController<T> {
	
	private final MedicineService ms;
	
	@Autowired
	public MedicineController(MedicineService ms) {
		this.ms = ms;
	}
	
	@GetMapping("/getMedicines")
	@PreAuthorize("hasRole('CLINIC_CENTER_ADMIN')")
	public List<MedicineDTO> getMedicines(){
		List<MedicineDTO> mDTO = new ArrayList<MedicineDTO>();
		List<Medicine> meds = ms.getMedicines();
		for (Medicine m : meds) {
			mDTO.add(new MedicineDTO(m.getName(), m.getDescription(), m.isAuthenticated()));
		}
		return mDTO;
	}
	
	@PostMapping("/addMedicine")
	public ResponseEntity<T> addMedicine(@Valid @RequestBody MedicineDTO m) {
		int flag = ms.addMedicine(new Medicine(m.getName(), m.getDescription(), m.isAuthenticated()));
		
		if(flag == 0)
			return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
		else
			return ResponseEntity.status(HttpStatus.OK).body(null);
	}

}
