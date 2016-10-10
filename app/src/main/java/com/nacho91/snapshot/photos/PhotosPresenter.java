package com.nacho91.snapshot.photos;

import com.codika.androidmvprx.presenter.BaseRxPresenter;
import com.nacho91.snapshot.api.ApiManager;
import com.nacho91.snapshot.model.Page;
import com.nacho91.snapshot.model.PhotosResponse;
import com.nacho91.snapshot.photos.binding.PhotoViewModel;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
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
        addSubscription(apiManager.recents(1).map(new Func1<PhotosResponse, List<PhotoViewModel>>() {
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
                .subscribe(new Subscriber<List<PhotoViewModel>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<PhotoViewModel> photos) {
                getView().onRecentsSuccess(photos);
            }
        }));
    }
}
