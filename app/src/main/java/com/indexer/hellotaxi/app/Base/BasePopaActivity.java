package com.indexer.hellotaxi.app.Base;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.indexer.hellotaxi.app.Application.popa;
import com.indexer.hellotaxi.app.Module.ActivityModule;
import com.indexer.hellotaxi.app.R;

import java.util.Arrays;
import java.util.List;

import dagger.ObjectGraph;

public abstract class BasePopaActivity extends ActionBarActivity {
    private ObjectGraph activityGraph;
    private GoogleMap mMap;

    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(getLayoutId());
        setUpMapIfNeeded();
        popa application = (popa) getApplication();
        activityGraph = application.getObjectGraph().plus(getModules().toArray());
        activityGraph.inject(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
          setUpMapIfNeeded();
    }

    @Override
    protected void onDestroy() {
        // Eagerly clear the reference to the activity graph to allow it to be garbage collected as
        // soon as possible.
        activityGraph = null;
        super.onDestroy();
    }

    private void setUpMapIfNeeded() {
        if (mMap != null) {
            return;
        }
        mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
        if (mMap != null) {
              start();
        }
    }


    /**
     * Run the demo-specific code.
     */
    protected abstract void start();

    protected GoogleMap getMap() {
        setUpMapIfNeeded();
        return mMap;
    }


    protected List<Object> getModules() {
        return Arrays.<Object>asList(new ActivityModule(this));
    }

    /**
     * Inject the supplied {@code object} using the activity-specific graph.
     */
    public void inject(Object object) {
        activityGraph.inject(object);
    }


}