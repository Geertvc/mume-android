package com.moodspaces.controller;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.moodspaces.model.DaoMaster;
import com.moodspaces.model.DaoMaster.DevOpenHelper;
import com.moodspaces.model.DaoSession;

public class DatabaseController {

	private static SQLiteDatabase db;
	
	private static DaoMaster master;
	private static DaoSession session;
	
	public static void initDatabaseController(Context context) {
		if (db != null) return;
		
		DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, "moodspaces", null);
		
		db = helper.getWritableDatabase();
		master = new DaoMaster(db);
		session = master.newSession();
		
		MoodSelectionController.init(session.getMoodSelectionDao());
		MoodSpotController.init(session.getMoodSpotDao());
		MoodPersonController.init(session.getMoodPersonDao());
		MoodTaskController.init(session.getMoodTaskDao());
		MoodEntryController.init(session.getMoodEntryDao(), session.getMoodEntryPersonMapDao());
	}

}
