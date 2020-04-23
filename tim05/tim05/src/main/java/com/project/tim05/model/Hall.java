package com.project.tim05.model;

import java.util.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Hall {
	
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;
   
   @Column(name = "name", nullable = false)
   private int name;
   @Column(name = "number", nullable = false)
   private int number;
   
   public Hall() {
	   super();
   }
   
   public Hall(int name, int number) {
	   super();
	   this.name = name;
	   this.number = number;
   }
	
	public int getName() {
		return name;
	}
	
	public void setName(int name) {
		this.name = name;
	}
	
	public int getNumber() {
		return number;
	}
	
	public void setNumber(int number) {
		this.number = number;
	}
   
   
   
   
   

}