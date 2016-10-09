package com.nacho91.snapshot.util;

import android.databinding.BindingAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.nacho91.snapshot.R;

/**
 * Created by Ignacio on 9/10/2016.
 */

public class BindingAdapters {

    @BindingAdapter({"bind:height","bind:width"})
    public static void loadPhotoSize(ImageView view, int height, int width){

        int newWidth = ScreenUtils.getScreenWidthSize(view.getContext()) / view.getResources().getInteger(R.integer.photo_span_count);
        int newHeight = (int) (((float)height / (float)width) * newWidth);

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(newWidth, newHeight);
        view.setLayoutParams(params);
    }
}
