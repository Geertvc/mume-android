package com.moodspaces.model;

import com.orm.androrm.Model;
import com.orm.androrm.field.CharField;

public class MoodPerson extends Model {

    protected CharField name = new CharField();

    public MoodPerson() {
        super();
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }
}