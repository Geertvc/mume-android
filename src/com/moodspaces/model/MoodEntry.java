package com.moodspaces.model;

import java.util.Date;

import com.orm.androrm.Model;
import com.orm.androrm.field.DateField;
import com.orm.androrm.field.ForeignKeyField;
import com.orm.androrm.field.ManyToManyField;
import com.orm.androrm.field.OneToManyField;

public class MoodEntry extends Model {

    protected DateField date = new DateField();
    protected ForeignKeyField<MoodTask> moodTask = new ForeignKeyField<MoodTask>(MoodTask.class);
    protected ForeignKeyField<MoodSpot> moodSpot = new ForeignKeyField<MoodSpot>(MoodSpot.class);
    protected OneToManyField<MoodEntry, MoodSelection> moodSelections = new OneToManyField<MoodEntry, MoodSelection>(
            MoodEntry.class, MoodSelection.class);
    protected ManyToManyField<MoodEntry, MoodPerson> moodPeople = new ManyToManyField<MoodEntry, MoodPerson>(
            MoodEntry.class, MoodPerson.class);

    public MoodEntry() {
        super();
        setDate(new Date());
    }

    public Date getDate() {
        return date.get();
    }
    
    public void setDate(Date date) {
        this.date.set(date);
    }

    public MoodTask getMoodTask() {
        return moodTask.get();
    }

    public void setMoodTask(MoodTask moodTask) {
        this.moodTask.set(moodTask);
    }

    public MoodSpot getMoodSpot() {
        return moodSpot.get();
    }

    public void setMoodSpot(MoodSpot moodSpot) {
        this.moodSpot.set(moodSpot);
    }
}
