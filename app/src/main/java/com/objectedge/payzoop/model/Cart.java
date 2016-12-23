package com.objectedge.payzoop.model;

import com.objectedge.payzoop.OCCApplication;
import com.objectedge.payzoop.event.CartEvent;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;

/**
 * Created by deepak.verma on 26-08-2016.
 */
public class Cart {

    @Inject
    EventBus mEventBus;

    public Cart(){
        OCCApplication.getRootComponent().inject(this);}

    public List<ProductModel> products = new ArrayList<ProductModel>();

    public void addProduct(ProductModel product){
        products.add(product);
        mEventBus.post(new CartEvent.UpdateCart(products.size()));
    }
}
