package com.moodspaces;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.moodspaces.exceptions.NotBoundException;
import com.moodspaces.model.MoodEntry;
import com.moodspaces.model.MoodPerson;
import com.moodspaces.model.MoodSpot;
import com.moodspaces.model.MoodTask;
import com.moodspaces.view.LocationDialog;
import com.moodspaces.view.PeopleDialog;
import com.moodspaces.view.TaskDialog;
import com.moodspaces.view.WheelView;
import com.orm.androrm.Filter;
import com.orm.androrm.QuerySet;

/**
 * Activity that allows the user to enter their mood, location and current
 * activity.
 * 
 */
public class InputActivity extends AbstractActivity implements LocationListener {

    private LocationManager locationManager;
    private Location currentLocation;

    private Spinner moodSpotsSpinner;
    private Spinner moodTasksSpinner;
    private TextView moodPeepsTextView;

    private ArrayAdapter<MoodSpot> moodSpotsAdapter;
    private ArrayAdapter<MoodTask> moodTasksAdapter;

    private PeopleDialog peopleDialog;

    private WheelView wheelView;
    private List<String> people;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        getSupportActionBar().setTitle(R.string.input_activity_title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        wheelView = (WheelView) findViewById(R.id.input_wheel);
    }

    @Override
    protected void onStart() {
        super.onStart();

        try {
            updateMoodSpots();
            updateMoodTasks();
        } catch (Exception e) {
            // Don't worry if it fails, the db just doesnt exist yet...
            // I know it's ugly but at least it works...
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getSupportMenuInflater().inflate(R.menu.activity_input, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                startActivity(new Intent(this, MoodSpacesActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        locationManager.removeUpdates(this);
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    protected void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }

    @Override
    public void onLocationChanged(Location location) {
        setCurrentLocation(location);
        // Order locations by distance
        // orderLocationsByDistance()
    }

    @Override
    public void onProviderDisabled(String provider) {
        // Not used
    }

    @Override
    public void onProviderEnabled(String provider) {
        // Not used
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // Not used
    }

    protected Spinner getMoodSpotsSpinner() {
        if (moodSpotsSpinner == null) {
            moodSpotsSpinner = (Spinner) findViewById(R.id.input_spinner_location);
        }
        return moodSpotsSpinner;
    }

    protected Spinner getMoodTasksSpinner() {
        if (moodTasksSpinner == null) {
            moodTasksSpinner = (Spinner) findViewById(R.id.input_spinner_activity);
        }
        return moodTasksSpinner;
    }

    protected TextView getMoodPeepsTextView() {
        if (moodPeepsTextView == null) {
            moodPeepsTextView = (TextView) findViewById(R.id.input_text_people);
        }
        return moodPeepsTextView;
    }

    protected ArrayAdapter<MoodSpot> getMoodSpotsAdapter() {
        if (moodSpotsAdapter == null) {
            moodSpotsAdapter = new ArrayAdapter<MoodSpot>(this, android.R.layout.simple_spinner_dropdown_item, MoodSpot
                    .objects(getApplicationContext()).all().toList());
        }
        return moodSpotsAdapter;
    }

    protected ArrayAdapter<MoodTask> getMoodTasksAdapter() {
        if (moodTasksAdapter == null) {
            moodTasksAdapter = new ArrayAdapter<MoodTask>(this, android.R.layout.simple_spinner_dropdown_item, MoodTask
                    .objects(getApplicationContext()).all().toList());
        }
        return moodTasksAdapter;
    }

    protected void updateMoodSpots() {
        Log.d(getClass().getSimpleName(), "Updating MoodSpots");
        getMoodSpotsSpinner().setAdapter(getMoodSpotsAdapter());
    }

    protected void updateMoodTasks() {
        Log.d(getClass().getSimpleName(), "Updating MoodTasks");
        getMoodTasksSpinner().setAdapter(getMoodTasksAdapter());
    }

    private List<String> getPeople() {
        if (people == null) {
            people = new ArrayList<String>();
        }

        return people;
    }

    private PeopleDialog getPeopleDialog() {
        if (peopleDialog == null) {
            peopleDialog = new PeopleDialog();
        }
        return peopleDialog;
    }

    public WheelView getWheelView() {
        return wheelView;
    }

    public void showLocationDialog(View view) {
        new LocationDialog().show(getSupportFragmentManager(), "LocationDialog");
    }

    public void showPeopleDialog(View view) {
        getPeopleDialog().show(getSupportFragmentManager(), "PeopleDialog");
    }

    public void showActivityDialog(View view) {
        new TaskDialog().show(getSupportFragmentManager(), "ActivityDialog");
    }

    public void createLocation(String locationName) {
        try {
            if (!locationName.equals("") && currentLocation != null) {
                MoodSpot spot = new MoodSpot(locationName, currentLocation.getLatitude(),
                        currentLocation.getLongitude());
                getService().createLocation(spot);
                getMoodSpotsAdapter().add(spot);
                getMoodSpotsSpinner().setSelection(getMoodSpotsAdapter().getPosition(spot), true);
            } else {
                Toast.makeText(this, "Failed to create location", Toast.LENGTH_SHORT).show();
            }
        } catch (NotBoundException e) {
            // Should not happen...
        }
    }

    public void createTask(String taskName) {
        try {
            if (!taskName.equals("")) {
                MoodTask task = new MoodTask(taskName);
                getService().createTask(task);
                getMoodTasksAdapter().add(task);
                getMoodTasksSpinner().setSelection(getMoodTasksAdapter().getPosition(task), true);
            } else {
                Toast.makeText(this, "Failed to create task", Toast.LENGTH_SHORT).show();
            }
        } catch (NotBoundException e) {
            // Should not happen...
        }
    }

    public void addEntry(View view) {
        try {
            MoodEntry moodEntry = new MoodEntry();
            moodEntry.addSelections(getWheelView().getSelections());
            moodEntry.setMoodSpot((MoodSpot) getMoodSpotsSpinner().getSelectedItem());
            moodEntry.setMoodTask((MoodTask) getMoodTasksSpinner().getSelectedItem());
            moodEntry.setMoodPeeps(convertToMoodPeeps(getPeople()));
            getService().addEntry(moodEntry);

            startActivity(new Intent(this, MoodSpacesActivity.class));
        } catch (NotBoundException e) {
            Log.e(getClass().getSimpleName(), "Service not bound");
        } catch (Exception e) {
            Toast.makeText(this, "Could not save MoodEntry", Toast.LENGTH_SHORT).show();
        }
    }

    private List<MoodPerson> convertToMoodPeeps(List<String> people) {
        List<MoodPerson> result = new ArrayList<MoodPerson>();
        for (String name : people) {
            QuerySet<MoodPerson> q = MoodPerson.objects(getApplicationContext()).filter(new Filter().is("name", name));
            if (!q.isEmpty()) {
                result.add(q.get(0));
            } else {
                MoodPerson person = new MoodPerson(name);
                person.save(getApplicationContext());
                result.add(person);
            }
        }
        return result;
    }

    public void setMoodPeeps(List<String> selectedMoodPeeps) {
        people = selectedMoodPeeps;

        updateMoodPeeps();
    }

    public void updateMoodPeeps() {
        StringBuilder sb = new StringBuilder();
        if (!getPeople().isEmpty()) {
            sb.append(getPeople().get(0));

            for (int i = 1; i < getPeople().size(); i++) {
                sb.append(", ");
                sb.append(getPeople().get(i));
            }
        }

        getMoodPeepsTextView().setText(sb.toString());
    }
    
    @SuppressWarnings("all")
    public int onBrol() {
    	int C = 0xC011;
    	int java = 2;
    	return C++ >> java;
    }
}