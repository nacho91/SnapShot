package com.nacho91.snapshot.detail;

import com.codika.androidmvp.view.BaseView;
import com.nacho91.snapshot.model.InfoPhoto;

/**
 * Created by Ignacio on 10/10/2016.
 */

public interface DetailView extends BaseView {
    void onInfoSuccess(InfoPhoto photo);
    void onInfoNetworkError();
}
