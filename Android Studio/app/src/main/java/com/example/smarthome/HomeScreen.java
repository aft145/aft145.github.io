package com.example.smarthome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeScreen extends AppCompatActivity {

    Button btn,change_password;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        btn=findViewById(R.id.btn_logout);
        change_password=findViewById(R.id.btn_change_pass);
        mAuth=FirebaseAuth.getInstance();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                Intent intent = new Intent(HomeScreen.this,Login.class);
                startActivity(intent);
                finish();
            }
        });

        change_password.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                EditText resetPass= new EditText(v.getContext());
                AlertDialog.Builder PassResetDialog=new AlertDialog.Builder(v.getContext());
                PassResetDialog.setTitle("Reset Password?");
                PassResetDialog.setMessage("Enter New Password Greater than 6 digits:");
                PassResetDialog.setView(resetPass);
                PassResetDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //extract email and send reset link
                        String newPassword = resetPass.getText().toString();
                        FirebaseUser user = mAuth.getCurrentUser();
                        user.updatePassword(newPassword).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(HomeScreen.this,"Password Changed Successfully",Toast.LENGTH_SHORT).show();

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(HomeScreen.this,"Error! Password not Changed"+e.getMessage(),Toast.LENGTH_SHORT).show();
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

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = FirebaseAuth. getInstance().getCurrentUser();
        if (currentUser == null)
        {
            Intent intent = new Intent(HomeScreen.this,Login.class);
            startActivity(intent);
            finish();
        }
    }
}