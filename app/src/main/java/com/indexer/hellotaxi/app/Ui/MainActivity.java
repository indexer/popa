package com.indexer.hellotaxi.app.Ui;

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
import com.indexer.hellotaxi.app.Base.BasePopaActivity;
import com.indexer.hellotaxi.app.Listener.mapMarkerListener;
import com.indexer.hellotaxi.app.Listener.mlocationListener;
import com.indexer.hellotaxi.app.R;
import javax.inject.Inject;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

@EActivity @OptionsMenu(R.menu.main)
public class MainActivity extends BasePopaActivity {
  @Inject LocationManager locationManager;
  @Inject Criteria mCriteria;
  @Inject ConnectivityManager connectivityManager;

  mlocationListener mlocationListener = new mlocationListener();

  @ViewById(R.id.taxiDriverName) TextView txtDriverName;

  //ToDo map move care to the taxi location
  @Click(R.id.innerlayout) void changeCard() {
    txtDriverName.setText(
        txtDriverName.getText().equals("Swan Htet Aung") ? "Arar Aung" : "Swan Htet Aung");
  }

  @Override @UiThread
  protected void start() {
    //All your normal criteria setup
    // let Android select the right location provider for you
    getSupportActionBar().setTitle(getResources().getString(R.string.title_activity_call));
    String myProvider = locationManager.getBestProvider(mCriteria, true);
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
      addIcon(iconFactory, "9/C",
          new LatLng(location.getLatitude() + 0.00002, location.getLongitude() + 0.0005));
      getMap().setOnMarkerClickListener(mapMarkerListener);
    } else {
      //ToDo request user location and find near by taxi here
      getMap().moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(16, 96), 19));
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
        return false;
      case R.id.action_add: //thi will going to the Register
        Intent intentToCallActivity = new Intent(this, CallActivity_.class);
        startActivity(intentToCallActivity);
        return false;
      case R.id.action_settings: //this will going to the SettingActiivty
        Intent intentToSettingActivity = new Intent(this, SettingActivity_.class);
        startActivity(intentToSettingActivity);
        return false;
      case R.id.action_login:
        Intent intentToLoginActivity = new Intent(this, LoginActivity_.class);
        startActivity(intentToLoginActivity);
        return false;
    }
    return false;
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    locationManager.removeUpdates(mlocationListener);
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


