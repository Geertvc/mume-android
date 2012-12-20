package com.moodspaces;

import com.moodspaces.controller.MoodSpotController;
import com.moodspaces.model.MoodEntry;


public class MoodSpotsActivity extends AbstractAccordionActivity {

	public MoodSpotsActivity() {
		super(R.string.moodspots_activity_title, R.menu.activity_moodspots);
	}

	@Override
	protected long getId(MoodEntry entry) {
		return entry.getMoodSpot().getId();
	}

	@Override
	protected String getName(long id) {
		return MoodSpotController.getById(id).getName();
	}
}
