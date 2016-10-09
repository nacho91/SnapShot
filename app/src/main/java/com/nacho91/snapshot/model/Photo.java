package com.nacho91.snapshot.model;

/**
 * Flickr photo model
 */
public class Photo {

    /*Phoot id*/
    private String id;

    /*Photo url*/
    private String url;

    /*Photo width*/
    private int width;

    /*Photo height*/
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
