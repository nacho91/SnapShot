package com.nacho91.snapshot.service;

import com.nacho91.snapshot.api.ApiManager;
import com.nacho91.snapshot.cache.CacheManager;
import com.nacho91.snapshot.model.InfoResponse;
import com.nacho91.snapshot.model.PhotosResponse;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by ignacio on 11/10/16.
 */

public class ServiceManager {

    private final ApiManager apiManager;
    private final CacheManager cacheManager;

    public ServiceManager(ApiManager apiManager, CacheManager cacheManager) {
        this.apiManager = apiManager;
        this.cacheManager = cacheManager;
    }

    public Observable<PhotosResponse> recents(boolean refresh, int page){

        if(refresh)
            return recentsWithCache(page);

        return Observable.concat(cacheManager.recents(), recentsWithCache(page))
                .filter(new Func1<PhotosResponse, Boolean>() {
                    @Override
                    public Boolean call(PhotosResponse photosResponse) {
                        return photosResponse.getPage().getPhotos().size() > 0;
                    }
                })
                .first();
    }

    private Observable<PhotosResponse> recentsWithCache(int page){
        return apiManager.recents(page).doOnNext(new Action1<PhotosResponse>() {
            @Override
            public void call(PhotosResponse photosResponse) {
                cacheManager.deletePhotos();
                cacheManager.savePhotos(photosResponse.getPage().getPhotos());
            }
        });
    }

    public Observable<InfoResponse> info(String photoId){
        return Observable.concat(cacheManager.info(photoId), infoWithCache(photoId))
                .filter(new Func1<InfoResponse, Boolean>() {
                    @Override
                    public Boolean call(InfoResponse infoResponse) {
                        return infoResponse.getPhoto() != null;
                    }
                }).first();
    }

    private Observable<InfoResponse> infoWithCache(String photoId){
        return apiManager.info(photoId).doOnNext(new Action1<InfoResponse>() {
            @Override
            public void call(InfoResponse infoResponse) {
                cacheManager.saveInfo(infoResponse.getPhoto());
            }
        });
    }

    public Observable<PhotosResponse> search(String query){
        return apiManager.search(query);
    }
}
