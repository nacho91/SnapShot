package com.nacho91.snapshot.model;

import java.util.List;

/**
 * Flickr page model
 */
public class Page {

    /*Current page number*/
    private int number;

    /*Total pages number*/
    private int pages;

    /*Photos per page*/
    private List<Photo> photos;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }
}
