package com.philipowino.myrestaurants;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();
    private Button button;
    private EditText location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initializations
        button = (Button) findViewById(R.id.button);
        location = (EditText) findViewById(R.id.zipCode);

        // Set on click listener for "find restaurant" button
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String locationZip = location.getText().toString();

                // Toast the value entered
                Toast.makeText(MainActivity.this,locationZip,Toast.LENGTH_LONG).show();

                // Log the value to Logcat
                Log.d(TAG,locationZip);
            }
        });





    }
}