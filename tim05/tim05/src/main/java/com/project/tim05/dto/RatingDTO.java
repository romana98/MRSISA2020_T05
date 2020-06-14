package com.project.tim05.dto;

public class RatingDTO {
	
	private int param;
	private int rate;
	private int apt_id;
	
	public RatingDTO() {
		super();
	}

	public RatingDTO(int param, int rate, int apt_id) {
		super();
		this.param = param;
		this.rate = rate;
		this.apt_id = apt_id;
	}

	public int getParam() {
		return param;
	}

	public void setParam(int param) {
		this.param = param;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public int getApt_id() {
		return apt_id;
	}

	public void setApt_id(int apt_id) {
		this.apt_id = apt_id;
	}

}
