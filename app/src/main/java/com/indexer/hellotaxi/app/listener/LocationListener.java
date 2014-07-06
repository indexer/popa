package com.indexer.hellotaxi.app.listener;

import android.app.Activity;
import android.app.AlertDialog;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import com.indexer.hellotaxi.app.ui.MainActivity;

public class LocationListener implements android.location.LocationListener {
  Activity mActivity;

  public LocationListener(MainActivity mainActivity) {
    mActivity = mainActivity;
  }

  @Override
  public void onLocationChanged(Location location) {
    Log.e("Change Location", "Current" + location.getLatitude() + location.getLongitude());
  }

  @Override
  public void onStatusChanged(String s, int i, Bundle bundle) {

  }

  @Override
  public void onProviderEnabled(String s) {
    Log.e("Provider", "Enable Provide");
  }

  @Override public void onProviderDisabled(String s) {
    Log.e("Provider", "Provider Disable");
    AlertDialog.Builder builderSingle = new AlertDialog.Builder(mActivity);
    builderSingle.setIcon(android.R.drawable.stat_sys_warning);
    builderSingle.setTitle("Location Service Disable");
    builderSingle.show();
    //ToDo View Leak in There fix asap
  }
}
