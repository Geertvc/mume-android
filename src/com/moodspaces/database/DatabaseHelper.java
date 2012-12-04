package com.moodspaces.database;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

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
        // Don't call super.onCreate() or you'll get a StackOverflowException!
        Log.d(getClass().getSimpleName(), "Database " + db.getPath() + " created!");
        try {
            TableUtils.createTable(conn, Activity.class);
            Log.d(getClass().getSimpleName(), "Activity table created");
            TableUtils.createTable(conn, Location.class);
            Log.d(getClass().getSimpleName(), "Location table created");
            TableUtils.createTable(conn, MoodSelection.class);
            Log.d(getClass().getSimpleName(), "MoodSelection table created");
            TableUtils.createTable(conn, Person.class);
            Log.d(getClass().getSimpleName(), "Person table created");
            TableUtils.createTable(conn, MoodEntry.class);
            Log.d(getClass().getSimpleName(), "MoodEntry table created");
        } catch (SQLException e) {
            Log.e(getClass().getSimpleName(), "Database table creation failed.");
        }
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        Log.d(getClass().getSimpleName(), "Database '" + db.getPath() + "' opened");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource conn, int from, int to) {
        // Not implemented
    }

    public Dao<MoodEntry, Integer> getEntriesDao() throws SQLException {
        if (entriesDao == null) {
            entriesDao = DaoManager.createDao(connectionSource, MoodEntry.class);
        }
        return entriesDao;
    }

    public Dao<Activity, Integer> getActivityDao() throws SQLException {
        if (activityDao == null) {
            activityDao = DaoManager.createDao(connectionSource, Activity.class);
        }
        return activityDao;
    }

    public Dao<Person, Integer> getPersonDao() throws SQLException {
        if (personDao == null) {
            personDao = DaoManager.createDao(connectionSource, Person.class);
        }
        return personDao;
    }

    public Dao<Location, Integer> getLocationDao() throws SQLException {
        if (locationDao == null) {
            locationDao = DaoManager.createDao(connectionSource, Location.class);
        }
        return locationDao;
    }

}