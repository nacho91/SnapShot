package com.nacho91.snapshot.detail;

import com.codika.androidmvprx.presenter.BaseRxPresenter;
import com.nacho91.snapshot.api.ApiManager;
import com.nacho91.snapshot.model.InfoResponse;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Ignacio on 10/10/2016.
 */

public class DetailPresenter extends BaseRxPresenter<DetailView> {

    private final ApiManager apiManager;

    @Inject
    public DetailPresenter(ApiManager apiManager) {
        this.apiManager = apiManager;
    }

    public void info(String photoId){
        addSubscription(apiManager.info(photoId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<InfoResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(InfoResponse infoResponse) {
                        getView().onInfoSuccess(infoResponse.getPhoto());
                    }
                }));
    }
}