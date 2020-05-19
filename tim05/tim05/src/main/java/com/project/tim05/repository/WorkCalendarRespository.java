package com.project.tim05.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.tim05.model.WorkCalendar;

public interface WorkCalendarRespository extends JpaRepository<WorkCalendar, Integer>  {

}
