package com.indexer.hellotaxi.app.ui;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.view.Menu;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.ui.IconGenerator;
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 400, 1, mlocationListener);
    }

    @Override
    @UiThread
    protected void startActiviy() {

        Location location = locationManager.getLastKnownLocation
                (LocationManager.GPS_PROVIDER);
        Popa = (popa_) getApplicationContext();
        IconGenerator iconFactory = new IconGenerator(this);
        getMap().moveCamera(CameraUpdateFactory.newLatLngZoom
                (new LatLng(location.getLatitude(),
                        location.getLongitude()), 13));
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0,
                mlocationListener);
        getMap().setMyLocationEnabled(true);
        iconFactory.setStyle(IconGenerator.STYLE_GREEN);
        addIcon(iconFactory, new LatLng(location.getLatitude(), location.getLongitude()));

        addIcon(iconFactory, new LatLng(location.getLatitude() +
                0.00002, location.getLongitude() + 0.0005));

        getMap().setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {

                return false;
            }
        });
    }


    private void addIcon(IconGenerator iconFactory, LatLng position) {
        MarkerOptions markerOptions = new MarkerOptions().
                icon(BitmapDescriptorFactory.fromResource(R.drawable.taxi)).
                position(position).
                anchor(iconFactory.getAnchorU(), iconFactory.getAnchorV());

        getMap().addMarker(markerOptions);
    }
}
