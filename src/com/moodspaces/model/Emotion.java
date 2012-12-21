package com.moodspaces.model;

import android.graphics.Color;

import com.moodspaces.R;

public enum Emotion {

	/* @formatter:off */
	SURPRISE("Surprise", R.id.emotions_surpise, 1, 
			Color.rgb(0, 129, 171)), //
	FEAR("Fear", R.id.emotions_fear, 0, 
			Color.rgb(0, 123, 51)), //
	TRUST("Trust", R.id.emotions_trust, -1, 
			Color.rgb(123, 189, 13)), //
	JOY("Joy", R.id.emotions_joy, -2, 
			Color.rgb(237, 197, 0)), //
	ANTICIPATION("Anticipation", R.id.emotions_anticipation, -3, 
			Color.rgb(232, 114, 0)), //
	ANGER("Anger", R.id.emotions_anger, -4, 
			Color.rgb(220, 0, 71)), //
	DISGUST("Disgust", R.id.emotions_disgust, 3, 
			Color.rgb(123, 78, 163)), //
	SADNESS("Sadness", R.id.emotions_sadness, 2, 
			Color.rgb(31, 108, 173));
	/* @formatter:on */

	private static final double UNIT_ANGLE = Math.PI / 4;

	public final String name;
	public final int id;
	public final double startPhi, endPhi;
	public final int color;

	private Emotion(String name, int id, int startPhi, int color) {
		this.id = id;
		this.startPhi = startPhi * UNIT_ANGLE;
		this.endPhi = this.startPhi + UNIT_ANGLE;
		this.name = name;
		this.color = color;
	}

	public static Emotion getByName(String name) {
		return valueOf(name.toUpperCase());
	}

	public static Emotion getByTheta(double theta) {
		for (Emotion emotion : values()) {
			if (theta > emotion.startPhi && theta < emotion.endPhi)
				return emotion;
		}
		throw new IllegalArgumentException("Invalid theta: " + theta);
	}
}
