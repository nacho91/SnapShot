package com.nacho91.snapshot.photos.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nacho91.snapshot.R;
import com.nacho91.snapshot.model.Photo;

import java.util.List;

/**
 * Created by Ignacio on 9/10/2016.
 */

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoHolder> {

    private List<Photo> photos;

    public PhotoAdapter(List<Photo> photos) {
        this.photos = photos;
    }

    @Override
    public PhotoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PhotoHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_photo, parent, false));
    }

    @Override
    public void onBindViewHolder(PhotoHolder holder, int position) {

        Photo photo = photos.get(position);

        holder.title.setText(photo.getTitle());
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    public static class PhotoHolder extends RecyclerView.ViewHolder{

        TextView title;

        public PhotoHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.photo_title);
        }
    }
}
