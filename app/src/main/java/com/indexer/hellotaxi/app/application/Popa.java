package com.indexer.hellotaxi.app.application;

import android.app.Application;
import com.indexer.hellotaxi.app.module.AndroidModule;
import dagger.ObjectGraph;
import java.util.Arrays;
import java.util.List;
import org.androidannotations.annotations.EApplication;

/**
 * Created by yemonkyaw on 5/10/14.
 */
@EApplication
public class Popa extends Application {
  private static com.indexer.hellotaxi.app.application.Popa mPopa;

  ObjectGraph objectGraph;

  public static com.indexer.hellotaxi.app.application.Popa getInstance() {
    return mPopa;
  }

  @Override
  public void onCreate() {
    super.onCreate();
    objectGraph = ObjectGraph.create(getModules().toArray());
    mPopa = this;
  }

  protected List<Object> getModules() {
    return Arrays.<Object>asList(new AndroidModule(this));
  }

  public ObjectGraph getObjectGraph() {
    return objectGraph;
  }
}


