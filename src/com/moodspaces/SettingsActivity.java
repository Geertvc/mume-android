package com.moodspaces;

import android.content.Intent;
import android.os.Bundle;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuItem;

public class SettingsActivity extends SherlockActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		getSupportActionBar().setTitle(R.string.settings_activity_title);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
            case android.R.id.home:
                startActivity(new Intent(this, MoodSpacesActivity.class));
                break;
        }
	    return super.onOptionsItemSelected(item);
	}
}
