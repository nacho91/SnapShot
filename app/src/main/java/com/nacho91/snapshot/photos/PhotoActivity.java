package com.nacho91.snapshot.photos;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.codika.androidmvp.activity.BaseMvpActivity;
import com.nacho91.snapshot.R;
import com.nacho91.snapshot.photos.adapter.PhotoAdapter;
import com.nacho91.snapshot.photos.binding.PhotoViewModel;
import com.nacho91.snapshot.photos.util.PhotoMarginDecoration;

import java.util.List;

/**
 * Created by Ignacio on 9/10/2016.
 */

public class PhotoActivity extends BaseMvpActivity<PhotosView, PhotosPresenter> implements PhotosView{

    private SwipeRefreshLayout photoRefresh;
    private RecyclerView photoList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos);

        photoRefresh = (SwipeRefreshLayout) findViewById(R.id.photo_refresh);
        photoRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPresenter().recents();
            }
        });

        photoList = (RecyclerView) findViewById(R.id.photo_list);
        photoList.addItemDecoration(new PhotoMarginDecoration(getResources().getDimensionPixelSize(R.dimen.photo_item_spacing)));
        photoList.setHasFixedSize(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        getPresenter().recents();
    }

    @Override
    public PhotosView getMvpView() {
        return this;
    }

    @Override
    public Loader<PhotosPresenter> getPresenterLoader() {
        return new PhotosLoader(this);
    }

    @Override
    public void onRecentsSuccess(List<PhotoViewModel> photos) {

        if(photoRefresh.isRefreshing())
            photoRefresh.setRefreshing(false);

        if(photoList.getAdapter() == null)
            photoList.setAdapter(new PhotoAdapter(photos));
        else{
            PhotoAdapter adapter = (PhotoAdapter) photoList.getAdapter();
            adapter.refresh(photos);
        }
    }
}
