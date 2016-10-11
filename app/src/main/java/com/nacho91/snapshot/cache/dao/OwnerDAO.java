package com.nacho91.snapshot.cache.dao;

import android.content.Context;

import com.nacho91.snapshot.model.Owner;

/**
 * Created by ignacio on 11/10/16.
 */

public class OwnerDAO extends GenericDAOImpl<Owner, String> {

    public OwnerDAO(Context context) {
        super(Owner.class, context);
    }
}
