package com.nacho91.snapshot.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.content.Loader;
import android.view.View;
import android.widget.TextView;

import com.codika.androidmvp.activity.BaseMvpActivity;
import com.nacho91.snapshot.R;
import com.nacho91.snapshot.detail.widget.ThreeTwoImageView;
import com.nacho91.snapshot.model.InfoPhoto;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by Ignacio on 10/10/2016.
 */

public class DetailActivity extends BaseMvpActivity<DetailView, DetailPresenter> implements DetailView {

    public static final String EXTRA_PHOTO_ID = "_PHOTO_ID_";
    public static final String EXTRA_PHOTO_URL = "_PHOTO_URL_";
    public static final String EXTRA_PHOTO_TITLE = "_PHOTO_TITLE_";

    private CoordinatorLayout detailRoot;
    private ThreeTwoImageView detailImage;
    private TextView detailTitle;
    private TextView detailUsername;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        detailRoot = (CoordinatorLayout) findViewById(R.id.detail_root);

        detailImage = (ThreeTwoImageView) findViewById(R.id.detail_image);
        ImageLoader.getInstance().displayImage(getIntent().getStringExtra(EXTRA_PHOTO_URL), detailImage);

        detailTitle = (TextView) findViewById(R.id.detail_title);
        detailTitle.setText(getIntent().getStringExtra(EXTRA_PHOTO_TITLE));

        detailUsername = (TextView) findViewById(R.id.detail_username);
    }

    @Override
    public void onResume() {
        super.onResume();
        loadInfo();
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
