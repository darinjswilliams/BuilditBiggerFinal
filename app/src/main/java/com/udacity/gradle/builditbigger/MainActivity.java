package com.udacity.gradle.builditbigger;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;


public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        fragmentManager = getSupportFragmentManager();
//        launchFragment(new MainActivityFragment(), MainActivityFragment.TAG, R.id.fragment_contaier);
////        new EndpointsAsyncTask().execute(getApplicationContext());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

//    public void launchFragment(Fragment fragment, String tag, int containier) {
//
//        fragmentManager.beginTransaction()
//                .replace(containier, fragment, tag)
//                .commit();
//    }

    public void tellJoke() {
        new EndpointsAsyncTask().execute(getApplicationContext());
    }

}

