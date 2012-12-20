package com.moodspaces.model;

import android.content.Context;

import com.orm.androrm.Model;
import com.orm.androrm.QuerySet;
import com.orm.androrm.field.CharField;

public class MoodPerson extends Model {
    
    public static QuerySet<MoodPerson> objects(Context context) {
        return objects(context, MoodPerson.class);
    }
    
    public static MoodPerson getById(Context ctx, int id) {
    	return objects(ctx).get(id);
    }

    protected CharField name = new CharField();

    public MoodPerson() {
        super();
    }
    
    public MoodPerson(String name) {
        this();
        setName(name);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }
}