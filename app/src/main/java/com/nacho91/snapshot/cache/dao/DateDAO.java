package com.nacho91.snapshot.cache.dao;

import android.content.Context;

import com.nacho91.snapshot.model.Date;

/**
 * Created by ignacio on 11/10/16.
 */

public class DateDAO extends GenericDAOImpl<Date, Long> {

    public DateDAO(Context context) {
        super(Date.class, context);
    }
}
