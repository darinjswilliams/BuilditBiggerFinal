package com.hcsc.adc.mylibrary;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import static com.hcsc.adc.mylibrary.AndroidLibActivity.EXTRA_JOKE;

public class FirstFragment extends Fragment {

    private TextView mTextView;
    private static final String TAG = FirstFragment.class.getSimpleName();

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        view.findViewById(R.id.button_first).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });

        //Get joke pass from Java lib
        Intent intent = getActivity().getIntent();

        //lets check intent
        if(Intent.ACTION_SEND.equals(intent.getAction()) && intent.getType() != null){
            String jokeStr = intent.getStringExtra(Intent.EXTRA_TEXT);

            TextView txtView =  (TextView) view.findViewById(R.id.textview_first);
            txtView.setText(jokeStr);
        } else {
            Log.i(TAG, "onViewCreated: Empty intent");
        }
       

        
    }
}
