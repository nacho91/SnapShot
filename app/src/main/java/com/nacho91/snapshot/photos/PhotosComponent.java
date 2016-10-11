package com.nacho91.snapshot.photos;

import com.nacho91.snapshot.dagger.AppComponent;
import com.nacho91.snapshot.dagger.scope.ActivityScope;

import dagger.Component;

/**
 * Created by ignacio on 11/10/16.
 */
@ActivityScope
@Component(dependencies = AppComponent.class)
public interface PhotosComponent {
    PhotosPresenter getPresenter();
}
