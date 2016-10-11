package com.nacho91.snapshot.detail;

import android.content.Context;

import com.codika.androidmvp.loader.PresenterLoader;
import com.nacho91.snapshot.SnapShotApplication;
import com.nacho91.snapshot.photos.PhotosPresenter;

/**
 * Created by Ignacio on 10/10/2016.
 */

public class DetailLoader extends PresenterLoader<DetailPresenter> {

    private DetailComponent component;

    public DetailLoader(Context context) {
        super(context);

        SnapShotApplication app = (SnapShotApplication) getContext().getApplicationContext();

        component = DaggerDetailComponent.builder().appComponent(app.getAppComponent()).build();
    }

    @Override
    public DetailPresenter getPresenter() {
        return component.getPresenter();
    }
}
