package com.nacho91.snapshot.dagger.module;

import android.app.Application;
import android.content.Context;

import com.nacho91.snapshot.dagger.scope.ApplicationScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ignacio on 18/05/16.
 */
@Module
public class AppModule {

    private final Application app;

    public AppModule(Application app) {
        this.app = app;
    }

    @Provides
    @ApplicationScope
    public Context provideContext() {
        return app;
    }

}
