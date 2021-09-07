package com.philipowino.myrestaurants;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Restaurants extends AppCompatActivity {
    private static final String TAG = "Restaurants";
    @BindView(R.id.locationView) TextView mLocation;
    @BindView(R.id.restaurantsListView) ListView mListView;
    @BindView(R.id.errorTextView) TextView mErrorTextView;
    @BindView(R.id.progressBar) ProgressBar mProgressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);
        ButterKnife.bind(this);


        // Set OnItemClickListener to display Toast when listItem is clicked
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String restaurant = ((TextView)view).getText().toString();
                Log.v("RestaurantsActivity", "In the onItemClickListener!");
                Toast.makeText(Restaurants.this, restaurant, Toast.LENGTH_LONG).show();
            }
        });

        // Initialize Intent
        Intent intent = getIntent();
        String location = intent.getStringExtra("location");
        mLocation.setText("Here are all the restaurants near: " + location);

        YelpApi client = YelpClient.getClient();
        Call<YelpBusinessesSearchResponse> call = client.getRestaurants(location, "restaurants");
        call.enqueue(new Callback<YelpBusinessesSearchResponse>() {
            @Override
            public void onResponse(Call<YelpBusinessesSearchResponse> call, Response<YelpBusinessesSearchResponse> response) {
                hideProgressBar();
                if (response.isSuccessful()) {
                    List<Business> restaurantsLIst = response.body().getBusinesses();
                    String[] restaurants = new String [restaurantsLIst.size()];
                    String[] categories = new String [restaurantsLIst.size()];

                    for (int i = 0; i < restaurants.length; i++) {
                        restaurants[i] = restaurantsLIst.get(i).getName();
                    }
                    Log.i(TAG,"Restaurants" + restaurantsLIst.toString());


                    for (int i = 0; i < categories.length; i++) {
                        Category category = restaurantsLIst.get(i).getCategories().get(0);
                        categories[i] = category.getTitle();
                    }

                    ArrayAdapter adapter = new MyRestaurantsArrayAdapter(Restaurants.this, android.R.layout.simple_list_item_1, restaurants,categories);
                    mListView.setAdapter(adapter);

                    showRestaurants();
                } else {
                    showUnsuccessfulMessage();
                }
            }

            @Override
            public void onFailure(Call<YelpBusinessesSearchResponse> call, Throwable t) {
                Log.e(TAG, "Error",t);
                hideProgressBar();
                showFailureMessage();

            }
        });

    }

    private void showFailureMessage() {
        mErrorTextView.setText("Something went wrong. Please check your Internet connection and try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
    }

    private void showUnsuccessfulMessage() {
        mErrorTextView.setText("Something went wrong. Please try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
    }

    private void showRestaurants() {
        mListView.setVisibility(View.VISIBLE);
        mLocation.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }
}