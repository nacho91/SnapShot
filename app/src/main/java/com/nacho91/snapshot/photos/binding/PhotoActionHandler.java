package com.nacho91.snapshot.photos.binding;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nacho91.snapshot.detail.DetailActivity;
import com.nacho91.snapshot.util.TransitionHelper;

import com.nacho91.snapshot.R;

/**
 * Created by Ignacio on 10/10/2016.
 */

public class PhotoActionHandler {

    public void onPhotoClick(Context context, ImageView photo, TextView title, String photoId, String photoUrl, String photoTitle){

        Intent detail = new Intent(context, DetailActivity.class);
        detail.putExtra(DetailActivity.EXTRA_PHOTO_ID, photoId);
        detail.putExtra(DetailActivity.EXTRA_PHOTO_URL, photoUrl);
        detail.putExtra(DetailActivity.EXTRA_PHOTO_TITLE, photoTitle);
        detail.putExtra(DetailActivity.EXTRA_TITLE_COLOR, title.getCurrentTextColor());
        detail.putExtra(DetailActivity.EXTRA_TITLE_SIZE, title.getTextSize());
        detail.putExtra(DetailActivity.EXTRA_TITLE_PADDING,
                new Rect(title.getPaddingLeft(),
                        title.getPaddingTop(),
                        title.getPaddingRight(),
                        title.getPaddingBottom()));

        Activity host = (Activity) context;

        Pair<View, String>[] pairs = TransitionHelper.createSafeTransitionParticipants((Activity) context, false,
                new Pair<>(title, context.getString(R.string.transition_title)),
                new Pair<>(photo, context.getString(R.string.transition_image)));

        ActivityOptionsCompat options =
                ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context, pairs);

        ActivityCompat.startActivity(host, detail, options.toBundle());
    }
}
