package com.nacho91.snapshot.model;

import com.google.gson.annotations.SerializedName;

/**
 * Flickr photo model
 */
public class Photo {

    /*Phoot id*/
    private String id;

    /*Photo url*/
    @SerializedName("url_z")
    private String url;

    /*Photo width*/
    @SerializedName("width_m")
    private int width;

    /*Photo height*/
    @SerializedName("height_m")
    private int height;

    /*Photo title*/
    private String title;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
