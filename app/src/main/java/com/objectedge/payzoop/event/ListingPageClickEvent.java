package com.objectedge.payzoop.event;

import com.objectedge.payzoop.model.DishModel;

/**
 * Created by deepak.verma on 10-08-2016.
 */
public class ListingPageClickEvent extends Event{

    public static class GoToPDPEvent extends ListingPageClickEvent{
        public DishModel product;

        public GoToPDPEvent(DishModel product){
            this.product = product;
        }
    }
}
