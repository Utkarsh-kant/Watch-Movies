package com.utkarsh.watchmovies.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.utkarsh.watchmovies.Activities.DetailActivity;
import com.utkarsh.watchmovies.Domain.GeneresItems;
import com.utkarsh.watchmovies.Domain.ListFilm;
import com.utkarsh.watchmovies.R;

import java.util.ArrayList;

public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.ViewHolder> {
   ArrayList<GeneresItems> items;
    Context context;

    public CategoryListAdapter(ArrayList<GeneresItems> items) {
        this.items = items;
    }


    @NonNull
    @Override
    public CategoryListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryListAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.catTxt.setText(items.get(position).getName());
   
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent idetail = new Intent(holder.itemView.getContext(), DetailActivity.class);
                context.startActivity(idetail);
            }
        });

    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView catTxt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            catTxt = itemView.findViewById(R.id.catTxt);
        }
    }
}
