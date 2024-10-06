package com.example.smilebuddymajorproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
TextView studtext,admtext;
WebView web;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        admtext=(TextView)findViewById(R.id.alogin);
        studtext=(TextView)findViewById(R.id.slogin);


        admtext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in1=new Intent(MainActivity.this,AdminLogin.class);
                startActivity(in1);

            }
        });

        studtext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(MainActivity.this,StudentLogin.class);
                startActivity(in);
            }
        });

      web=(WebView)findViewById(R.id.htmlp);
      WebSettings webSettings=web.getSettings();
        webSettings.setJavaScriptEnabled(true);
        web.loadUrl("file:///android_asset/welcomee.html");
    }

}