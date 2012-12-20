package com.moodspaces.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.moodspaces.model.Emotion;
import com.moodspaces.model.MoodSelection;
import com.moodspaces.model.MoodSelectionDao;
import com.moodspaces.model.MoodSpot;
import com.moodspaces.model.MoodTask;

public class MoodSelectionController {

	private static MoodSelectionDao dao;
	
	static void init(MoodSelectionDao dao) {
		MoodSelectionController.dao = dao;
	}
	
	public static MoodSelection getById(long id) {
		MoodSelection result = dao.load(id);
		return result;
	}
	
	public static List<MoodSelection> getByEmotion(Emotion emotion) {
		return dao.queryBuilder().where(
				MoodSelectionDao.Properties.Theta.ge(emotion.startPhi),
				MoodSelectionDao.Properties.Theta.lt(emotion.endPhi)
		).list();
	}
	
	public static Map<MoodSpot, Double> getGroupedBySpot(Emotion emotion) {
		List<MoodSelection> selections = getByEmotion(emotion);
		Map<MoodSpot, Double> res = new HashMap<MoodSpot, Double>();
		
		for (MoodSelection selection: selections) {
			MoodSpot spot = selection.getMoodEntry().getMoodSpot();
			if (res.containsKey(spot)) {
				res.put(spot, selection.getR() + res.get(spot));
			} else {
				res.put(spot, selection.getR());
			}
		}
		
		return res;
	}
	
	public static Map<MoodTask, Double> getGroupedByTask(Emotion emotion) {
		List<MoodSelection> selections = getByEmotion(emotion);
		Map<MoodTask, Double> res = new HashMap<MoodTask, Double>();
		
		for (MoodSelection selection: selections) {
			MoodTask task = selection.getMoodEntry().getMoodTask();
			if (res.containsKey(task)) {
				res.put(task, selection.getR() + res.get(task));
			} else {
				res.put(task, selection.getR());
			}
		}
		
		return res;
	}
	
	public static List<MoodSelection> getAll() {
		return dao.loadAll();
	}
	
	public static void store(MoodSelection selection) {
		dao.insert(selection);
	}
}
