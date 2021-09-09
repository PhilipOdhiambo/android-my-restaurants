package com.philipowino.myrestaurants.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.philipowino.myrestaurants.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Restaurants extends AppCompatActivity {

    @BindView(R.id.locationView) TextView mLocation;
    @BindView(R.id.restaurantsLIstView) ListView mListView;

    private String[] restaurants = new String[] {"Mi Mero Mole", "Mother's Bistro",
            "Life of Pie", "Screen Door", "Luc Lac", "Sweet Basil",
            "Slappy Cakes", "Equinox", "Miss Delta's", "Andina",
            "Lardo", "Portland City Grill", "Fat Head's Brewery",
            "Chipotle", "Subway"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);
        ButterKnife.bind(this);

        // Initialize and set ArrayAdapter
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, restaurants);
        mListView.setAdapter(arrayAdapter);

        // Set OnItemClickListener to display Toast when listItem is clicked
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String restaurant = ((TextView)view).getText().toString();
                Toast.makeText(Restaurants.this, restaurant, Toast.LENGTH_LONG).show();
            }
        });

        // Initialize Intent
        Intent intent = getIntent();
        String location = intent.getStringExtra("location");
        mLocation.setText("Here are all the restaurants near: " + location);
    }
}