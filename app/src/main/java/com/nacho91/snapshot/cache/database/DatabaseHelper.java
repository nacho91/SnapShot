package com.nacho91.snapshot.cache.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.nacho91.snapshot.model.Date;
import com.nacho91.snapshot.model.InfoPhoto;
import com.nacho91.snapshot.model.Owner;
import com.nacho91.snapshot.model.Photo;

import java.sql.SQLException;

/**
 * Created by ignacio on 11/10/16.
 */

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    static final String DB_NAME = "SnapShot.sqlite";
    static final int DB_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Photo.class);
            TableUtils.createTable(connectionSource, InfoPhoto.class);
            TableUtils.createTable(connectionSource, Owner.class);
            TableUtils.createTable(connectionSource, Date.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

    }
}
