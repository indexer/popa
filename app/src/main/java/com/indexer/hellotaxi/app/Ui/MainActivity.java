package com.indexer.hellotaxi.app.Ui;

import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.view.MenuItem;
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
import javax.inject.Inject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

@EActivity @OptionsMenu(R.menu.main)
public class MainActivity extends BasePopaActivity {
  @Inject LocationManager locationManager;
  mlocationListener mlocationListener = new mlocationListener();

  @ViewById(R.id.taxiDriverName) TextView txtDriverName;

  @Click(R.id.innerlayout) void changeCard() {
    if (txtDriverName.getText().equals("Swan Htet Aung")) {
      txtDriverName.setText("Arar Aung");
    } else {
      txtDriverName.setText("Swan Htet Aung");
    }
  }

  @AfterViews void title() {
    getSupportActionBar().setTitle(getResources().getString(R.string.title_activity_call));
  }

  @Override @UiThread
  protected void start() {
    locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

    //All your normal criteria setup
    Criteria criteria = new Criteria();
    //Use FINE or COARSE (or NO_REQUIREMENT) here
    criteria.setAccuracy(Criteria.ACCURACY_FINE);
    criteria.setPowerRequirement(Criteria.POWER_LOW);
   criteria.setAltitudeRequired(true);
    criteria.setSpeedRequired(true);
    criteria.setCostAllowed(true);
    criteria.setBearingRequired(true);
    // let Android select the right location provider for you
    String myProvider = locationManager.getBestProvider(criteria, true);
    locationManager.requestLocationUpdates(myProvider, 0, 0, mlocationListener);
    mapMarkerListener mapMarkerListener = new mapMarkerListener(this);
    IconGenerator iconFactory = new IconGenerator(this);
    getMap().setMyLocationEnabled(true);
    Location location = locationManager.getLastKnownLocation(myProvider);
    if (location != null) {
      getMap().moveCamera(CameraUpdateFactory.newLatLngZoom(
          new LatLng(location.getLatitude(), location.getLongitude()), 19));
      iconFactory.setStyle(IconGenerator.STYLE_GREEN);
        addIcon(iconFactory, "3/W", new LatLng(location.getLatitude(), location.getLongitude()));
        addIcon(iconFactory, "9/C", new LatLng(location.getLatitude() + 0.00002, location.getLongitude() + 0.0005));
      getMap().setOnMarkerClickListener(mapMarkerListener);
    } else {

    }
  }

  //to show Maker with Customize cluster item theme
  private void addIcon(IconGenerator iconFactory, String text, LatLng position) {
    MarkerOptions markerOptions = new MarkerOptions().
        icon(BitmapDescriptorFactory.fromBitmap(iconFactory.makeIcon(text))).
        position(position).
        anchor(iconFactory.getAnchorU(), iconFactory.getAnchorV());
    getMap().addMarker(markerOptions);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        this.onBackPressed();
        finish();
        return false;
      case R.id.action_add:
        //thi will going to the Register
        Intent intentToCallActivity = new Intent(this, CallActivity_.class);
        startActivity(intentToCallActivity);
        return false;
      case R.id.action_settings:
        //this will going to the SettingActiivty
        Intent intentToSettingActivity = new Intent(this, SettingActivity_.class);
        startActivity(intentToSettingActivity);
        return false;
    }
    return false;
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    locationManager.removeUpdates(mlocationListener);
  }
}


