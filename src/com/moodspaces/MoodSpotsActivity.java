package com.moodspaces;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

/**
 * Activity that shows the mood data combined with the location.
 * 
 * @author Geert Van Campenhout
 */
public class MoodSpotsActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood_spots);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_mood_spots, menu);
        return true;
    }
}
