package com.moodspaces.model;

import java.util.ArrayList;

public class MoodData {
	
	private ArrayList<Point2f> selectedMoods;
	
	public MoodData(){
		selectedMoods = new ArrayList<Point2f>();
	}
	
	public void addSelectedMood(Point2f point){
		selectedMoods.add(point);
	}
}
