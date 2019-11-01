package com.example.fire;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    EditText email2;
    EditText password2;
    Button login;
    TextView newaccount;
    FirebaseAuth mfirebaseauth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mfirebaseauth = FirebaseAuth.getInstance();
        email2 = findViewById(R.id.email2);
        password2 = findViewById(R.id.password2);
        login = findViewById(R.id.login);
        newaccount = findViewById(R.id.registernow);



        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mfbuser = mfirebaseauth.getCurrentUser();
                if(mfbuser != null){
                    Toast.makeText(LoginActivity.this,"You are Logged in",Toast.LENGTH_SHORT).show();
                    Intent tohomer = new Intent(LoginActivity.this,HomeActivity.class
                    );
                    startActivity(tohomer);
                }
                else{
                    Toast.makeText(LoginActivity.this,"Please Log in Again!",Toast.LENGTH_SHORT).show();

                }
            }
        };

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailr = email2.getText().toString();
                String passwordr = password2.getText().toString();

                if(emailr.isEmpty()){
                    email2.setError("Please Enter Email field!");
                    email2.requestFocus();
                }
                else if(passwordr.isEmpty()){//if password is empty
                    password2.setError("Please enter password");
                    password2.requestFocus();
                }
                else if(emailr.isEmpty() && passwordr.isEmpty()){//if both are empty
                    Toast.makeText(LoginActivity.this,"Fields are empty!!",Toast.LENGTH_SHORT).show();
                }
                else if(!(emailr.isEmpty() && passwordr.isEmpty())){//if they are not empty proceed to login
                    mfirebaseauth.signInWithEmailAndPassword(emailr,passwordr).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()){
                                Toast.makeText(LoginActivity.this,"Login has Failed Try AGAIN!",Toast.LENGTH_SHORT).show();

                            }
                            else {
                                Intent toHome = new Intent(LoginActivity.this, HomeActivity.class);
                            }
                    }

                    });
                } //oncomplete listen to show task completed if email is created

                else{
                    Toast.makeText(LoginActivity.this,"Custom error has occurred try AGAIN!",Toast.LENGTH_SHORT).show();

                }
            }
        });
        newaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toMain = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(toMain);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mfirebaseauth.addAuthStateListener(mAuthStateListener);
    }
}
