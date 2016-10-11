package com.nacho91.snapshot.cache.dao;

import android.content.Context;

import com.nacho91.snapshot.model.InfoPhoto;

/**
 * Created by ignacio on 11/10/16.
 */

public class InfoPhotoDAO extends GenericDAOImpl<InfoPhoto, String> {

    public InfoPhotoDAO(Context context) {
        super(InfoPhoto.class, context);
    }
}
