package com.example.smarthome;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class signup extends AppCompatActivity {
    TextView sign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        sign=findViewById(R.id.registered);
        sign.setOnClickListener(view -> open());
    }
    public void open()
    {
        Intent intent= new Intent(this, Login.class);

            startActivity(intent);

    }
}