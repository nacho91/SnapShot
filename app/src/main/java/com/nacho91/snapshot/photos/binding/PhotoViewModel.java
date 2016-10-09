package com.nacho91.snapshot.photos.binding;

import com.nacho91.snapshot.model.Photo;

/**
 * Created by Ignacio on 9/10/2016.
 */

public class PhotoViewModel {

    private String title;
    private String url;

    public PhotoViewModel(Photo photo){
        title = photo.getTitle();
        url = photo.getUrl();
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }
}
