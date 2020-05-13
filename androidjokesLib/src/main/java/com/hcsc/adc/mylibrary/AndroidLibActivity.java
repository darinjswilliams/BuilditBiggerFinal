package com.hcsc.adc.mylibrary;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class AndroidLibActivity extends AppCompatActivity {

    public static final String EXTRA_JOKE = "AndroidLibActivity";
    private final static String TAG = AndroidLibActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i(TAG, "onCreate: Android Library Now display the joke");
        Intent intent = getIntent();

        //lets check intent
            String jokeStr = intent.getStringExtra(AndroidLibActivity.EXTRA_JOKE);
        Log.i(TAG, "onCreate: here is the joke.." + jokeStr);

    }

}
