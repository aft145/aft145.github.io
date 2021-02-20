package com.example.smarthome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplasaaahSacreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splasaaah_sacreen);
        new Handler().postDelayed(new Runnable() {
            @Override public void run() {
                Intent i = new Intent(SplasaaahSacreen.this, Login.class); startActivity(i);
                startActivity(i);
                finish(); } }, 3000);
        }
    }

