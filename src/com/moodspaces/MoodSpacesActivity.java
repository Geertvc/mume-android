package com.moodspaces;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

public class MoodSpacesActivity extends SherlockActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_moodspaces);
		getSupportActionBar().setTitle(R.string.moodspaces_activity_title);
		initializeUI();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getSupportMenuInflater().inflate(R.menu.activity_moodspaces, menu);
		return true;
	}

    private void initializeUI() {
    	findViewById(R.id.button_moodspots).setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				startActivity(new Intent(MoodSpacesActivity.this, MoodSpotsActivity.class));
			}
		});
    	
    	findViewById(R.id.button_moodpeeps).setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				startActivity(new Intent(MoodSpacesActivity.this, MoodPeepsActivity.class));
			}
		});
    	
    	findViewById(R.id.button_moodtimes).setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				startActivity(new Intent(MoodSpacesActivity.this, MoodTimesActivity.class));
			}
		});
    	
    	findViewById(R.id.button_moodtasks).setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				startActivity(new Intent(MoodSpacesActivity.this, MoodTasksActivity.class));
			}
		});
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
}
