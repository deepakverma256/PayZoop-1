package com.objectedge.payzoop;


import android.app.Application;

import com.objectedge.payzoop.di.ApplicationModule;
import com.objectedge.payzoop.di.DaggerRootComponent;
import com.objectedge.payzoop.di.RestModule;
import com.objectedge.payzoop.di.RootComponent;
import com.objectedge.payzoop.model.Cart;


/**
 * Created by michaelkatkov on 8/4/16.
 */
public class OCCApplication extends Application {

    public static final String TAG = "OCCApplication";

    private static RootComponent mRootComponent;

    private static Cart cart;

    public static RootComponent getRootComponent() {
        return mRootComponent;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        initComponents();
    }


    private void initComponents() {
        ApplicationModule applicationModule = new ApplicationModule(this);
        RestModule restModule = new RestModule();
        mRootComponent = DaggerRootComponent.builder().restModule(restModule).applicationModule(applicationModule)
                .build();
    }
}
