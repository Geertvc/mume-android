package com.moodspaces;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

/**
 * Activity that shows the mood data combined with the people that were present at that moment.
 * 
 * @author Geert Van Campenhout
 */
public class MoodPeepsActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood_peeps);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_mood_peeps, menu);
        return true;
    }
}
