package com.objectedge.payzoop.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.objectedge.payzoop.OCCApplication;
import com.objectedge.payzoop.R;
import com.objectedge.payzoop.credentials.DeveloperKey;
import com.objectedge.payzoop.event.RestEvent;
import com.objectedge.payzoop.model.APIMetadata;
import com.objectedge.payzoop.rest.OCCRestService;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;

public class Splash extends Activity {
    private static final String TAG = Splash.class.getSimpleName();

    @Inject
    OCCRestService mOCCRestService;

    @Inject
    EventBus mEventBus;

    /** Duration of wait **/
    private final int SPLASH_DISPLAY_LENGTH = 1000;

    Boolean moveAdead = true;
    protected boolean _active = true;
    protected int _splashTime = 3000;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.splash_screen);

        OCCApplication.getRootComponent().inject(Splash.this); //inject activity into RootComponent
        mEventBus.register(this);//register Events Catcher
        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/
        int secondsDelayed = 1;
        Thread splashThread = new Thread() {
            @Override
            public void run() {
                try {
                    initiaization();
                    int waited = 0;
                    while (_active ) {
                        Log.d(TAG, String.format("Going to sleep"));
                        sleep(100);
                    }
                } catch (Exception e) {

                } finally {

                    startActivity(new Intent(Splash.this,
                            ProductListingActivity.class));
                    finish();
                }
            };
        };
        splashThread.start();
    }

    private void initiaization() {
        mOCCRestService.getAuthKey(new APIMetadata("admin","admin"));
        Log.d(TAG, String.format("intiation called"));
    }


    public void onEventMainThread(RestEvent.GetAPIKeySuccessEvent event) {
        Log.d(TAG, String.format("SUCCESS"));
        DeveloperKey.setAPIKey(event.key);
        Toast toast = Toast.makeText(getApplicationContext(),DeveloperKey.APIKey.getId_token(), Toast.LENGTH_SHORT);
        toast.show();
        _active = false;
    }

    public void onEventMainThread(RestEvent.GetAPIKeyFailureEvent event) {
        //DeveloperKey.APIKey = event.key;
        Log.d(TAG, String.format("FAILURE"));
        Toast toast = Toast.makeText(getApplicationContext(),"API call failed "+event.response, Toast.LENGTH_SHORT);
        toast.show();
        _active = false;
    }
}