package com.objectedge.restroappproto.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.objectedge.restroappproto.OCCApplication;
import com.objectedge.restroappproto.R;
import com.objectedge.restroappproto.model.Cart;

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
}
