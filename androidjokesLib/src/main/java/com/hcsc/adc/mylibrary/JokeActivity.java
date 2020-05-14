package com.hcsc.adc.mylibrary;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class JokeActivity extends AppCompatActivity {

    public static final String EXTRA_JOKE = "AndroidLibActivity";
    private final static String TAG = JokeActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        Log.i(TAG, "onCreate: INSIDE ANDROID LIB ACTIVITY");

    }

}
