package com.project.tim05.dto;
/***********************************************************************
 * Module:  Hall.java
 * Author:  Vukasin
 * Purpose: Defines the Class Hall
 ***********************************************************************/

import java.util.*;

public class HallDTO {
   
    private int name;
    private int number;
	
    public HallDTO(int name, int number) {
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