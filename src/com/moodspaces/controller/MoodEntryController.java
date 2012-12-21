package com.moodspaces.controller;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import android.util.Log;

import com.moodspaces.model.MoodEntry;
import com.moodspaces.model.MoodEntryDao;
import com.moodspaces.model.MoodEntryPersonMap;
import com.moodspaces.model.MoodEntryPersonMapDao;
import com.moodspaces.model.MoodPerson;
import com.moodspaces.model.MoodSelection;
import com.moodspaces.model.MoodSpot;
import com.moodspaces.util.Collections;
import com.moodspaces.util.Collections.Map;

public class MoodEntryController {

	private static MoodEntryDao entryDao;
	private static MoodEntryPersonMapDao entryPersonMapDao;
	
	static void init(MoodEntryDao entry, MoodEntryPersonMapDao entryPersonMap) {
		entryDao = entry;
		entryPersonMapDao = entryPersonMap;
	}
	
	public static List<MoodEntryPersonMap> getAll() {
		return entryPersonMapDao.loadAll();
	}
	
	public static List<MoodPerson> getPeeps(MoodEntry entry) {
		List<MoodEntryPersonMap> maps = entryPersonMapDao.queryBuilder()
				.where(MoodEntryPersonMapDao.Properties.EntryId.eq(entry.getId())).list();
		
		return Collections.map(maps, new Map<MoodEntryPersonMap, MoodPerson> () {

			@Override
			public MoodPerson map(MoodEntryPersonMap entry) {
				return entry == null ? null : entry.getMoodPerson();
			}
			
		});
	}
	
	public static List<MoodEntry> getEntries(MoodPerson person) {
		List<MoodEntryPersonMap> maps = entryPersonMapDao.queryBuilder()
				.where(MoodEntryPersonMapDao.Properties.PersonId.eq(person.getId())).list();
		
		return Collections.map(maps, new Map<MoodEntryPersonMap, MoodEntry>() {

			@Override
			public MoodEntry map(MoodEntryPersonMap entry) {
				return entry == null ? null : entry.getMoodEntry();
			}
			
		});
	}
	
	public static void store(MoodEntry entry, Collection<MoodSelection> selections, Collection<MoodPerson> peeps) {
		entry.setDate(new Date());
		entryDao.insert(entry);
		entry.getSelections().addAll(selections);
		entry.update();
		
		Log.i(MoodEntryController.class.getSimpleName(),
				String.format("Added moodentry with r=%f, theta=%f", selections.iterator().next().getR(),
						selections.iterator().next().getTheta())
				);
		
		for (MoodSelection selection : selections) {
			selection.setMoodEntry(entry);
			MoodSelectionController.store(selection);
		}
		
		for (MoodPerson person : peeps) {
			MoodEntryPersonMap map = new MoodEntryPersonMap();
			map.setMoodEntry(entry);
			map.setMoodPerson(person);
			
			entryPersonMapDao.insert(map);
		}
	}
	
	public static List<MoodEntry> getBySpot(MoodSpot spot) {
		return entryDao.queryBuilder()
				.where(MoodEntryDao.Properties.SpotId.eq(spot.getId()))
				.list();
	}
}
