package com.indexer.hellotaxi.app.Pref;

/**
 * Created by yemonkyaw on 6/19/14.
 */

import android.content.SharedPreferences;

public class StringPreference {
  private final SharedPreferences msharedPreferences;
  private final String mkey;
  private final String defaultValue;

  public StringPreference(SharedPreferences sharedPreferences, String key) {
    this(sharedPreferences, key, null);
  }

  public StringPreference(SharedPreferences preferences, String key, String defaultValue) {
    this.msharedPreferences = preferences;
    this.mkey = key;
    this.defaultValue = defaultValue;
  }

  public SharedPreferences getMsharedPreferences() {
    return msharedPreferences;
  }

  public String getMkey() {
    return mkey;
  }

  public String getDefaultValue() {
    return defaultValue;
  }
}
