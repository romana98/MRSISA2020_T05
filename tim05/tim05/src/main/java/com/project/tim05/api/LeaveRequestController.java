package com.project.tim05.api;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.tim05.service.LeaveRequestService;

public class LeaveRequestController {
	
	@Autowired
	private final LeaveRequestService lrs;

	public LeaveRequestController(LeaveRequestService lrs) {
		this.lrs = lrs;
	}
	
	
	
	

}
