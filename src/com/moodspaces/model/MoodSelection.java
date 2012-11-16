package com.moodspaces.model;

import java.util.Observable;

public class MoodSelection extends Observable {

    public enum Event {
        CHANGED_R, CHANGED_THETA
    }

    private double r;
    private double theta;

    public MoodSelection() {
        this(0, 0);
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