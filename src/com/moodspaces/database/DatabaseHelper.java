package com.moodspaces.database;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.moodspaces.model.Activity;
import com.moodspaces.model.Location;
import com.moodspaces.model.MoodEntry;
import com.moodspaces.model.MoodSelection;
import com.moodspaces.model.Person;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
    private static final String DATABASE_NAME = "moodspaces.db";
    private static final int DATABASE_VERSION = 1;

    private Dao<MoodEntry, Integer> entriesDao = null;
    private Dao<Activity, Integer> activityDao = null;
    private Dao<Person, Integer> personDao = null;
    private Dao<Location, Integer> locationDao = null;
    
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource conn) {
        try {
            entriesDao = DaoManager.createDao(conn, MoodEntry.class);
            
            TableUtils.createTableIfNotExists(conn, Activity.class);
            TableUtils.createTableIfNotExists(conn, Location.class);
            TableUtils.createTableIfNotExists(conn, MoodEntry.class);
            TableUtils.createTableIfNotExists(conn, MoodSelection.class);
            TableUtils.createTableIfNotExists(conn, Person.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource conn, int from, int to) {
        // Not implemented
    }
    
    public Dao<MoodEntry, Integer> getEntriesDao() {
        return entriesDao;
    }
    
    public Dao<Activity, Integer> getActivityDao() {
        return activityDao;
    }

    public Dao<Person, Integer> getPersonDao() {
        return personDao;
    }
    
    public Dao<Location, Integer> getLocationDao() {
        return locationDao;
    }

}