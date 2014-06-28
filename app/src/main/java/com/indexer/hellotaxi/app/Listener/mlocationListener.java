package com.indexer.hellotaxi.app.Listener;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import javax.inject.Inject;

/**
 * Created by yemonkyaw on 5/10/14.
 */

public class mlocationListener implements LocationListener {

  @Inject LocationManager locationManager;
  @Inject Context appContext;

  @Override
  public void onLocationChanged(Location location) {
    Log.e("Chage Location", "Current" + location.getLatitude() + location.getLongitude());
  }

  @Override
  public void onStatusChanged(String s, int i, Bundle bundle) {

  }

  @Override
  public void onProviderEnabled(String s) {

  }

  @Override public void onProviderDisabled(String s) {
    boolean gpsEnable;
    boolean networkEnable = false;
    //ToDo Check why this not work :'(
    gpsEnable = locationManager.isProviderEnabled(locationManager.GPS_PROVIDER);
    if (!gpsEnable && networkEnable) {
      AlertDialog.Builder dialog = new AlertDialog.Builder(appContext);
      dialog.setPositiveButton("Open Location Service", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface paramDialogInterface, int paramInt) {
              // TODO Auto-generated method stub
              Intent myIntent = new Intent(Settings.ACTION_SECURITY_SETTINGS);
              appContext.startActivity(myIntent);
              //get gps
            }
          }
      );
      dialog.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {

        @Override
        public void onClick(DialogInterface paramDialogInterface, int paramInt) {
          // TODO Auto-generated method stub

        }
      });
      dialog.show();
    }
  }
}
