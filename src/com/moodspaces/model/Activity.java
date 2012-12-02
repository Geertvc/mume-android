package com.moodspaces.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "activity")
public class Activity {

    @DatabaseField(generatedId = true)
    private int id;
    
    @DatabaseField(unique = true)
	private String activity;
	
    public Activity() {
        // ORMLite needs a no-arg constructor
    }
    
	public Activity(String activity){
		this.activity = activity;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}
}
