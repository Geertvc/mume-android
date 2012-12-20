package com.moodspaces.controller;

import java.util.List;

import com.moodspaces.model.MoodPerson;
import com.moodspaces.model.MoodPersonDao;

public class MoodPersonController {

	private static MoodPersonDao dao;
	
	static void init(MoodPersonDao dao) {
		MoodPersonController.dao = dao;
	}
	
	public static MoodPerson getById(long id) {
		MoodPerson result = dao.load(id);
		return result;
	}
	
	public static MoodPerson getByName(String name) {
		MoodPerson result = dao.queryBuilder().where(MoodPersonDao.Properties.Name.eq(name)).unique();
		return result;
	}
	
	public static List<MoodPerson> getAll() {
		return dao.loadAll();
	}
	
	public static void store(MoodPerson person) {
		dao.insert(person);
	}
	
	public static MoodPerson create(String name) {
		MoodPerson person;
		
		if ((person = getByName(name)) != null)
			return person;
		
		person = new MoodPerson();
		person.setName(name);
		store(person);
		return person;
	}
}
