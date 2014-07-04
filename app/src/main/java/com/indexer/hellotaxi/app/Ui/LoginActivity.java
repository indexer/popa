package com.indexer.hellotaxi.app.ui;

import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import com.indexer.hellotaxi.app.R;
import com.indexer.hellotaxi.app.base.BasePopaActivity;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.activity_login)
public class LoginActivity extends BasePopaActivity {
  @AfterViews void showLoginUi() {
    ActionBar mActionBar = getSupportActionBar();
    mActionBar.setDisplayHomeAsUpEnabled(true);
    mActionBar.setHomeButtonEnabled(true);
    mActionBar.setIcon(R.drawable.ic_launcher);
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

  @Override protected void start() {

  }
}
