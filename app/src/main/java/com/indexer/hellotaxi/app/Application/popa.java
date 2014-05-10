package com.indexer.hellotaxi.app.Application;

import android.app.Application;

import com.indexer.hellotaxi.app.Module.AndroidModule;

import org.androidannotations.annotations.EApplication;

import java.util.Arrays;
import java.util.List;

import dagger.ObjectGraph;

/**
 * Created by yemonkyaw on 5/10/14.
 */
@EApplication
public class popa extends Application {

    ObjectGraph objectGraph;

    @Override public void onCreate(){
        super.onCreate();
        objectGraph = objectGraph.create(getModules().toArray());
    }

    protected List<Object> getModules() {
        return Arrays.<Object>asList(new AndroidModule(this));
    }

    public ObjectGraph getObjectGraph() {
        return objectGraph;
    }

}


