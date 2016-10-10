package com.nacho91.snapshot.model;


/**
 * Created by Ignacio on 10/10/2016.
 */

public class InfoPhoto {

    /*Phoot id*/
    private String id;

    private Owner owner;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }
}
