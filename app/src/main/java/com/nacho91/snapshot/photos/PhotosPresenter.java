package com.nacho91.snapshot.photos;

import com.codika.androidmvprx.presenter.BaseRxPresenter;
import com.nacho91.snapshot.api.ApiManager;
import com.nacho91.snapshot.model.Page;
import com.nacho91.snapshot.model.Response;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Ignacio on 9/10/2016.
 */

public class PhotosPresenter extends BaseRxPresenter<PhotosView> {

    private final ApiManager apiManager;

    public PhotosPresenter(ApiManager apiManager) {
        this.apiManager = apiManager;
    }

    public void recents(){
        addSubscription(apiManager.recents(1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<Response>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Response response) {
                getView().onRecentsSuccess(response.getPage().getPhotos());
            }
        }));
    }
}
