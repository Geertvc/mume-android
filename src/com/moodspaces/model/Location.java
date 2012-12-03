package com.moodspaces.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "location")
public class Location {
    @DatabaseField(generatedId = true)
    private int id;
    
    @DatabaseField(unique = true)
    private String name;

    @DatabaseField
    private double latitude;

    @DatabaseField
    private double longitude;

    public Location() {
        // ORMLite needs a no-arg constructor
    }

    public Location(String name, double lat, double lon) {
        this.name = name;
        this.latitude = lat;
        this.longitude = lon;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
}
