package com.moodspaces.controller;

import java.util.List;

import com.moodspaces.model.MoodTask;
import com.moodspaces.model.MoodTaskDao;

public class MoodTaskController {

	private static MoodTaskDao dao;
	
	static void init(MoodTaskDao dao) {
		MoodTaskController.dao = dao;
	}
	
	public static MoodTask getById(long id) {
		MoodTask result = dao.load(id);
		return result;
	}
	
	public static MoodTask getByName(String name) {
		MoodTask result = dao.queryBuilder().where(MoodTaskDao.Properties.Name.eq(name)).unique();
		return result;
	}
	
	public static List<MoodTask> getAll() {
		return dao.loadAll();
	}
	
	public static void store(MoodTask task) {
		dao.insert(task);
	}
	
	public static MoodTask create(String name) {
		MoodTask task;
		
		if ((task = getByName(name)) != null)
			return task;
		
		task = new MoodTask();
		task.setName(name);
		store(task);
		return task;
	}
}
