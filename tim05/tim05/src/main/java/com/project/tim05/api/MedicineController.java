package com.project.tim05.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
public class MedicineController {
	
	private final MedicineService ms;
	
	@Autowired
	public MedicineController(MedicineService ms) {
		this.ms = ms;
	}
	
	@GetMapping("/getMedicines")
	public List<MedicineDTO> getMedicines(){
		List<MedicineDTO> mDTO = new ArrayList<MedicineDTO>();
		List<Medicine> meds = ms.getMedicines();
		for (Medicine m : meds) {
			mDTO.add(new MedicineDTO(m.getName(), m.getDescription(), m.isAuthenticated()));
		}
		return mDTO;
	}
	
	@PostMapping("/addMedicine")
	public int addMedicine(@RequestBody MedicineDTO m) {
		ms.addMedicine(new Medicine(m.getName(), m.getDescription(), m.isAuthenticated()));
		return 200;
	}

}
