package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.hcsc.adc.mylibrary.JokeActivity;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;


public class MainActivity extends AppCompatActivity implements jokeInterface {

    private FragmentManager fragmentManager;
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        launchFragment(new MainActivityFragment(), MainActivityFragment.TAG, R.id.fragment_contaier);
//        new EndpointsAsyncTask().execute(getApplicationContext());
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

    public void launchFragment(Fragment fragment, String tag, int containier) {

        fragmentManager.beginTransaction()
                .replace(containier, fragment, tag)
                .commit();
    }

    public void tellJoke() {
        new EndpointsAsyncTask().execute(getApplicationContext());
    }

    @Override
    public void startJokeIntent(Intent intent) {
        startActivity(intent);
    }
}

class EndpointsAsyncTask extends AsyncTask<Context, Void, String> implements jokeInterface {
    private static MyApi myApiService = null;
    private Context context;
    private static final String TAG = EndpointsAsyncTask.class.getSimpleName();

    @Override
    protected String doInBackground(Context... params) {
        if (myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();
        }

        context = params[0];

        try {
            Log.i(TAG, "doInBackground: ");
            String jokeStr = myApiService.sayHi().execute().getData();
            Log.i(TAG, "doInBackground: data "  + jokeStr);
            return jokeStr;
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        Log.i(TAG, "onPostExecute: here is the joke " + result);
        if (isNullOrEmpty(result)) {
            Intent mIntent = new Intent(context, JokeActivity.class);
//            mIntent.setAction(Intent.ACTION_SEND);
            mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mIntent.putExtra(JokeActivity.EXTRA_JOKE, result);
            startJokeIntent(mIntent);
        } else {
            displayToast();
        }

    }


    private boolean isNullOrEmpty(String str) {
        if (str != null && !str.isEmpty())
            return true;
        return false;
    }

    private void displayToast() {
        Toast.makeText(context, Constants.NO_JOKES, Toast.LENGTH_LONG).show();
    }


    @Override
    public void startJokeIntent(Intent intent) {
        context.startActivity(intent);
    }
}
