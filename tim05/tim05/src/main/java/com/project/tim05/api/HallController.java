package com.project.tim05.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.tim05.dto.HallDTO;
import com.project.tim05.model.ClinicAdministrator;
import com.project.tim05.model.Hall;
import com.project.tim05.service.ClinicAdministratorService;
import com.project.tim05.service.HallService;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/halls")
@RestController
public class HallController {
	
	private final HallService hs;
	private final ClinicAdministratorService cas;
	
	@Autowired
	public HallController(HallService hs, ClinicAdministratorService cas) {
		this.hs = hs;
		this.cas = cas;
	}
	
	@PostMapping("/addHall")
	public void addHall(@RequestBody HallDTO hall) {
		Hall h = new Hall();
		h.setName(hall.getName());
		h.setNumber(hall.getNumber());
		List<ClinicAdministrator> list = cas.getClinicAdministrators();
		for(ClinicAdministrator ca : list) {
			if(ca.getEmail().equals(hall.getAdmin())) {
				h.setClinic(ca.getClinic());
				h.setClinicAdmin(ca);
				break;
			}
		}
		hs.addHall(h);
	}
	

}
