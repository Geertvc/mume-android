package com.moodspaces.model;

import android.content.Context;

import com.orm.androrm.Model;
import com.orm.androrm.QuerySet;
import com.orm.androrm.field.CharField;

public class MoodTask extends Model {

    public static QuerySet<MoodTask> objects(Context context) {
        return objects(context, MoodTask.class);
    }
    
    public static MoodTask getById(Context ctx, int id) {
    	return objects(ctx).get(id);
    }

    protected CharField name = new CharField(/* optional max length */);

    public MoodTask() {
        super();
    }

    public MoodTask(String name) {
        setName(name);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    @Override
    public String toString() {
        return getName();
    }
}
