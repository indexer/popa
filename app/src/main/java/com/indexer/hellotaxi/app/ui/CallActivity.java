package com.indexer.hellotaxi.app.ui;

import android.app.Activity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.indexer.hellotaxi.app.R;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringArrayRes;
import org.androidannotations.annotations.res.StringRes;

import java.util.ArrayList;
import java.util.Arrays;

import javax.inject.Inject;

@EActivity(R.layout.activity_call)
public class CallActivity extends Activity {

    @Inject
    ActivityTitleController titleController;

    @StringRes(R.string.Signup)
    String title;

    @StringArrayRes(R.array.carTypeList)
    String[] carTypeList;

    @ViewById(R.id.carType)
    Spinner carType;



    @Click(R.id.radioDriver)
    void Show(){
        carType.setVisibility(View.VISIBLE);
    }

    @Click(R.id.radioPassenger)
    void Hide(){
        carType.setVisibility(View.GONE);
    }

    @AfterViews
    void addSpinner() {
        ArrayList<String> typeCar = new ArrayList<String>(Arrays.asList(carTypeList));
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, typeCar);
        carType.setAdapter(dataAdapter);
    }

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
