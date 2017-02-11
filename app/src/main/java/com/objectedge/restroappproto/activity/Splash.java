package com.objectedge.restroappproto.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.zxing.Result;
import com.objectedge.restroappproto.OCCApplication;
import com.objectedge.restroappproto.R;
import com.objectedge.restroappproto.credentials.DeveloperKey;
import com.objectedge.restroappproto.event.RestEvent;
import com.objectedge.restroappproto.model.APIMetadata;
import com.objectedge.restroappproto.rest.OCCRestService;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;
import me.dm7.barcodescanner.zxing.ZXingScannerView;


public class Splash extends Activity implements ZXingScannerView.ResultHandler{
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

    private ZXingScannerView mScannerView;
    public static final String JSON_STRING = "json_string";


   /* @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);
        scan();
    }*/

    public void scan(){
        mScannerView = new ZXingScannerView(this);
        setContentView(mScannerView);
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    @Override
    public void handleResult(Result result) {
        //Do anything with result here :D
        Log.w("handleResult", result.getText());
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Scan result");
        builder.setMessage(result.getText());
        AlertDialog alertDialog = builder.create();
        //alertDialog.show();

        Intent myIntent = new Intent(Splash.this, com.objectedge.restroappproto.activity.ProductListingActivity.class);
        myIntent.putExtra(JSON_STRING, result.getText());
        startActivity(myIntent);
        //Resume scanning
        //mScannerView.resumeCameraPreview(this);
    }

    private class AsyncTaskRunner extends AsyncTask<String, String, String> {

        private String resp;
        ProgressDialog progressDialog;

        @Override
        protected String doInBackground(String... params) {
            publishProgress("Sleeping..."); // Calls onProgressUpdate()
            try {
                int time = Integer.parseInt(params[0])*1000;
                initiaization();
                Thread.sleep(time);
                resp = "Slept for " + params[0] + " seconds";
            } catch (InterruptedException e) {
                e.printStackTrace();
                resp = e.getMessage();
            } catch (Exception e) {
                e.printStackTrace();
                resp = e.getMessage();
            }
            return resp;
        }


        @Override
        protected void onPostExecute(String result) {
            // execution of result of Long time consuming operation
            scan();
        }
    }

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.splash_screen);

        OCCApplication.getRootComponent().inject(Splash.this); //inject activity into RootComponent
        mEventBus.register(this);//register Events Catcher
        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/
        Integer secondsDelayed = 5;
        AsyncTaskRunner runner = new AsyncTaskRunner();
        String sleepTime = secondsDelayed.toString();
        runner.execute(sleepTime);
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