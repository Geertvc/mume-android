package com.moodspaces;

import com.moodspaces.model.MoodEntry;
import com.moodspaces.model.MoodTask;


public class MoodTasksActivity extends AbstractAccordionActivity {

	public MoodTasksActivity() {
		super(R.string.moodtasks_activity_title, R.menu.activity_moodtasks);
	}

	@Override
	protected int getId(MoodEntry entry) {
		return entry.getMoodTask().getId();
	}

	@Override
	protected String getName(int id) {
		return MoodTask.getById(this, id).getName();
	}
}
