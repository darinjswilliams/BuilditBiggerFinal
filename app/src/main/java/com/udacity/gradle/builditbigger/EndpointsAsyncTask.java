package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.hcsc.adc.mylibrary.JokeActivity;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

public class EndpointsAsyncTask extends AsyncTask<Context, Void, String> implements JokeInterface {
    private static MyApi myApiService = null;
    private Context context;
    private static final String TAG = EndpointsAsyncTask.class.getSimpleName();

    @Override
    public String doInBackground(Context... params) {
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
    public void onPostExecute(String result) {
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
