package com.moodspaces;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

public class MoodSpotsActivity extends SherlockActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_moodspots);
		getSupportActionBar().setTitle(R.string.moodspots_activity_title);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getSupportMenuInflater().inflate(R.menu.activity_moodspots, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()) {
			case android.R.id.home:
				startActivity(new Intent(this, MoodSpacesActivity.class));
				break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void startSettingsActivity(View view) {
		startActivity(new Intent(this, SettingsActivity.class));
	}
}
