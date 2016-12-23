package com.objectedge.payzoop.di;


import com.objectedge.payzoop.activity.CartActivity;
import com.objectedge.payzoop.activity.PDPActivity;
import com.objectedge.payzoop.activity.ProductListingActivity;
import com.objectedge.payzoop.adapter.NavigationDrawerRecyclerAdapter;
import com.objectedge.payzoop.adapter.ProductRecyclerAdapter;
import com.objectedge.payzoop.model.Cart;
import com.objectedge.payzoop.rest.OCCRestService;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = { RestModule.class, ApplicationModule.class })
public interface RootComponent {

    void inject(ProductListingActivity productListingActivity);

    void inject(PDPActivity pdpActivity);

    void inject(OCCRestService OCCRestService);

    void inject(ProductRecyclerAdapter productGridAdapter);

    void inject(NavigationDrawerRecyclerAdapter navigationGridAdapter);

    void inject(Cart Cart);

    void inject(CartActivity cartActivity);
}
