package com.example.smilebuddymajorproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

public class AdminLogin extends AppCompatActivity {
TextView asignup;
Button btnAdmin;
WebView web;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        web=(WebView)findViewById(R.id.welcomeasign);
        WebSettings webSettings=web.getSettings();
        webSettings.setJavaScriptEnabled(true);
        web.loadUrl("file:///android_asset/welcomeas.html");


        //click event of login to redirect on admin dashboard
        btnAdmin=findViewById(R.id.btnadmin);
        btnAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(AdminLogin.this,AdminDashboard.class);
                startActivity(in);
            }
        });

    }

}