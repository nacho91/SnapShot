package com.nacho91.snapshot.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Ignacio on 10/10/2016.
 */
@DatabaseTable(tableName = "owners")
public class Owner {

    @DatabaseField(id = true)
    private String nsid;

    @DatabaseField
    private String username;

    public String getNsid() {
        return nsid;
    }

    public void setNsid(String nsid) {
        this.nsid = nsid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
