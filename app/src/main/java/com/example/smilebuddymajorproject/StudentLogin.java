package com.example.smilebuddymajorproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class StudentLogin extends AppCompatActivity {
TextView ssignup;
WebView web;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);

        ssignup=(TextView)findViewById(R.id.ssignup);

        ssignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(StudentLogin.this, StudentSignUp.class);
                startActivity(in);
            }
        });

        web=(WebView)findViewById(R.id.welcomeasign);
        WebSettings webSettings=web.getSettings();
        webSettings.setJavaScriptEnabled(true);
        web.loadUrl("file:///android_asset/welcomeas.html");

        TextView uname=findViewById(R.id.uname);
        EditText password=findViewById(R.id.password);
        Button btnlogin=findViewById(R.id.btnlogin);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database=FirebaseDatabase.getInstance();
                DatabaseReference myRef=database.getReference("StudentDetails");    //studentdetails is the tablename of db

                Query query=myRef.orderByChild("mobno").equalTo(uname.getText().toString());   //mobno is the filename of db
//uname.getText() is the refernce of EditText
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists())
                        {
                            //password is the filename of db
                            if((dataSnapshot.child(uname.getText().toString()).child("password").getValue().toString()).equals(password.getText().toString()))
                            {
                                Intent calldashboard=new Intent(StudentLogin.this,StudentDashboardd.class);

                                //All user data is now avilable in snapshot, then let's create a SharedPreference and store all data of user.
                                SharedPreferences shareddata=getSharedPreferences("logindata",MODE_PRIVATE);
                                SharedPreferences.Editor editor=shareddata.edit();
                                editor.putString("address",dataSnapshot.child(uname.getText().toString()).child("address").getValue().toString());
                                editor.putString("branch",dataSnapshot.child(uname.getText().toString()).child("branch").getValue().toString());
                                editor.putString("course",dataSnapshot.child(uname.getText().toString()).child("course").getValue().toString());
                                editor.putString("email",dataSnapshot.child(uname.getText().toString()).child("email").getValue().toString());
                                editor.putString("gender",dataSnapshot.child(uname.getText().toString()).child("gender").getValue().toString());
                                editor.putString("mobno",dataSnapshot.child(uname.getText().toString()).child("mobno").getValue().toString());
                                editor.putString("name",dataSnapshot.child(uname.getText().toString()).child("name").getValue().toString());
                                editor.putString("password",dataSnapshot.child(uname.getText().toString()).child("password").getValue().toString());
                                editor.putString("year",dataSnapshot.child(uname.getText().toString()).child("year").getValue().toString());
                                editor.commit();
                                startActivity(calldashboard);
                            }
                           else
                            {
                                Toast.makeText(StudentLogin.this, "Password Not Matched", Toast.LENGTH_LONG).show();
                            }
                        }
                        else
                        {
                            Toast.makeText(StudentLogin.this, "UserId Password is Invalid", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }

}