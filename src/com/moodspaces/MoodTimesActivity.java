package com.moodspaces;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MoodTimesActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood_times);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_mood_times, menu);
        return true;
    }
}
