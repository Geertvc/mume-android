package com.moodspaces.model;

public class Location {
	private float latitude;
	private float longitude;
	
	public Location(float lat, float lon){
		this.latitude = lat;
		this.longitude = lon;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
}
