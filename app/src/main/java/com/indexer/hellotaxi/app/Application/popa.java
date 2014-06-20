package com.indexer.hellotaxi.app.Application;

import android.app.Application;
import com.indexer.hellotaxi.app.Module.AndroidModule;
import dagger.ObjectGraph;
import java.util.Arrays;
import java.util.List;
import org.androidannotations.annotations.EApplication;

/**
 * Created by yemonkyaw on 5/10/14.
 */
@EApplication
public class popa extends Application {
  private static popa Popa;

  ObjectGraph objectGraph;
  private Double latitude;
  private Double longitude;

  public static popa getInstance() {
    return Popa;
  }

  @Override
  public void onCreate() {
    super.onCreate();
    objectGraph = objectGraph.create(getModules().toArray());
    Popa = this;
  }

  protected List<Object> getModules() {
    return Arrays.<Object>asList(new AndroidModule(this));
  }

  public ObjectGraph getObjectGraph() {
    return objectGraph;
  }
}


