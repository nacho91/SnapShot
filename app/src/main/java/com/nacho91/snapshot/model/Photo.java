package com.nacho91.snapshot.model;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Flickr photo model
 */
@DatabaseTable(tableName = "photos")
public class Photo {

    /*Phoot id*/
    @DatabaseField(id = true)
    private String id;

    /*Photo url*/
    @DatabaseField
    @SerializedName("url_z")
    private String url;

    /*Photo width*/
    @DatabaseField
    @SerializedName("width_z")
    private int width;

    /*Photo height*/
    @DatabaseField
    @SerializedName("height_z")
    private int height;

    /*Photo title*/
    @DatabaseField
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
