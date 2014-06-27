package com.indexer.hellotaxi.app.Listener;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by yemonkyaw on 5/10/14.
 */

public class mlocationListener implements LocationListener {

  @Override
  public void onLocationChanged(Location location) {
    Log.e("Chage Location","Current"+location.getLatitude()+location.getLongitude());
  }

  @Override
  public void onStatusChanged(String s, int i, Bundle bundle) {

  }

  @Override
  public void onProviderEnabled(String s) {

  }

  @Override
  public void onProviderDisabled(String s) {

  }
}
