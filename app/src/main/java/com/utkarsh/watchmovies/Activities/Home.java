package com.utkarsh.watchmovies.Activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.utkarsh.watchmovies.Adapters.CategoryListAdapter;
import com.utkarsh.watchmovies.Adapters.FilmListAdapter;
import com.utkarsh.watchmovies.Adapters.SliderAdapter;
import com.utkarsh.watchmovies.Domain.GeneresItems;
import com.utkarsh.watchmovies.Domain.ListFilm;
import com.utkarsh.watchmovies.Domain.SliderItem;
import com.utkarsh.watchmovies.R;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {
    private RecyclerView.Adapter adapterBestMovie,adapterUpcomingMovie;
    private RecyclerView recyclerViewBestMovie,recyclerViewUpcomingMovie,recyclerViewCategory;
    private ProgressBar loading1,loading2,loading3;
    private RequestQueue requestQueue;
    private StringRequest mstringRequest1,mstringRequest2,mstringRequest3;
    ViewPager2 viewPager;
    Handler handler=new Handler();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.home);
        viewPager = findViewById(R.id.viewPager);
        recyclerViewBestMovie = findViewById(R.id.recyclerViewBestMovie);
        recyclerViewUpcomingMovie = findViewById(R.id.recyclerViewUpcomingMovie);
        recyclerViewCategory = findViewById(R.id.recyclerViewCategory);
        loading1 = findViewById(R.id.loading1);
        loading2 = findViewById(R.id.loading2);
        loading3 = findViewById(R.id.loading3);
        banner();
        sendRequestBest();
        sendRequestUpcoming();
        sendRequestCategory();

    }

    private void sendRequestCategory() {
        String url = "https://moviesapi.ir/api/v1/genres";
        requestQueue = Volley.newRequestQueue(this);
        loading2.setVisibility(View.VISIBLE);
        mstringRequest2 = new StringRequest(Request.Method.GET, url, s -> {
            Gson gson=new Gson();
            loading2.setVisibility(View.GONE);
          ArrayList<GeneresItems>catList=gson.fromJson(s,new TypeToken<ArrayList<GeneresItems>>(){}.getType());
           CategoryListAdapter categoryListAdapter=new CategoryListAdapter(catList);
            recyclerViewCategory.setAdapter(categoryListAdapter);

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                loading2.setVisibility(View.GONE);
                Log.d("Utkarsh", "onErrorResponse: "+ volleyError.toString());

            }
        });
        requestQueue.add(mstringRequest2);
    }

    private void sendRequestBest() {
        String url = "https://moviesapi.ir/api/v1/movies?page=1";
        requestQueue = Volley.newRequestQueue(this);
        loading1.setVisibility(View.VISIBLE);
        mstringRequest1 = new StringRequest(Request.Method.GET, url, s -> {
            Gson gson=new Gson();
            loading1.setVisibility(View.GONE);
            ListFilm items=gson.fromJson(s,ListFilm.class);
            adapterBestMovie=new FilmListAdapter(items);
            recyclerViewBestMovie.setAdapter(adapterBestMovie);

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                loading1.setVisibility(View.GONE);
                Log.d("Utkarsh", "onErrorResponse: "+ volleyError.toString());

            }
        });
        requestQueue.add(mstringRequest1);
    }
    private void sendRequestUpcoming() {
        String url = "https://moviesapi.ir/api/v1/movies?page=2";
        requestQueue = Volley.newRequestQueue(this);
        loading3.setVisibility(View.VISIBLE);
        mstringRequest3 = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Gson gson=new Gson();
                loading3.setVisibility(View.GONE);
                ListFilm items=gson.fromJson(s,ListFilm.class);
                adapterUpcomingMovie=new FilmListAdapter(items);
                recyclerViewUpcomingMovie.setAdapter(adapterUpcomingMovie);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                loading3.setVisibility(View.GONE);
                Log.d("Utkarsh", "onErrorResponse: "+ volleyError.toString());

            }
        });
        requestQueue.add(mstringRequest3);
    }







    @Override
    protected void onResume() {
        super.onResume();
        handler.postAtTime(sliderRunnable,2000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(sliderRunnable);
    }

    private void banner() {
        List<SliderItem> sliderItems = new ArrayList<>();
        sliderItems.add(new SliderItem(R.drawable.wide));
        sliderItems.add(new SliderItem(R.drawable.wide1));
        sliderItems.add(new SliderItem(R.drawable.wide3));
        viewPager.setAdapter(new SliderAdapter(sliderItems, viewPager));
        viewPager.setClipToPadding(false);
        viewPager.setClipChildren(false);
        viewPager.setOffscreenPageLimit(3);
        viewPager.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
              float r=1-Math.abs(position);
              page.setScaleY(0.85f+r*0.15f);
            }
        });
        viewPager.setPageTransformer(compositePageTransformer);
        viewPager.setCurrentItem(1);
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                handler.removeCallbacks(sliderRunnable);
            }
        });



    }
   private Runnable sliderRunnable=new Runnable() {
       @Override
       public void run() {
           viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
       }
   };
        }
