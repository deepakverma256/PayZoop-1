package com.objectedge.restroappproto.event;

import com.objectedge.restroappproto.model.DishModel;

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
