package com.moodspaces.model;

import java.util.HashSet;
import java.util.Set;

import com.orm.androrm.Model;
import com.orm.androrm.field.DoubleField;

public class MoodSelection extends Model {

    protected DoubleField r = new DoubleField();
    protected DoubleField theta = new DoubleField();

    private Set<MoodSelectionListener> listeners = new HashSet<MoodSelectionListener>();

    public MoodSelection() {
        this(0, 0);
    }

    public MoodSelection(double r, double theta) {
        super();
        setR(r);
        setTheta(theta);
    }

    public double getR() {
        return r.get();
    }

    public void setR(double r) {
        if (r != getR() && r <= 1) {
            this.r.set(r);

            for (MoodSelectionListener listener : listeners) {
                listener.onUpdateR(r);
            }
        }
    }

    public double getTheta() {
        return theta.get();
    }

    public void setTheta(double theta) {
        if (theta != getTheta()) {
            this.theta.set(theta);

            for (MoodSelectionListener listener : listeners) {
                listener.onUpdateTheta(theta);
            }
        }
    }

    public interface MoodSelectionListener {
        public void onUpdateR(double r);

        public void onUpdateTheta(double theta);
    }
}