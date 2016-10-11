package com.nacho91.snapshot.photos;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.content.Loader;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ProgressBar;

import com.codika.androidmvp.activity.BaseMvpActivity;
import com.jakewharton.rxbinding.support.v7.widget.RxSearchView;
import com.jakewharton.rxbinding.support.v7.widget.SearchViewQueryTextEvent;
import com.nacho91.snapshot.R;
import com.nacho91.snapshot.photos.adapter.PhotoAdapter;
import com.nacho91.snapshot.photos.binding.PhotoViewModel;
import com.nacho91.snapshot.photos.util.PhotoMarginDecoration;
import com.nacho91.snapshot.util.AnimUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by Ignacio on 9/10/2016.
 */
public class PhotoActivity extends BaseMvpActivity<PhotosView, PhotosPresenter> implements PhotosView{

    private CoordinatorLayout photoRoot;
    private ProgressBar photoProgress;
    private SwipeRefreshLayout photoRefresh;
    private RecyclerView photoList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            postponeEnterTransition();
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.photo_toolbar);
        setSupportActionBar(toolbar);

        photoRoot = (CoordinatorLayout) findViewById(R.id.photo_root);

        photoRefresh = (SwipeRefreshLayout) findViewById(R.id.photo_refresh);
        photoRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPresenter().recents(true);
            }
        });

        photoList = (RecyclerView) findViewById(R.id.photo_list);
        photoList.addItemDecoration(new PhotoMarginDecoration(getResources().getDimensionPixelSize(R.dimen.photo_item_spacing)));
        photoList.setHasFixedSize(true);
        photoList.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                photoList.getViewTreeObserver().removeOnPreDrawListener(this);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    startPostponedEnterTransition();
                }
                return false;
            }
        });

        photoProgress = (ProgressBar) findViewById(R.id.photo_progress);
    }

    @Override
    public void onResume() {
        super.onResume();
        getPresenter().recents(false);
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

    private void loadPhotos(List<PhotoViewModel> photos, boolean refresh){
        if(photoList.getAdapter() == null)
            photoList.setAdapter(new PhotoAdapter(photos));

        if(refresh){
            PhotoAdapter adapter = (PhotoAdapter) photoList.getAdapter();
            adapter.refresh(photos);
        }
    }

    @Override
    public void onRecentsSuccess(List<PhotoViewModel> photos) {

        loadPhotos(photos, photoRefresh.isRefreshing());

        photoRefresh.setRefreshing(false);
    }

    @Override
    public void onSearchSuccess(List<PhotoViewModel> photos) {
        loadPhotos(photos, true);
    }

    @Override
    public void onRecentsNetworkError() {
        Snackbar.make(photoRoot, R.string.general_connection_error_message, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.general_retry_button, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        getPresenter().recents(false);
                    }
                })
                .show();
    }

    @Override
    public void onSearchNetworkError() {
        photoRefresh.setRefreshing(false);

        Snackbar.make(photoRoot, R.string.general_connection_error_message, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.general_retry_button, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        getPresenter().recents(true);
                    }
                })
                .show();
    }

    @Override
    public void showProgress() {
        AnimUtils.fadeInFadeOut(photoProgress, photoRefresh);
    }

    @Override
    public void hideProgress() {
        AnimUtils.fadeInFadeOut(photoRefresh, photoProgress);
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
                    getPresenter().recents(false);
                    return;
                }
                getPresenter().search(textViewTextChangeEvent.queryText().toString());
            }
        };
    }

}
