package com.nacho91.snapshot.util;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by Ignacio on 9/10/2016.
 */

public class ScreenUtils {

    public static int getScreenWidthSize(Context context){
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.widthPixels;
    }
}
