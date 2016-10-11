package com.nacho91.snapshot.photos;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.content.Loader;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.codika.androidmvp.activity.BaseMvpActivity;
import com.jakewharton.rxbinding.support.v7.widget.RxSearchView;
import com.jakewharton.rxbinding.support.v7.widget.SearchViewQueryTextEvent;
import com.jakewharton.rxbinding.widget.TextViewTextChangeEvent;
import com.nacho91.snapshot.R;
import com.nacho91.snapshot.photos.adapter.PhotoAdapter;
import com.nacho91.snapshot.photos.binding.PhotoViewModel;
import com.nacho91.snapshot.photos.util.PhotoMarginDecoration;

import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;

/**
 * Created by Ignacio on 9/10/2016.
 */

public class PhotoActivity extends BaseMvpActivity<PhotosView, PhotosPresenter> implements PhotosView{

    private CoordinatorLayout photoRoot;
    private SwipeRefreshLayout photoRefresh;
    private RecyclerView photoList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos);

        Toolbar toolbar = (Toolbar) findViewById(R.id.photo_toolbar);
        setSupportActionBar(toolbar);

        photoRoot = (CoordinatorLayout) findViewById(R.id.photo_root);

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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_photos, menu);

        MenuItem searchItem = menu.findItem(R.id.photos_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        RxSearchView.queryTextChangeEvents(searchView)
                .debounce(400, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getSearchObserver());

        return true;
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

    @Override
    public void onRecentsNetworkError() {

        Snackbar.make(photoRoot, R.string.general_connection_error_message, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.general_retry_button, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        getPresenter().recents();
                    }
                })
                .show();
    }

    private Observer<SearchViewQueryTextEvent> getSearchObserver(){
        return new Observer<SearchViewQueryTextEvent>(){

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(SearchViewQueryTextEvent textViewTextChangeEvent) {
                if(textViewTextChangeEvent.queryText().length() == 0){
                    getPresenter().recents();
                    return;
                }
                getPresenter().search(textViewTextChangeEvent.queryText().toString());
            }
        };
    }

}
