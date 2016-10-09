package com.nacho91.snapshot.photos.binding;

import com.nacho91.snapshot.model.Photo;

/**
 * Created by Ignacio on 9/10/2016.
 */

public class PhotoViewModel {

    private String title;
    private String url;
    private int height;
    private int width;

    public PhotoViewModel(Photo photo){
        title = photo.getTitle();
        url = photo.getUrl();
        height = photo.getHeight();
        width = photo.getWidth();
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }
}
