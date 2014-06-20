package com.indexer.hellotaxi.app.Listener;

import android.app.Activity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.indexer.hellotaxi.app.R;

/**
 * Created by yemonkyaw on 5/11/14.
 */

public class mapMarkerListener implements GoogleMap.OnMarkerClickListener {
  Activity mActivity;

  public mapMarkerListener(Activity activity) {
    mActivity = activity;
  }

  @Override
  public boolean onMarkerClick(Marker marker) {
    AnimationSet set = new AnimationSet(true);
    Animation animation = new AlphaAnimation(0.0f, 1.0f);
    animation.setDuration(500);
    set.addAnimation(animation);

    animation =
        new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
            Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 0.0f);

    animation.setDuration(500);
    set.addAnimation(animation);
    RelativeLayout layout = (RelativeLayout) mActivity.findViewById(R.id.innerlayout);
    layout.setVisibility(View.VISIBLE);
    layout.setAnimation(set);
    return false;
  }
}
