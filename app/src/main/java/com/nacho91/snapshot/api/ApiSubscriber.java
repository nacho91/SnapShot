package com.nacho91.snapshot.api;

import android.util.Log;

import rx.Subscriber;

/**
 * Created by ignacio on 26/05/16.
 */
public class ApiSubscriber<T, V> extends Subscriber<T> {

    private Class<V> error;

    public ApiSubscriber(Class<V> error){
        this.error = error;
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        ApiException error = (ApiException) e;
        Log.v("ApiSubscriber", error.getKind().name());

        switch (error.getKind()){
            case NETWORK:
                onNetworkError();
                break;
            case UNEXPECTED:
                onUnknowError();
                break;
            case HTTP:
                onHttpError(error.getErrorBodyAs(this.error));
                break;
            case NOT_FOUND:
                onNotFoundError();
                break;
        }
    }

    @Override
    public void onNext(T t) {

    }

    public void onNetworkError(){

    }

    public void onNotFoundError(){

    }

    public void onHttpError(V v){

    }

    public void onUnknowError(){

    }
}
