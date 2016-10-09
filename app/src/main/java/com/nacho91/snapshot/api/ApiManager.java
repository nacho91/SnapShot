package com.nacho91.snapshot.api;

import com.nacho91.snapshot.BuildConfig;
import com.nacho91.snapshot.model.Page;

import rx.Observable;


/**
 * Created by Ignacio on 9/10/2016.
 */

public class ApiManager {

    static final String RECENTS_METHOD = "flickr.photos.getRecent";

    static final String URL_PHOTO_EXTRA = "url_m";
    static final String FORMAT = "json";
    static final int ITEMS_PER_PAGE = 25;

    private final SnapShotApi api;

    public ApiManager(SnapShotApi api){
        this.api = api;
    }

    public Observable<Page> recents(int page){
        return api.recents(RECENTS_METHOD, BuildConfig.API_KEY, page, ITEMS_PER_PAGE, URL_PHOTO_EXTRA, FORMAT);
    }
}
