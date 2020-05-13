package com.hcsc.adc.mylibrary;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class FirstFragment extends Fragment {

    public static final String TAG = FirstFragment.class.getSimpleName();

    public FirstFragment() {
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_first, container, false);

        Log.i(TAG, "onCreateView: INSIDE ANDROID LIBRARY FRAGMENT");
        //Get joke pass from Java lib
        Intent intent = getActivity().getIntent();

        //lets check intent
        if(Intent.ACTION_SEND.equals(intent.getAction()) && intent.getType() != null){
            String jokeStr = intent.getStringExtra(AndroidLibActivity.EXTRA_JOKE);
            Log.i(TAG, "onCreateView: AFTER ACTION SEND  WITH JOKE " + jokeStr);

            TextView txtView =  (TextView) root.findViewById(R.id.textview_first);

            if(jokeStr != null && jokeStr.length() != 0){
                Log.i(TAG, "onCreateView: inside joke if check length " + jokeStr.length());
                txtView.setText(jokeStr);
            }

        } else {
            Log.i(TAG, "onViewCreated: Empty intent");
        }

        return root;
    }

}
