package com.moodspaces;

import android.content.Context;
import android.content.Intent;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.moodspaces.exceptions.NotBoundException;
import com.moodspaces.model.MoodSpot;
import com.moodspaces.view.ActivityDialog;
import com.moodspaces.view.LocationDialog;
import com.moodspaces.view.PeopleDialog;

/**
 * Activity that allows the user to enter their mood, location and current
 * activity.
 * 
 */
public class InputActivity extends AbstractActivity implements LocationListener {

    private LocationManager locationManager;
    private android.location.Location currentLocation;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        getSupportActionBar().setTitle(R.string.input_activity_title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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

    public void showLocationDialog(View view) {
        new LocationDialog().show(getSupportFragmentManager(), "LocationDialog");
    }

    public void showPeopleDialog(View view) {
        new PeopleDialog().show(getSupportFragmentManager(), "PeopleDialog");
    }

    public void showActivityDialog(View view) {
        new ActivityDialog().show(getSupportFragmentManager(), "ActivityDialog");
    }

    public void addEntry(View view) {
        try {
            getService().makeAToast();
        } catch (NotBoundException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public android.location.Location getCurrentLocation() {
        return currentLocation;
    }

    protected void setCurrentLocation(android.location.Location currentLocation) {
        this.currentLocation = currentLocation;
    }

    @Override
    public void onLocationChanged(android.location.Location location) {
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

    public void createLocation(String locationName) {
        try {
            if (!locationName.equals("") && currentLocation != null) {
                getService().createLocation(
                        new MoodSpot(locationName, currentLocation.getLatitude(), currentLocation.getLongitude()));
            } else {
                Toast.makeText(this, "Failed to create location", Toast.LENGTH_SHORT).show();
            }
        } catch (NotBoundException e) {
            // Should not happen...
        }
    }
}