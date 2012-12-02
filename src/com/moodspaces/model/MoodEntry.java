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
	private Activity activity;
    
    @DatabaseField(foreign = true)
	private Location location;
    
	@ForeignCollectionField
	private Collection<MoodSelection> moodEntries = new HashSet<MoodSelection>();
    
	@ForeignCollectionField
	private Collection<Person> people = new HashSet<Person>();
	
	public MoodEntry() {
        // ORMLite needs a no-arg constructor
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Collection<MoodSelection> getMoodEntries() {
		return moodEntries;
	}

	public void setMoodEntries(Collection<MoodSelection> moodEntries) {
		this.moodEntries = moodEntries;
	}

	public Collection<Person> getPeople() {
		return people;
	}

	public void setPeople(Collection<Person> people) {
		this.people = people;
	}
}
