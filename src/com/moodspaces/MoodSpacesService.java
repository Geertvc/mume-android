package com.moodspaces;

import java.sql.SQLException;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.moodspaces.database.DatabaseHelper;
import com.moodspaces.model.Location;

public class MoodSpacesService extends Service {

    private MoodSpacesBinder binder = new MoodSpacesBinder();
    private DatabaseHelper helper;

    public DatabaseHelper getHelper() {
        return helper;
    }

    public void makeAToast() {
        Toast.makeText(this, "This is a toast!", Toast.LENGTH_SHORT).show();
    }

    public void createLocation(Location location) {
        try {
            getHelper().getLocationDao().create(location);
            Toast.makeText(this, "Location created!", Toast.LENGTH_SHORT).show();
        } catch (SQLException e) {
            // TODO Inform user of failure
            Log.e(getClass().getSimpleName(), "Failed to create location!");
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        helper = new DatabaseHelper(this);
        Log.d(getClass().getSimpleName(), "Bind Request Received!");
        return binder;
    }

    /**
     * Class used for the client Binder. Because we know this service always
     * runs in the same process as its clients, we don't need to deal with IPC.
     */
    public class MoodSpacesBinder extends Binder {
        public MoodSpacesService getService() {
            // Return this instance of MoodSpacesService
            return MoodSpacesService.this;
        }
    }

}
