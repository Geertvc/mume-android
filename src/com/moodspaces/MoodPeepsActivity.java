package com.moodspaces;

import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.moodspaces.controller.DatabaseController;
import com.moodspaces.controller.MoodPersonController;
import com.moodspaces.model.MoodPerson;

public class MoodPeepsActivity extends SherlockActivity {

	private ListView listView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_moodpeeps);
		getSupportActionBar().setTitle(R.string.moodpeeps_activity_title);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		DatabaseController.initDatabaseController(this);
	}

	@Override
	protected void onStart() {
		super.onStart();

		try {
			updateMoodPeeps();
		} catch (SQLException e) {
			// No Problem, no database yet...
		}
	}

	private void updateMoodPeeps() {
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1);
		Log.d(getClass().getSimpleName(), "Logging Moodpeeps");
		for (MoodPerson person : MoodPersonController.getAll()) {
			Log.d(getClass().getSimpleName(), person.getName());
			adapter.add(person.getName());
		}
		getListView().setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getSupportMenuInflater().inflate(R.menu.activity_moodpeeps, menu);
		return true;
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

	public ListView getListView() {
		if (listView == null) {
			listView = (ListView) findViewById(R.id.moodpeeps_list);
		}

		return listView;
	}

	public void startSettingsActivity(View view) {
		startActivity(new Intent(this, SettingsActivity.class));
	}
}
