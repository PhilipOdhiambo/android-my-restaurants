package com.philipowino.myrestaurants;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Restaurants extends AppCompatActivity {
    private TextView mLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);

        // Custom Code
        mLocation = (TextView) findViewById(R.id.locationView);
        Intent intent = getIntent();
        String location = intent.getStringExtra("location");
        mLocation.setText(location);
    }
}