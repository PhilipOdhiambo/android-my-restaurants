package com.philipowino.myrestaurants;

import android.content.Intent;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import com.philipowino.myrestaurants.ui.MainActivity;
import com.philipowino.myrestaurants.ui.Restaurants;

import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowActivity;

@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {

    private MainActivity activity;

    @Before
    public void setUp() throws Exception {
        activity = Robolectric.buildActivity(MainActivity.class)
                .create()
                .resume()
                .get();
    }

    @Test
    public void validateTextViewContent() {
        TextView appNameTextView = activity.findViewById(R.id.appNameTextView);
        assertTrue("My Restaurants".equals(appNameTextView.getText().toString()));
    }


    @Test // Using Shadows
    public void secondActivityStarted() {

        activity.findViewById(R.id.button).performClick();
        Intent expectedIntent = new Intent(activity, Restaurants.class);

        ShadowActivity shadowActivity = org.robolectric.Shadows.shadowOf(activity);

        Intent actualIntent = shadowActivity.getNextStartedActivity();

        assertTrue(actualIntent.filterEquals(expectedIntent));

    }
}
