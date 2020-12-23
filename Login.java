package com.example.smarthome;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {
        TextView Sign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Sign=findViewById(R.id.up);

        Sign.setOnClickListener(view -> open());
        }
    public void open()
    {
        Intent intent= new Intent(this, signup.class);

        startActivity(intent);

    }
    }