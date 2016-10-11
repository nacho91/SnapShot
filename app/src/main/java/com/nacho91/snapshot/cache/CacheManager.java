package com.nacho91.snapshot.cache;

import android.content.Context;

import com.nacho91.snapshot.cache.dao.PhotoDAO;
import com.nacho91.snapshot.model.Page;
import com.nacho91.snapshot.model.Photo;
import com.nacho91.snapshot.model.PhotosResponse;

import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by ignacio on 11/10/16.
 */

public class CacheManager {

    private final Context context;

    public CacheManager(Context context) {
        this.context = context;
    }

    public void savePhotos(List<Photo> photos){
        PhotoDAO photoDAO = new PhotoDAO(context);
        photoDAO.save(photos);
    }

    public void deletePhotos(){
        PhotoDAO photoDAO = new PhotoDAO(context);
        photoDAO.deleteAll();
    }

    public Observable<PhotosResponse> recents(){

        return Observable.create(new Observable.OnSubscribe<PhotosResponse>() {
            @Override
            public void call(Subscriber<? super PhotosResponse> subscriber) {
                PhotoDAO photoDAO = new PhotoDAO(context);

                PhotosResponse photosResponse = new PhotosResponse();

                Page page = new Page();
                page.setNumber(1);

                page.setPhotos(photoDAO.findAll());

                photosResponse.setPage(page);

                subscriber.onNext(photosResponse);
                subscriber.onCompleted();
            }
        });
    }
}
