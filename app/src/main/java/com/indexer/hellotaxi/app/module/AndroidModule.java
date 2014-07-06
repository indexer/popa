package com.indexer.hellotaxi.app.module;

import android.app.Application;
import android.content.Context;
import android.location.Criteria;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import com.indexer.hellotaxi.app.application.Popa;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

/**
 * Created by yemonkyaw on 5/10/14.
 */
@Module(library = true)
public class AndroidModule {
  private Popa application;

  public AndroidModule(Popa application) {
    this.application = application;
  }

  @Provides @Singleton Application provideApplicationContext() {
    return application;
  }

  @Provides @Singleton LocationManager provideLocationManager() {
    return (LocationManager) application.getSystemService(Context.LOCATION_SERVICE);
  }

  @Provides @Singleton Criteria provoideCriteria() {
    Criteria criteria = new Criteria();
    //Use FINE or COARSE (or NO_REQUIREMENT) here
    criteria.setAccuracy(Criteria.ACCURACY_FINE);
    criteria.setPowerRequirement(Criteria.POWER_LOW);
    criteria.setAltitudeRequired(true);
    criteria.setSpeedRequired(true);
    criteria.setCostAllowed(true);
    criteria.setBearingRequired(true);
    return criteria;
  }

  @Provides @Singleton ConnectivityManager provideConnectivityManager() {
    return (ConnectivityManager) application.getSystemService(Context.CONNECTIVITY_SERVICE);
  }
}
