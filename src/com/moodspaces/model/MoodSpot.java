package com.moodspaces.model;

import android.content.Context;

import com.orm.androrm.Model;
import com.orm.androrm.QuerySet;
import com.orm.androrm.field.CharField;
import com.orm.androrm.field.DoubleField;

public class MoodSpot extends Model {
	
	public static QuerySet<MoodSpot> objects(Context ctx) {
		return objects(ctx, MoodSpot.class);
	}
	
	public static MoodSpot getById(Context ctx, int id) {
		return objects(ctx).get(id);
	}
	
    protected CharField name = new CharField(/* optional length */);
    protected DoubleField latitude = new DoubleField();
    protected DoubleField longitude = new DoubleField();

    public MoodSpot() {
        // initialize ID's and stuff
        super();
    }

    public MoodSpot(String name, double latitude, double longitude) {
        setName(name);
        setLatitude(latitude);
        setLongitude(longitude);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public double getLatitude() {
        return latitude.get();
    }

    public void setLatitude(double latitude) {
        this.latitude.set(latitude);
    }

    public double getLongitude() {
        return longitude.get();
    }

    public void setLongitude(double longitude) {
        this.longitude.set(longitude);
    }
    
    @Override
    public String toString() {
        return getName();
    }
}
