package com.nacho91.snapshot.detail;

import android.app.SharedElementCallback;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.content.Loader;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.codika.androidmvp.activity.BaseMvpActivity;
import com.nacho91.snapshot.R;
import com.nacho91.snapshot.detail.widget.ThreeTwoImageView;
import com.nacho91.snapshot.model.InfoPhoto;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import java.util.List;

/**
 * Created by Ignacio on 10/10/2016.
 */

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class DetailActivity extends BaseMvpActivity<DetailView, DetailPresenter> implements DetailView {

    public static final String EXTRA_PHOTO_ID = "_PHOTO_ID_";
    public static final String EXTRA_PHOTO_URL = "_PHOTO_URL_";
    public static final String EXTRA_PHOTO_TITLE = "_PHOTO_TITLE_";

    private CoordinatorLayout detailRoot;
    private ThreeTwoImageView detailImage;
    private TextView detailTitle;
    private TextView detailUsername;

    private float targetTextSize;
    private ColorStateList targetTextColors;

    private SharedElementCallback elementCallback = new SharedElementCallback() {

        @Override
        public void onSharedElementStart(List<String> sharedElementNames,
                                         List<View> sharedElements,
                                         List<View> sharedElementSnapshots) {
            targetTextSize = detailTitle.getTextSize();
            targetTextColors = detailTitle.getTextColors();
            float textSize = getIntent().getFloatExtra("TextSize", targetTextSize);
            detailTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
            detailTitle.setTextColor(getIntent().getIntExtra("TextColor", Color.BLACK));
            Rect padding = getIntent().getParcelableExtra("Padding");
            detailTitle.setPadding(padding.left, padding.top, padding.right, padding.bottom);
        }

        @Override
        public void onSharedElementEnd(List<String> sharedElementNames,
                                       List<View> sharedElements,
                                       List<View> sharedElementSnapshots) {
            detailTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, targetTextSize);
            detailTitle.setTextColor(targetTextColors != null ? targetTextColors : detailTitle.getTextColors());
        }
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back_arrow);

        detailRoot = (CoordinatorLayout) findViewById(R.id.detail_root);

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .resetViewBeforeLoading(true)
                .build();

        detailImage = (ThreeTwoImageView) findViewById(R.id.detail_image);
        ImageLoader.getInstance().displayImage(getIntent().getStringExtra(EXTRA_PHOTO_URL), detailImage, options);

        detailTitle = (TextView) findViewById(R.id.detail_title);
        detailTitle.setText(getIntent().getStringExtra(EXTRA_PHOTO_TITLE));

        detailUsername = (TextView) findViewById(R.id.detail_username);

        setEnterSharedElementCallback(elementCallback);
    }

    @Override
    public void onResume() {
        super.onResume();
        loadInfo();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == android.R.id.home){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                finishAfterTransition();
            }else {
                finish();
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void loadInfo(){
        getPresenter().info(getIntent().getStringExtra(EXTRA_PHOTO_ID));
    }

    @Override
    public DetailView getMvpView() {
        return this;
    }

    @Override
    public Loader<DetailPresenter> getPresenterLoader() {
        return new DetailLoader(this);
    }

    @Override
    public void onInfoSuccess(InfoPhoto photo) {
        detailUsername.setText(photo.getOwner().getUsername());
    }

    @Override
    public void onInfoNetworkError() {

        Snackbar.make(detailRoot, R.string.general_connection_error_message, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.general_retry_button, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        loadInfo();
                    }
                })
                .show();
    }
}
