package com.philipowino.myrestaurants;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.button) Button button;
    @BindView(R.id.zipCode) EditText location;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        // Set on click listener for "find restaurant" button
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Passing data with Intent
                String textToPass = location.getText().toString();

                Intent intent = new Intent(MainActivity.this,Restaurants.class);
                intent.putExtra("location",textToPass);

                startActivity(intent);
            }
        });





    }
}