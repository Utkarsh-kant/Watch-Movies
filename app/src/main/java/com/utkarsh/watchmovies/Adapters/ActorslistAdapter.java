package com.utkarsh.watchmovies.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.utkarsh.watchmovies.R;

import java.util.List;


public class ActorslistAdapter extends RecyclerView.Adapter<ActorslistAdapter.ViewHolder> {
    List<String>images;
    Context context;

    public ActorslistAdapter(List<String> images) {
        this.images = images;
    }

    @NonNull
    @Override
    public ActorslistAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context= parent.getContext();
         View view= LayoutInflater.from(context).inflate(R.layout.viewholder_actors,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ActorslistAdapter.ViewHolder holder, int position) {
        Glide.with(context)
                .load(images.get(position))
                .into(holder.itemImages);
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView itemImages;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemImages=itemView.findViewById(R.id.itemImages);
        }
    }
}
