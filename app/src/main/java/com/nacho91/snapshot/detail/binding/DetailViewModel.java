package com.nacho91.snapshot.detail.binding;

import com.nacho91.snapshot.model.InfoPhoto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by ignacio on 11/10/16.
 */

public class DetailViewModel {

    static final String DATE_FORMAT = "EEE, d MMM yyyy HH:mm";
    static final String SERVER_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private String username;
    private String date;

    public DetailViewModel(InfoPhoto photo){
        username = photo.getOwner().getUsername();

        try {
            Date serverDate = new SimpleDateFormat(SERVER_DATE_FORMAT, Locale.getDefault()).parse(photo.getDates().getTaken());
            date = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault()).format(serverDate);
        } catch (ParseException e) {
            date = photo.getDates().getTaken();
        }
    }

    public String getUsername(){
        return username;
    }

    public String getDate(){
        return date;
    }
}
