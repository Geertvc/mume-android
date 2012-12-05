package com.moodspaces.model;

import java.util.Collection;
import java.util.HashSet;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "entries")
public class MoodEntry {
    @DatabaseField(generatedId = true)
    private int id;
    
    @DatabaseField(foreign = true)
	private MoodTask moodTask;
    
    @DatabaseField(foreign = true)
	private MoodSpot moodSpot;
    
	@ForeignCollectionField
	private Collection<MoodSelection> moodEntries = new HashSet<MoodSelection>();
    
	@ForeignCollectionField
	private Collection<MoodPerson> people = new HashSet<MoodPerson>();
	
	public MoodEntry() {
        // ORMLite needs a no-arg constructor
	}

	public MoodTask getMoodTask() {
		return moodTask;
	}

	public void setMoodTask(MoodTask moodTask) {
		this.moodTask = moodTask;
	}

	public MoodSpot getMoodSpot() {
		return moodSpot;
	}

	public void setMoodSpot(MoodSpot moodSpot) {
		this.moodSpot = moodSpot;
	}

	public Collection<MoodSelection> getMoodEntries() {
		return moodEntries;
	}

	public void setMoodEntries(Collection<MoodSelection> moodEntries) {
		this.moodEntries = moodEntries;
	}

	public Collection<MoodPerson> getPeople() {
		return people;
	}

	public void setPeople(Collection<MoodPerson> people) {
		this.people = people;
	}
}
