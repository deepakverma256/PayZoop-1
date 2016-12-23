package com.objectedge.payzoop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.objectedge.payzoop.OCCApplication;
import com.objectedge.payzoop.R;
import com.objectedge.payzoop.event.CartEvent;
import com.objectedge.payzoop.model.Cart;

import javax.inject.Inject;

import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

public class CartActivity extends AppCompatActivity {

    @Inject
    Cart cart;

    private String codeFormat,codeContent;

    //@BindView(R.id.scan_format)
    private TextView formatTxt;

    //@BindView(R.id.scan_content)
    private TextView contentTxt;

    @Inject
    EventBus mEventBus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        ButterKnife.bind(this);
        OCCApplication.getRootComponent().inject(this);
       // mEventBus.register(this);

        formatTxt = (TextView) findViewById(R.id.scan_format);
        contentTxt =  (TextView) findViewById(R.id.scan_content);
    }

    /**
     * event handler for scan button
     * @param view view of the activity
     */
    public void scanNow(View view){
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ONE_D_CODE_TYPES);
        integrator.setPrompt("Scan a barcode");
        integrator.setResultDisplayDuration(0);
        integrator.setWide();  // Wide scanning rectangle, may work better for 1D barcodes
        integrator.setCameraId(0);  // Use a specific camera of the device
        integrator.initiateScan();
    }

    /**
     * function handle scan result
     * @param requestCode
     * @param resultCode
     * @param intent
     */
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        //retrieve scan result
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);

        if (scanningResult != null) {
            //we have a result
            String scanContent = scanningResult.getContents();
            String scanFormat = scanningResult.getFormatName();

            // display it on screen
            formatTxt.setText("FORMAT: " + scanFormat);
            contentTxt.setText("CONTENT: " + scanContent);

            mEventBus.post(new CartEvent.GetProductForBarcodeEvent(scanContent));

        }else{
            Toast toast = Toast.makeText(getApplicationContext(),"No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
