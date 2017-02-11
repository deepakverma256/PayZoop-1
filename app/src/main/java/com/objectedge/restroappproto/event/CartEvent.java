package com.objectedge.restroappproto.event;


import com.objectedge.restroappproto.event.RestEvent;
import com.objectedge.restroappproto.model.ProductModel;

/**
 * Created by deepak.verma on 26-08-2016.
 */
public class CartEvent extends Event {

    public static class AddToCart extends CartEvent{
        public ProductModel productAdded;

        public AddToCart(ProductModel productAdded){
            this.productAdded = productAdded;
        }
    }

    public static class UpdateCart extends CartEvent{
        public int cartItemCount;

        public UpdateCart(int cartItemCount){
            this.cartItemCount = cartItemCount;
        }
    }

    public static class GetProductForBarcodeEvent extends RestEvent {

        public String barcodeNumber;


        public GetProductForBarcodeEvent(String barcodeNumber) {
            this.barcodeNumber = barcodeNumber;
        }
    }
}
