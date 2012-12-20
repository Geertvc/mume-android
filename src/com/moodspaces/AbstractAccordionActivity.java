package com.moodspaces;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.moodspaces.controller.DatabaseController;
import com.moodspaces.controller.MoodSelectionController;
import com.moodspaces.model.Emotion;
import com.moodspaces.model.MoodEntry;
import com.moodspaces.model.MoodSelection;
import com.sentaca.android.accordion.widget.AccordionView;

public abstract class AbstractAccordionActivity extends SherlockActivity {

	private AccordionView accordionView;
	private final int titleId, menuId;

	public AbstractAccordionActivity(int titleId, int menuId) {
		this.titleId = titleId;
		this.menuId = menuId;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getSupportMenuInflater().inflate(menuId, menu);
		return true;
	}

	protected void add(String emotion, String value) {
		add(Emotion.getByName(emotion), value);
	}

	protected void add(Emotion emotion, String value) {
		if (accordionView == null)
			throw new IllegalStateException();
		
		Log.d(getClass().getSimpleName(), "Adding value \"" + value + "\" for emotion " + emotion);

		LinearLayout ll = (LinearLayout) accordionView.getChildById(emotion.id);
		TextView tv = new TextView(this);
		tv.setText(value);
		ll.addView(tv);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_accordion);
		getSupportActionBar().setTitle(titleId);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		DatabaseController.initDatabaseController(this);

		accordionView = (AccordionView) findViewById(R.id.accordion_view);
		populate();
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

	public void startSettingsActivity(View view) {
		startActivity(new Intent(this, SettingsActivity.class));
	}

	protected abstract long getId(MoodEntry entry);

	protected abstract String getName(long id);

	protected void populate() {
		for (Emotion emotion : Emotion.values()) {
			List<MoodSelection> selections = MoodSelectionController.getByEmotion(emotion);

			Log.i("AbstractAccordionActivity::populate", String.format(
					"Number of entries for emotion %s [%f-%f]: %d", emotion.name,
					emotion.startPhi, emotion.endPhi,
					selections.size()));

			final Map<Long, Double> values = new TreeMap<Long, Double>();

			for (MoodSelection selection : selections) {
				long id = getId(selection.getMoodEntry());

				if (!values.containsKey(id))
					values.put(id, selection.getR());
				else
					values.put(id, values.get(id) + selection.getR());
			}

			List<Map.Entry<Long, Double>> entries = new ArrayList<Map.Entry<Long, Double>>(
					values.size());
			entries.addAll(values.entrySet());
			Collections.sort(entries,
					new Comparator<Map.Entry<Long, Double>>() {

						@Override
						public int compare(Entry<Long, Double> lhs,
								Entry<Long, Double> rhs) {
							return (int) Math.signum(lhs.getValue()
									- rhs.getValue());
						}

					});
			
			if (entries.size() == 0 && selections.size() > 0) {
				Log.e("AbstractAccordionActivity::populate", "Entries.size() == 0 but selections.size() > 0");
			}

			int size = Math.min(3, entries.size());
			for (int i = 0; i < size; i++) {
				add(emotion, getName(entries.get(i).getKey()));
			}
		}
	}

}