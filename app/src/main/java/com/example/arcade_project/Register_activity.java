package com.example.arcade_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Register_activity extends AppCompatActivity {
    Button button;
    private static final  String FILE_NAME = "Save_doc.txt";
    EditText meditText;
    EditText mUserName,mPassword,mEmail;
    FirebaseAuth fAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        mUserName = findViewById(R.id.editText_Name);
        mPassword = findViewById(R.id.editText_Password);
        mEmail = findViewById(R.id.editText_Mail);

        fAuth = FirebaseAuth.getInstance();

       /* FirebaseUser currentUser = fAuth.getCurrentUser();
        if(currentUser != null) {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(Register_activity.this,login_activity.class));

            finish();
        }*/






        button = (Button) findViewById(R.id.GotoLoginbutton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Username = mUserName.getText().toString().trim();
                String Password = mPassword.getText().toString().trim();
                String email = mEmail.getText().toString().trim();


                if (TextUtils.isEmpty(Username)){
                    mUserName.setError("user name is required ");
                    return;
                }

                if (TextUtils.isEmpty(Password)) {
                    mPassword.setError("password is required");
                    return;
                }

                if (TextUtils.isEmpty(email))  {
                    mEmail.setError("email is required");
                    return;
                }

                fAuth.createUserWithEmailAndPassword(Username,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Register_activity.this,"User created",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Register_activity.this,login_activity.class));
                        }
                        else{
                            Toast.makeText(Register_activity.this,"Error!"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });




            }
        });

        //startActivity(new Intent(Register_activity.this,login_activity.class));


    }


}