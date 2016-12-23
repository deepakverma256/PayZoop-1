package com.objectedge.payzoop.camera;

import android.view.View;

import com.google.zxing.integration.android.IntentIntegrator;
import com.objectedge.payzoop.activity.ProductListingActivity;

/**
 * Created by deepak.verma on 23-12-2016.
 */
public class BarCodeScanner {

    /**
     * event handler for scan button
     * @param view view of the activity
     */
    public void scanNow(View view, ProductListingActivity pla){
        IntentIntegrator integrator = new IntentIntegrator(pla);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ONE_D_CODE_TYPES);
        integrator.setPrompt("Scan a barcode");
        integrator.setResultDisplayDuration(0);
        integrator.setWide();  // Wide scanning rectangle, may work better for 1D barcodes
        integrator.setCameraId(0);  // Use a specific camera of the device
        integrator.initiateScan();
    }
}
