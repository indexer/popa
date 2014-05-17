package com.indexer.hellotaxi.app.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.indexer.hellotaxi.app.Listener.newPhotoListener;
import com.indexer.hellotaxi.app.R;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OnActivityResult;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringArrayRes;
import org.androidannotations.annotations.res.StringRes;

import java.io.FileDescriptor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.inject.Inject;

@EActivity(R.layout.activity_call)
public class CallActivity extends Activity {

    Uri mImageUri;

    @Inject
    ActivityTitleController titleController;

    @StringRes(R.string.Signup)
    String title;

    @StringArrayRes(R.array.carTypeList)
    String[] carTypeList;

    @ViewById(R.id.carType)
    Spinner carType;

    @ViewById(R.id.txtCarNumber)
    EditText carNumber;

    @ViewById(R.id.imgUser)
    ImageView imgUser;


    @Click(R.id.radioDriver)
    void Show() {
        carType.setVisibility(View.VISIBLE);
        carNumber.setVisibility(View.VISIBLE);

    }

    @Click(R.id.radioPassenger)
    void Hide() {
        carType.setVisibility(View.GONE);
        carNumber.setVisibility(View.GONE);
    }


    @AfterViews
    void addSpinner() {

        ArrayList<String> typeCar = new ArrayList<String>(Arrays.asList(carTypeList));
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, typeCar);
        carType.setAdapter(dataAdapter);
        newPhotoListener newPhotoListener = new newPhotoListener(this);
        imgUser.setOnClickListener(newPhotoListener);

    }

    @OnActivityResult(1)
    void onResult(Intent data){
        mImageUri = data.getData();
        try {
            imgUser.setImageBitmap(getBimapFromUri(mImageUri));
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    private Bitmap getBimapFromUri(Uri mImageUri) throws IOException {
        ParcelFileDescriptor paracelFileDescriptor = null;

        paracelFileDescriptor = getContentResolver().openFileDescriptor(mImageUri, "r");
        FileDescriptor fileDescriptor = paracelFileDescriptor.getFileDescriptor();
        Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
        paracelFileDescriptor.close();

        return image;
    }


}
