package com.nacho91.snapshot.photos.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nacho91.snapshot.R;
import com.nacho91.snapshot.databinding.AdapterPhotoBinding;
import com.nacho91.snapshot.model.Photo;
import com.nacho91.snapshot.photos.binding.PhotoViewModel;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by Ignacio on 9/10/2016.
 */

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoHolder> {

    private List<PhotoViewModel> photos;

    public PhotoAdapter(List<PhotoViewModel> photos) {
        this.photos = photos;
    }

    @Override
    public PhotoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PhotoHolder((AdapterPhotoBinding) DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.adapter_photo, parent, false));
    }

    @Override
    public void onBindViewHolder(PhotoHolder holder, int position) {

        PhotoViewModel photo = photos.get(position);

        holder.binding.setPhoto(photo);
        holder.binding.executePendingBindings();
        ImageLoader.getInstance().displayImage(photo.getUrl(), holder.binding.photoImage);
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    public void refresh(List<PhotoViewModel> photos) {
        this.photos.clear();
        this.photos.addAll(photos);
        notifyDataSetChanged();
    }

    public static class PhotoHolder extends RecyclerView.ViewHolder{

        AdapterPhotoBinding binding;

        public PhotoHolder(AdapterPhotoBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
