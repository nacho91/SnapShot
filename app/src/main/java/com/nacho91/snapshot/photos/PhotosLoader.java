package com.nacho91.snapshot.photos;

import android.content.Context;

import com.codika.androidmvp.loader.PresenterLoader;
import com.nacho91.snapshot.SnapShotApplication;

/**
 * Created by Ignacio on 9/10/2016.
 */

public class PhotosLoader extends PresenterLoader<PhotosPresenter> {

    private PhotosComponent component;

    public PhotosLoader(Context context) {
        super(context);

        SnapShotApplication app = (SnapShotApplication) getContext().getApplicationContext();

        component = DaggerPhotosComponent.builder().appComponent(app.getAppComponent()).build();
    }

    @Override
    public PhotosPresenter getPresenter() {
        return component.getPresenter();
    }
}
