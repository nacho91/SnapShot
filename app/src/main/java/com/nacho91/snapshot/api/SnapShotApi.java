package com.nacho91.snapshot.api;

import com.nacho91.snapshot.model.Response;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;


/**
 * Created by Ignacio on 9/10/2016.
 */

public interface SnapShotApi {

    @GET("./")
    Observable<Response> recents(@Query("method") String method,
                                 @Query("api_key") String apiKey,
                                 @Query("page") int page,
                                 @Query("per_page") int perPage,
                                 @Query("extras") String extras,
                                 @Query("format") String format,
                                 @Query("nojsoncallback") int noJsonCallback);
}
