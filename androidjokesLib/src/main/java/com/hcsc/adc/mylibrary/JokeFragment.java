package com.hcsc.adc.mylibrary;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class JokeFragment extends Fragment {

    public static final String TAG = JokeFragment.class.getSimpleName();
    private TextView mTxtView;

    public JokeFragment() {
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_joke, container, false);

        Log.i(TAG, "onCreateView: INSIDE ANDROID LIBRARY FRAGMENT");

        //Get the Intent
        Intent intent = getActivity().getIntent();

        //Get joke from Intent
        String jokeStr = intent.getStringExtra(JokeActivity.EXTRA_JOKE);

        Log.i(TAG, "onCreateView: AFTER ACTION SEND  WITH JOKE " + jokeStr);

        //Set text view
        mTxtView =  root.findViewById(R.id.textview_first);

        if(jokeStr != null && jokeStr.length() != 0){
            Log.i(TAG, "onCreateView: inside joke if check length " + jokeStr.length());
            mTxtView.setText(jokeStr);
        }
        return root;
    }

}
