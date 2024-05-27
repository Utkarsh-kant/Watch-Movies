package com.utkarsh.watchmovies.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.utkarsh.watchmovies.Adapters.ActorslistAdapter;
import com.utkarsh.watchmovies.Adapters.CategoryEachFilmListAdapter;
import com.utkarsh.watchmovies.Domain.FilmItem;
import com.utkarsh.watchmovies.R;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private ProgressBar progressBar;
private TextView titleTxt,movieRateText,movieTimeTxt,movieSummaryInfo,movieActorInfo;
private RecyclerView.Adapter adapterActorList, adapterCategory;
private RecyclerView recyclerViewActor, recyclerViewCategory;
private int idFilm;
private ImageView imgBack,pic2;
private ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail);
        idFilm=getIntent().getIntExtra("id",0);
        initView();
        sendRequest();
    }

    private void sendRequest() {
        mRequestQueue = Volley.newRequestQueue(this);
        progressBar.setVisibility(View.VISIBLE);
        scrollView.setVisibility(View.GONE);
        mStringRequest = new StringRequest(Request.Method.GET, "https://moviesapi.ir/api/v1/movies/" + idFilm, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Gson gson=new Gson();
                progressBar.setVisibility(View.GONE);
                scrollView.setVisibility(View.VISIBLE);
                FilmItem item=gson.fromJson(s,FilmItem.class);
                Glide.with(DetailActivity.this).load(item.getPoster()).into(pic2);
                titleTxt.setText(item.getTitle());
                movieRateText.setText(item.getImdbRating());
                movieTimeTxt.setText(item.getRuntime());
                movieSummaryInfo.setText(item.getPlot());
                movieActorInfo.setText(item.getActors());
                 if (item.getImages()!=null){
                     adapterActorList=new ActorslistAdapter(item.getImages());
                     recyclerViewActor.setAdapter(adapterActorList);

                 }
                 if (item.getGenres()!=null){
                     adapterCategory=new CategoryEachFilmListAdapter(item.getGenres());
                     recyclerViewCategory.setAdapter(adapterCategory);
                 }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
              progressBar.setVisibility(View.GONE );
            }
        });
        mRequestQueue.add(mStringRequest);

    }

    private void initView() {
        titleTxt=findViewById(R.id.titleTxt);
        movieRateText=findViewById(R.id.movieRateText);
        movieTimeTxt=findViewById(R.id.movieTimeTxt);
        movieSummaryInfo=findViewById(R.id.movieSummaryInfo);
        movieActorInfo=findViewById(R.id.movieActorInfo);
        recyclerViewActor=findViewById(R.id.recyclerViewActor);
        recyclerViewCategory=findViewById(R.id.recyclerViewCategory);
        progressBar=findViewById(R.id.progressbarDetail);
        imgBack=findViewById(R.id.imgBack);
        pic2=findViewById(R.id.pic2);
        scrollView=findViewById(R.id.scrollView);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });
    }
}
