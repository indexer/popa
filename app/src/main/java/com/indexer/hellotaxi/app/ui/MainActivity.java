package com.indexer.hellotaxi.app.ui;

import android.support.v7.app.ActionBarActivity;
import android.view.Menu;

import com.indexer.hellotaxi.app.R;

import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.activity_main)
public class MainActivity extends ActionBarActivity {


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
