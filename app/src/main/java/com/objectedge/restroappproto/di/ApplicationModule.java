package com.objectedge.restroappproto.di;


import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;

import com.objectedge.restroappproto.model.Cart;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import de.greenrobot.event.EventBus;


/**
 * Created by michaelkatkov on 8/4/16.
 */
@Module
public class ApplicationModule {

    private Application mApplication;


    public ApplicationModule(Application application) {
        mApplication = application;
    }


    @Singleton
    @Provides
    public Context provideContext() {
        return mApplication;
    }


    @Singleton
    @Provides
    public EventBus provideEventBus() {
        return EventBus.getDefault();
    }


    @Provides
    @Singleton
    LayoutInflater provideLayoutInflater() {
        return (LayoutInflater) mApplication.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Provides
    @Singleton
    Cart provideCart() {
        return new Cart();
    }
}
