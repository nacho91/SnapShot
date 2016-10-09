package com.nacho91.snapshot.photos;

import android.content.Context;

import com.codika.androidmvp.loader.PresenterLoader;
import com.nacho91.snapshot.SnapShotApplication;

/**
 * Created by Ignacio on 9/10/2016.
 */

public class PhotosLoader extends PresenterLoader<PhotosPresenter> {

    public PhotosLoader(Context context) {
        super(context);
    }

    @Override
    public PhotosPresenter getPresenter() {

        SnapShotApplication app = (SnapShotApplication) getContext().getApplicationContext();

        return new PhotosPresenter(app.getApiManager());
    }
}
