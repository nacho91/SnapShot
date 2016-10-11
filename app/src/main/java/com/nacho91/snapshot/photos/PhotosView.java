package com.nacho91.snapshot.photos;

import com.codika.androidmvp.view.BaseView;
import com.nacho91.snapshot.model.Photo;
import com.nacho91.snapshot.photos.binding.PhotoViewModel;

import java.util.List;

/**
 * Created by Ignacio on 9/10/2016.
 */

public interface PhotosView extends BaseView {

    void onRecentsSuccess(List<PhotoViewModel> photos);
    void onSearchSuccess(List<PhotoViewModel> photos);
    void onRecentsNetworkError();
    void onSearchNetworkError();
    void showProgress();
    void hideProgress();
}
