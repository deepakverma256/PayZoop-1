package com.objectedge.restroappproto.model;


import com.google.gson.annotations.SerializedName;
import com.objectedge.restroappproto.model.ProductModel;

import java.util.List;


/**
 * Created by sachin.yadav on 04-08-2016.
 */
public class GetProductsListModel {

    @SerializedName("items")
    List<ProductModel> products;


    public List<ProductModel> getProducts() {
        return products;
    }


    public void setProducts(List<ProductModel> products) {
        this.products = products;
    }
}
