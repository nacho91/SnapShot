package com.nacho91.snapshot.photos.binding;

import android.content.Context;
import android.content.Intent;

import com.nacho91.snapshot.detail.DetailActivity;

/**
 * Created by Ignacio on 10/10/2016.
 */

public class PhotoActionHandler {

    public void onPhotoClick(Context context, String photoId, String photoUrl, String photoTitle){

        Intent detail = new Intent(context, DetailActivity.class);
        detail.putExtra(DetailActivity.EXTRA_PHOTO_ID, photoId);
        detail.putExtra(DetailActivity.EXTRA_PHOTO_URL, photoUrl);
        detail.putExtra(DetailActivity.EXTRA_PHOTO_TITLE, photoTitle);

        context.startActivity(detail);
    }
}
