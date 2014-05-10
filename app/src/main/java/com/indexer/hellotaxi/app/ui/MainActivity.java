package com.indexer.hellotaxi.app.ui;

import android.view.Menu;

import com.indexer.hellotaxi.app.Base.BasePopaActivity;
import com.indexer.hellotaxi.app.R;

import org.androidannotations.annotations.EActivity;

@EActivity
public class MainActivity extends BasePopaActivity {


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
