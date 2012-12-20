package com.moodspaces.model;

import com.moodspaces.R;

public enum Emotion {
	
	/* @formatter:off */
	SURPRISE("Surprise", R.id.emotions_surpise, 0),
	FEAR("Fear", R.id.emotions_fear, 1),
	TRUST("Trust", R.id.emotions_trust, 2),
	JOY("Joy", R.id.emotions_joy, 3),
	ANTICIPATION("Anticipation", R.id.emotions_anticipation, -4),
	ANGER("Anger", R.id.emotions_anger, -3),
	DISGUST("Disgust", R.id.emotions_disgust, -2),
	SADNESS("Sadness", R.id.emotions_sadness, -1);
	/* @formatter:on */

	private static final double UNIT_ANGLE = Math.PI / 4;

	public final String name;
	public final int id;
	public final double startPhi, endPhi;

	private Emotion(String name, int id, int startPhi) {
		this.id = id;
		this.startPhi = startPhi * UNIT_ANGLE;
		this.endPhi = this.startPhi + UNIT_ANGLE;
		this.name = name;
	}

	public static Emotion getByName(String name) {
		return valueOf(name.toUpperCase());
	}
}
