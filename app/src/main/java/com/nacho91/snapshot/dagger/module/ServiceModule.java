package com.nacho91.snapshot.dagger.module;

import com.nacho91.snapshot.api.ApiManager;
import com.nacho91.snapshot.cache.CacheManager;
import com.nacho91.snapshot.dagger.scope.ApplicationScope;
import com.nacho91.snapshot.service.ServiceManager;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ignacio on 11/10/16.
 */
@Module
public class ServiceModule {

    @Provides
    @ApplicationScope
    ServiceManager provideServiceManager(CacheManager cacheManager, ApiManager apiManager){
        return new ServiceManager(apiManager, cacheManager);
    }
}
