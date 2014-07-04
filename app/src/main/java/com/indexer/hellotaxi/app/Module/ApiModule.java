package com.indexer.hellotaxi.app.module;

import com.squareup.okhttp.OkHttpClient;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import retrofit.Endpoint;
import retrofit.Endpoints;
import retrofit.RestAdapter;
import retrofit.client.Client;
import retrofit.client.OkClient;

/**
 * Created by yemonkyaw on 6/21/14.
 */
@Module(library = true, complete = false)
public class ApiModule {
  public static final String apiServer = "www.google.com";

  @Provides @Singleton Client provideClient(OkHttpClient client) {
    return new OkClient(client);
  }

  @Provides @Singleton Endpoint provideEndPoint() {
    return Endpoints.newFixedEndpoint(apiServer);
  }

  @Provides @Singleton RestAdapter provideRestAdapter(Endpoint endpoint, Client client) {
    return new RestAdapter.Builder() //
        .setClient(client) //
        .setEndpoint(endpoint) //
        .build();
  }
}
