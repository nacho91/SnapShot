package com.nacho91.snapshot.detail;

import android.content.Context;

import com.codika.androidmvp.loader.PresenterLoader;
import com.nacho91.snapshot.SnapShotApplication;
import com.nacho91.snapshot.photos.PhotosPresenter;

/**
 * Created by Ignacio on 10/10/2016.
 */

public class DetailLoader extends PresenterLoader<DetailPresenter> {

    public DetailLoader(Context context) {
        super(context);
    }

    @Override
    public DetailPresenter getPresenter() {
        SnapShotApplication app = (SnapShotApplication) getContext().getApplicationContext();

        return new DetailPresenter(app.getApiManager());
    }
}
