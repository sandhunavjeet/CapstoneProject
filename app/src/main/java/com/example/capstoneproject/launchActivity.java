package com.example.capstoneproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class launchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);


        final Context context;
        context = this;
        Thread timer = new Thread(){
            public void run(){
                try{
                    sleep(3000);
                }catch (Exception e){
                    Log.e("Launch","Thread wait failed");

                }finally {
                    finish();
                    Intent loginintent = new Intent(context,MainActivity.class);
                    startActivity(loginintent);
                }
            }
        };
        timer.start();
    }



}
