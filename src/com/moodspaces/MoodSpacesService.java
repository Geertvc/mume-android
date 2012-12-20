package com.moodspaces;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.moodspaces.controller.DatabaseController;

public class MoodSpacesService extends Service {

    private MoodSpacesBinder binder = new MoodSpacesBinder();

    @Override
    public IBinder onBind(Intent intent) {
    	DatabaseController.initDatabaseController(getApplicationContext());
    	
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
