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

import com.project.tim05.dto.DiagnosisDTO;
import com.project.tim05.model.Diagnosis;
import com.project.tim05.service.DiagnosisService;


@CrossOrigin(origins = "https://localhost:4200")

@RequestMapping("/diagnosis")
@RestController
public class DiagnosisContoller<T> {

	private final DiagnosisService ds;
	
	@Autowired
	public DiagnosisContoller(DiagnosisService ds) {
		this.ds = ds;
	}
	
	@GetMapping("/getDiagnosises")
	@PreAuthorize("hasRole('CLINIC_CENTER_ADMIN') || hasRole('DOCTOR')")
	public List<DiagnosisDTO> getDiagnosis(){
		List<DiagnosisDTO> dDTO = new ArrayList<DiagnosisDTO>();
		List<Diagnosis> diags = ds.getDiagnosises();
		for (Diagnosis d : diags) {
			dDTO.add(new DiagnosisDTO(d.getId(), d.getName(), d.getDescription()));
		}
		return dDTO;
	}
	
	@PostMapping("/addDiagnosis")
	@PreAuthorize("hasRole('CLINIC_CENTER_ADMIN')")
	public ResponseEntity<T> addMedicine(@Valid @RequestBody DiagnosisDTO d) {
		int flag = ds.addDiagnosis(new Diagnosis(d.getName(), d.getDescription()));
	
		if(flag == 0)
			return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
		else
			return ResponseEntity.status(HttpStatus.OK).body(null);
	}
}
