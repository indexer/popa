package com.indexer.hellotaxi.app.ui;

import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.ui.IconGenerator;

import com.indexer.hellotaxi.app.R;
import com.indexer.hellotaxi.app.base.BasePopaActivity;
import com.indexer.hellotaxi.app.listener.LocationListener;
import com.indexer.hellotaxi.app.listener.MapMarkerListener;
import javax.inject.Inject;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

@EActivity @OptionsMenu(R.menu.main)
public class MainActivity extends BasePopaActivity {
  @Inject LocationManager mLocationManager;
  @Inject Criteria mCriteria;
  @Inject ConnectivityManager mConnectivityManager;

  LocationListener mLocationListener = new LocationListener(this);
  @ViewById(R.id.taxiDriverName) TextView txtDriverName;

  // TODO map move care to the taxi location
  @Click(R.id.innerlayout) void changeCard() {
    txtDriverName.setText(
        txtDriverName.getText().equals("Swan Htet Aung") ? "Arar Aung" : "Swan Htet Aung");
  }

  @Override @UiThread
  protected void start() {
    // All your normal criteria setup
    // let Android select the right location provider for you
    getSupportActionBar().setTitle(getResources().getString(R.string.title_activity_call));

    String myProvider = mLocationManager.getBestProvider(mCriteria, true);
    mLocationManager.requestLocationUpdates(myProvider, 0, 0, mLocationListener);
    MapMarkerListener mMarkerListener = new MapMarkerListener(this);
    IconGenerator iconFactory = new IconGenerator(this);
    getMap().setMyLocationEnabled(true);
    Location mLocation = mLocationManager.getLastKnownLocation(myProvider);

    if (mLocation != null) {
      getMap().moveCamera(CameraUpdateFactory.newLatLngZoom(
          new LatLng(mLocation.getLatitude(), mLocation.getLongitude()), 19));
      iconFactory.setStyle(IconGenerator.STYLE_GREEN);
      addIcon(iconFactory, "3/W", new LatLng(mLocation.getLatitude(), mLocation.getLongitude()));
      addIcon(iconFactory, "9/C",
          new LatLng(mLocation.getLatitude() + 0.00002, mLocation.getLongitude() + 0.0005));
      getMap().setOnMarkerClickListener(mMarkerListener);
    } else {
      // TODO request user location and find near by taxi here
      getMap().moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(16, 96), 19));
    }
  }

  // To show maker with customize cluster item theme
  private void addIcon(IconGenerator iconFactory, String text, LatLng position) {
    MarkerOptions markerOptions = new MarkerOptions().
        icon(BitmapDescriptorFactory.fromBitmap(iconFactory.makeIcon(text))).
        position(position).
        anchor(iconFactory.getAnchorU(), iconFactory.getAnchorV());
    getMap().addMarker(markerOptions);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    return super.onCreateOptionsMenu(menu);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int i = item.getItemId();
    switch (i) {
      case android.R.id.home:
        this.onBackPressed();
        finish();
      case R.id.action_add: // This will going to the register
        Intent intentToCallActivity = new Intent(this, CallActivity_.class);
        startActivity(intentToCallActivity);
      case R.id.action_settings: // This will going to the Setting Activity
        Intent intentToSettingActivity = new Intent(this, SettingActivity_.class);
        startActivity(intentToSettingActivity);
      case R.id.action_login:
        Intent intentToLoginActivity = new Intent(this, LoginActivity_.class);
        startActivity(intentToLoginActivity);
    }
    return false;
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    mLocationManager.removeUpdates(mLocationListener);
  }

  @Override protected void onResume() {
    super.onResume();
    checkPlayServices();
  }

  private boolean checkPlayServices() {
    int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
    if (status != ConnectionResult.SUCCESS) {
      if (GooglePlayServicesUtil.isUserRecoverableError(status)) {
        Toast.makeText(this, "User Recoverable Error", Toast.LENGTH_LONG).show();
      } else {
        Toast.makeText(this, "This device is not supported.", Toast.LENGTH_LONG).show();
        finish();
      }
      return false;
    }
    return true;
  }
}


