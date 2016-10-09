package com.nacho91.snapshot;

import android.app.Application;

import com.nacho91.snapshot.api.ApiManager;
import com.nacho91.snapshot.api.SnapShotApi;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ignacio on 9/10/2016.
 */

public class SnapShotApplication extends Application {

    private ApiManager apiManager;

    @Override
    public void onCreate() {
        super.onCreate();

        apiManager = new ApiManager(provideSubteApi(provideOkHttpClient()));
    }

    private OkHttpClient provideOkHttpClient(){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        if(BuildConfig.DEBUG)
            httpClient.interceptors().add(logging);

        return httpClient.build();
    }

    private SnapShotApi provideSubteApi(OkHttpClient client){
        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        return retrofit.create(SnapShotApi.class);
    }

    public ApiManager getApiManager(){
        return apiManager;
    }
}
