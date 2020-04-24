package com.project.tim05.model;


import javax.persistence.*;

@Entity
@Table(name="halls")
public class Hall {
	
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "hall_id", unique=true, nullable = false)
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