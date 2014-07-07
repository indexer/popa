package com.indexer.hellotaxi.app.ui;

import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import com.indexer.hellotaxi.app.R;
import org.androidannotations.annotations.AfterTextChange;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_login)
public class LoginActivity extends ActionBarActivity {
  @ViewById(R.id.btnLogin) Button btnLogin;
  @ViewById(R.id.userPhoneEditText) EditText userPhoneEditText;

  //@BeforeTextChange(R.id.userPhoneEditText) void beforeTextChange() {
  //  btnLogin.setEnabled(false);
  //}

  @AfterTextChange(R.id.userPhoneEditText) void afterTextChange(Editable s) {
    if (s.toString().trim().length() == 0) {
      btnLogin.setEnabled(false);
    } else {
      btnLogin.setEnabled(true);
    }
  }

  @AfterViews void showLoginUi() {
    ActionBar mActionBar = getSupportActionBar();
    mActionBar.setDisplayHomeAsUpEnabled(true);
    mActionBar.setHomeButtonEnabled(true);
    mActionBar.setIcon(R.drawable.ic_launcher);
    btnLogin.setEnabled(false);
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
