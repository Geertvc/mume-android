package com.moodspaces.model;

import java.util.Observable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "moodselection")
public class MoodSelection extends Observable {

    public enum Event {
        CHANGED_R, CHANGED_THETA
    }

    @DatabaseField(generatedId = true)
    private int id;
    
    @DatabaseField
    private double r = 0d;
    
    @DatabaseField
    private double theta = 0d;
    
    @DatabaseField(foreign = true)
    private MoodEntry entry;

    public MoodSelection() {
        // ORMLite needs a no-arg constructor
    }

    public MoodSelection(double r, double theta) {
        this.r = r;
        this.theta = theta;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        if (r != getR() && r <= 1) {
            this.r = r;
        }
    }

    public double getTheta() {
        return theta;
    }

    public void setTheta(double theta) {
        if (theta != getTheta()) {
            this.theta = theta;
        }
    }
}