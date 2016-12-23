package com.objectedge.payzoop.event;

import com.objectedge.payzoop.model.ProductModel;

/**
 * Created by deepak.verma on 10-08-2016.
 */
public class ListingPageClickEvent extends Event{

    public static class GoToPDPEvent extends ListingPageClickEvent{
        public ProductModel product;

        public GoToPDPEvent(ProductModel product){
            this.product = product;
        }
    }
}
