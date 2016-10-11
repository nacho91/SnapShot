package com.nacho91.snapshot.cache.dao;

import android.content.Context;

import com.nacho91.snapshot.model.Photo;

/**
 * Created by ignacio on 11/10/16.
 */

public class PhotoDAO extends GenericDAOImpl<Photo, String> {

    public PhotoDAO(Context context) {
        super(Photo.class, context);
    }
}
