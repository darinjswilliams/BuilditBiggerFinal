package com.udacity.gradle.builditbigger.testing;

import android.content.Context;
import android.content.Intent;

import com.hcsc.adc.mylibrary.JokeActivity;
import com.udacity.gradle.builditbigger.EndpointsAsyncTask;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.platform.app.InstrumentationRegistry;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;

@RunWith(AndroidJUnit4ClassRunner.class)
public class EndpointsAsyncTaskTest {

    private CountDownLatch mSignal;
    private String joke;
    private Context mConext;
    private EndpointsAsyncTask mEndpointsAsysTask;
    private Intent mIntent;
    private String checkBackGround;
    private static final String TAG  = EndpointsAsyncTaskTest.class.getSimpleName();


    @Before
    public void setUp() {

        //lets get a context
        mConext = InstrumentationRegistry.getInstrumentation().getContext();
        assertNotNull(mConext);

        checkBackGround = null;

        //Reference Endpoint Task
        mEndpointsAsysTask = new EndpointsAsyncTask();
        assertNotNull(mEndpointsAsysTask);
    }


    @Test
    public void PostExecute(){
        Intent mIntent = new Intent(mConext, JokeActivity.class);
        mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mIntent.putExtra(JokeActivity.EXTRA_JOKE, "testing again");
        assertNotNull(mIntent);
    }

    @Test
    public void doInBackground() {

        checkBackGround = new EndpointsAsyncTask().doInBackground(mConext);
        assertNotNull(checkBackGround);
    }

    @Test
    public void endPointAsyncTask() throws InterruptedException {

        String jokeReturn = null;

        try {
            mSignal = new CountDownLatch(1);

//            mEndpointsAsysTask.execute(mConext);

            jokeReturn  = mIntent.getStringExtra("AndroidLibActivity");
            mSignal.await(8, TimeUnit.SECONDS);
            assertNotSame(mIntent.getStringExtra(JokeActivity.EXTRA_JOKE), jokeReturn);
            assertNotNull("Empty String Return", jokeReturn);

        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }


}
