package com.moodspaces.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "people")
public class MoodPerson {
	
    @DatabaseField
    private int id;
    
    @DatabaseField
    private String name;
    
    @DatabaseField(foreign = true)
    private MoodEntry entry;
    
    public MoodPerson() {
        // ORMLite needs a no-arg constructor
    }
	
	public MoodPerson(String name){
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}