package com.objectedge.restroappproto.di;


import com.objectedge.restroappproto.activity.CartActivity;
import com.objectedge.restroappproto.activity.PDPActivity;
import com.objectedge.restroappproto.activity.ProductListingActivity;
import com.objectedge.restroappproto.activity.Splash;
import com.objectedge.restroappproto.adapter.NavigationDrawerRecyclerAdapter;
import com.objectedge.restroappproto.adapter.ProductRecyclerAdapter;
import com.objectedge.restroappproto.model.Cart;
import com.objectedge.restroappproto.rest.OCCRestService;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = { com.objectedge.restroappproto.di.RestModule.class, com.objectedge.restroappproto.di.ApplicationModule.class })
public interface RootComponent {

    void inject(ProductListingActivity.PlaceholderFragment placeholderFragment);

    void inject(ProductListingActivity productListingActivity);

    void inject(PDPActivity pdpActivity);

    void inject(OCCRestService OCCRestService);

    void inject(ProductRecyclerAdapter productGridAdapter);

    void inject(NavigationDrawerRecyclerAdapter navigationGridAdapter);

    void inject(Cart Cart);

    void inject(CartActivity cartActivity);

    void inject(Splash splash);
}
