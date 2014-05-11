package com.indexer.hellotaxi.app.Listener;

import android.app.Activity;
import android.content.Intent;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.indexer.hellotaxi.app.ui.CallActivity_;

/**
 * Created by yemonkyaw on 5/11/14.
 */
public class mapMarkerListener implements GoogleMap.OnMarkerClickListener {
    Activity mActivity;

    public mapMarkerListener(Activity activity)
    {
        mActivity =activity;
    }
    @Override
    public boolean onMarkerClick(Marker marker) {

        Intent i = new Intent(mActivity,CallActivity_.class);
        mActivity.startActivity(i);
        return false;
    }
}
