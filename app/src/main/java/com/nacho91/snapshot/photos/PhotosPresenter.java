package com.nacho91.snapshot.photos;

import com.codika.androidmvprx.presenter.BaseRxPresenter;
import com.nacho91.snapshot.api.ApiManager;
import com.nacho91.snapshot.api.ApiSubscriber;
import com.nacho91.snapshot.model.Page;
import com.nacho91.snapshot.model.PhotosResponse;
import com.nacho91.snapshot.photos.binding.PhotoViewModel;
import com.nacho91.snapshot.service.ServiceManager;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Ignacio on 9/10/2016.
 */

public class PhotosPresenter extends BaseRxPresenter<PhotosView> {

    private final ServiceManager service;

    private List<PhotoViewModel> photos;

    @Inject
    public PhotosPresenter(ServiceManager service) {
        this.service = service;
    }

    @Override
    public void onAttachView(PhotosView view) {
        super.onAttachView(view);
        if(photos != null){
            getView().onRecentsSuccess(photos);
        }
    }

    public void recents(boolean refresh){

        if(!refresh && photos != null){
            return;
        }

        addSubscription(service.recents(refresh, 1)
                .map(new Func1<PhotosResponse, List<PhotoViewModel>>() {
                    @Override
                    public List<PhotoViewModel> call(PhotosResponse response) {

                        List<PhotoViewModel> photos = new ArrayList<>();

                        Page page = response.getPage();

                        for (int index = 0; index < page.getPhotos().size(); index++) {
                            photos.add(new PhotoViewModel(page.getPhotos().get(index)));
                        }

                        return photos;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new RecentsSubscriber()));
    }

    public void search(String query){

        getView().showProgress();

        addSubscription(service.search(query)
                .map(new Func1<PhotosResponse, List<PhotoViewModel>>() {
                    @Override
                    public List<PhotoViewModel> call(PhotosResponse response) {

                        List<PhotoViewModel> photos = new ArrayList<PhotoViewModel>();

                        Page page = response.getPage();

                        for (int index = 0; index < page.getPhotos().size(); index++) {
                            photos.add(new PhotoViewModel(page.getPhotos().get(index)));
                        }

                        return photos;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new SearchSubscriber()));
    }

    public void setPhotos(List<PhotoViewModel> photos) {
        this.photos = photos;
    }

    private class RecentsSubscriber extends ApiSubscriber<List<PhotoViewModel>, String>{

        public RecentsSubscriber() {
            super(String.class);
        }

        @Override
        public void onCompleted() {
            super.onCompleted();
            getView().hideProgress();
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            getView().hideProgress();
        }

        @Override
        public void onNetworkError() {
            getView().onRecentsNetworkError();
        }

        @Override
        public void onNext(List<PhotoViewModel> photos) {
            setPhotos(photos);
            getView().onRecentsSuccess(photos);
        }
    }

    private class SearchSubscriber extends ApiSubscriber<List<PhotoViewModel>, String>{

        public SearchSubscriber() {
            super(String.class);
        }

        @Override
        public void onCompleted() {
            super.onCompleted();
            getView().hideProgress();
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            getView().hideProgress();
        }

        @Override
        public void onNetworkError() {
           getView().onSearchNetworkError();
        }

        @Override
        public void onNext(List<PhotoViewModel> photos) {
            getView().onSearchSuccess(photos);
        }
    }
}
