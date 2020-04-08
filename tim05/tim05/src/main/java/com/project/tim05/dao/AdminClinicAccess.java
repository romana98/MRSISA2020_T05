package com.project.tim05.dao;

import java.util.List;

import com.project.tim05.model.AdminClinic;

public interface AdminClinicAccess {

	void addAdminClinic(AdminClinic ac);
	
	List<AdminClinic> getAdminClinics();

}
