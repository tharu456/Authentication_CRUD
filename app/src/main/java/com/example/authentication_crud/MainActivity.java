package com.example.authentication_crud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.ktx.Firebase;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();

        Handler handler=new Handler(Looper.myLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (user==null){
                    startActivity(new Intent(MainActivity.this,LoginActivity.class));
                }else{
                    startActivity(new Intent(MainActivity.this,ProfileActivity.class));
                }

            }
        },3000);
    }
}