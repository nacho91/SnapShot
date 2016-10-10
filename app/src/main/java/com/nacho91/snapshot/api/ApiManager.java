package com.nacho91.snapshot.api;

import com.nacho91.snapshot.BuildConfig;
import com.nacho91.snapshot.model.InfoResponse;
import com.nacho91.snapshot.model.PhotosResponse;

import rx.Observable;


/**
 * Created by Ignacio on 9/10/2016.
 */

public class ApiManager {

    static final String RECENTS_METHOD = "flickr.photos.getRecent";
    static final String INFO_METHOD = "flickr.photos.getInfo";

    static final String URL_PHOTO_EXTRA = "url_z";
    static final int ITEMS_PER_PAGE = 25;
    static final String FORMAT = "json";

    private final SnapShotApi api;

    public ApiManager(SnapShotApi api){
        this.api = api;
    }

    public Observable<PhotosResponse> recents(int page){
        return api.recents(RECENTS_METHOD, BuildConfig.API_KEY, page, ITEMS_PER_PAGE, URL_PHOTO_EXTRA, FORMAT, 1);
    }

    public Observable<InfoResponse> info(String photoId){
        return api.info(INFO_METHOD, BuildConfig.API_KEY, photoId, FORMAT, 1);
    }
}
