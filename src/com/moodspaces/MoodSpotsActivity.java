package com.moodspaces;

import java.util.HashMap;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.sentaca.android.accordion.widget.AccordionView;

public class MoodSpotsActivity extends SherlockActivity {
	
	private static final Map<String, Integer> emotionIdMap;
	
	static {
		emotionIdMap = new HashMap<String, Integer>();
		
		emotionIdMap.put("Acceptance", R.id.emotions_acceptance);
		emotionIdMap.put("Anger", R.id.emotions_anger);
		emotionIdMap.put("Anticipation", R.id.emotions_anticipation);
		emotionIdMap.put("Disgust", R.id.emotions_disgust);
		emotionIdMap.put("Fear", R.id.emotions_fear);
		emotionIdMap.put("Joy", R.id.emotions_joy);
		emotionIdMap.put("Sadness", R.id.emotions_sadness);
		emotionIdMap.put("Surprise", R.id.emotions_surpise);
	}
	
	private AccordionView accordionView;
	
	public MoodSpotsActivity() {
	}
	
	private void add(String emotion, String value) {
		if (!emotionIdMap.containsKey(emotion))
			throw new IllegalArgumentException("Invalid emotion: " + emotion);
		if (accordionView == null)
			throw new IllegalStateException();
		
		LinearLayout ll = (LinearLayout) accordionView.getChildById(emotionIdMap.get(emotion));
		TextView tv = new TextView(this);
		tv.setText(value);
		ll.addView(tv);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_moodspots);
		getSupportActionBar().setTitle(R.string.moodspots_activity_title);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		accordionView = (AccordionView) findViewById(R.id.accordion_view);
		populate();
	}
	
	private void populate() {
		// FIXME do some heavy calculations etc etc etc
		add("Anticipation", "200A 02.252");
		add("Acceptance", "Thuis");
		add("Acceptance", "De 6/0");
		add("Surprise", "Oma & Opa");
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
