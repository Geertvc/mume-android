package com.moodspaces.controller;

import java.util.List;

import com.moodspaces.model.MoodSpot;
import com.moodspaces.model.MoodSpotDao;

public class MoodSpotController {

	private static MoodSpotDao dao;
	
	static void init(MoodSpotDao dao) {
		MoodSpotController.dao = dao;
	}
	
	public static MoodSpot getById(long id) {
		MoodSpot result = dao.load(id);
		if (result != null) dao.detach(result);
		return result;
	}
	
	public static MoodSpot getByName(String name) {
		MoodSpot result = dao.queryBuilder().where(MoodSpotDao.Properties.Name.eq(name)).unique();
		if (result != null) dao.detach(result);
		return result;
	}
	
	public static List<MoodSpot> getAll() {
		return dao.loadAll();
	}
	
	public static void store(MoodSpot spot) {
		dao.insert(spot);
	}
	
	public static MoodSpot create(String name, double latitude, double longitude) {
		MoodSpot spot;
		
		if ((spot = getByName(name)) != null)
			return spot;
		
		spot = new MoodSpot();
		spot.setName(name);
		spot.setLatitude(latitude);
		spot.setLongitude(longitude);
		store(spot);
		return spot;
	}
}
