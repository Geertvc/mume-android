package com.moodspaces;

import com.moodspaces.model.MoodEntry;
import com.moodspaces.model.MoodSpot;


public class MoodSpotsActivity extends AbstractAccordionActivity {

	public MoodSpotsActivity() {
		super(R.string.moodspots_activity_title, R.menu.activity_moodspots);
	}

	@Override
	protected int getId(MoodEntry entry) {
		return entry.getMoodSpot().getId();
	}

	@Override
	protected String getName(int id) {
		return MoodSpot.getById(this, id).getName();
	}
}
