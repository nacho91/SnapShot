package com.nacho91.snapshot.dagger;

import android.content.Context;


import com.nacho91.snapshot.api.ApiManager;
import com.nacho91.snapshot.cache.CacheManager;
import com.nacho91.snapshot.dagger.module.ApiModule;
import com.nacho91.snapshot.dagger.module.AppModule;
import com.nacho91.snapshot.dagger.module.CacheModule;
import com.nacho91.snapshot.dagger.module.ServiceModule;
import com.nacho91.snapshot.dagger.scope.ApplicationScope;
import com.nacho91.snapshot.service.ServiceManager;

import dagger.Component;

/**
 * Created by ignacio on 26/05/16.
 */
@ApplicationScope
@Component( modules = {AppModule.class, ApiModule.class, CacheModule.class, ServiceModule.class})
public interface AppComponent {
    Context getContext();
    ApiManager getApiManager();
    CacheManager getCacheManger();
    ServiceManager getServiceManager();
}
