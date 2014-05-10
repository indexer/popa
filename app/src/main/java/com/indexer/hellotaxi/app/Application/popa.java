package com.indexer.hellotaxi.app.Application;

import android.app.Application;

import org.androidannotations.annotations.EApplication;
import dagger.ObjectGraph;

/**
 * Created by yemonkyaw on 5/10/14.
 */
@EApplication
public class popa extends Application {

    ObjectGraph objectGraph;


    public ObjectGraph getObjectGraph() {
        return objectGraph;
    }

}


