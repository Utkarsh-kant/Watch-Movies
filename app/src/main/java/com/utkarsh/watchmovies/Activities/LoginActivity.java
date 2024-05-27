package com.utkarsh.watchmovies.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.google.android.material.textfield.TextInputEditText;
import com.utkarsh.watchmovies.R;

public class LoginActivity extends AppCompatActivity {
    TextInputEditText lUser,lPassword;
    AppCompatButton btnLogin;
    TextView lTxtReg,lForgotpass;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
         findId();
        String UserName=lUser.getText().toString().trim();
        String UPassword=lPassword.getText().toString().trim();
         btnLogin.setOnClickListener(view->{
            
                 Intent intent=new Intent(LoginActivity.this, Home.class);
                 startActivity(intent);




         });

    }
    public void findId(){
        lUser=findViewById(R.id.lUser);
        lPassword=findViewById(R.id.lPassword);
        btnLogin=findViewById(R.id.btnLogin);
        lTxtReg=findViewById(R.id.lTxtReg);
        lForgotpass=findViewById(R.id.lForgotpass);

    }
}
