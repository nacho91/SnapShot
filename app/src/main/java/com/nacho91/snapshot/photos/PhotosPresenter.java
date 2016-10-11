package com.nacho91.snapshot.photos;

import com.codika.androidmvprx.presenter.BaseRxPresenter;
import com.nacho91.snapshot.api.ApiManager;
import com.nacho91.snapshot.api.ApiSubscriber;
import com.nacho91.snapshot.model.Page;
import com.nacho91.snapshot.model.PhotosResponse;
import com.nacho91.snapshot.photos.binding.PhotoViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Ignacio on 9/10/2016.
 */

public class PhotosPresenter extends BaseRxPresenter<PhotosView> {

    private final ApiManager apiManager;

    @Inject
    public PhotosPresenter(ApiManager apiManager) {
        this.apiManager = apiManager;
    }

    public void recents(){
        addSubscription(apiManager.recents(1)
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
                .subscribe(new RecentsSubscriber()));
    }

    public void search(String query){
        addSubscription(apiManager.search(query)
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

    private class RecentsSubscriber extends ApiSubscriber<List<PhotoViewModel>, String>{

        public RecentsSubscriber() {
            super(String.class);
        }

        @Override
        public void onNetworkError() {
            getView().onRecentsNetworkError();
        }

        @Override
        public void onNext(List<PhotoViewModel> photos) {
            getView().onRecentsSuccess(photos);
        }
    }

    private class SearchSubscriber extends ApiSubscriber<List<PhotoViewModel>, String>{

        public SearchSubscriber() {
            super(String.class);
        }

        @Override
        public void onNetworkError() {
            super.onNetworkError();
        }

        @Override
        public void onNext(List<PhotoViewModel> photos) {
            getView().onRecentsSuccess(photos);
        }
    }
}
