package com.nacho91.snapshot.cache;

import android.content.Context;

import com.nacho91.snapshot.cache.dao.InfoPhotoDAO;
import com.nacho91.snapshot.cache.dao.OwnerDAO;
import com.nacho91.snapshot.cache.dao.PhotoDAO;
import com.nacho91.snapshot.model.InfoPhoto;
import com.nacho91.snapshot.model.InfoResponse;
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

    public void saveInfo(InfoPhoto photo){
        InfoPhotoDAO infoPhotoDAO = new InfoPhotoDAO(context);
        OwnerDAO ownerDAO = new OwnerDAO(context);

        infoPhotoDAO.save(photo);
        ownerDAO.save(photo.getOwner());
    }

    public Observable<InfoResponse> info(final String photoId){
        return  Observable.create(new Observable.OnSubscribe<InfoResponse>() {
            @Override
            public void call(Subscriber<? super InfoResponse> subscriber) {
                InfoResponse response = new InfoResponse();

                InfoPhotoDAO infoPhotoDAO = new InfoPhotoDAO(context);

                response.setPhoto(infoPhotoDAO.findById(photoId));

                subscriber.onNext(response);
                subscriber.onCompleted();
            }
        });
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
