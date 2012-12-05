package com.moodspaces;

import java.util.HashSet;
import java.util.Set;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.moodspaces.model.MoodEntry;
import com.moodspaces.model.MoodPerson;
import com.moodspaces.model.MoodSelection;
import com.moodspaces.model.MoodSpot;
import com.moodspaces.model.MoodTask;
import com.orm.androrm.DatabaseAdapter;
import com.orm.androrm.Model;

public class MoodSpacesService extends Service {

    private static final String DB_NAME = "moodspaces.db";
    
    private MoodSpacesBinder binder = new MoodSpacesBinder();
    private DatabaseAdapter adapter;

    public void createLocation(MoodSpot moodSpot) {
        if (moodSpot.save(getApplicationContext())) {
            Toast.makeText(this, "Location created!", Toast.LENGTH_SHORT).show();
            Log.i(getClass().getSimpleName(), "Location saved!");
        } else {
            Log.e(getClass().getSimpleName(), "Location not saved!");
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        if (adapter == null) {
            DatabaseAdapter.setDatabaseName(DB_NAME);
            adapter = new DatabaseAdapter(getApplicationContext());
//            Set<Class<? extends Model>> models = new HashSet<Class<? extends Model>>();
//            models.add(MoodEntry.class);
//            models.add(MoodPerson.class);
//            models.add(MoodSelection.class);
//            models.add(MoodSpot.class);
//            models.add(MoodTask.class);
//            adapter.setModels(models);
        }

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
