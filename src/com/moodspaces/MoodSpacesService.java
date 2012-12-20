package com.moodspaces;

import java.util.LinkedList;
import java.util.List;

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

    public void createTask(MoodTask moodTask) {
        if (moodTask.save(getApplicationContext())) {
            Toast.makeText(this, "Task created!", Toast.LENGTH_SHORT).show();
            Log.i(getClass().getSimpleName(), "Task saved!");
        } else {
            Log.e(getClass().getSimpleName(), "Task not saved!");
        }
    }

    public void addEntry(MoodEntry moodEntry) throws Exception {
        if(moodEntry.save(getApplicationContext())) {
            Toast.makeText(this, "Entry saved!", Toast.LENGTH_SHORT).show();
            Log.i(getClass().getSimpleName(), "Entry saved!");
        } else {
            throw new Exception("Entry not stored");
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        if (adapter == null) {
            adapter = DatabaseAdapter.getInstance(getApplicationContext());
            List<Class<? extends Model>> models = new LinkedList<Class<? extends Model>>();
            models.add(MoodEntry.class);
            models.add(MoodPerson.class);
            models.add(MoodSelection.class);
            models.add(MoodSpot.class);
            models.add(MoodTask.class);
            adapter.setModels(models);
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
