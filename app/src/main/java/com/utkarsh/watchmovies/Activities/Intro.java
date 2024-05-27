package com.utkarsh.watchmovies.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.utkarsh.watchmovies.R;

public class Intro extends AppCompatActivity {
    MaterialButton get_Started;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.intro);
        get_Started=findViewById(R.id.get_Started);
        get_Started.setOnClickListener(view->{
            LoadIntents(LoginActivity.class);

        });

    }
    public void LoadIntents(Class<?> activityClass){
        Intent intent=new Intent(this,activityClass);
        startActivity(intent);
    }
}
