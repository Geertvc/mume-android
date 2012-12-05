package com.moodspaces.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = MoodEntryMoodPerson.TABLE_NAME)
public class MoodEntryMoodPerson {
    public final static String TABLE_NAME = "moodentry_moodperson";
    public final static String FIELD_NAME_ID = "id";
    public final static String FIELD_NAME_ENTRY = "moodentry";
    public final static String FIELD_NAME_PERSON = "moodperson";

    @DatabaseField(generatedId = true, columnName = FIELD_NAME_ID)
    private int id;

    @DatabaseField(foreign = true, columnName = FIELD_NAME_ENTRY)
    private MoodEntry moodEntry;

    @DatabaseField(foreign = true, columnName = FIELD_NAME_PERSON)
    private MoodPerson moodPerson;

    public int getId() {
        return id;
    }

    public MoodEntry getMoodEntry() {
        return moodEntry;
    }

    public MoodPerson getMoodPerson() {
        return moodPerson;
    }

}
