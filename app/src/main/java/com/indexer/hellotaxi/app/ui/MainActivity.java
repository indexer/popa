package com.indexer.hellotaxi.app.ui;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.view.Menu;
import android.view.MenuInflater;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.ui.IconGenerator;
import com.indexer.hellotaxi.app.Application.popa_;
import com.indexer.hellotaxi.app.Base.BasePopaActivity;
import com.indexer.hellotaxi.app.Listener.mapMarkerListener;
import com.indexer.hellotaxi.app.Listener.mlocationListener;
import com.indexer.hellotaxi.app.R;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;

import javax.inject.Inject;

@EActivity
public class MainActivity extends BasePopaActivity {
    @Inject
    LocationManager locationManager;
    mlocationListener mlocationListener = new mlocationListener();



    @Override
    protected void start() {

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0,
                mlocationListener);
        mapMarkerListener mapMarkerListener = new mapMarkerListener(this);
        IconGenerator iconFactory = new IconGenerator(this);
        getMap().setMyLocationEnabled(true);
        Location location = locationManager.getLastKnownLocation
                (LocationManager.GPS_PROVIDER);
        getMap().moveCamera(CameraUpdateFactory.newLatLngZoom
                (new LatLng(location.getLatitude(),
                        location.getLongitude()), 13));
        iconFactory.setStyle(IconGenerator.STYLE_GREEN);
        addIcon(iconFactory, new LatLng(location.getLatitude(), location.getLongitude()));
        addIcon(iconFactory, new LatLng(location.getLatitude() +
                0.00002, location.getLongitude() + 0.0005));
        getMap().setOnMarkerClickListener(mapMarkerListener);


    }


    private void addIcon(IconGenerator iconFactory, LatLng position) {
        MarkerOptions markerOptions = new MarkerOptions().
                icon(BitmapDescriptorFactory.fromResource(R.drawable.taxi)).
                position(position).
                anchor(iconFactory.getAnchorU(), iconFactory.getAnchorV());
        getMap().addMarker(markerOptions);
    }
}
