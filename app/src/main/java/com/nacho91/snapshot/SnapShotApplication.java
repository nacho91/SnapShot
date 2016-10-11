package com.nacho91.snapshot;

import android.app.Application;

import com.nacho91.snapshot.api.ApiManager;
import com.nacho91.snapshot.api.SnapShotApi;
import com.nacho91.snapshot.dagger.AppComponent;
import com.nacho91.snapshot.dagger.DaggerAppComponent;
import com.nacho91.snapshot.dagger.module.AppModule;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ignacio on 9/10/2016.
 */

public class SnapShotApplication extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();

        initImageLoader();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    private void initImageLoader(){

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .resetViewBeforeLoading(true)
                .displayer(new FadeInBitmapDisplayer(700))
                .build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .defaultDisplayImageOptions(options)
                .memoryCacheSizePercentage(5)
                .build();

        ImageLoader.getInstance().init(config);
    }
}
