package com.moodspaces;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.moodspaces.MoodSpacesService.MoodSpacesBinder;
import com.moodspaces.exceptions.NotBoundException;

public class AbstractActivity extends SherlockFragmentActivity implements ServiceConnection {

    private MoodSpacesService service;

    @Override
    protected void onStart() {
        super.onStart();
        // Bind to the service
        Log.d(getClass().getSimpleName(), "Binding...");
        bindService(new Intent(this, MoodSpacesService.class), this, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (isBound()) {
            Log.d(getClass().getSimpleName(), "Unbinding...");
            unbindService(this);
        }
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder binder) {
        service = ((MoodSpacesBinder) binder).getService();
        Log.d(getClass().getSimpleName(), "Bound!");
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        service = null;
        Log.d(getClass().getSimpleName(), "Unbound!");
    }

    protected boolean isBound() {
        return service != null;
    }

    protected MoodSpacesService getService() throws NotBoundException {
        if (!isBound()) {
            throw new NotBoundException();
        }
        return service;
    }
}
