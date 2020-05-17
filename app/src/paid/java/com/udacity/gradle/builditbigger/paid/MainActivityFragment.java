package com.udacity.gradle.builditbigger.paid;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.udacity.gradle.builditbigger.MainActivity;
import com.udacity.gradle.builditbigger.R;

import androidx.fragment.app.Fragment;
/**
 * A simple {@link Fragment} subclass.
 */
public class MainActivityFragment extends Fragment {

    private Button javaBtn;
    private String jokes;
    public static final String TAG = MainActivityFragment.class.getSimpleName();

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_main, container, false);

        javaBtn = (Button) root.findViewById(R.id.javaBtn);

        javaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).tellJoke();
                Log.i(TAG, "onClick: button clicked...");
            }
        });

        return root;
    }
}
