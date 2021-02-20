package com.example.smarthome;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;

import android.widget.EditText;

import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;



public class Login extends AppCompatActivity {
        TextView Sign,forgot;
        private EditText Username;
        private EditText Password;
         private CheckBox checkbox;
        private FirebaseAuth mAuth;
        private ProgressBar progressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Sign=findViewById(R.id.up);
        forgot=findViewById(R.id.forgot);
        progressBar=findViewById(R.id.login_progressbar);
        mAuth= FirebaseAuth.getInstance();
        Username= findViewById(R.id.username);
        Password= findViewById(R.id.password);
        checkbox=findViewById(R.id.login_check);
        Button login_btn = findViewById(R.id.login);
        login_btn.setOnClickListener(view -> {
        String username=Username.getText().toString();
         String password=Password.getText().toString();
         if (!TextUtils.isEmpty(username) || !TextUtils.isEmpty(password))
         {
             progressBar.setVisibility(View.VISIBLE);
             mAuth.signInWithEmailAndPassword(username,password).addOnCompleteListener(task -> {
                 if (task.isSuccessful())
                 {
                     sendtoMain();
                 }else {
                     String error=task.getException().getMessage();
                     Toast.makeText(getApplicationContext(),  "ERROR:"+ error, Toast.LENGTH_LONG ).show();

                 }
             });

         }
        });
        checkbox.setOnCheckedChangeListener((compoundButton, b) -> {
            if(b){
                Password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }else {
                Password.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        });

        forgot.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                EditText resetPass= new EditText(v.getContext());
                AlertDialog.Builder PassResetDialog=new AlertDialog.Builder(v.getContext());
                PassResetDialog.setTitle("Reset Password?");
                PassResetDialog.setMessage("Enter Your Email to Recieve Reset Link:");
                PassResetDialog.setView(resetPass);
                PassResetDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        //extract email and send reset link
                        String mail= resetPass.getText().toString();
                        mAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>(){

                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(Login.this,"Reset Link sent to your Email",Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener(){

                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Login.this,"Error! Link is not send"+e.getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
                PassResetDialog.setNegativeButton("No",new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        //close dialog
                    }
                });
                PassResetDialog.create().show();
            }
        });
                Sign.setOnClickListener(view -> open());


    }
    public void open()
    {
        Intent intent= new Intent(this, signup.class);
         startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = FirebaseAuth. getInstance().getCurrentUser();
        if(currentUser != null)
        {
            Intent intent = new Intent(Login.this,HomeScreen.class);
            startActivity(intent);
            finish();
        }

    }
    
    private void sendtoMain() {
        Intent intent = new Intent(Login.this,HomeScreen.class);
        startActivity(intent);
        finish();
    }


    
}