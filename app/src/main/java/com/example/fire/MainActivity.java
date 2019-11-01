package com.example.fire;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class MainActivity extends AppCompatActivity {

    EditText emailid;
    EditText passwordid;
    Button register;
    TextView alreadyaccount;

    FirebaseAuth mFirebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.loadingPanel).setVisibility(View.GONE);

        mFirebaseAuth = FirebaseAuth.getInstance();
        emailid = findViewById(R.id.email);
        passwordid = findViewById(R.id.password);


        register = findViewById(R.id.register);
        alreadyaccount = findViewById(R.id.alreadyaccount);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.loadingPanel).setVisibility(View.VISIBLE);
                String emailr = emailid.getText().toString();
                String passwordr = passwordid.getText().toString();
                if(emailr.isEmpty()){
                    emailid.setError("Field Email is Empty");
                }
                else if(passwordr.isEmpty()){
                    passwordid.setError("Field password is empty");
                }
                else if(emailr.isEmpty() && passwordr.isEmpty()){

                    Toast.makeText(MainActivity.this, "Both Fields are empty",Toast.LENGTH_SHORT).show();
                }
                else if(!(emailr.isEmpty() && passwordr.isEmpty())){
                    mFirebaseAuth.createUserWithEmailAndPassword(emailr,passwordr).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                       FirebaseUser user = mFirebaseAuth.getCurrentUser();
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(!task.isSuccessful()){
                                Toast.makeText(MainActivity.this, "USER NOT CREATED", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(MainActivity.this,"Your Account has been created",Toast.LENGTH_SHORT).show();
                                Intent toHome = new Intent(MainActivity.this,HomeActivity.class);
                                startActivity(toHome);

                            }
                            findViewById(R.id.loadingPanel).setVisibility(View.GONE);
                        }
                    });
                }
                else{
                    Toast.makeText(MainActivity.this, "An Error Occurred!!", Toast.LENGTH_SHORT).show();
                }
            }


        });
        alreadyaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent tologin = new Intent(MainActivity.this, LoginActivity.class);
//                startActivity(tologin);
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
            }
        });
    }
}
