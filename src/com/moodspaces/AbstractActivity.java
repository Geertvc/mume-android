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
    
    protected void debug(String s) {
    	Log.d(getClass().getSimpleName(), s);
    }
    
    protected void info(String s) {
    	Log.i(getClass().getSimpleName(), s);
    }
    
    protected void error(String s) {
    	Log.e(getClass().getSimpleName(), s);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Bind to the service
        debug("Binding...");
        bindService(new Intent(this, MoodSpacesService.class), this, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (isBound()) {
            debug("Unbinding...");
            unbindService(this);
        }
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder binder) {
        service = ((MoodSpacesBinder) binder).getService();
        debug("Bound!");
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        service = null;
        debug("Unbound!");
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
