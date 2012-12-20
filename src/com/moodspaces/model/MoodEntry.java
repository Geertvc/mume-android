package com.moodspaces.model;

import java.util.Date;
import java.util.List;
import java.util.Set;

import android.content.Context;

import com.orm.androrm.Model;
import com.orm.androrm.QuerySet;
import com.orm.androrm.field.DateField;
import com.orm.androrm.field.ForeignKeyField;
import com.orm.androrm.field.ManyToManyField;
import com.orm.androrm.field.OneToManyField;

public class MoodEntry extends Model {

    public static QuerySet<MoodEntry> objects(Context context) {
	    return objects(context, MoodEntry.class);
	}

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

    public void addSelections(Set<MoodSelection> selections) {
        this.moodSelections.addAll(selections);
        for(MoodSelection selection: selections) {
        	selection.setMoodEntry(this);
        }
    }

    public void addSelection(MoodSelection selection) {
        this.moodSelections.add(selection);
        selection.setMoodEntry(this);
    }

    public QuerySet<MoodSelection> getMoodSelections(Context context) {
        return this.moodSelections.get(context, this);
    }

    public void setMoodPeeps(List<MoodPerson> selectedMoodPeeps) {
        this.moodPeople.addAll(selectedMoodPeeps);
    }

    public void addMoodPerson(MoodPerson person) {
        this.moodPeople.add(person);
    }
}