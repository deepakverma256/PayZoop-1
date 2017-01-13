package com.objectedge.payzoop.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.zxing.Result;
import com.objectedge.payzoop.R;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScannerActivity extends Activity implements ZXingScannerView.ResultHandler{
    private ZXingScannerView mScannerView;
    public static final String JSON_STRING = "json_string";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);
        scan();
    }

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
        alertDialog.show();

        Intent myIntent = new Intent(ScannerActivity.this, ProductListingActivity.class);
        myIntent.putExtra(JSON_STRING, result.getText());
        startActivity(myIntent);
        //Resume scanning
        //mScannerView.resumeCameraPreview(this);
    }
}
