package com.example.fire;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NewtonHome extends AppCompatActivity {

    Button gall, contac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newton_home);

        gall = findViewById(R.id.Gallery);
        contac = findViewById(R.id.contact);

        gall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent togalle = new Intent(NewtonHome.this, GalleryActivity.class);
                startActivity(togalle);
            }
        });
        contac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tocontac = new Intent(NewtonHome.this, ContactActivity.class);
                startActivity(tocontac);
            }
        });
    }

}
