package com.moodspaces;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.moodspaces.controller.DatabaseController;

public class MoodSpacesActivity extends SherlockActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moodspaces);
        getSupportActionBar().setTitle(R.string.moodspaces_activity_title);
        
		DatabaseController.initDatabaseController(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getSupportMenuInflater().inflate(R.menu.activity_moodspaces, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_input:
                startActivity(new Intent(this, InputActivity.class));
                break;
            case R.id.menu_settings:
                startActivity(new Intent(this, SettingsActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void startMoodSpots(View view) {
        startActivity(new Intent(MoodSpacesActivity.this, MoodSpotsActivity.class));
    }

    public void startMoodPeeps(View view) {
        startActivity(new Intent(MoodSpacesActivity.this, MoodPeepsActivity.class));
    }

    public void startMoodTimes(View view) {
        startActivity(new Intent(MoodSpacesActivity.this, MoodTimesActivity.class));
    }

    public void startMoodTasks(View view) {
        startActivity(new Intent(MoodSpacesActivity.this, MoodTasksActivity.class));
    }

}
