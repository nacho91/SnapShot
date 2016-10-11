package com.nacho91.snapshot.dagger.module;

import android.content.Context;

import com.nacho91.snapshot.cache.CacheManager;
import com.nacho91.snapshot.dagger.scope.ApplicationScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ignacio on 11/10/16.
 */
@Module
public class CacheModule {

    @Provides
    @ApplicationScope
    CacheManager provideCacheManager(Context context){
        return new CacheManager(context);
    }

}
