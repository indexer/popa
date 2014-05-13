package com.indexer.hellotaxi.app.ui;

import android.app.Activity;
import android.view.MenuItem;

import com.indexer.hellotaxi.app.R;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.res.StringRes;

import javax.inject.Inject;

@EActivity(R.layout.activity_call)
public class CallActivity extends Activity {

    @Inject
    ActivityTitleController titleController;

    @StringRes(R.string.title_activity_call)
    String title;


    @AfterInject
    void title() {
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setTitle(title);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.onBackPressed();
                finish();
                return false;
        }
        return false;
    }

}
