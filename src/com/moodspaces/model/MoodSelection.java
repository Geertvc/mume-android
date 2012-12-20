package com.moodspaces.model;

import android.content.Context;

import com.orm.androrm.Filter;
import com.orm.androrm.Model;
import com.orm.androrm.QuerySet;
import com.orm.androrm.field.DoubleField;
import com.orm.androrm.field.ForeignKeyField;
import com.orm.androrm.migration.Migrator;

public class MoodSelection extends Model {

	public static QuerySet<MoodSelection> objects(Context ctx) {
		return objects(ctx, MoodSelection.class);
	}

	public static QuerySet<MoodSelection> getByEmotion(Context ctx,
			Emotion emotion) {
		Filter f = new Filter();

		f.is("theta", ">=", "" + emotion.startPhi);
		f.is("theta", "<", "" + emotion.endPhi);
		
		return objects(ctx).filter(f);
	}

	protected DoubleField r = new DoubleField();
	protected DoubleField theta = new DoubleField();
	protected ForeignKeyField<MoodEntry> entry = new ForeignKeyField<MoodEntry>(
			MoodEntry.class);

	public MoodSelection() {
		this(0, 0);
	}

	public MoodSelection(double r, double theta) {
		setR(r);
		setTheta(theta);
	}

	public double getR() {
		return r.get();
	}

	public void setR(double r) {
		if (r > 0 && r <= 1) {
			this.r.set(r);
		}
	}

	public double getTheta() {
		return theta.get();
	}

	public void setTheta(double theta) {
		this.theta.set(theta);
	}

	public MoodEntry getEntry() {
		return entry.get();
	}

	public void setMoodEntry(MoodEntry moodEntry) {
		entry.set(moodEntry);
	}
	
	@Override
	protected void migrate(Context ctx) {
		Migrator<MoodSelection> migrator = new Migrator<MoodSelection>(MoodSelection.class);
		
		migrator.addField("entry", new ForeignKeyField<MoodEntry>(MoodEntry.class));
		
		migrator.migrate(ctx);
	}
}