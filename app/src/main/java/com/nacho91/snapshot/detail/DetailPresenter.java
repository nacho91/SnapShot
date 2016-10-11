package com.nacho91.snapshot.detail;

import com.codika.androidmvprx.presenter.BaseRxPresenter;
import com.nacho91.snapshot.api.ApiSubscriber;
import com.nacho91.snapshot.detail.binding.DetailViewModel;
import com.nacho91.snapshot.model.InfoResponse;
import com.nacho91.snapshot.service.ServiceManager;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Ignacio on 10/10/2016.
 */

public class DetailPresenter extends BaseRxPresenter<DetailView> {

    private final ServiceManager service;

    @Inject
    public DetailPresenter(ServiceManager service) {
        this.service = service;
    }

    public void info(String photoId){
        addSubscription(service.info(photoId)
                .map(new Func1<InfoResponse, DetailViewModel>() {
                    @Override
                    public DetailViewModel call(InfoResponse infoResponse) {
                        return new DetailViewModel(infoResponse.getPhoto());
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new DetailSubscriber()));
    }

    private class DetailSubscriber extends ApiSubscriber<DetailViewModel, String>{

        public DetailSubscriber() {
            super(String.class);
        }

        @Override
        public void onNetworkError() {
            getView().onInfoNetworkError();
        }

        @Override
        public void onNext(DetailViewModel detail) {
            getView().onInfoSuccess(detail);
        }
    }
}
