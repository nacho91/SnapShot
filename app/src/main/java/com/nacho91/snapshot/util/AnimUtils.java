package com.nacho91.snapshot.util;

import android.view.View;
import android.view.animation.AnimationUtils;

/**
 * Created by ignacio on 14/05/16.
 */
public class AnimUtils {

    public static void fadeInFadeOut(View fadeIn, View fadeOut){
        fadeIn.startAnimation(AnimationUtils.loadAnimation(
                fadeIn.getContext(), android.R.anim.fade_in));
        fadeOut.startAnimation(AnimationUtils.loadAnimation(
                fadeOut.getContext(), android.R.anim.fade_out));

        fadeIn.setVisibility(View.VISIBLE);
        fadeOut.setVisibility(View.GONE);
    }
}
