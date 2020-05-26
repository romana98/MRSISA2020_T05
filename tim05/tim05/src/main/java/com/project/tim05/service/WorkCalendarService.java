package com.project.tim05.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.tim05.model.WorkCalendar;
import com.project.tim05.repository.WorkCalendarRespository;
@Service
public class WorkCalendarService{
	
	@Autowired
	private WorkCalendarRespository wcr;

	
	public int addCalendar(WorkCalendar wc) {
		wcr.save(wc);
		
		return 0;
	}
}
