package com.example.restapi.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.restapi.R;
import com.example.restapi.model.Photo;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.shape.Shapeable;

import java.util.List;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder> {
    private List<Photo> photoList;

//    construtor
    public PhotoAdapter(List<Photo> photoList) {
        this.photoList = photoList;

    }

    @NonNull
    @Override
    public PhotoAdapter.PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.photo, parent, false);
        PhotoAdapter.PhotoViewHolder photoViewHolder = new PhotoAdapter.PhotoViewHolder(view);
        return photoViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoAdapter.PhotoViewHolder holder, int position) {
//        população de dados
        holder.txtAutor.setText(photoList.get(position).getAuthor());
        Glide.with(holder.itemView.getContext())
                .load(photoList.get(position).getDownload_url())
                .circleCrop()
                .into(holder.imgPhoto);
    }

    @Override
    public int getItemCount() {
        return photoList.size();
    }

    public class PhotoViewHolder extends RecyclerView.ViewHolder {
        ShapeableImageView imgPhoto;
        TextView txtAutor;
        public PhotoViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.imageView2);
            txtAutor = itemView.findViewById(R.id.textAutor);
        }
    }
}
