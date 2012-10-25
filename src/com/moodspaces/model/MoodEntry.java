package com.moodspaces.model;

import java.util.ArrayList;

public class MoodEntry {
	private Activity activity;
	private Location location;
	private ArrayList<MoodSelection> moodEntries;
	private ArrayList<People> people;
	
	public MoodEntry(ArrayList<MoodSelection> moodEntries, Location location, Activity activity, ArrayList<People> people){
		this.moodEntries = moodEntries;
		this.location = location;
		this.activity = activity;
		this.people = people;
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

	public ArrayList<MoodSelection> getMoodEntries() {
		return moodEntries;
	}

	public void setMoodEntries(ArrayList<MoodSelection> moodEntries) {
		this.moodEntries = moodEntries;
	}

	public ArrayList<People> getPeople() {
		return people;
	}

	public void setPeople(ArrayList<People> people) {
		this.people = people;
	}
}
