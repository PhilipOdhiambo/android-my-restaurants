package com.philipowino.myrestaurants.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.philipowino.myrestaurants.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements  View.OnClickListener {
    public static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.button1) Button button;
    @BindView(R.id.zipCode) EditText location;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if (view == button) {
            // Passing data with Intent
            String textToPass = location.getText().toString();
            Intent intent = new Intent(MainActivity.this,Restaurants.class);
            intent.putExtra("location",textToPass);
            startActivity(intent);
        }
    }
}