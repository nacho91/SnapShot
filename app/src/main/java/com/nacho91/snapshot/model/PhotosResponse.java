package com.nacho91.snapshot.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ignacio on 9/10/2016.
 */
public class PhotosResponse {

    @SerializedName("photos")
    private Page page;

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }
}
