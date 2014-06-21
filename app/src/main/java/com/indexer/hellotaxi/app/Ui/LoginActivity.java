package com.indexer.hellotaxi.app.Ui;

import android.app.Activity;
import android.view.MenuItem;
import com.indexer.hellotaxi.app.R;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.activity_login)
public class LoginActivity extends Activity {

  @AfterViews void showLoginUi() {
    getActionBar().setDisplayHomeAsUpEnabled(true);
    getActionBar().setHomeButtonEnabled(true);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    switch (item.getItemId()) {
      case android.R.id.home:
        this.onBackPressed();
        return false;
    }
    return false;
  }
}
