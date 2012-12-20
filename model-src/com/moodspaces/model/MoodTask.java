package com.moodspaces.model;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table MOOD_TASK.
 */
public class MoodTask {

    private Long id;
    /** Not-null value. */
    private String name;

    public MoodTask() {
    }

    public MoodTask(Long id) {
        this.id = id;
    }

    public MoodTask(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /** Not-null value. */
    public String getName() {
        return name;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setName(String name) {
        this.name = name;
    }
    
    /*keep?*/
    public String toString() { return name; }

}
