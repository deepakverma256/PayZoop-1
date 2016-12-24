package com.objectedge.payzoop.model;


import com.google.gson.annotations.SerializedName;


/**
 * Created by sachin.yadav on 02-08-2016.
 */
public class ProductModel {

    @SerializedName("name")
    String name;
    @SerializedName("listPrice")
    String listPrice;
    @SerializedName("primaryThumbImageURL")
    String primaryThumbImageURL;
    @SerializedName("id")
    String id;
    @SerializedName("imageUrl")
    String imageUrl;
    @SerializedName("description")
    String description;
    String productId;
    String barcodeId;
    String skuCode;
    String priceId;
    double salePrice;
    int quantity = 1;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public String getBarcodeId() {
        return barcodeId;
    }

    public void setBarcodeId(String barcodeId) {
        this.barcodeId = barcodeId;
    }






    public String getProductName() {
        return name;
    }


    public void setProductName(String name) {
        this.name = name;
    }


    public String getListPrice() {
        return listPrice;
    }


    public void setListPrice(String listPrice) {
        this.listPrice = listPrice;
    }


    public String getPrimaryThumbImageURL() {
        return primaryThumbImageURL;
    }


    public void setPrimaryThumbImageURL(String primaryThumbImageURL) {
        this.primaryThumbImageURL = primaryThumbImageURL;
    }


    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }


    public String getImageURL() {
        return imageUrl;
    }


    public void setImageURL(String imageUrl) {
        this.imageUrl = imageUrl;
    }


    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object object)
    {
        boolean matchFound = false;

        if (object != null && object instanceof ProductModel)
        {
            matchFound = this.id == ((ProductModel) object).id;
        }

        return matchFound;
    }
}