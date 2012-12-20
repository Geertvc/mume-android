package com.moodspaces.util;

import java.util.ArrayList;
import java.util.List;

public class Collections {

	public static interface Map<T, U> {
		U map(T entry);
	}
	
	public static <T, U> List<U> map(List<T> orig, Map<T, U> map) {
		List<U> res = new ArrayList<U>(orig.size());
		for (T t : orig)
			res.add(map.map(t));
		return res;
	}
}
