package com.indexer.hellotaxi.app.Base;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.indexer.hellotaxi.app.Application.popa;
import com.indexer.hellotaxi.app.Module.ActivityModule;

import java.util.Arrays;
import java.util.List;

import dagger.ObjectGraph;

public abstract class BasePopaActivity extends FragmentActivity {
    private ObjectGraph activityGraph;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
      super.onCreate(saveInstanceState);
        popa application = (popa)getApplication();
        activityGraph = application.getObjectGraph().plus(getModules().toArray());
        activityGraph.inject(this);
    }

    @Override protected void onDestroy() {
        // Eagerly clear the reference to the activity graph to allow it to be garbage collected as
        // soon as possible.
        activityGraph = null;
        super.onDestroy();
    }


    protected List<Object> getModules() {
        return Arrays.<Object>asList(new ActivityModule(this));
    }

    /** Inject the supplied {@code object} using the activity-specific graph. */
    public void inject(Object object) {
        activityGraph.inject(object);
    }


}