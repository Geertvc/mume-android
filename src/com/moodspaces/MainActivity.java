package com.moodspaces;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.view.Menu;
import android.view.View;
import android.view.Window;

/**
 * The main Activity of the application that shows the possible options and screens.
 * 
 * @author Geert Van Campenhout
 */
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
    
    /**
	 * This method makes sure the gradient that is used in the background does not look banded.
	 */
	@Override
	public void onAttachedToWindow() {
	    super.onAttachedToWindow();
	    Window window = getWindow();
	    window.setFormat(PixelFormat.RGBA_8888);
	}
    
    /**
     * Starts the InputActivity to enter a new mood.
     */
    public void startNewMood(View view){
    	Intent intent = new Intent(this, InputActivity.class);
    	startActivity(intent);
    }
    
    /**
     * Starts the MoodSpotsActivity.
     */
    public void startMoodSpots(View view){
    	Intent intent = new Intent(this, MoodSpotsActivity.class);
    	startActivity(intent);
    }
    
    /**
     * Starts the MoodTimesActivity.
     */
    public void startMoodTimes(View view){
    	Intent intent = new Intent(this, MoodTimesActivity.class);
    	startActivity(intent);
    }
    
    /**
     * Starts the MoodPeepsActivity.
     */
    public void startMoodPeeps(View view){
    	Intent intent = new Intent(this, MoodPeepsActivity.class);
    	startActivity(intent);
    }
}