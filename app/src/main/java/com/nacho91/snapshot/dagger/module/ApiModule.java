package com.nacho91.snapshot.dagger.module;

import com.nacho91.snapshot.BuildConfig;
import com.nacho91.snapshot.api.ApiManager;
import com.nacho91.snapshot.api.RxApiCallAdapterFactory;
import com.nacho91.snapshot.api.SnapShotApi;
import com.nacho91.snapshot.dagger.scope.ApplicationScope;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Emiliano Mallo on 10/05/16.
 */
@Module
public class ApiModule {

    @Provides
    @ApplicationScope
    OkHttpClient provideOkHttpClient(){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        if(BuildConfig.DEBUG)
            httpClient.interceptors().add(logging);

        return httpClient.build();
    }

    @Provides
    @ApplicationScope
    SnapShotApi provideSubteApi(OkHttpClient client){

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxApiCallAdapterFactory.create())
                .build();

        return retrofit.create(SnapShotApi.class);
    }

    @Provides
    @ApplicationScope
    ApiManager provideApiManager(SnapShotApi api){
        return new ApiManager(api);
    }

}
