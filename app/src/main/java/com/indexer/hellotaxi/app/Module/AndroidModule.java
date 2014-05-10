package com.indexer.hellotaxi.app.Module;

import android.app.Application;
import android.content.Context;
import android.location.LocationManager;

import com.indexer.hellotaxi.app.Application.popa;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by yemonkyaw on 5/10/14.
 */
@Module(library = true)
public class AndroidModule {
    private popa application;


    public AndroidModule(popa application) {
        this.application = application;
    }


    @Provides
    @Singleton
    Application provideApplicationContext() {
        return application;
    }

    @Provides
    @Singleton
    LocationManager provideLocationManager() {
        return (LocationManager) application.getSystemService(Context.LOCATION_SERVICE);
    }
}
