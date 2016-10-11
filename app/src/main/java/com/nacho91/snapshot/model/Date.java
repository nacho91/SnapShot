package com.nacho91.snapshot.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by ignacio on 11/10/16.
 */
@DatabaseTable(tableName = "dates")
public class Date {

    @DatabaseField(generatedId = true)
    private long id;

    @DatabaseField
    private String taken;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTaken() {
        return taken;
    }

    public void setTaken(String taken) {
        this.taken = taken;
    }
}
