package com.moodspaces;

import com.moodspaces.controller.MoodTaskController;
import com.moodspaces.model.MoodEntry;


public class MoodTasksActivity extends AbstractAccordionActivity {

	public MoodTasksActivity() {
		super(R.string.moodtasks_activity_title, R.menu.activity_moodtasks);
	}

	@Override
	protected long getId(MoodEntry entry) {
		return entry.getMoodTask().getId();
	}

	@Override
	protected String getName(long id) {
		return MoodTaskController.getById(id).getName();
	}
}
