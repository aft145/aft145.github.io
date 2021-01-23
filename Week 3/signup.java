package com.example.smarthome;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class signup extends AppCompatActivity {
    TextView sign;

    private EditText Email;
    private EditText Password;
    private EditText confirm_pass;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    private CheckBox checkBox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        sign=findViewById(R.id.registered);
        sign.setOnClickListener(view -> open());
        progressBar = findViewById(R.id.signup_progressbar);
        confirm_pass= findViewById(R.id.confirm_password);
        Email= findViewById(R.id.email);
        Password= findViewById(R.id.password);
        checkBox=findViewById(R.id.signup_check);
        Button register_btn = findViewById(R.id.register);
        mAuth= FirebaseAuth.getInstance();

        checkBox.setOnCheckedChangeListener((compoundButton, b) -> {
            if(b){
                Password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                confirm_pass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }else {
                Password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                confirm_pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        });


        register_btn.setOnClickListener(view -> {
            String email=Email.getText().toString();
            String pass=Password.getText().toString();
            String CPassword=confirm_pass.getText().toString();
            if (!TextUtils.isEmpty(email) || !TextUtils.isEmpty(pass) || !TextUtils.isEmpty(CPassword))
            {
                if(pass.equals(CPassword))
                {
                    progressBar.setVisibility(View.VISIBLE);

                    mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(task -> {
                        if (task.isSuccessful())
                        {
                            sendtoMain();
                        }else {
                            String error=task.getException().getMessage();
                            Toast.makeText(getApplicationContext(),  "ERROR:"+ error, Toast.LENGTH_LONG ).show();
                        }
                        progressBar.setVisibility(View.INVISIBLE);
                    });
                }else {
                    Toast.makeText(getApplicationContext(),  "Password and its Confirmation Not Matched", Toast.LENGTH_LONG ).show();

                }

                }
            //}
        });


    }
    public void open()
    {
        Intent intent= new Intent(this, Login.class);

            startActivity(intent);

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = FirebaseAuth. getInstance().getCurrentUser();
        if(currentUser != null)
        {
            sendtoMain();
        }

    }
    private void sendtoMain(){
        Intent intent = new Intent(signup.this,HomeScreen.class);
        startActivity(intent);
        finish();
    }

}