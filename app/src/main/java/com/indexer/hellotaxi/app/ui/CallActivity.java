package com.indexer.hellotaxi.app.ui;

import android.app.Activity;

import com.indexer.hellotaxi.app.R;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ItemClick;
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

    @Click(android.R.id.home)
    public void back() {
        this.onBackPressed();
    }

    @Override
    public void onResume() {
        super.onResume();
    }


}
