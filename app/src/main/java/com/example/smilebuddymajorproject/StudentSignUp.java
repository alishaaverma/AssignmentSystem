package com.example.smilebuddymajorproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

public class StudentSignUp extends AppCompatActivity {
TextView btn;
ImageView add, userimg;
EditText txtname,txtcourse,txtbranch,txtyear,txtmobno,txtemail,txtpassword,txtaddress;
Button btnsignup;
RadioGroup rdbgender;
RadioButton rdbmale,rdbfemale,rdbother;
//create a object of StorageReferance class.(step 1)
    StorageReference storage;
    public static String imageurldb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_sign_up);

//get referance of cloud storage to the StorageReferance.(step 2)
        storage= FirebaseStorage.getInstance().getReference();

        btn = (TextView) findViewById(R.id.reg);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(StudentSignUp.this, StudentLogin.class);
                startActivity(in);
            }
        });

        //open gallery on click of + button
       add=(ImageView) findViewById(R.id.addpic);
        userimg=(ImageView) findViewById(R.id.imgup);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openg=new Intent();
                openg.setType("image/*");
                openg.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(openg,1);
            }
        });

        //store data in database on click register now button
        txtname=findViewById(R.id.txtname);
        txtcourse=findViewById(R.id.txtcourse);
        txtbranch=findViewById(R.id.txtbranch);
        txtyear=findViewById(R.id.txtyear);
        txtemail=findViewById(R.id.txtemail);
        txtmobno=findViewById(R.id.txtmobno);
        txtaddress=findViewById(R.id.txtaddress);
        txtpassword=findViewById(R.id.txtpassword);
        rdbgender=findViewById(R.id.rdbgender);
        rdbfemale=findViewById(R.id.rdbmale);
        rdbfemale=findViewById(R.id.rdbfemale);
        rdbother=findViewById(R.id.rdbother);
        btnsignup=findViewById(R.id.btnsignup);

        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database=FirebaseDatabase.getInstance();
                DatabaseReference myRef=database.getReference("StudentDetails");
                HashMap<String,String> hashMap=new HashMap<>();
                hashMap.put("name",txtname.getText().toString());
                hashMap.put("course",txtcourse.getText().toString());
                hashMap.put("branch",txtbranch.getText().toString());
                hashMap.put("year",txtyear.getText().toString());
                hashMap.put("gender","male");
                hashMap.put("mobno",txtmobno.getText().toString());
                hashMap.put("email",txtemail.getText().toString());
                hashMap.put("password",txtpassword.getText().toString());
                hashMap.put("address",txtaddress.getText().toString());
                hashMap.put("picture",imageurldb);
                myRef.child(txtmobno.getText().toString()).setValue(hashMap);
                Toast.makeText(StudentSignUp.this, "Registration Successful", Toast.LENGTH_LONG).show();
            }
        });
    }


//show activity in image box
   @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && data!=null)
        {
            //get path from gallery and display in a ImageView id is: userimg
            Uri imagepath=data.getData();
            userimg.setImageURI(imagepath);

            //move image in the firebase storage
            StorageReference imagereferance=storage.child("profilepic/"+System.currentTimeMillis()+".jpg");
            imagereferance.putFile(imagepath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    imagereferance.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            imageurldb=uri.toString();
                        }
                    });
                }
            });

            Toast.makeText(this, "Image Uploaded", Toast.LENGTH_LONG).show();
        }
    }
}