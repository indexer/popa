package com.indexer.hellotaxi.app.ui;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.ui.IconGenerator;
import com.indexer.hellotaxi.app.Base.BasePopaActivity;
import com.indexer.hellotaxi.app.Listener.mapMarkerListener;
import com.indexer.hellotaxi.app.Listener.mlocationListener;
import com.indexer.hellotaxi.app.R;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringRes;

import javax.inject.Inject;

@EActivity
@OptionsMenu(R.menu.main)
public class MainActivity extends BasePopaActivity {
    @Inject
    LocationManager locationManager;
    mlocationListener mlocationListener = new mlocationListener();

    @StringRes(R.string.findtaxi)
    String findTaxi;

    @ViewById(R.id.taxiDriverName)
    TextView txtDriverName;


    @Click(R.id.innerlayout)
    void changeCard() {
        if (txtDriverName.getText().equals("Swan Htet Aung")) {
            txtDriverName.setText("Arar Aung");
        } else {
            txtDriverName.setText("Swan Htet Aung");
        }
    }

    @AfterInject
    void title() {
        getActionBar().setTitle(findTaxi);
    }

    @Override
    @UiThread
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
                        location.getLongitude()), 19));
        iconFactory.setStyle(IconGenerator.STYLE_GREEN);
        addIcon(iconFactory, "3/W", new LatLng(location.getLatitude(), location.getLongitude()));
        addIcon(iconFactory, "9/C", new LatLng(location.getLatitude() +
                0.00002, location.getLongitude() + 0.0005));
        getMap().setOnMarkerClickListener(mapMarkerListener);


    }

    private void addIcon(IconGenerator iconFactory, String text, LatLng position) {
        MarkerOptions markerOptions = new MarkerOptions().
                icon(BitmapDescriptorFactory.fromBitmap(iconFactory.makeIcon(text))).
                position(position).
                anchor(iconFactory.getAnchorU(), iconFactory.getAnchorV());
        getMap().addMarker(markerOptions);
    }
}
