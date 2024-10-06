package com.example.smilebuddymajorproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PasswordChange extends AppCompatActivity {
TextView gotoonlogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_change);

        final EditText npass=findViewById(R.id.npass);
        final EditText cpass=findViewById(R.id.cpass);
        Button changepass=findViewById(R.id.changepass);
        changepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newpass=npass.getText().toString();
                String confirmpass=cpass.getText().toString();

                if(newpass.equals(confirmpass))
                {
                    Toast.makeText(PasswordChange.this, "Password Changed Successfully", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(PasswordChange.this, "New Password And Confirm Password Mismatch", Toast.LENGTH_LONG).show();
                }
            }
        });

        gotoonlogin=(TextView)findViewById(R.id.ssignup);

        gotoonlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(PasswordChange.this, StudentLogin.class);
                startActivity(in);
            }
        });
    }
}