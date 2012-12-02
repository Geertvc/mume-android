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
    private float latitude;

    @DatabaseField
    private float longitude;

    public Location() {
        // ORMLite needs a no-arg constructor
    }

    public Location(float lat, float lon) {
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
