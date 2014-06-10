package com.indexer.hellotaxi.app.ui;

import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;

import com.indexer.hellotaxi.app.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.activity_setting)
public class SettingActivity extends ActionBarActivity {

    @AfterViews
    void startUI(){
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        supportActionBar.setHomeButtonEnabled(true);
    }

}
