package com.moodspaces;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    public void startNewMood(View view){
    	Intent intent = new Intent(this, InputActivity.class);
    	startActivity(intent);
    }
    
    public void startMoodSpots(View view){
    	Intent intent = new Intent(this, MoodSpotsActivity.class);
    	startActivity(intent);
    }
    
    public void startMoodTimes(View view){
    	Intent intent = new Intent(this, MoodTimesActivity.class);
    	startActivity(intent);
    }
    
    public void startMoodPeeps(View view){
    	Intent intent = new Intent(this, MoodPeepsActivity.class);
    	startActivity(intent);
    }
}