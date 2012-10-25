package com.moodspaces.model;

public class MoodSelection {
	
	private float r;
	private float theta;
	
	public MoodSelection(float r, float theta){
		this.r = r;
		this.theta = theta;
	}

	public float getR() {
		return r;
	}

	public void setR(float r) {
		this.r = r;
	}

	public float getTheta() {
		return theta;
	}

	public void setPhi(float theta) {
		this.theta = theta;
	}
}
