package com.moodspaces.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "people")
public class Person {
	
    @DatabaseField
    private int id;
    
    @DatabaseField
    private String name;
    
    public Person() {
        // ORMLite needs a no-arg constructor
    }
	
	public Person(String name){
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}