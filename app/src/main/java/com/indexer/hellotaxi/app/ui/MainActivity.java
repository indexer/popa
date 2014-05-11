package com.indexer.hellotaxi.app.ui;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.view.Menu;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.indexer.hellotaxi.app.Application.popa_;
import com.indexer.hellotaxi.app.Base.BasePopaActivity;
import com.indexer.hellotaxi.app.Listener.mlocationListener;
import com.indexer.hellotaxi.app.R;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;

import javax.inject.Inject;

@EActivity
public class MainActivity extends BasePopaActivity {
    @Inject
    LocationManager locationManager;
    popa_ Popa;
    mlocationListener mlocationListener = new mlocationListener();
    ;

    @Override
    @UiThread
    protected void startDemo() {

        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        Popa = (popa_) getApplicationContext();

        getMap().moveCamera(CameraUpdateFactory.newLatLngZoom
                (new LatLng(location.getLatitude(), location.getLongitude()), 13));
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0,
                mlocationListener);
        getMap().setMyLocationEnabled(true);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mlocationListener = new mlocationListener();
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 400, 1, mlocationListener);
    }
}
