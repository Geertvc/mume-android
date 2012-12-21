package com.moodspaces;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.moodspaces.controller.DatabaseController;
import com.moodspaces.controller.MoodEntryController;
import com.moodspaces.controller.MoodSpotController;
import com.moodspaces.model.Emotion;
import com.moodspaces.model.MoodEntry;
import com.moodspaces.model.MoodSelection;
import com.moodspaces.model.MoodSpot;

public class MoodSpotsActivity extends SherlockActivity {

	private GoogleMap map;

	public MoodSpotsActivity() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_moodspots);
		getSupportActionBar().setTitle(R.string.moodspots_activity_title);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		DatabaseController.initDatabaseController(this);

		map = ((MapFragment) getFragmentManager().findFragmentById(
				R.id.spots_map)).getMap();

		initMap();
		populate();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getSupportMenuInflater().inflate(R.menu.activity_moodspots, menu);
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

	private void initMap() {
		LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		List<String> providers = locationManager.getProviders(true);

		Location location;
		if (providers.contains(LocationManager.GPS_PROVIDER)) {
			location = locationManager
					.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		} else if (providers.contains(LocationManager.NETWORK_PROVIDER)) {
			location = locationManager
					.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
		} else {
			location = locationManager
					.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
		}
		LatLng latLng = new LatLng(location.getLatitude(),
				location.getLongitude());

		Log.i(getClass().getSimpleName(), "Lat: " + latLng.latitude + " long: "
				+ latLng.longitude);

		map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
	}

	private void populate() {
		List<MoodSpot> spots = MoodSpotController.getAll();

		// Home:: Lat: 50.8716645 long: 4.7043541
		/*
		 * LatLng home = new LatLng(50.8716645, 4.7043541); map.addMarker(new
		 * MarkerOptions() .draggable(false) .title("Home") .position(home));
		 */
		
		Log.d(getClass().getSimpleName(), "Showing " + spots.size() + " spots");
		for (MoodSpot spot : spots) {
			Bitmap bm = createBitmap(spot);
			if (bm == null) {
				Log.d(getClass().getSimpleName(), "empty spot");
				continue;
			}
			Log.d(getClass().getSimpleName(), "adding overlay");
			
			/*map.addGroundOverlay(
					new GroundOverlayOptions()
							.image(BitmapDescriptorFactory.fromBitmap(bm))
							.position(new LatLng(spot.getLatitude(), spot.getLongitude()), 25)
			);*/
			
			map.addMarker(new MarkerOptions()
								.draggable(false)
								.icon(BitmapDescriptorFactory.fromBitmap(bm))
								.title(spot.getName())
								.anchor(.5f, .5f)
								.position(new LatLng(spot.getLatitude(), spot.getLongitude()))
			);
		}

		map = null;
	}

	private static final int BIT_SIZE = 20;

	private Map<Emotion, Double> processSpot(MoodSpot spot) {
		List<MoodEntry> entries = MoodEntryController.getBySpot(spot);
		Log.d(getClass().getSimpleName(), "spot has " + entries.size() + " entries");
		Map<Emotion, Double> res = new EnumMap<Emotion, Double>(Emotion.class);

		for (Emotion emotion : Emotion.values()) {
			res.put(emotion, 0d);
		}

		for (MoodEntry entry : entries) {
			for (MoodSelection selection : entry.getSelections()) {
				Emotion e = Emotion.getByTheta(selection.getTheta());
				res.put(e, selection.getR() + res.get(e));
			}
		}

		return res;
	}

	private Bitmap createBitmap(MoodSpot spot) {
		Map<Emotion, Double> emotions = processSpot(spot);
		double max = -1;
		for (Emotion e : Emotion.values()) {
			if (emotions.containsKey(e))
				max = Math.max(max, emotions.get(e));
		}
		if (max <= 0.)
			return null;
		double scale = ((double) BIT_SIZE) / (2 * max);

		List<Map.Entry<Emotion, Double>> entries = new ArrayList<Map.Entry<Emotion, Double>>(
				emotions.size());
		entries.addAll(emotions.entrySet());
		Collections.sort(entries, new Comparator<Map.Entry<Emotion, Double>>() {

			@Override
			public int compare(Entry<Emotion, Double> lhs,
					Entry<Emotion, Double> rhs) {
				return (int) Math.signum(rhs.getValue() - lhs.getValue());
			}

		});
		// largest R is now first

		Bitmap res = Bitmap.createBitmap(BIT_SIZE, BIT_SIZE,
				Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(res);

		for (Map.Entry<Emotion, Double> entry : entries) {
			if (entry.getValue() == 0.)
				continue;
			
			Log.d(getClass().getSimpleName(), "Drawing layer for " + entry.getKey());
			Paint p = new Paint();
			p.setColor(entry.getKey().color);
			canvas.drawCircle(BIT_SIZE / 2, BIT_SIZE / 2,
					(float) (entry.getValue() * scale), p);
		}

		return res;
	}
}
